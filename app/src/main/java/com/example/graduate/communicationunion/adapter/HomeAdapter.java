package com.example.graduate.communicationunion.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.graduate.communicationunion.R;
import com.example.graduate.communicationunion.bean.Info;
import com.example.graduate.communicationunion.utils.GlideImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.Transformer;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


public class HomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final int BANNER_VIEW_TYPE = 0;//轮播图
    private final int CLASSIFY_VIEW_TYPE = 1;//分类
    private final int RECOMMEND_VIEW_TYPE = 2;//推荐

    private List<Info> recommendList;
    private Context context;
    private List<Integer> picList;
    private ClassifyClickListener classifyClickListener;

    private RecommendClickListener recommendClickListener;

    public HomeAdapter(List<Info> recommendList, Context context, List<Integer> picList, ClassifyClickListener classifyClickListener, RecommendClickListener recommendClickListener) {
        this.recommendList = recommendList;
        this.context = context;
        this.picList = picList;
        this.classifyClickListener = classifyClickListener;
        this.recommendClickListener = recommendClickListener;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return BANNER_VIEW_TYPE;
        } else if (position == 1) {
            return CLASSIFY_VIEW_TYPE;
        } else {
            return RECOMMEND_VIEW_TYPE;
        }
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        if (viewHolder instanceof BannerHolder) {
            BannerHolder bannerHolder = (BannerHolder) viewHolder;
            setBanner(bannerHolder);
        } else if (viewHolder instanceof ClassifyHolder) {
            ClassifyHolder classifyHolder = (ClassifyHolder) viewHolder;
            classifyHolder.classifyEvent();
        } else {
            RecommendHolder recommendHolder = (RecommendHolder) viewHolder;
            recommendHolder.update(recommendList.get(i - 2), i - 2);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if (viewType == BANNER_VIEW_TYPE) {
            view = LayoutInflater.from(context).inflate(R.layout.section_home_head_banner, parent, false);
            return new BannerHolder(view);
        } else if (viewType == CLASSIFY_VIEW_TYPE) {
            view = LayoutInflater.from(context).inflate(R.layout.section_home_info_classify, parent, false);
            return new ClassifyHolder(view);
        } else {
            view = LayoutInflater.from(context).inflate(R.layout.item_news, parent, false);
            return new RecommendHolder(view);
        }
    }


    @Override
    public int getItemCount() {
        return recommendList.size() + 2;
    }


    private void setBanner(BannerHolder bannerHolder) {
        bannerHolder.vp_banner.setImageLoader(new GlideImageLoader());
        bannerHolder.vp_banner.setImages(picList);
        bannerHolder.vp_banner.setBannerAnimation(Transformer.Default);
        bannerHolder.vp_banner.isAutoPlay(true);
        bannerHolder.vp_banner.setDelayTime(3500);
        bannerHolder.vp_banner.start();
    }

    public class RecommendHolder extends RecyclerView.ViewHolder {
        private TextView tv_news_title;
        private CircleImageView ci_news;
        private TextView tv_news_update;
        private int position;

        public RecommendHolder(@NonNull View itemView) {
            super(itemView);
            tv_news_title = itemView.findViewById(R.id.tv_news_title);
            tv_news_update = itemView.findViewById(R.id.tv_news_update);
            ci_news = itemView.findViewById(R.id.ci_news);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (recommendClickListener != null) {
                        recommendClickListener.RecommendListItemClick(recommendList.get(position));
                    }
                }
            });
        }

        public void update(Info item, int i) {
            position = i;
            RequestOptions options = new RequestOptions().placeholder(R.mipmap.ic_app)
                    .error(R.mipmap.ic_app);
            Glide.with(context).load(item.getAvatar()).apply(options).into(ci_news);
            tv_news_title.setText(item.getTitle());
            tv_news_update.setText("发布时间:"+item.getDate());
        }
    }


    public class BannerHolder extends RecyclerView.ViewHolder {
        private Banner vp_banner;

        public BannerHolder(View itemView) {
            super(itemView);
            vp_banner = itemView.findViewById(R.id.vp_banner);
        }
    }

    public class ClassifyHolder extends RecyclerView.ViewHolder {

        private LinearLayout ll_policy;
        private LinearLayout ll_news;
        private LinearLayout ll_active;
        private LinearLayout ll_famous;
        private LinearLayout ll_conclusion;
        private LinearLayout ll_item;


        public ClassifyHolder(@NonNull View itemView) {
            super(itemView);
            ll_policy = itemView.findViewById(R.id.ll_policy);
            ll_news = itemView.findViewById(R.id.ll_news);
            ll_active = itemView.findViewById(R.id.ll_active);
            ll_famous = itemView.findViewById(R.id.famous);
            ll_conclusion = itemView.findViewById(R.id.conclusion);
            ll_item = itemView.findViewById(R.id.ll_item);
        }

        public void classifyEvent() {
            ll_policy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (classifyClickListener != null) {
                        classifyClickListener.onPolicyClick();
                    }
                }
            });
            ll_news.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (classifyClickListener != null) {
                        classifyClickListener.onNewsClick();
                    }
                }
            });
            ll_active.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (classifyClickListener != null) {
                        classifyClickListener.onActiveClick();
                    }
                }
            });
            ll_famous.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (classifyClickListener != null) {
                        classifyClickListener.onFamousClick();
                    }
                }
            });
            ll_conclusion.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (classifyClickListener != null) {
                        classifyClickListener.onConclusionClick();
                    }
                }
            });
            ll_item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (classifyClickListener != null) {
                        classifyClickListener.onItemClick();
                    }
                }
            });

        }
    }

    public interface ClassifyClickListener {
        void onPolicyClick();

        void onNewsClick();

        void onActiveClick();

        void onFamousClick();

        void onConclusionClick();

        void onItemClick();
    }

    public interface RecommendClickListener {
        void RecommendListItemClick(Info info);
    }

}

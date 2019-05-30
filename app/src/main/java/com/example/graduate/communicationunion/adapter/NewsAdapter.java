package com.example.graduate.communicationunion.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.graduate.communicationunion.R;
import com.example.graduate.communicationunion.bean.Info;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsHolder> {
    private List<Info> infoList;
    private Context context;
    private NewListHandler newListHandler;

    public NewsAdapter(List<Info> infoList, Context context, NewListHandler newListHandler) {
        this.infoList = infoList;
        this.context = context;
        this.newListHandler=newListHandler;
    }

    @NonNull
    @Override
    public NewsHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
       View  view = LayoutInflater.from(context).inflate(R.layout.item_news, parent, false);
        return new NewsHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsHolder newsHolder, int i) {

        newsHolder.update(infoList.get(i), i );
    }

    @Override
    public int getItemCount() {
        return infoList.size();
    }

    public class NewsHolder extends RecyclerView.ViewHolder{
        private TextView tv_news_title;
        private CircleImageView ci_news;
        private TextView tv_news_update;
        private int position;
        public NewsHolder(@NonNull View itemView) {
            super(itemView);
            tv_news_title = itemView.findViewById(R.id.tv_news_title);
            tv_news_update = itemView.findViewById(R.id.tv_news_update);
            ci_news = itemView.findViewById(R.id.ci_news);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (newListHandler != null) {
                        newListHandler.HandlerInfoClick(infoList.get(position));
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

    public interface NewListHandler{
        void HandlerInfoClick(Info info);
    }
}

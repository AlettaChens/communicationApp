package com.example.graduate.communicationunion.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.graduate.communicationunion.R;
import com.example.graduate.communicationunion.bean.Book;
import com.example.graduate.communicationunion.bean.Formu;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class TalkContentAdapter extends RecyclerView.Adapter<TalkContentAdapter.TalkListHolder> {
    private List<Formu> bookList;
    private Context context;
    private TalkClickHandle bookClickHandle;
    private String updateType;

    public TalkContentAdapter(List<Formu> bookList, Context context, TalkClickHandle bookClickHandle) {
        this.bookList = bookList;
        this.context = context;
        this.bookClickHandle = bookClickHandle;
    }

    @NonNull
    @Override
    public TalkListHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new TalkListHolder(LayoutInflater.from(context).inflate(R.layout.item_talk, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull TalkListHolder bookListHolder, int i) {
        bookListHolder.update(bookList.get(i), i);
    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }

    public class TalkListHolder extends RecyclerView.ViewHolder {
        private int position;
        private TextView tv_talk_title;
        private CircleImageView ci_talk;
        private TextView tv_talk_update;
        private CheckBox rb_star;

        public TalkListHolder(@NonNull View itemView) {
            super(itemView);
            tv_talk_title = itemView.findViewById(R.id.tv_talk_title);
            tv_talk_update = itemView.findViewById(R.id.tv_talk_update);
            ci_talk = itemView.findViewById(R.id.ci_talk);
            rb_star = itemView.findViewById(R.id.rb_star);

            rb_star.setOnClickListener(new View.OnClickListener() {//防止checkBox事件冲突
                @Override
                public void onClick(View v) {
                    int starCurrent = Integer.parseInt(rb_star.getTag().toString());
                    if (rb_star.isChecked()) {
                        updateType = "增加";
                        starCurrent++;
                        rb_star.setChecked(true);
                    } else {
                        updateType = "减少";
                        starCurrent--;
                        rb_star.setChecked(false);
                    }
                    rb_star.setTag(String.valueOf(starCurrent));
                    rb_star.setText(String.valueOf(starCurrent));
                    bookClickHandle.onStarChange(bookList.get(position).getFormuId(), updateType);
                }
            });
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (bookClickHandle != null) {
                        bookClickHandle.bookClick(bookList.get(position));
                    }
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    bookClickHandle.bookLongClick(bookList.get(position));
                   return true;
                }
            });
        }

        public void update(Formu book, int position) {
            this.position = position;
            RequestOptions options = new RequestOptions().placeholder(R.mipmap.ic_app)
                    .error(R.mipmap.ic_app);
            Glide.with(context).load(book.getAnthorAvatar()).apply(options).into(ci_talk);
            tv_talk_title.setText(book.getTitle());
            tv_talk_update.setText("发布时间:" + book.getPublishDate());
            rb_star.setText(String.valueOf(book.getStar()));
            rb_star.setTag(String.valueOf(book.getStar()));
            if (book.getIsCheck() == 1) {
                rb_star.setChecked(true);
            } else {
                rb_star.setChecked(false);
            }
        }
    }

    public interface TalkClickHandle {
        void bookClick(Formu formu);

        void onStarChange(int forumId, String changeType);

        void bookLongClick(Formu book);
    }


}

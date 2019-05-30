package com.example.graduate.communicationunion.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.graduate.communicationunion.R;
import com.example.graduate.communicationunion.bean.Comment;
import java.util.List;


public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.CommentHolder> {
    private List<Comment> commentList;
    private Context context;

    public CommentAdapter(List<Comment> commentList, Context context) {
        this.commentList = commentList;
        this.context = context;
    }

    @NonNull
    @Override
    public CommentHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new CommentHolder(LayoutInflater.from(context).inflate(R.layout.item_comment,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull CommentHolder commentHolder, int i) {
        commentHolder.update(commentList.get(i),i);
    }

    @Override
    public int getItemCount() {
        return commentList.size();
    }

    public class CommentHolder extends RecyclerView.ViewHolder {
        private int position;
        private TextView tv_comment_username;
        private TextView tv_content;
        private TextView tv_comment_time;

        public CommentHolder(@NonNull View itemView) {
            super(itemView);
            tv_comment_username = itemView.findViewById(R.id.tv_comment_username);
            tv_content = itemView.findViewById(R.id.tv_content);
            tv_comment_time = itemView.findViewById(R.id.tv_comment_time);
        }
        public void update(Comment comment, int position) {
            this.position = position;
            tv_comment_username.setText(comment.getComment_user());
            tv_comment_time.setText("发布时间:" + comment.getDate());
            tv_content.setText(comment.getContent());
        }
    }
}

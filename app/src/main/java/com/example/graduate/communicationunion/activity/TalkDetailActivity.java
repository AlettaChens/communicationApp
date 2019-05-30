package com.example.graduate.communicationunion.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.graduate.communicationunion.R;
import com.example.graduate.communicationunion.adapter.CommentAdapter;
import com.example.graduate.communicationunion.bean.Comment;
import com.example.graduate.communicationunion.bean.Formu;
import com.example.graduate.communicationunion.presenter.CommentPresenter;
import com.example.graduate.communicationunion.presenter.callback.CommonCallback;
import com.example.graduate.communicationunion.utils.CommunicateSP;
import com.example.graduate.communicationunion.utils.MessageUtils;
import com.gizwits.energy.android.lib.base.BaseActivity;
import com.gizwits.energy.android.lib.presenter.IDefaultListLoader;


import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

@ContentView(R.layout.activty_talk_detail)
public class TalkDetailActivity extends BaseActivity {
    @ViewInject(R.id.iv_back)
    private ImageView iv_back;
    @ViewInject(R.id.tv_title)
    private TextView tv_title;
    @ViewInject(R.id.iv_user)
    private CircleImageView iv_user;
    @ViewInject(R.id.tv_talk_username)
    private TextView tv_talk_username;
    @ViewInject(R.id.tv_talk_time)
    private TextView tv_talk_time;
    @ViewInject(R.id.tv_content)
    private TextView tv_content;
    @ViewInject(R.id.rv_comment)
    private RecyclerView rv_comment;
    @ViewInject(R.id.et_comment)
    private EditText et_comment;
    @ViewInject(R.id.btn_comment)
    private Button btn_comment;
    private CommentPresenter commentPresenter;
    private CommunicateSP communicateSP;
    private CommentAdapter commentAdapter;
    Formu formu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent getIntent = getIntent();
        formu = (Formu) getIntent.getSerializableExtra("formu");
        tv_title.setText(formu.getTitle());
        RequestOptions options = new RequestOptions().placeholder(R.mipmap.ic_app)
                .error(R.mipmap.ic_app);
        Glide.with(TalkDetailActivity.this).load(formu.getAnthorAvatar()).apply(options).into(iv_user);
        tv_talk_username.setText(formu.getAnthorName());
        tv_talk_time.setText(formu.getPublishDate());
        tv_content.setText(formu.getContent());
        commentPresenter = new CommentPresenter(TalkDetailActivity.this);
        communicateSP = new CommunicateSP(TalkDetailActivity.this);
        commentPresenter.getAllCommentByUserId(formu.getFormuId(), new CommentHandle());

    }

    public class CommentHandle implements IDefaultListLoader<Comment> {
        @Override
        public void onLoadFail(String msg) {
            MessageUtils.showShortToast(TalkDetailActivity.this, msg);
        }

        @Override
        public void onLoadSuccess(@NonNull List<Comment> list) {
            commentAdapter = new CommentAdapter(list, TalkDetailActivity.this);
            rv_comment.setLayoutManager(new LinearLayoutManager(TalkDetailActivity.this));
            rv_comment.addItemDecoration(new DividerItemDecoration(TalkDetailActivity.this, DividerItemDecoration.VERTICAL));
            rv_comment.setAdapter(commentAdapter);
        }

        @Override
        public void onListEnd() {

        }

        @Override
        public void onRequestStarted() {
            showLoadingDialog("加载中...");
        }

        @Override
        public void onRequestCanceled() {

        }

        @Override
        public void onRequestFinished() {
            dismissLoadingDialog();
        }
    }

    @Event({R.id.btn_comment, R.id.iv_back})
    private void backClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                onBackPressed();
                break;
            case R.id.btn_comment:
                commentPresenter.publishComment(communicateSP.getNickName(), et_comment.getText().toString(), formu.getFormuId(), new CommonCallback() {
                    @Override
                    public void onSuccess(String message) {
                        MessageUtils.showShortToast(TalkDetailActivity.this, message);
                        commentPresenter.getAllCommentByUserId(formu.getFormuId(), new CommentHandle());
                    }

                    @Override
                    public void onFail(String message) {
                        MessageUtils.showShortToast(TalkDetailActivity.this, message);
                    }

                    @Override
                    public void onError(Throwable e) {
                        MessageUtils.showShortToast(TalkDetailActivity.this, e.toString());
                    }

                    @Override
                    public void onRequestStarted() {

                    }

                    @Override
                    public void onRequestCanceled() {

                    }

                    @Override
                    public void onRequestFinished() {

                    }
                });
                break;
        }
    }
}

package com.example.graduate.communicationunion.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.graduate.communicationunion.R;
import com.example.graduate.communicationunion.bean.Info;
import com.example.graduate.communicationunion.presenter.HistoryPresenter;
import com.example.graduate.communicationunion.presenter.TalkPresenter;
import com.example.graduate.communicationunion.utils.CommunicateSP;
import com.example.graduate.communicationunion.utils.MessageUtils;
import com.gizwits.energy.android.lib.base.BaseActivity;


import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

@ContentView(R.layout.activity_news_detail)
public class NewsDetail extends BaseActivity {
    @ViewInject(R.id.iv_back)
    private ImageView iv_back;
    @ViewInject(R.id.iv_news)
    private ImageView iv_news;
    @ViewInject(R.id.tv_news_title)
    private TextView tv_news_title;
    @ViewInject(R.id.tv_news_content)
    private TextView tv_news_content;
    @ViewInject(R.id.tv_news_time)
    private TextView tv_news_time;
    private HistoryPresenter historyPresenter;
    private CommunicateSP communicateSP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Info info = (Info) getIntent().getSerializableExtra("info");
        RequestOptions requestOptions = new RequestOptions().placeholder(R.mipmap.ic_app).error(R.mipmap.ic_app);
        Glide.with(NewsDetail.this).load(info.getAvatar()).apply(requestOptions).into(iv_news);
        tv_news_title.setText(info.getTitle());
        tv_news_time.setText(info.getDate());
        tv_news_content.setText(info.getContent());
        communicateSP = new CommunicateSP(NewsDetail.this);
        historyPresenter = new HistoryPresenter(NewsDetail.this);
        historyPresenter.lookHistory(info.getNewId(), communicateSP.getUserId(), new UpdateHandle());

    }

    @Event(R.id.iv_back)
    private void OnNewsDetailClick(View view) {
        onBackPressed();
    }

    public class UpdateHandle implements HistoryPresenter.IPublishHandler {
        @Override
        public void onUpdateSuccess(String message) {

        }

        @Override
        public void onUpdateFail(String message) {
            MessageUtils.showShortToast(NewsDetail.this, message);
        }

        @Override
        public void onUpdateError(Throwable e) {
            MessageUtils.showShortToast(NewsDetail.this, e.toString());
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
    }
}

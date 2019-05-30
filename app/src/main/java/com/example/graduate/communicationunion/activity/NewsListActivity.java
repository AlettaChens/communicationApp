package com.example.graduate.communicationunion.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.example.graduate.communicationunion.R;
import com.example.graduate.communicationunion.adapter.NewsAdapter;
import com.example.graduate.communicationunion.bean.Info;
import com.example.graduate.communicationunion.presenter.InfoPreseneter;
import com.example.graduate.communicationunion.utils.MessageUtils;
import com.gizwits.energy.android.lib.base.BaseActivity;
import com.gizwits.energy.android.lib.presenter.IDefaultListLoader;


import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.List;

@ContentView(R.layout.activity_news)
public class NewsListActivity extends BaseActivity {
    @ViewInject(R.id.rv_news_list)
    private RecyclerView rv_news_list;
    @ViewInject(R.id.iv_back)
    private ImageView iv_back;
    private InfoPreseneter infoPreseneter;
    private String type;
    private NewsAdapter newsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent getIntent = getIntent();
        type = getIntent.getStringExtra("type");
        infoPreseneter = new InfoPreseneter(NewsListActivity.this);
        infoPreseneter.showTypeList(type, new IDefaultListLoader<Info>() {
            @Override
            public void onLoadFail(String msg) {
                MessageUtils.showShortToast(NewsListActivity.this,"加载失败");
            }

            @Override
            public void onLoadSuccess(@NonNull List<Info> list) {
                newsAdapter = new NewsAdapter(list, NewsListActivity.this, new NewsAdapter.NewListHandler() {
                    @Override
                    public void HandlerInfoClick(Info info) {
                        Intent intent= new Intent(NewsListActivity.this, NewsDetail.class);
                        intent.putExtra("info",info);
                        startActivity(intent);
                    }
                });
                rv_news_list.setLayoutManager(new LinearLayoutManager(NewsListActivity.this));
                rv_news_list.addItemDecoration(new DividerItemDecoration(NewsListActivity.this, DividerItemDecoration.VERTICAL));
                rv_news_list.setAdapter(newsAdapter);
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
        });
    }

    @Event(R.id.iv_back)
    private void OnNewsListClick(View view) {
        onBackPressed();
    }

}

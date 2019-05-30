package com.example.graduate.communicationunion.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.graduate.communicationunion.R;
import com.example.graduate.communicationunion.activity.BookDetail;
import com.example.graduate.communicationunion.activity.TalkDetailActivity;
import com.example.graduate.communicationunion.adapter.BookAdapter;
import com.example.graduate.communicationunion.adapter.TalkContentAdapter;
import com.example.graduate.communicationunion.bean.Book;
import com.example.graduate.communicationunion.bean.Formu;
import com.example.graduate.communicationunion.presenter.BookPresenter;
import com.example.graduate.communicationunion.presenter.TalkPresenter;
import com.example.graduate.communicationunion.presenter.callback.CommonCallback;
import com.example.graduate.communicationunion.utils.CommunicateSP;
import com.example.graduate.communicationunion.utils.MessageUtils;
import com.gizwits.energy.android.lib.base.BaseFragment;
import com.gizwits.energy.android.lib.presenter.IDefaultListLoader;


import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.List;

@ContentView(R.layout.fragment_talk_content)
public class TalkContentFragment extends BaseFragment {
    @ViewInject(R.id.rv_talk)
    private RecyclerView rv_talk;
    private TalkPresenter talkPresenter;
    private TalkContentAdapter talkContentAdapter;
    private CommunicateSP communicateSP;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        communicateSP = new CommunicateSP(getActivity());
    }

    @Override
    public void onResume() {
        super.onResume();
        talkPresenter.getAllTalk(communicateSP.getUserId(), new IDefaultListLoader<Formu>() {
            @Override
            public void onLoadFail(String msg) {

            }

            @Override
            public void onLoadSuccess(@NonNull List<Formu> list) {
                talkContentAdapter = new TalkContentAdapter(list, getActivity(), new TalkContentAdapter.TalkClickHandle() {
                    @Override
                    public void bookClick(Formu formu) {
                        Intent intent = new Intent(getActivity(), TalkDetailActivity.class);
                        intent.putExtra("formu", formu);
                        startActivity(intent);
                    }

                    @Override
                    public void bookLongClick(Formu book) {

                    }

                    @Override
                    public void onStarChange(int forumId, String changeType) {
                        talkPresenter.updateStar(forumId, communicateSP.getUserId(), changeType, new updateStarHandle());
                    }
                });
                rv_talk.setLayoutManager(new LinearLayoutManager(getActivity()));
                rv_talk.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
                rv_talk.setAdapter(talkContentAdapter);
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

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        talkPresenter = new TalkPresenter(getActivity());

    }


    public class updateStarHandle implements CommonCallback {
        @Override
        public void onSuccess(String message) {
            MessageUtils.showShortToast(getActivity(), message);
        }

        @Override
        public void onFail(String message) {
            MessageUtils.showShortToast(getActivity(), message);
        }

        @Override
        public void onError(Throwable e) {
            MessageUtils.showShortToast(getActivity(), e.toString());
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

package com.example.graduate.communicationunion.activity;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.example.graduate.communicationunion.R;
import com.example.graduate.communicationunion.adapter.TalkContentAdapter;
import com.example.graduate.communicationunion.bean.Formu;
import com.example.graduate.communicationunion.presenter.TalkPresenter;
import com.example.graduate.communicationunion.presenter.callback.CommonCallback;
import com.example.graduate.communicationunion.utils.CommunicateSP;
import com.example.graduate.communicationunion.utils.MessageUtils;
import com.gizwits.energy.android.lib.base.BaseActivity;
import com.gizwits.energy.android.lib.presenter.IDefaultListLoader;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.List;

@ContentView(R.layout.activity_lib_talk)
public class PersonTalkActivity extends BaseActivity {
    @ViewInject(R.id.rv_person_talk)
    private RecyclerView rv_person_talk;
    @ViewInject(R.id.iv_back)
    private ImageView iv_back;
    private TalkPresenter talkPresenter;
    private TalkContentAdapter talkContentAdapter;
    private CommunicateSP communicateSP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        communicateSP = new CommunicateSP(PersonTalkActivity.this);
        talkPresenter = new TalkPresenter(PersonTalkActivity.this);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        getList();
    }

    private void getList() {
        talkPresenter.getAllTalkByUserName(communicateSP.getNickName(), new IDefaultListLoader<Formu>() {
            @Override
            public void onLoadFail(String msg) {

            }

            @Override
            public void onLoadSuccess(@NonNull List<Formu> list) {
                talkContentAdapter = new TalkContentAdapter(list, PersonTalkActivity.this, new TalkContentAdapter.TalkClickHandle() {
                    @Override
                    public void bookClick(Formu formu) {
                        Intent intent = new Intent(PersonTalkActivity.this, TalkDetailActivity.class);
                        intent.putExtra("formu", formu);
                        startActivity(intent);
                    }

                    @Override
                    public void bookLongClick(final Formu book) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(PersonTalkActivity.this);
                        builder.setMessage("是否删除？")
                                .setCancelable(false)
                                .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        talkPresenter.deleteTalk(book.getFormuId(), new CommonCallback() {
                                            @Override
                                            public void onSuccess(String message) {
                                                getList();
                                                MessageUtils.showShortToast(PersonTalkActivity.this, message);
                                            }

                                            @Override
                                            public void onFail(String message) {
                                                MessageUtils.showShortToast(PersonTalkActivity.this, message);
                                            }

                                            @Override
                                            public void onError(Throwable e) {
                                                MessageUtils.showShortToast(PersonTalkActivity.this, e.toString());
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
                                    }
                                })
                                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                }).create().show();
                    }

                    @Override
                    public void onStarChange(int forumId, String changeType) {
                        talkPresenter.updateStar(forumId, communicateSP.getUserId(), changeType, new updateStarHandle());
                    }
                });
                rv_person_talk.setLayoutManager(new LinearLayoutManager(PersonTalkActivity.this));
                rv_person_talk.addItemDecoration(new DividerItemDecoration(PersonTalkActivity.this, DividerItemDecoration.VERTICAL));
                rv_person_talk.setAdapter(talkContentAdapter);
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

    public class updateStarHandle implements CommonCallback {
        @Override
        public void onSuccess(String message) {
            MessageUtils.showShortToast(PersonTalkActivity.this, message);
        }

        @Override
        public void onFail(String message) {
            MessageUtils.showShortToast(PersonTalkActivity.this, message);
        }

        @Override
        public void onError(Throwable e) {
            MessageUtils.showShortToast(PersonTalkActivity.this, e.toString());
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



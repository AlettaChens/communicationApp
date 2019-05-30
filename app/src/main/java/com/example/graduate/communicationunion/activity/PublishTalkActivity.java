package com.example.graduate.communicationunion.activity;


import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.graduate.communicationunion.R;
import com.example.graduate.communicationunion.presenter.BookPresenter;
import com.example.graduate.communicationunion.presenter.TalkPresenter;
import com.example.graduate.communicationunion.utils.CommunicateSP;
import com.example.graduate.communicationunion.utils.MessageUtils;
import com.gizwits.energy.android.lib.base.BaseActivity;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

@ContentView(R.layout.activity_publish_talk)
public class PublishTalkActivity extends BaseActivity {
    @ViewInject(R.id.iv_back)
    private ImageView iv_back;
    @ViewInject(R.id.tv_public_talk)
    private TextView tv_public_talk;
    @ViewInject(R.id.et_talk_title)
    private EditText et_talk_title;
    @ViewInject(R.id.et_talk_content)
    private EditText et_talk_content;
    private TalkPresenter talkPresenter;
    private CommunicateSP communicateSP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        talkPresenter = new TalkPresenter(PublishTalkActivity.this);
        communicateSP = new CommunicateSP(PublishTalkActivity.this);
    }

    @Event({R.id.iv_back, R.id.tv_public_talk})
    private void onTalkPubCilck(View view) {
        switch (view.getId()) {
            case R.id.iv_back: {
                onBackPressed();
                break;
            }
            case R.id.tv_public_talk: {
                talkPresenter.publishTalk(et_talk_title.getText().toString(), et_talk_content.getText().toString(), communicateSP.getAvatar(), communicateSP.getNickName(), new UpdateHandle());
                break;
            }
        }

    }

    public class UpdateHandle implements TalkPresenter.IPublishHandler {
        @Override
        public void onUpdateSuccess(String message) {
            MessageUtils.showShortToast(PublishTalkActivity.this, message);
            finish();
        }

        @Override
        public void onUpdateFail(String message) {
            MessageUtils.showShortToast(PublishTalkActivity.this, message);
        }

        @Override
        public void onUpdateError(Throwable e) {
            MessageUtils.showShortToast(PublishTalkActivity.this, e.toString());
        }

        @Override
        public void onRequestStarted() {
            showLoadingDialog("更新数据中...");
        }

        @Override
        public void onRequestCanceled() {

        }

        @Override
        public void onRequestFinished() {
            dismissLoadingDialog();
        }
    }
}

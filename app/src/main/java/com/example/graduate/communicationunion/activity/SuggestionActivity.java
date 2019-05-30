package com.example.graduate.communicationunion.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.graduate.communicationunion.R;
import com.example.graduate.communicationunion.presenter.SuggestionPresenter;
import com.example.graduate.communicationunion.utils.CommunicateSP;
import com.example.graduate.communicationunion.utils.MessageUtils;
import com.gizwits.energy.android.lib.base.BaseActivity;


import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

@ContentView(R.layout.activity_suggstion)
public class SuggestionActivity extends BaseActivity {
    @ViewInject(R.id.et_feedback)
    private EditText et_feedback;
    @ViewInject(R.id.btn_feedback)
    private Button btn_feedback;
    @ViewInject(R.id.iv_back)
    private ImageView iv_back;
    private SuggestionPresenter suggestionPresenter;
    private CommunicateSP communicateSP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        suggestionPresenter = new SuggestionPresenter(SuggestionActivity.this);
        communicateSP = new CommunicateSP(SuggestionActivity.this);
    }

    @Event({R.id.iv_back,R.id.btn_feedback})
    private void OnSuggestionClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back: {
                onBackPressed();
                break;
            }
            case R.id.btn_feedback: {
                suggestionPresenter.suggestion(communicateSP.getAvatar(), communicateSP.getNickName(), et_feedback.getText().toString(), new SuggestionPresenter.ISuggestionHandler() {
                    @Override
                    public void onSuggestionSuccess(String message) {
                        MessageUtils.showShortToast(SuggestionActivity.this, message);
                    }

                    @Override
                    public void onSuggestionFail(String message) {
                        MessageUtils.showShortToast(SuggestionActivity.this, message);
                    }

                    @Override
                    public void onSuggestionError(Throwable e) {
                        MessageUtils.showShortToast(SuggestionActivity.this, e.toString());
                    }

                    @Override
                    public void onRequestStarted() {
                        showLoadingDialog("发布中...");
                    }

                    @Override
                    public void onRequestCanceled() {

                    }

                    @Override
                    public void onRequestFinished() {
                        dismissLoadingDialog();
                    }
                });
                break;
            }
        }
    }
}

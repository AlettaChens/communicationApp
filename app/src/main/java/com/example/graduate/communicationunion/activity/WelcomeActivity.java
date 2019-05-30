package com.example.graduate.communicationunion.activity;

import android.content.Intent;
import android.os.Bundle;

import com.example.graduate.communicationunion.R;
import com.example.graduate.communicationunion.utils.CommunicateSP;
import com.gizwits.energy.android.lib.base.BaseActivity;


import org.xutils.view.annotation.ContentView;
import org.xutils.x;

@ContentView(R.layout.activity_welcome)
public class WelcomeActivity extends BaseActivity {
    private CommunicateSP communicateSP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        communicateSP = new CommunicateSP(WelcomeActivity.this);
        x.task().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (communicateSP.getisLogin()) {
                    startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
                } else {
                    startActivity(new Intent(WelcomeActivity.this, LoginActivity.class));
                }
                finish();
            }
        }, 2000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}

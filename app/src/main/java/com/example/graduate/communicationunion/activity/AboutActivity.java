package com.example.graduate.communicationunion.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.graduate.communicationunion.R;
import com.gizwits.energy.android.lib.base.BaseActivity;


import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

@ContentView(R.layout.activity_about)
public class AboutActivity extends BaseActivity {
    @ViewInject(R.id.iv_back)
    private ImageView iv_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Event(R.id.iv_back)
    private void OnAboutClick(View view) {
        onBackPressed();
    }

}

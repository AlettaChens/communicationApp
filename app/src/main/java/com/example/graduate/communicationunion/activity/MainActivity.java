package com.example.graduate.communicationunion.activity;


import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.graduate.communicationunion.R;
import com.example.graduate.communicationunion.fragment.HomeFragment;
import com.example.graduate.communicationunion.fragment.PersonFragment;
import com.example.graduate.communicationunion.fragment.TalkFragment;
import com.gizwits.energy.android.lib.base.BaseFragmentActivity;


import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

@ContentView(R.layout.activity_main)
public class MainActivity extends BaseFragmentActivity {
    @ViewInject(R.id.rb_home)
    private RadioButton rb_home;
    @ViewInject(R.id.rb_talk)
    private RadioButton rb_talk;
    @ViewInject(R.id.rb_person)
    private RadioButton rb_person;
    private long mExitTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showFragment(R.id.fl_exhibition_view, new HomeFragment());
    }

    @Event(value = R.id.rg_tab, type = RadioGroup.OnCheckedChangeListener.class)
    private void OnMianClick(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rb_home: {
                showFragment(R.id.fl_exhibition_view, new HomeFragment());
                break;
            }
            case R.id.rb_talk: {
                showFragment(R.id.fl_exhibition_view, new TalkFragment());
                break;
            }
            case R.id.rb_person: {
                showFragment(R.id.fl_exhibition_view, new PersonFragment());
                break;
            }
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if ((System.currentTimeMillis() - mExitTime) > 2000) {
                Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                mExitTime = System.currentTimeMillis();
            } else {
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


}

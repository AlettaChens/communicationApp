package com.example.graduate.communicationunion.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.example.graduate.communicationunion.R;
import com.example.graduate.communicationunion.activity.PersonBookActivity;
import com.example.graduate.communicationunion.activity.PersonTalkActivity;
import com.example.graduate.communicationunion.activity.PublishTalkActivity;
import com.example.graduate.communicationunion.activity.PublishBookActivity;
import com.example.graduate.communicationunion.adapter.TalkAdapter;

import com.example.graduate.communicationunion.dialog.MenuFuncationDialog;
import com.gizwits.energy.android.lib.base.BaseFragment;


import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

@ContentView(R.layout.fragment_talk)
public class TalkFragment extends BaseFragment {
    @ViewInject(R.id.tl_talk)
    private TabLayout tl_talk;
    @ViewInject(R.id.vp_talk)
    private ViewPager vp_talk;
    private List<Fragment> fragments;
    private List<String> titles;
    private TalkAdapter talkAdapter;
    private MenuFuncationDialog menuFuncationDialog;
    private MenuFuncListener menuFuncListener;



    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (fragments == null) {
            fragments = new ArrayList<>();
        }
        if (titles == null) {
            titles = new ArrayList<>();
        }
        menuFuncListener = new MenuFuncListener();
        menuFuncationDialog = new MenuFuncationDialog(getActivity(), menuFuncListener);
        titles.add("图书大厅");
        titles.add("论坛大厅");
        fragments.add(new BookFragment());
        fragments.add(new TalkContentFragment());
        talkAdapter = new TalkAdapter(getChildFragmentManager(), fragments, titles);
        vp_talk.setAdapter(talkAdapter);
        tl_talk.setupWithViewPager(vp_talk);
    }

    @Event({R.id.iv_menu})
    private void OnTalkClick(View view) {
        switch (view.getId()){
            case R.id.iv_menu:{
                menuFuncationDialog.show();
                break;
            }
        }

    }


    public class MenuFuncListener implements MenuFuncationDialog.DialogItemListener {
        @Override
        public void ItemClick(String type) {
            menuFuncationDialog.dismiss();
            switch (type) {
                case "发布图书信息": {
                    startActivity(new Intent(getActivity(), PublishBookActivity.class));
                    break;
                }
                case "发布论坛帖子": {
                    startActivity(new Intent(getActivity(), PublishTalkActivity.class));
                    break;
                }
                case "个人图书信息": {
                    startActivity(new Intent(getActivity(), PersonBookActivity.class));
                    break;
                }
                default: {
                    startActivity(new Intent(getActivity(), PersonTalkActivity.class));
                    break;
                }
            }
        }
    }


}

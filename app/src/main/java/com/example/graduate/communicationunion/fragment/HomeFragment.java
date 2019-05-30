package com.example.graduate.communicationunion.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;


import com.example.graduate.communicationunion.R;
import com.example.graduate.communicationunion.activity.NewsDetail;
import com.example.graduate.communicationunion.activity.NewsListActivity;
import com.example.graduate.communicationunion.adapter.HomeAdapter;
import com.example.graduate.communicationunion.bean.Info;
import com.example.graduate.communicationunion.presenter.InfoPreseneter;
import com.example.graduate.communicationunion.utils.CommunicateSP;
import com.example.graduate.communicationunion.utils.MessageUtils;
import com.gizwits.energy.android.lib.base.BaseFragment;
import com.gizwits.energy.android.lib.presenter.IDefaultListLoader;


import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

@ContentView(R.layout.fragment_home)
public class HomeFragment extends BaseFragment {
    @ViewInject(R.id.rv_home)
    private RecyclerView rv_home;
    private List<Integer> bannerResouce;
    private HomeAdapter homeAdapter;
    private ClassifyMoudle classifyMoudle;
    private RecommendModule recommendModule;
    private InfoPreseneter infoPreseneter;
    private CommunicateSP communicateSP;


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initBanner();
        classifyMoudle = new ClassifyMoudle();
        recommendModule = new RecommendModule();
        infoPreseneter = new InfoPreseneter(getActivity());
        communicateSP = new CommunicateSP(getActivity());
        if (communicateSP != null) {
            infoPreseneter.recommend(communicateSP.getUserId(), new IDefaultListLoader<Info>() {
                @Override
                public void onLoadFail(String msg) {

                }

                @Override
                public void onLoadSuccess(@NonNull List<Info> list) {
                    homeAdapter = new HomeAdapter(list, getActivity(), bannerResouce, classifyMoudle, recommendModule);
                    rv_home.setLayoutManager(new LinearLayoutManager(getActivity()));
                    rv_home.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));
                    rv_home.setAdapter(homeAdapter);
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

    }

    private void initBanner() {
        if (bannerResouce == null) {
            bannerResouce = new ArrayList<>();
        }
        bannerResouce.add(R.mipmap.first_banner);
        bannerResouce.add(R.mipmap.second_banner);
        bannerResouce.add(R.mipmap.third_banner);
    }

    public class ClassifyMoudle implements HomeAdapter.ClassifyClickListener {
        @Override
        public void onPolicyClick() {
          Intent intent1= new Intent(getActivity(), NewsListActivity.class);
          intent1.putExtra("type","最新政策");
            startActivity(intent1);
        }

        @Override
        public void onNewsClick() {
            Intent intent1= new Intent(getActivity(), NewsListActivity.class);
            intent1.putExtra("type","考研资讯");
            startActivity(intent1);
        }

        @Override
        public void onActiveClick() {
            Intent intent1= new Intent(getActivity(), NewsListActivity.class);
            intent1.putExtra("type","考研动态");
            startActivity(intent1);
        }

        @Override
        public void onFamousClick() {
            Intent intent1= new Intent(getActivity(), NewsListActivity.class);
            intent1.putExtra("type","考研人物");
            startActivity(intent1);
        }

        @Override
        public void onConclusionClick() {
            Intent intent1= new Intent(getActivity(), NewsListActivity.class);
            intent1.putExtra("type","考研心得");
            startActivity(intent1);
        }

        @Override
        public void onItemClick() {
            Intent intent1= new Intent(getActivity(), NewsListActivity.class);
            intent1.putExtra("type","考研科目");
            startActivity(intent1);
        }
    }

    public class RecommendModule implements HomeAdapter.RecommendClickListener {
        @Override
        public void RecommendListItemClick(Info info) {
            Intent intent= new Intent(getActivity(), NewsDetail.class);
            intent.putExtra("info",info);
            startActivity(intent);
        }
    }

}

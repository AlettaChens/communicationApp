package com.example.graduate.communicationunion.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.graduate.communicationunion.R;
import com.example.graduate.communicationunion.activity.AboutActivity;
import com.example.graduate.communicationunion.activity.SuggestionActivity;
import com.example.graduate.communicationunion.activity.UpdateUserInfoActivity;
import com.example.graduate.communicationunion.dialog.PhotoSelectDialog;
import com.example.graduate.communicationunion.presenter.UserPresenter;
import com.example.graduate.communicationunion.utils.CommunicateSP;
import com.example.graduate.communicationunion.utils.MessageUtils;
import com.gizwits.energy.android.lib.base.BaseFragment;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import java.io.File;
import java.util.List;
import de.hdodenhof.circleimageview.CircleImageView;
import static android.app.Activity.RESULT_OK;

@ContentView(R.layout.fragment_person)
public class PersonFragment extends BaseFragment {
    @ViewInject(R.id.user_image)
    private CircleImageView user_image;
    @ViewInject(R.id.tv_username)
    private TextView tv_username;
    @ViewInject(R.id.rl_update_info)
    private RelativeLayout rl_update_info;
    @ViewInject(R.id.rl_suggestion)
    private RelativeLayout rl_suggestion;
    @ViewInject(R.id.rl_about)
    private RelativeLayout rl_about;
    @ViewInject(R.id.btn_logout)
    private Button btn_logout;
    private UserPresenter userPresenter;
    private PhotoSelectDialog photoSelectDialog;
    private CommunicateSP communicateSP;


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        userPresenter = new UserPresenter(getActivity());
        communicateSP = new CommunicateSP(getActivity());
    }

    @Override
    public void onResume() {
        super.onResume();
        RequestOptions options = new RequestOptions().placeholder(R.mipmap.user_default)
                .error(R.mipmap.user_default);
        Glide.with(this).load(communicateSP.getAvatar()).apply(options).into(user_image);
        tv_username.setText(communicateSP.getNickName());
    }

    @Event({R.id.user_image, R.id.rl_update_info, R.id.rl_suggestion, R.id.rl_about, R.id.btn_logout})
    private void onUserClick(View view) {
        switch (view.getId()) {
            case R.id.user_image: {
                showPhotoChoice();
                break;
            }

            case R.id.rl_update_info: {
                startActivity(new Intent(getActivity(), UpdateUserInfoActivity.class));
                break;
            }
            case R.id.rl_suggestion: {
                startActivity(new Intent(getActivity(), SuggestionActivity.class));
                break;
            }
            case R.id.rl_about: {
                startActivity(new Intent(getActivity(), AboutActivity.class));
                break;
            }
            case R.id.btn_logout: {
                userPresenter.clearLoginInfo();
                getActivity().finish();
                break;
            }
        }
    }

    private void showPhotoChoice() {
        photoSelectDialog = new PhotoSelectDialog(getActivity(), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                photoSelectDialog.dismiss();
                switch (v.getId()) {
                    case R.id.btn_take_photo:
                        PictureSelector.create(PersonFragment.this)
                                .openCamera(PictureMimeType.ofImage())
                                .enableCrop(true)
                                .compress(true)
                                .forResult(PictureConfig.CHOOSE_REQUEST);
                        break;
                    case R.id.btn_pick_photo:
                        PictureSelector.create(PersonFragment.this)
                                .openGallery(PictureMimeType.ofImage())
                                .enableCrop(true)
                                .compress(true)
                                .selectionMode(PictureConfig.SINGLE)
                                .forResult(PictureConfig.CHOOSE_REQUEST);
                        break;
                    default:
                        break;
                }
            }
        });
        photoSelectDialog.showAtLocation(getActivity().getWindow().getDecorView(), Gravity.BOTTOM, 0, 0);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        List<LocalMedia> images;
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    images = PictureSelector.obtainMultipleResult(data);
                    File file = new File(images.get(0).getCompressPath());
                    userPresenter.updatAvatar(file, communicateSP.getUserId(), new UpdateHandle());
                    break;
            }
        }
    }

    public class UpdateHandle implements UserPresenter.IUpdateHandler {
        @Override
        public void onUpdateSuccess(String message) {
            RequestOptions options = new RequestOptions().placeholder(R.mipmap.user_default)
                    .error(R.mipmap.user_default);
            Glide.with(PersonFragment.this).load(message).apply(options).into(user_image);
        }

        @Override
        public void onUpdateFail(String message) {
            MessageUtils.showShortToast(getActivity(), message);
            Log.i("photo", message);
        }

        @Override
        public void onUpdateError(Throwable e) {
            MessageUtils.showShortToast(getActivity(), e.toString());
            Log.i("photo", e.toString());
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

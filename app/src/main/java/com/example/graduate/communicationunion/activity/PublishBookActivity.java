package com.example.graduate.communicationunion.activity;

import android.content.Intent;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.graduate.communicationunion.R;
import com.example.graduate.communicationunion.dialog.PhotoSelectDialog;
import com.example.graduate.communicationunion.fragment.PersonFragment;
import com.example.graduate.communicationunion.presenter.BookPresenter;
import com.example.graduate.communicationunion.presenter.UserPresenter;
import com.example.graduate.communicationunion.utils.CommunicateSP;
import com.example.graduate.communicationunion.utils.MessageUtils;
import com.gizwits.energy.android.lib.base.BaseActivity;
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

@ContentView(R.layout.activty_add_book)
public class PublishBookActivity extends BaseActivity {
    @ViewInject(R.id.iv_book_url)
    private ImageView iv_book_url;
    @ViewInject(R.id.et_book_price)
    private EditText et_book_price;
    @ViewInject(R.id.et_book_title)
    private EditText et_book_title;
    @ViewInject(R.id.et_des)
    private EditText et_des;
    @ViewInject(R.id.iv_back)
    private ImageView iv_back;
    @ViewInject(R.id.tv_publish_book)
    private TextView tv_publish_book;
    private CommunicateSP communicateSP;
    private BookPresenter bookPresenter;
    private File tempfile;
    private PhotoSelectDialog photoSelectDialog;

    @Override
    protected void onResume() {
        super.onResume();
        bookPresenter= new BookPresenter(PublishBookActivity.this);
        communicateSP= new CommunicateSP(PublishBookActivity.this);
    }

    private void showPhotoChoice() {
        photoSelectDialog = new PhotoSelectDialog(PublishBookActivity.this, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                photoSelectDialog.dismiss();
                switch (v.getId()) {
                    case R.id.btn_take_photo:
                        PictureSelector.create(PublishBookActivity.this)
                                .openCamera(PictureMimeType.ofImage())
                                .enableCrop(true)
                                .compress(true)
                                .forResult(PictureConfig.CHOOSE_REQUEST);
                        break;
                    case R.id.btn_pick_photo:
                        PictureSelector.create(PublishBookActivity.this)
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
        photoSelectDialog.showAtLocation(PublishBookActivity.this.getWindow().getDecorView(), Gravity.BOTTOM, 0, 0);
    }

    @Event({R.id.iv_back, R.id.tv_publish_book, R.id.iv_book_url})
    private void OnPublishBookClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back: {
                onBackPressed();
                break;
            }
            case R.id.tv_publish_book: {
                bookPresenter.publishBook(tempfile, et_book_title.getText().toString(), Float.parseFloat(et_book_price.getText().toString()), et_des.getText().toString(), communicateSP.getPhone(), communicateSP.getUserId(), new UpdateHandle());
                break;
            }
            case R.id.iv_book_url: {
                showPhotoChoice();
                break;
            }
        }
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
                    tempfile = file;
                    Glide.with(PublishBookActivity.this).load(file).into(iv_book_url);
                    break;
            }
        }
    }

    public class UpdateHandle implements BookPresenter.IPublishHandler {
        @Override
        public void onUpdateSuccess(String message) {
            MessageUtils.showShortToast(PublishBookActivity.this, message);
            finish();
        }

        @Override
        public void onUpdateFail(String message) {
            MessageUtils.showShortToast(PublishBookActivity.this, message);
        }

        @Override
        public void onUpdateError(Throwable e) {
            MessageUtils.showShortToast(PublishBookActivity.this, e.toString());
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

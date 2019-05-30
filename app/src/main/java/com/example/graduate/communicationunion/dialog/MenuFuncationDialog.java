package com.example.graduate.communicationunion.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.graduate.communicationunion.R;


public class MenuFuncationDialog extends Dialog implements View.OnClickListener {
    private DialogItemListener dialogItemListener;
    private Context context;


    public MenuFuncationDialog(@NonNull Context context, DialogItemListener dialogItemListener) {
        super(context, R.style.dialogStyle);
        this.dialogItemListener = dialogItemListener;
        this.context = context;
        initDialogParam();
    }

    private void initDialogParam() {
        Window window = getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        params.gravity = Gravity.CENTER;
        window.setAttributes(params);
        setCancelable(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = View.inflate(context, R.layout.dialog_talk_function, null);
        LinearLayout ll_add_book = view.findViewById(R.id.ll_add_book);
        LinearLayout ll_add_talk = view.findViewById(R.id.ll_add_talk);
        LinearLayout ll_lib_book = view.findViewById(R.id.ll_lib_book);
        LinearLayout ll_lib_talk = view.findViewById(R.id.ll_lib_talk);
        ImageView iv_dismiss = view.findViewById(R.id.iv_dismiss);
        ll_add_book.setOnClickListener(this);
        ll_add_talk.setOnClickListener(this);
        ll_lib_book.setOnClickListener(this);
        ll_lib_talk.setOnClickListener(this);
        iv_dismiss.setOnClickListener(this);
        setContentView(view);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_add_book: {
                if (dialogItemListener != null) {
                    dialogItemListener.ItemClick("发布图书信息");
                }
                break;
            }
            case R.id.ll_add_talk: {
                if (dialogItemListener != null) {
                    dialogItemListener.ItemClick("发布论坛帖子");
                }
                break;
            }
            case R.id.ll_lib_book: {
                if (dialogItemListener != null) {
                    dialogItemListener.ItemClick("个人图书信息");
                }
                break;
            }
            case R.id.ll_lib_talk: {
                if (dialogItemListener != null) {
                    dialogItemListener.ItemClick("个人论坛帖子信息");
                }
                break;
            }
            default: {
                dismiss();
                break;
            }


        }
    }

    public interface DialogItemListener {
        void ItemClick(String type);
    }
}

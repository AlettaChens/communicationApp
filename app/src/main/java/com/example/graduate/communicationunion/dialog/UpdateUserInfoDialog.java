package com.example.graduate.communicationunion.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.graduate.communicationunion.R;

public class UpdateUserInfoDialog extends Dialog {
    private UpdateCompelet updateCompelet;
    private Context context;
    private String title;


    public UpdateUserInfoDialog(@NonNull Context context, UpdateCompelet updateCompelet,String title) {
        super(context, R.style.dialogStyle);
        this.updateCompelet = updateCompelet;
        this.context = context;
        this.title=title;
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
        View view =View.inflate(context, R.layout.dialog_update_user_info, null);
        final TextView tv_title = view.findViewById(R.id.tv_title);
        final EditText content_ed = view.findViewById(R.id.content_ed);
        Button negative_btn = view.findViewById(R.id.negative_btn);
        Button positive_btn = view.findViewById(R.id.positive_btn);
        tv_title.setText(title);
        negative_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        positive_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (updateCompelet != null) {
                    updateCompelet.onCompelet(title, content_ed.getText().toString());
                    dismiss();
                }
            }
        });
        setContentView(view);
    }

    public interface UpdateCompelet {
        void onCompelet(String type, String info);
    }
}

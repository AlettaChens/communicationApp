package com.example.graduate.communicationunion.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.graduate.communicationunion.R;
import com.example.graduate.communicationunion.bean.Book;
import com.gizwits.energy.android.lib.base.BaseActivity;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

@ContentView(R.layout.activity_book_detail)
public class BookDetail extends BaseActivity {
    @ViewInject(R.id.iv_book)
    private ImageView iv_book;
    @ViewInject(R.id.iv_back)
    private ImageView iv_back;
    @ViewInject(R.id.tv_book_title)
    private TextView tv_book_title;
    @ViewInject(R.id.tv_book_price)
    private TextView tv_book_price;
    @ViewInject(R.id.tv_book_phone)
    private TextView tv_book_phone;
    @ViewInject(R.id.tv_book_time)
    private TextView tv_book_time;
    @ViewInject(R.id.tv_book_des)
    private TextView tv_book_des;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent getIntent = getIntent();
        Book book = (Book) getIntent.getSerializableExtra("book");
        tv_book_title.setText("书名："+book.getTitle());
        tv_book_des.setText(book.getDescription());
        tv_book_price.setText("价格："+String.valueOf(book.getPrice()));
        tv_book_time.setText("发布日期："+book.getDate());
        tv_book_phone.setText("联系电话："+book.getPhone());
        RequestOptions options = new RequestOptions().placeholder(R.mipmap.ic_app)
                .error(R.mipmap.ic_app);
        Glide.with(BookDetail.this).load(book.getBook_url()).apply(options).into(iv_book);
    }

    @Event(R.id.iv_back)
    private void OnBookDetailClick(View view) {
        onBackPressed();
    }
}

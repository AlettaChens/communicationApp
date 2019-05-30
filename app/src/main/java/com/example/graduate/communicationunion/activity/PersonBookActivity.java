package com.example.graduate.communicationunion.activity;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.graduate.communicationunion.R;
import com.example.graduate.communicationunion.adapter.BookAdapter;
import com.example.graduate.communicationunion.bean.Book;
import com.example.graduate.communicationunion.presenter.BookPresenter;
import com.example.graduate.communicationunion.presenter.callback.CommonCallback;
import com.example.graduate.communicationunion.utils.CommunicateSP;
import com.example.graduate.communicationunion.utils.MessageUtils;
import com.gizwits.energy.android.lib.base.BaseActivity;
import com.gizwits.energy.android.lib.presenter.IDefaultListLoader;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.List;

@ContentView(R.layout.activty_book_lib)
public class PersonBookActivity extends BaseActivity {
    @ViewInject(R.id.rv_book_lib)
    private RecyclerView rv_book_lib;
    private BookPresenter bookPresenter;
    private BookAdapter bookAdapter;
    private CommunicateSP communicateSP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        communicateSP = new CommunicateSP(PersonBookActivity.this);
        bookPresenter = new BookPresenter(PersonBookActivity.this);
        getList();

    }

    private void getList() {
        bookPresenter.getAllBookByUserId(communicateSP.getUserId(), new IDefaultListLoader<Book>() {
            @Override
            public void onLoadFail(String msg) {

            }

            @Override
            public void onLoadSuccess(@NonNull List<Book> list) {
                bookAdapter = new BookAdapter(list, PersonBookActivity.this, new BookAdapter.BookClickHandle() {
                    @Override
                    public void bookClick(Book book) {
                        Intent intent = new Intent(PersonBookActivity.this, BookDetail.class);
                        intent.putExtra("book", book);
                        startActivity(intent);
                    }

                    @Override
                    public void bookLongClick(final Book book) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(PersonBookActivity.this);
                        builder.setMessage("是否删除？")
                                .setCancelable(false)
                                .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        bookPresenter.deleteBook(book.getBookId(), new CommonCallback() {
                                            @Override
                                            public void onSuccess(String message) {
                                                MessageUtils.showShortToast(PersonBookActivity.this, message);
                                                getList();
                                            }

                                            @Override
                                            public void onFail(String message) {
                                                MessageUtils.showShortToast(PersonBookActivity.this, message);
                                            }

                                            @Override
                                            public void onError(Throwable e) {
                                                MessageUtils.showShortToast(PersonBookActivity.this, e.toString());
                                            }

                                            @Override
                                            public void onRequestStarted() {

                                            }

                                            @Override
                                            public void onRequestCanceled() {

                                            }

                                            @Override
                                            public void onRequestFinished() {

                                            }
                                        });
                                    }
                                })
                                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                }).create().show();
                    }
                });
                rv_book_lib.setLayoutManager(new LinearLayoutManager(PersonBookActivity.this));
                rv_book_lib.addItemDecoration(new DividerItemDecoration(PersonBookActivity.this, DividerItemDecoration.VERTICAL));
                rv_book_lib.setAdapter(bookAdapter);
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

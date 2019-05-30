package com.example.graduate.communicationunion.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.graduate.communicationunion.R;
import com.example.graduate.communicationunion.activity.BookDetail;
import com.example.graduate.communicationunion.adapter.BookAdapter;
import com.example.graduate.communicationunion.bean.Book;
import com.example.graduate.communicationunion.presenter.BookPresenter;
import com.gizwits.energy.android.lib.base.BaseFragment;
import com.gizwits.energy.android.lib.presenter.IDefaultListLoader;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.List;

@ContentView(R.layout.fragment_book)
public class BookFragment extends BaseFragment {
    @ViewInject(R.id.rv_book)
    private RecyclerView rv_book;
    private BookPresenter bookPresenter;
    private BookAdapter bookAdapter;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bookPresenter = new BookPresenter(getActivity());
    }

    @Override
    public void onResume() {
        super.onResume();
        bookPresenter.getAllBook(new IDefaultListLoader<Book>() {
            @Override
            public void onLoadFail(String msg) {

            }

            @Override
            public void onLoadSuccess(@NonNull List<Book> list) {
                bookAdapter = new BookAdapter(list, getActivity(), new BookAdapter.BookClickHandle() {
                    @Override
                    public void bookClick(Book book) {
                        Intent intent = new Intent(getActivity(), BookDetail.class);
                        intent.putExtra("book", book);
                        startActivity(intent);
                    }

                    @Override
                    public void bookLongClick(Book book) {

                    }
                });
                rv_book.setLayoutManager(new LinearLayoutManager(getActivity()));
                rv_book.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
                rv_book.setAdapter(bookAdapter);
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

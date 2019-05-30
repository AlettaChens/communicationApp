package com.example.graduate.communicationunion.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.graduate.communicationunion.R;
import com.example.graduate.communicationunion.bean.Book;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookListHolder> {
    private List<Book> bookList;
    private Context context;
    private BookClickHandle bookClickHandle;

    public BookAdapter(List<Book> bookList, Context context, BookClickHandle bookClickHandle) {
        this.bookList = bookList;
        this.context = context;
        this.bookClickHandle = bookClickHandle;
    }

    @NonNull
    @Override
    public BookListHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new BookListHolder(LayoutInflater.from(context).inflate(R.layout.item_book,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull BookListHolder bookListHolder, int i) {
        bookListHolder.update(bookList.get(i),i);
    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }

    public class BookListHolder extends RecyclerView.ViewHolder{
        private int position;
        private TextView tv_book_title;
        private CircleImageView ci_book;
        private TextView tv_book_update;
        public BookListHolder(@NonNull View itemView) {
            super(itemView);
            tv_book_title = itemView.findViewById(R.id.tv_book_title);
            tv_book_update = itemView.findViewById(R.id.tv_book_update);
            ci_book = itemView.findViewById(R.id.ci_book);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (bookClickHandle != null) {
                        bookClickHandle.bookClick(bookList.get(position));
                    }
                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    bookClickHandle.bookLongClick(bookList.get(position));
                    return true;
                }
            });
        }
        public void update(Book book,int position){
            this.position=position;
            RequestOptions options = new RequestOptions().placeholder(R.mipmap.ic_app)
                    .error(R.mipmap.ic_app);
            Glide.with(context).load(book.getBook_url()).apply(options).into(ci_book);
            tv_book_title.setText(book.getTitle());
            tv_book_update.setText("发布时间:"+book.getDate());
        }
    }
    public interface BookClickHandle{
        void bookClick(Book book);
        void bookLongClick(Book book);
    }
}

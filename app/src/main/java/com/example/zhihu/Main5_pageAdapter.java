package com.example.zhihu;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by hp on 2017/12/10.
 */

public class Main5_pageAdapter extends RecyclerView.Adapter<Main5_pageAdapter.ViewHolder> {
    private Context mContxet;

    private List<Main5_page> mMain5_pageList;

    static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        ImageView avatar;
        TextView author;
        TextView time;
        TextView likes;
        TextView content_1;

        public ViewHolder(View view) {
            super(view);
            cardView = (CardView) view;
            avatar = (ImageView) view.findViewById(R.id.avatar);
            likes = (TextView) view.findViewById(R.id.likes);
            time = (TextView) view.findViewById(R.id.time);
            content_1 = (TextView) view.findViewById(R.id.content_1);
            author = (TextView) view.findViewById(R.id.author);
        }
    }

    public Main5_pageAdapter(List<Main5_page>main5_pageList) {
        mMain5_pageList = main5_pageList;
    }

    @Override
    public Main5_pageAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContxet==null) {
            mContxet = parent.getContext();
        }
        View view = LayoutInflater.from(mContxet).inflate(R.layout.main5_page,
                parent,false);
        final Main5_pageAdapter.ViewHolder holder = new Main5_pageAdapter.ViewHolder(view);
        holder.avatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Main5_page main5_page = mMain5_pageList.get(position);
                Toast.makeText(v.getContext(),"长评",Toast.LENGTH_SHORT).show();
            }
        });
        holder.content_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Main5_page main5_page = mMain5_pageList.get(position);
                Toast.makeText(v.getContext(),"长评",Toast.LENGTH_SHORT).show();

            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(Main5_pageAdapter.ViewHolder holder, int position) {
        Main5_page main5_page = mMain5_pageList.get(position);
        holder.content_1.setText(main5_page.getContent_1());
        holder.author.setText(main5_page.getAuthor());
        holder.time.setText(main5_page.getTime());
        holder.likes.setText(main5_page.getLikes());
        Glide.with(mContxet).load(main5_page.getM5_image()).into(holder.avatar);
    }

    @Override
    public int getItemCount() {
        return mMain5_pageList.size();
    }
}

package com.example.zhihu;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
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
 * Created by hp on 2017/12/9.
 */

public class Main2_pageAdapter extends RecyclerView.Adapter<Main2_pageAdapter.ViewHolder> {
    private Context mContxet;

    private List<Main2_page> mMain2_pageList;

    static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        ImageView main2_images;
        TextView main2_title;
        TextView main2_id;
        TextView main2_data;
        TextView display_data;

        public ViewHolder(View view) {
            super(view);
            cardView = (CardView) view;
            main2_images = (ImageView) view.findViewById(R.id.main2_images);
            main2_data = (TextView) view.findViewById(R.id.main2_date);
            main2_id = (TextView) view.findViewById(R.id.main2_id);
            display_data = (TextView) view.findViewById(R.id.display_date);
            main2_title = (TextView) view.findViewById(R.id.main2_title);
        }
    }

    public Main2_pageAdapter(List<Main2_page>main2_pageList) {
        mMain2_pageList = main2_pageList;
    }

    @Override
    public Main2_pageAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContxet==null) {
            mContxet = parent.getContext();
        }
        final View view = LayoutInflater.from(mContxet).inflate(R.layout.main2_page,
                parent,false);
        final Main2_pageAdapter.ViewHolder holder = new Main2_pageAdapter.ViewHolder(view);
        holder.main2_images.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Main2_page main2_page = mMain2_pageList.get(position);
            }
        });
        holder.main2_title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Main2_page main2_page = mMain2_pageList.get(position);

            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(Main2_pageAdapter.ViewHolder holder, int position) {
        Main2_page main2_page = mMain2_pageList.get(position);
        holder.main2_id.setText(main2_page.getMain2_id());
        holder.display_data.setText(main2_page.getDisplay_date());
        holder.main2_title.setText(main2_page.getMain2_title());
        holder.main2_data.setText(main2_page.getMain2_data());
        Glide.with(mContxet).load(main2_page.getImage()).into(holder.main2_images);
    }

    @Override
    public int getItemCount() {
        return mMain2_pageList.size();
    }
}

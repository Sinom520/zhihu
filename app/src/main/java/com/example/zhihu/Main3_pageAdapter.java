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

public class Main3_pageAdapter extends RecyclerView.Adapter<Main3_pageAdapter.ViewHolder> {
    static public String id2;//记录点击文章的id

    static public String hide2_title;//记录点击文章的标题

    static public String hide2_image;//记录点击文章的图片

    static public Intent intent2=new Intent();

    private Context mContxet;

    private List<Main3_page> mMain3_pageList;

    static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        ImageView main3_images;
        TextView main3_title;
        TextView main3_id;

        public ViewHolder(View view) {
            super(view);
            cardView = (CardView) view;
            main3_images = (ImageView) view.findViewById(R.id.main3_images);
            main3_id = (TextView) view.findViewById(R.id.main3_id);
            main3_title = (TextView) view.findViewById(R.id.main3_title);
        }
    }

    public Main3_pageAdapter(List<Main3_page>main3_pageList) {
        mMain3_pageList = main3_pageList;
    }

    @Override
    public Main3_pageAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContxet==null) {
            mContxet = parent.getContext();
        }
        final View view = LayoutInflater.from(mContxet).inflate(R.layout.main3_page,
                parent,false);
        final Main3_pageAdapter.ViewHolder holder = new Main3_pageAdapter.ViewHolder(view);
        holder.main3_images.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Main3_page main3_page = mMain3_pageList.get(position);
                id2 = main3_page.getMain3_id();
                hide2_title = main3_page.getMain3_title();
                hide2_image = main3_page.getM3_image();
                Intent intent2=new Intent();
                intent2.setClass(v.getContext(),Main7page.class );
                v.getContext() .startActivity(intent2);
            }
        });
        holder.main3_title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Main3_page main3_page = mMain3_pageList.get(position);
                id2 = main3_page.getMain3_id();
                hide2_title = main3_page.getMain3_title();
                hide2_image = main3_page.getM3_image();
                intent2.setClass(v.getContext(),Main7page.class );
                v.getContext() .startActivity(intent2);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(Main3_pageAdapter.ViewHolder holder, int position) {
        Main3_page main3_page = mMain3_pageList.get(position);
        holder.main3_id.setText(main3_page.getMain3_id());
        holder.main3_title.setText(main3_page.getMain3_title());
        Glide.with(mContxet).load(main3_page.getM3_image()).into(holder.main3_images);
    }

    @Override
    public int getItemCount() {
        return mMain3_pageList.size();
    }
}

package com.example.zhihu;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import static com.example.zhihu.Main_pageAdapter.id1;

import static com.example.zhihu.Main3_pageAdapter.id2;

/**
 * Created by hp on 2017/12/16.
 */

public class Main8_pageAdapter extends RecyclerView.Adapter<Main8_pageAdapter.ViewHolder> {
    private Context mContxet;

    private List<Main8_page> mMain8_pageList;

    static public Boolean sign1 = true;


    static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView main8_title;
        TextView main8_id;
        ImageView main8_images;

        public ViewHolder(View view) {
            super(view);
            cardView = (CardView) view;
            main8_id = (TextView) view.findViewById(R.id.main8_id);
            main8_title = (TextView) view.findViewById(R.id.main8_title);
            main8_images = (ImageView) view.findViewById(R.id.main8_images);
        }
    }

    public Main8_pageAdapter(List<Main8_page>main8_pageList) {
        mMain8_pageList = main8_pageList;
    }

    @Override
    public Main8_pageAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContxet==null) {
            mContxet = parent.getContext();
        }
        View view = LayoutInflater.from(mContxet).inflate(R.layout.main8_page,
                parent,false);
        final Main8_pageAdapter.ViewHolder holder = new Main8_pageAdapter.ViewHolder(view);
        holder.main8_title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Main8_page main8_page = mMain8_pageList.get(position);
                id2 = main8_page.getMain8_id();
                sign1 = false;
                Intent intent3=new Intent();
                intent3.setClass(v.getContext(),Main7page.class );
                v.getContext() .startActivity(intent3);
            }
        });
        holder.main8_images.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Main8_page main8_page = mMain8_pageList.get(position);
                id2 = main8_page.getMain8_id();
                sign1 = false;
                Intent intent4=new Intent();
                intent4.setClass(v.getContext(),Main7page.class );
                v.getContext() .startActivity(intent4);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(Main8_pageAdapter.ViewHolder holder, int position) {
        Main8_page main8_page = mMain8_pageList.get(position);
        holder.main8_id.setText(main8_page.getMain8_id());
        holder.main8_title.setText(main8_page.getMain8_title());
        Glide.with(mContxet).load(main8_page.getMain8_thumbnail()).into(holder.main8_images);
    }

    @Override
    public int getItemCount() {
        return mMain8_pageList.size();
    }
}

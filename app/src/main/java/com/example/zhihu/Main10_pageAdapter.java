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

import static com.example.zhihu.Main3_pageAdapter.id2;

/**
 * Created by hp on 2017/12/16.
 */

public class Main10_pageAdapter extends RecyclerView.Adapter<Main10_pageAdapter.ViewHolder> {
    private Context mContxet;

    private List<Main10_page> mMain10_pageList;

    static public Boolean sign4 = true;


    static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView main10_title;
        TextView main10_id;
        ImageView main10_images;

        public ViewHolder(View view) {
            super(view);
            cardView = (CardView) view;
            main10_id = (TextView) view.findViewById(R.id.main10_id);
            main10_title = (TextView) view.findViewById(R.id.main10_title);
            main10_images = (ImageView) view.findViewById(R.id.main10_images);
        }
    }

    public Main10_pageAdapter(List<Main10_page>main10_pageList) {mMain10_pageList = main10_pageList;
    }

    @Override
    public Main10_pageAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContxet==null) {
            mContxet = parent.getContext();
        }
        View view = LayoutInflater.from(mContxet).inflate(R.layout.main10_page,
                parent,false);
        final Main10_pageAdapter.ViewHolder holder = new Main10_pageAdapter.ViewHolder(view);
        holder.main10_title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Main10_page main10_page = mMain10_pageList.get(position);
                id2 = main10_page.getMain10_id();
                sign4 = false;
                Intent intent3=new Intent();
                intent3.setClass(v.getContext(),Main7page.class );
                v.getContext() .startActivity(intent3);
            }
        });
        holder.main10_images.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Main10_page main10_page = mMain10_pageList.get(position);
                id2 = main10_page.getMain10_id();
                sign4 = false;
                Intent intent4=new Intent();
                intent4.setClass(v.getContext(),Main7page.class );
                v.getContext() .startActivity(intent4);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(Main10_pageAdapter.ViewHolder holder, int position) {
        Main10_page main10_page = mMain10_pageList.get(position);
        holder.main10_id.setText(main10_page.getMain10_id());
        holder.main10_title.setText(main10_page.getMain10_title());
        Glide.with(mContxet).load(main10_page.getMain10_thumbnail()).into(holder.main10_images);
    }

    @Override
    public int getItemCount() {
        return mMain10_pageList.size();
    }
}

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
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by hp on 2017/12/7.
 */

public class Main_pageAdapter extends RecyclerView.Adapter<Main_pageAdapter.ViewHolder> {

    private Context mContxet;

    private List<Main_page>mMain_pageList;

    static public String id1;//判断点击的为哪一个专栏

    static public String hide1_title;//记录点击专栏的标题

    static public String hide1_images;//记录点击专栏的图片

    static public String hide1_massage;//记录点击专栏的内容

    static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        ImageView thumbnail;
        TextView main_name;
        TextView main_id;
        TextView description;

        public ViewHolder(View view) {
            super(view);
            cardView = (CardView) view;
            thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
            main_name = (TextView) view.findViewById(R.id.main_name);
            main_id = (TextView) view.findViewById(R.id.main_id);
            description = (TextView) view.findViewById(R.id.description);
        }
    }

    public Main_pageAdapter(List<Main_page>main_pageList) {
        mMain_pageList = main_pageList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContxet==null) {
            mContxet = parent.getContext();
        }
        View view = LayoutInflater.from(mContxet).inflate(R.layout.main_page,
                parent,false);
        final ViewHolder holder = new ViewHolder(view);
        holder.thumbnail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Main_page main_page = mMain_pageList.get(position);
                id1 = main_page.getMain_id();
                hide1_title = main_page.getMain_name();
                hide1_images = main_page.getImages();
                hide1_massage = main_page.getDescription();
                Intent intent1=new Intent();
                intent1.setClass(v.getContext(),Main2page.class );
                v.getContext() .startActivity(intent1);
            }
        });
        holder.main_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Main_page main_page = mMain_pageList.get(position);
                id1 = main_page.getMain_id();
                hide1_title = main_page.getMain_name();
                hide1_images = main_page.getImages();
                hide1_massage = main_page.getDescription();
                Intent intent1=new Intent();
                intent1.setClass(v.getContext(),Main2page.class );
                v.getContext() .startActivity(intent1);
            }
        });
        holder.description.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Main_page main_page = mMain_pageList.get(position);
                id1 = main_page.getMain_id();
                hide1_title = main_page.getMain_name();
                hide1_images = main_page.getImages();
                hide1_massage = main_page.getDescription();
                Intent intent1=new Intent();
                intent1.setClass(v.getContext(),Main2page.class );
                v.getContext() .startActivity(intent1);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder,int position) {
        Main_page main_page = mMain_pageList.get(position);
        holder.main_name.setText(main_page.getMain_name());
        holder.description.setText(main_page.getDescription());
        holder.main_id.setText(main_page.getMain_id());
        Glide.with(mContxet).load(main_page.getImages()).into(holder.thumbnail);
    }

    @Override
    public int getItemCount() {
        return mMain_pageList.size();
    }
}

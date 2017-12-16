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

import static com.example.zhihu.Main_pageAdapter.id1;

/**
 * Created by hp on 2017/12/9.
 */

public class Main4_pageAdapter extends RecyclerView.Adapter<Main4_pageAdapter.ViewHolder> {
    private Context mContxet;

    private List<Main4_page> mMain4_pageList;

    static public Boolean flag6 = false;

    static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView main4_name;
        TextView main4_id;
        TextView main4_description;
        ImageView main4_images;

        public ViewHolder(View view) {
            super(view);
            cardView = (CardView) view;
            main4_id = (TextView) view.findViewById(R.id.main4_id);
            main4_name = (TextView) view.findViewById(R.id.main4_name);
            main4_description = (TextView) view.findViewById(R.id.main4_description);
            main4_images = (ImageView) view.findViewById(R.id.main4_images);
        }
    }

    public Main4_pageAdapter(List<Main4_page>main4_pageList) {
        mMain4_pageList = main4_pageList;
    }

    @Override
    public Main4_pageAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContxet==null) {
            mContxet = parent.getContext();
        }
        View view = LayoutInflater.from(mContxet).inflate(R.layout.main4_page,
                parent,false);
        final Main4_pageAdapter.ViewHolder holder = new Main4_pageAdapter.ViewHolder(view);
        holder.main4_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Main4_page main4_page = mMain4_pageList.get(position);
                id1 = main4_page.getMain4_id();
                Intent intent1=new Intent();
                intent1.setClass(v.getContext(),Main2page.class );
                v.getContext() .startActivity(intent1);
                flag6 = true;
            }
        });
        holder.main4_description.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Main4_page main4_page = mMain4_pageList.get(position);
                id1 = main4_page.getMain4_id();
                Intent intent1=new Intent();
                intent1.setClass(v.getContext(),Main2page.class );
                v.getContext() .startActivity(intent1);
                flag6 = true;
            }
        });
        holder.main4_images.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Main4_page main4_page = mMain4_pageList.get(position);
                id1 = main4_page.getMain4_id();
                Intent intent1=new Intent();
                intent1.setClass(v.getContext(),Main2page.class );
                v.getContext() .startActivity(intent1);
                flag6 = true;
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(Main4_pageAdapter.ViewHolder holder, int position) {
        Main4_page main4_page = mMain4_pageList.get(position);
        holder.main4_id.setText(main4_page.getMain4_id());
        holder.main4_name.setText(main4_page.getMain4_name());
        holder.main4_description.setText(main4_page.getMain4_description());
        Glide.with(mContxet).load(main4_page.getMain4_thumbnail()).into(holder.main4_images);
    }

    @Override
    public int getItemCount() {
        return mMain4_pageList.size();
    }
}

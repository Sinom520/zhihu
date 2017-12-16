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

/**
 * Created by hp on 2017/12/16.
 */

public class Main9_pageAdapter extends RecyclerView.Adapter<Main9_pageAdapter.ViewHolder> {
    private Context mContxet;

    private List<Main9_page> mMain9_pageList;

    static public Boolean sign2 = false;

    static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView main9_name;
        TextView main9_id;
        TextView main9_description;
        ImageView main9_images;

        public ViewHolder(View view) {
            super(view);
            cardView = (CardView) view;
            main9_id = (TextView) view.findViewById(R.id.main9_id);
            main9_name = (TextView) view.findViewById(R.id.main9_name);
            main9_description = (TextView) view.findViewById(R.id.main9_description);
            main9_images = (ImageView) view.findViewById(R.id.main9_images);
        }
    }

    public Main9_pageAdapter(List<Main9_page>main9_pageList) {
        mMain9_pageList = main9_pageList;
    }

    @Override
    public Main9_pageAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContxet==null) {
            mContxet = parent.getContext();
        }
        View view = LayoutInflater.from(mContxet).inflate(R.layout.main9_page,
                parent,false);
        final Main9_pageAdapter.ViewHolder holder = new Main9_pageAdapter.ViewHolder(view);
        holder.main9_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Main9_page main9_page = mMain9_pageList.get(position);
                id1 = main9_page.getMain9_id();
                Intent intent9=new Intent();
                intent9.setClass(v.getContext(),Main2page.class );
                v.getContext() .startActivity(intent9);
                sign2 = true;
            }
        });
        holder.main9_description.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Main9_page main9_page = mMain9_pageList.get(position);
                id1 = main9_page.getMain9_id();
                Intent intent9=new Intent();
                intent9.setClass(v.getContext(),Main2page.class );
                v.getContext() .startActivity(intent9);
                sign2 = true;
            }
        });
        holder.main9_images.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Main9_page main9_page = mMain9_pageList.get(position);
                id1 = main9_page.getMain9_id();
                Intent intent1=new Intent();
                intent1.setClass(v.getContext(),Main2page.class );
                v.getContext() .startActivity(intent1);
                sign2 = true;
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(Main9_pageAdapter.ViewHolder holder, int position) {
        Main9_page main9_page = mMain9_pageList.get(position);
        holder.main9_id.setText(main9_page.getMain9_id());
        holder.main9_name.setText(main9_page.getMain9_name());
        holder.main9_description.setText(main9_page.getMain9_description());
        Glide.with(mContxet).load(main9_page.getMain9_thumbnail()).into(holder.main9_images);
    }

    @Override
    public int getItemCount() {
        return mMain9_pageList.size();
    }
}

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

public class Main6_pageAdapter extends RecyclerView.Adapter<Main6_pageAdapter.ViewHolder> {
    private Context mContxet;

    private List<Main6_page> mMain6_pageList;

    static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        ImageView avatar6;
        TextView author6;
        TextView time6;
        TextView likes6;
        TextView content_6;

        public ViewHolder(View view) {
            super(view);
            cardView = (CardView) view;
            avatar6 = (ImageView) view.findViewById(R.id.avatar6);
            likes6 = (TextView) view.findViewById(R.id.likes6);
            time6 = (TextView) view.findViewById(R.id.time6);
            content_6 = (TextView) view.findViewById(R.id.content_6);
            author6 = (TextView) view.findViewById(R.id.author6);
        }
    }

    public Main6_pageAdapter(List<Main6_page>main6_pageList) {
        mMain6_pageList = main6_pageList;
    }

    @Override
    public Main6_pageAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContxet==null) {
            mContxet = parent.getContext();
        }
        View view = LayoutInflater.from(mContxet).inflate(R.layout.main6_page,
                parent,false);
        final Main6_pageAdapter.ViewHolder holder = new Main6_pageAdapter.ViewHolder(view);
        holder.avatar6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Main6_page main6_page = mMain6_pageList.get(position);
                Toast.makeText(v.getContext(),"短评",Toast.LENGTH_SHORT).show();
            }
        });
        holder.content_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Main6_page main6_page = mMain6_pageList.get(position);
                Toast.makeText(v.getContext(),"短评",Toast.LENGTH_SHORT).show();

            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(Main6_pageAdapter.ViewHolder holder, int position) {
        Main6_page main6_page = mMain6_pageList.get(position);
        holder.content_6.setText(main6_page.getContent_6());
        holder.author6.setText(main6_page.getAuthor6());
        holder.time6.setText(main6_page.getTime6());
        holder.likes6.setText(main6_page.getLikes6());
        Glide.with(mContxet).load(main6_page.getM6_image()).into(holder.avatar6);
    }

    @Override
    public int getItemCount() {
        return mMain6_pageList.size();
    }
}

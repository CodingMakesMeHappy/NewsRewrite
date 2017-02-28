package com.example.hp.newsrewrite;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by HP on 2017/2/26.
 */

public class ListAdapter extends RecyclerView.Adapter {
    private List<ListBean> listBeen;
    private LayoutInflater layoutInflater;
    private Context context;

    ListAdapter(List<ListBean> listBeen, Context context) {
        this.listBeen = listBeen;
        this.context = context;
    }


    public class NewsViewHold extends RecyclerView.ViewHolder {

        // RecyclerView list;
        ImageView item_pic;
        TextView item_subject;
        TextView item_summary;

        public NewsViewHold(View itemView) {
            super(itemView);
            // list = (RecyclerView)itemView.findViewById(R.id.list);
            item_pic = (ImageView) itemView.findViewById(R.id.item_pic);
            item_subject = (TextView) itemView.findViewById(R.id.item_subject);
            item_summary = (TextView) itemView.findViewById(R.id.item_summary);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        view = layoutInflater.from(context).inflate(R.layout.item_laylout, parent, false);
        NewsViewHold newsViewHold = new NewsViewHold(view);

        return newsViewHold;

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final int positionFinal = position;
        NewsViewHold newsViewHold = (NewsViewHold) holder;
        Glide.with(context).load(listBeen.get(positionFinal).pic).asBitmap()
                .error(R.mipmap.ic_launcher).into(newsViewHold.item_pic);
        newsViewHold.item_subject.setText(listBeen.get(positionFinal).subject);
        newsViewHold.item_summary.setText(listBeen.get(positionFinal).summary);
        newsViewHold.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("index",String.valueOf(listBeen.get(positionFinal).index));
                Intent intent = new Intent();
                intent.putExtras(bundle);
                intent.setClass(context,NewsContent.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listBeen.size();
    }
}

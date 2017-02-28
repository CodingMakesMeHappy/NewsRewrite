package com.example.hp.newsrewrite;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by HP on 2017/2/26.
 */

public class ListAdapter extends RecyclerView.Adapter {
    private List<ListBean> listBeen;
    private LayoutInflater layoutInflater;

    ListAdapter(List<ListBean> listBeen) {
        this.listBeen = listBeen;
    }


    public class NewsViewHold extends RecyclerView.ViewHolder {

        RecyclerView list;
        ImageView item_pic;
        TextView item_subject;
        TextView item_summary;

        public NewsViewHold(View itemView) {
            super(itemView);
            list = (RecyclerView)itemView.findViewById(R.id.list);
            item_pic = (ImageView) itemView.findViewById(R.id.item_pic);
            item_subject = (TextView) itemView.findViewById(R.id.item_subject);
            item_summary = (TextView) itemView.findViewById(R.id.item_summary);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        view = layoutInflater.from(context).inflate(R.layout.news_item, parent, false);

        return new NewsViewHold(view);
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return listBeen.size();
    }
}

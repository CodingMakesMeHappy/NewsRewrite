package com.example.hp.newsrewrite;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by HP on 2017/2/26.
 */

public class ListAdapter extends RecyclerView.Adapter {
    private List<ListBean> listBeen;

    ListAdapter(List<ListBean> listBeen) {
        this.listBeen = listBeen;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
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

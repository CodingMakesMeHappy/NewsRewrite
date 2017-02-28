package com.example.hp.newsrewrite;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;

/**
 * Created by HP on 2017/2/26.
 */

public class NewsAsyncTask extends AsyncTask<String, Void, List<ListBean>> {

    private Context context;
    private ListAdapter listAdapter;
    private RecyclerView list;

    NewsAsyncTask(Context context, RecyclerView list) {
        this.context = context;
        this.list = list;
    }

    @Override
    protected List<ListBean> doInBackground(String... params) {
        return getJsonData(params[0]);
    }

    @Override
    protected void onPostExecute(List<ListBean> listBeen) {
        super.onPostExecute(listBeen);
        ListAdapter listAdapter;
        listAdapter = new ListAdapter(listBeen,context);
       // LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        list.setAdapter(listAdapter);
      //  list.setLayoutManager(linearLayoutManager);
      //  mSwipeLayout.setRefreshing(false);
    }


    private List<ListBean> getJsonData(String url) {
        String jsonString = null;
        try {
            jsonString = readStream(new URL(url).openStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        Gson gson = new Gson();
        ListBeanList listBeenList;
        listBeenList = gson.fromJson(jsonString, ListBeanList.class);

        return listBeenList.data;

    }

    private String readStream(InputStream is) {
        InputStreamReader isr;
        String res = "";
        try {
            String line;
            isr = new InputStreamReader(is, "utf-8");
            BufferedReader br = new BufferedReader(isr);
            while ((line = br.readLine()) != null) {
                res += line;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }
}




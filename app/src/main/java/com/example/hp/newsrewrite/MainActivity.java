package com.example.hp.newsrewrite;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public int page = 1;
    public final static String urlHead = "http://open.twtstudio.com/api/v1/news/1/page/";
    private RecyclerView list;
    private ListAdapter listAdapter;
    public SwipeRefreshLayout refreshList;
    private boolean isLoading = false;
    private List<ListBean> dataToShow = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list = (RecyclerView) findViewById(R.id.list);
        refreshList = (SwipeRefreshLayout) findViewById(R.id.list_refresh);
        refreshList.setProgressViewOffset(true, 0, 100);
        refreshList.setSize(SwipeRefreshLayout.DEFAULT);
        refreshList.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        refreshList.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        new NewsAsyncTask().execute(urlHead + String.valueOf(page));

                    }
                }
        );
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this);
        list.setLayoutManager(linearLayoutManager);



        list.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int totalCount = linearLayoutManager.getItemCount();
                int lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
                if (!isLoading && totalCount < (lastVisibleItem + 2)) {
                    page++;
                    new NewsAsyncTask().execute(urlHead + String.valueOf(page));

                }
            }
        });
        refreshList.post(new Runnable() {
            @Override
            public void run() {
                listAdapter = new ListAdapter(dataToShow, MainActivity.this);
                list.setAdapter(listAdapter);
                new NewsAsyncTask().execute(urlHead + String.valueOf(page));

            }
        });
    }

    private class NewsAsyncTask extends AsyncTask<String, Void, List<ListBean>> {



        @Override
        protected List<ListBean> doInBackground(String... params) {
            return getJsonData(params[0]);
        }

        @Override
        protected void onPostExecute(List<ListBean> listBeen) {
            super.onPostExecute(listBeen);


//            ListAdapter listAdapter;
//            listAdapter = new ListAdapter(listBeen, MainActivity.this);
//            list.setAdapter(listAdapter);

            listAdapter.notifyDataSetChanged();

            refreshList.setRefreshing(false);
            isLoading = false;
        }


        private List<ListBean> getJsonData(String url) {
            isLoading = true;
            String jsonString = null;
            try {
                jsonString = readStream(new URL(url).openStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
            Gson gson = new Gson();
            ListBeanList listBeenList;
            listBeenList = gson.fromJson(jsonString, ListBeanList.class);

            dataToShow.addAll(listBeenList.data);
            return dataToShow;

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
}

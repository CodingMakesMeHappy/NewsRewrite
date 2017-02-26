package com.example.hp.newsrewrite;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    public int page = 1;
    public final static String urlHead = "http://open.twtstudio.com/api/v1/news/1/page/";
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView)findViewById(R.id.list);
        new NewsAsyncTask(MainActivity.this).execute(urlHead + String.valueOf(page));

    }


}

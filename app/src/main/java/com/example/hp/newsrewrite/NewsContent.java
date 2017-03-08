package com.example.hp.newsrewrite;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;


public class NewsContent extends Activity {

    TextView content_title;
    WebView story;
    TextView news_come, gonggao, shengao, sheying;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_layout);


        content_title = (TextView) findViewById(R.id.content_title);
        story = (WebView) findViewById(R.id.story);
        news_come = (TextView) findViewById(R.id.news_come);
        gonggao = (TextView) findViewById(R.id.gonggao);
        shengao = (TextView) findViewById(R.id.shengao);
        sheying = (TextView) findViewById(R.id.sheying);


        Bundle bundle = getIntent().getExtras();
        int index = bundle.getInt("index");
        String contentUrl = "http://open.twtstudio.com/api/v1/news/" + String.valueOf(index);
        System.out.print("aaabbbb" + index);

        new ContentAsyncTask().execute(contentUrl);
    }

    private class ContentAsyncTask extends AsyncTask<String, Void, ContentBean.ContentDataBean> {


        @Override
        protected ContentBean.ContentDataBean doInBackground(String... params) {
            return getJsonData(params[0]);
        }

        @Override
        protected void onPostExecute(ContentBean.ContentDataBean contentDataBean) {
            super.onPostExecute(contentDataBean);
            content_title.setText(contentDataBean.subject);
            story.loadDataWithBaseURL(null, contentDataBean.content, "text/html", "utf-8", null);
            news_come.setText("来源:" + contentDataBean.newscome);
            gonggao.setText("供稿:" + contentDataBean.gonggao);
            if (contentDataBean.shenggao != null) {
                shengao.setText("审稿:" + contentDataBean.shenggao);
            }
            if (contentDataBean.sheying != "") {
                sheying.setText("摄影:" + contentDataBean.sheying);
            }
        }


        private ContentBean.ContentDataBean getJsonData(String url) {

            String jsonString = null;
            try {
                jsonString = readStream(new URL(url).openStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
            Gson gson = new Gson();
            ContentBean contentBean;
            contentBean = gson.fromJson(jsonString, ContentBean.class);
            System.out.println("aadaa");
            System.out.println("abcabc" + jsonString);
            Log.d("tagg", "getJsonData: ");
            Log.d("tagg", "getJsonData: " + contentBean.error_code);
            return contentBean.data;

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

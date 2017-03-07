package com.example.hp.newsrewrite;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by HP on 2017/3/1.
 */

public class NewsContent extends Activity {

    TextView content_title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_layout);
        content_title = (TextView)findViewById(R.id.content_title);
        Bundle bundle=getIntent().getExtras();
        int index = bundle.getInt("index");
        String storyUrl = "http://open.twtstudio.com/api/v1/news/" + String.valueOf(index);

    }

    class contentAsyncTask extends AsyncTask<String, Void, ContentBean>{

        @Override
        protected ContentBean doInBackground(String... params) {
            return null;
        }
        @Override
        protected void onPostExecute(ContentBean contentBean){
            super.onPostExecute(contentBean);

        }


    }
}

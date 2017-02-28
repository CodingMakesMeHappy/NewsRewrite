package com.example.hp.newsrewrite;

import android.app.Activity;
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
        String index = bundle.getString("index");
        content_title.setText(index);
    }
}

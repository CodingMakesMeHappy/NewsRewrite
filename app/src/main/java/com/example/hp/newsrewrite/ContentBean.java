package com.example.hp.newsrewrite;

/**
 * Created by HP on 2017/3/2.
 */

public class ContentBean {
    int error_code;
    String message;
    ContentDataBean data;

    class ContentDataBean {
        String subject;
        String content;
        String newscome;
        String gonggao;
        String shenggao;
        String sheying;
        int visitcount;
    }
}

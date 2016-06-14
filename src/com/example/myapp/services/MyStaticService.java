package com.example.myapp.services;

import android.app.IntentService;
import android.content.Intent;

/**
 * Created by 李钊颖 on 2016/5/24.
 */
public class MyStaticService extends IntentService {
    public static final String ACTION="android.intent.action.MY_STATIC_ACTION";

    public MyStaticService() {
        super("");
    }

    public MyStaticService(String name) {
        super(name);
    }
    @Override
    protected void onHandleIntent(Intent intent) {
        Intent i=new Intent(ACTION);
        sendBroadcast(i);
    }
}

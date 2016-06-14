package com.example.myapp.services;

import android.app.IntentService;
import android.content.Intent;

/**
 * Created by 李钊颖 on 2016/5/24.
 */
public class MyOrderService extends IntentService {
    public static final String ACTION="android.intent.action.MY_ORDER_ACTION";

    public MyOrderService() {
        super("");
    }

    public MyOrderService(String name) {
        super(name);
    }
    @Override
    protected void onHandleIntent(Intent intent) {
        String msg=intent.getStringExtra("msg");
        Intent i=new Intent(ACTION);
        i.putExtra("msg",msg);
        sendOrderedBroadcast(i,"com.my.order.receiver.permission");
    }
}

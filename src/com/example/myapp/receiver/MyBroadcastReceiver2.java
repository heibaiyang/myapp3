package com.example.myapp.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by 李钊颖 on 2016/5/23.
 */
public class MyBroadcastReceiver2 extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.v("r2",intent.getStringExtra("mykey"));
    }
}

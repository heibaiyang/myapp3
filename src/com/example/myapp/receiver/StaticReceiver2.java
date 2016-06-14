package com.example.myapp.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by 李钊颖 on 2016/5/24.
 */
public class StaticReceiver2 extends BroadcastReceiver {
    private static String TAG="这是静态注册";
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.v(TAG,"这是Receiver2");
    }
}

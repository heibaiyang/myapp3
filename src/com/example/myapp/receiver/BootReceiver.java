package com.example.myapp.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by 李钊颖 on 2016/5/24.
 */
public class BootReceiver extends BroadcastReceiver {
    private static String TAG="bootReceiver";
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.v(TAG,"启动推送消息");
    }
}

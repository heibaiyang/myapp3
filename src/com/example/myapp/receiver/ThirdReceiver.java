package com.example.myapp.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by 李钊颖 on 2016/5/24.
 */
public class ThirdReceiver extends BroadcastReceiver {
    private static String TAG="thirdReceiver";
    @Override
    public void onReceive(Context context, Intent intent) {
        String msg=getResultExtras(true).getString("msg");
        Log.v(TAG,msg);
    }
}

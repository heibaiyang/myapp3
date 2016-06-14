package com.example.myapp.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by 李钊颖 on 2016/5/24.
 */
public class FirstReceiver extends BroadcastReceiver {
    private static String TAG="firstReceiver";
    @Override
    public void onReceive(Context context, Intent intent) {
        String msg=intent.getStringExtra("msg");
        Log.v(TAG,msg);

        Bundle bundle=new Bundle();
        bundle.putString("msg",msg+" "+TAG);
        setResultExtras(bundle);
    }
}

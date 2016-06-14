package com.example.myapp.services;

import android.app.Activity;
import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import com.example.myapp.utils.HttpUtils;

/**
 * Created by 李钊颖 on 2016/5/18.
 */
public class MyTestService extends IntentService {

    public static final String ACTION="jsj.gdgm.edu";

    public MyTestService() {
        super("");
    }

    public MyTestService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        //Service-ResultReceiver的代码
        /*String json= HttpUtils.getStringBuffer(intent.getStringExtra("url")).toString();
        Log.v("json-service",json);
        ResultReceiver receiver=(ResultReceiver) intent.getExtras().get("myReceiver");
        Bundle bundle=new Bundle();
        bundle.putString("myjson",json);
        if(receiver!=null)
            receiver.send(Activity.RESULT_OK,bundle);*/


        //12-04
        /*String json= HttpUtils.getStringBuffer(intent.getStringExtra("url")).toString();
        Log.v("json-service",json);
        Intent i=new Intent(ACTION);
        i.putExtra("myjson",json);
        LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(i);*/

        Intent i=new Intent(ACTION);
        int k=1;
        while(k<=100)
        {
            i.putExtra("mykey","value"+(k++));
            LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

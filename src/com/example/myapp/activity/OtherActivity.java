package com.example.myapp.activity;

import android.app.Activity;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import com.example.myapp.R;
import com.example.myapp.receiver.MyBroadcastReceiver2;
import com.example.myapp.services.MyTestService;

/**
 * Created by 李钊颖 on 2016/5/23.
 */
public class OtherActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.other_view);
    }

    MyBroadcastReceiver2 r2=new MyBroadcastReceiver2();
    @Override
    protected void onResume() {
        super.onResume();
        Log.v("activity","onResume");
        IntentFilter filter=new IntentFilter(MyTestService.ACTION);
        LocalBroadcastManager.getInstance(getApplicationContext()).registerReceiver(r2,filter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.v("activity","onPause");
        LocalBroadcastManager.getInstance(getApplicationContext()).unregisterReceiver(r2);
    }
}

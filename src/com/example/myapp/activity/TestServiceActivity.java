package com.example.myapp.activity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import com.example.myapp.R;
import com.example.myapp.receiver.MyBroadcastReceiver1;
import com.example.myapp.services.MyTestService;

/**
 * Created by 李钊颖 on 2016/5/18.
 */
public class TestServiceActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.service_view);
        final Button btn_start_service=(Button)findViewById(R.id.btn_start_service);
        btn_start_service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*new Thread(new Runnable(){
                    public void run() {
                        try {
                            Thread.sleep(5000);
                            btn_start_service.setText("Service");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();*/

                launchMyService();
                //btn_start_service.setText("Service");
            }
        });

        final Button btn_go=(Button)findViewById(R.id.btn_go);
        btn_go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TestServiceActivity.this,OtherActivity.class));
            }
        });
    }

    protected  void launchMyService(){
        //Service-ResultReceiver的代码
        /*Intent intent=new Intent(getApplicationContext(), MyTestService.class);
        intent.putExtra("url","http://52.38.232.240:8080/jfinal_gradle/users");
        intent.putExtra("myReceiver",receiver);
        startService(intent);*/


        //12-04
        /*Intent intent=new Intent(getApplicationContext(), MyTestService.class);
        intent.putExtra("url","http://52.38.232.240:8080/jfinal_gradle/users");
        startService(intent);*/

        Intent intent=new Intent(this, MyTestService.class);
        startService(intent);
    }

    MyBroadcastReceiver1 r1=new MyBroadcastReceiver1();

    //12-04
    /*Handler handler=new Handler();

    //Service-ResultReceiver的代码
    *//*private ResultReceiver receiver=new ResultReceiver(handler){
        @Override
        protected void onReceiveResult(int resultCode, Bundle resultData) {
            if(resultCode==RESULT_OK)
                Log.v("activity-json",resultData.getString("myjson","error"));
        }
    };*//*

    BroadcastReceiver receiver=new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.v("activity-json",intent.getStringExtra("myjson"));
        }
    };*/



    @Override
    protected void onResume() {
        super.onResume();
        Log.v("activity","onResume");
        IntentFilter filter=new IntentFilter(MyTestService.ACTION);
        LocalBroadcastManager.getInstance(getApplicationContext()).registerReceiver(r1,filter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.v("activity","onPause");
        LocalBroadcastManager.getInstance(getApplicationContext()).unregisterReceiver(r1);
    }
}

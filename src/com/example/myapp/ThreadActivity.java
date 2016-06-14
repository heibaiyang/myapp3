package com.example.myapp;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import com.example.myapp.R;

/**
 * Created by 李钊颖 on 2016/5/3.
 */
public class ThreadActivity extends Activity{
    Thread thread;
    int count=0;
    boolean bol=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thread_test);

        Button btnStart=(Button)findViewById(R.id.btnStart);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                thread=new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while(bol){
                            Log.v("count=",String.valueOf(count++));
                            try {
                                thread.sleep(2000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
                thread.start();
            }
        });

        Button btnStop=(Button)findViewById(R.id.btnStop);
        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bol=false;
            }
        });

    }
}

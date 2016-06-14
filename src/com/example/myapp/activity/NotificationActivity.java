package com.example.myapp.activity;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.view.View;
import android.widget.Button;
import com.example.myapp.NetworkActivity;
import com.example.myapp.R;

/**
 * Created by 李钊颖 on 2016/5/25.
 */
public class NotificationActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.service_view);
        Button btn_send=(Button)findViewById(R.id.btn_start_service);
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplication(), NetworkActivity.class);

                NotificationCompat.Builder builder=new NotificationCompat.Builder(getApplicationContext())
                        .setSmallIcon(R.drawable.ic_launcher)
                        .setContentTitle("通知")
                        .setContentText("通知内容")
                        .setAutoCancel(true);
                NotificationManager manager=(NotificationManager) getApplicationContext().getSystemService(getApplicationContext().NOTIFICATION_SERVICE);
                manager.notify(1,builder.build());
            }
        });
    }
}

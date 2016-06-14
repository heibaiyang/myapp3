package com.example.myapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.example.myapp.R;
import com.example.myapp.services.MyOrderService;
import com.example.myapp.services.MyStaticService;

/**
 * Created by 李钊颖 on 2016/5/24.
 */
public class OrderActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.service_view);
        final Button btn_start_service=(Button)findViewById(R.id.btn_start_service);
        btn_start_service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchMyService();
            }
        });
    }

    protected  void launchMyService(){
        Intent intent=new Intent(OrderActivity.this, MyOrderService.class);
        intent.putExtra("msg","这是发送的有序广播内容");
        startService(intent);
    }
}

package com.example.myapp.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import com.example.myapp.R;
import okhttp3.*;

import java.io.IOException;

/**
 * Created by 李钊颖 on 2016/5/31.
 */
public class OkhttpActvity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.service_view);
        final Button btn_start_service=(Button)findViewById(R.id.btn_start_service);
        btn_start_service.setText("获取数据");

        /*get*/
        /*final String url="http://52.38.232.240:8080/jfinal_gradle/sendget1?uuid=1001&userPwd=1234";
        btn_start_service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OkHttpClient client=new OkHttpClient();
                Request request=new Request.Builder()
                        .url(url)
                        .build();
                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Log.v("failure","获取数据失败！");
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        Log.v("success",response.body().string());
                    }
                });
            }
        });*/

        /*post*/
        final String url="http://52.38.232.240:8080/jfinal_gradle/sendpost";
        final String uuid="1001";
        final String userPwd="1234";
        btn_start_service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OkHttpClient client=new OkHttpClient();
                RequestBody body=new FormBody.Builder()
                        .add("uuid",uuid)
                        .add("userPwd",userPwd)
                        .build();
                Request request=new Request.Builder()
                        .url(url)
                        .post(body)
                        .build();
                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Log.v("failure","获取数据失败！");
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        Log.v("success",response.body().string());
                    }
                });
            }
        });
    }
}

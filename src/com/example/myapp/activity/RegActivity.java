package com.example.myapp.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.myapp.R;
import com.google.gson.Gson;
import okhttp3.*;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 李钊颖 on 2016/6/8.
 */
public class RegActivity extends Activity {
    private EditText et_id,et_name,et_role,et_pwd,et_cpwd;
    private Button btn_save,btn_reset;
    Handler handler=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        handler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                if(msg.what==0x110)
                {
                    String json=msg.obj.toString();
                    Map<String,Object> map=new HashMap<>();
                    Gson gson=new Gson();
                    map=gson.fromJson(json,map.getClass());
                    if(map!=null&&(boolean)map.get("success"))
                    {
                        Toast.makeText(RegActivity.this, map.get("msg").toString(), Toast.LENGTH_SHORT).show();
                        finish();
                    }
                    else
                    {
                        Toast.makeText(RegActivity.this, map.get("msg").toString(), Toast.LENGTH_SHORT).show();
                    }
                }

            }
        };

        et_id=(EditText) findViewById(R.id.et_reg_id);
        et_name=(EditText) findViewById(R.id.et_reg_name);
        et_role=(EditText) findViewById(R.id.et_reg_role);
        et_pwd=(EditText) findViewById(R.id.et_reg_pwd);
        et_cpwd=(EditText) findViewById(R.id.et_reg_cpwd);
        btn_save=(Button) findViewById(R.id.btn_reg_save);
        btn_reset=(Button) findViewById(R.id.btn_reg_reset);

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id=et_id.getText().toString().trim();
                String name=et_name.getText().toString().trim();
                String role=et_role.getText().toString().trim();
                String pwd=et_pwd.getText().toString().trim();
                String cpwd=et_cpwd.getText().toString().trim();

                if(StringUtils.isBlank(id))
                {
                    Toast.makeText(RegActivity.this, "id不能为空！", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(StringUtils.isBlank(pwd))
                {
                    Toast.makeText(RegActivity.this, "密码不能为空！", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!pwd.equals(cpwd))
                {
                    Toast.makeText(RegActivity.this, "两次输入的密码必须一致！", Toast.LENGTH_SHORT).show();
                    return;
                }

                String url="http://52.38.232.240:8080/jfinal_gradle/apkusers/save";
                OkHttpClient client=new OkHttpClient();
                RequestBody body=new FormBody.Builder()
                        .add("users.userid",id)
                        .add("users.username",name)
                        .add("users.userrole",role)
                        .add("users.userpwd",pwd)
                        .build();
                Request request=new Request.Builder()
                        .url(url)
                        .post(body)
                        .build();

                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Log.v("filure","获取数据失败");
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        Message msg=new Message();
                        msg.what=0x110;
                        msg.obj=response.body().string();
                        handler.sendMessage(msg);
                    }
                });
            }
        });

        btn_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                et_id.setText("");
                et_name.setText("");
                et_role.setText("");
                et_pwd.setText("");
                et_cpwd.setText("");
            }
        });
    }
}

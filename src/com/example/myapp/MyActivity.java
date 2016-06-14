package com.example.myapp;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.myapp.data.UsersData;
import com.example.myapp.models.Employes;
import com.example.myapp.models.Users;
import com.example.myapp.utils.JsonUtils;
import com.google.gson.Gson;

import java.util.LinkedHashMap;
import java.util.Map;

public class MyActivity extends Activity {
    Button btn_skip, btn_reg;
    EditText et_id, et_pwd;
    TextView tv_count;
    int count=10;
    Handler handler;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        btn_skip = (Button) findViewById(R.id.btn_skip);//跳过按钮
        btn_reg = (Button) findViewById(R.id.btn_reg);//注册按钮
        et_id = (EditText) findViewById(R.id.et_id);
        et_pwd = (EditText) findViewById(R.id.et_pwd);
        /*//获取本地数据
        SharedPreferences setting=getApplicationContext().getSharedPreferences("settings",MODE_PRIVATE);
        String id=setting.getString("id","null");//第二个string为默认值
        String pwd=setting.getString("pwd","null");
        et_id.setText(id);et_pwd.setText(pwd);
        //获取所有数据
        Map<String,?> map=new LinkedHashMap<>();
        map=setting.getAll();
        for(String key:map.keySet())
        {
            Log.v("key",key);
        }*/
        btn_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Users users = new Users("1003", "李四", "12345");
               /* //如果使用类似这种创建对象的方式，容易导致内存泄露
                UsersData usersData=new UsersData(getApplicationContext());
                UsersData usersData1=new UsersData(getApplicationContext());*/
                //采用单例模式
                UsersData myUsers = UsersData.getMyInstance(getApplicationContext());
                long flag = myUsers.add(users);
                if (flag > 0) {
                    Toast.makeText(MyActivity.this, "成功！" + flag, Toast.LENGTH_SHORT).show();
                }

                /*String json= JsonUtils.objectToJson(users);
                Log.v("json",json);

                Users u=new Users();
                u=(Users) JsonUtils.jsonToObject(json,Users.class);
                Log.v("users",u.getUid()+"/"+u.getUname()+"/"+u.getUpwd());

                Employes employes=new Employes("1002","李四");
                String eJson= JsonUtils.objectToJson(employes);
                Log.v("json",eJson);

                Employes employes1=new Employes();
                employes1=(Employes) JsonUtils.jsonToObject(eJson,Employes.class);
                Log.v("employes1",employes1.getId()+"%"+employes1.getName());*/

                /*//存储数据到本地文件
                SharedPreferences setting=getApplicationContext().getSharedPreferences("settings",MODE_PRIVATE);//"settings"是文件名
                SharedPreferences.Editor editor=setting.edit();//打开编辑器
                editor.putString("id",et_id.getText().toString());
                editor.putString("pwd",et_pwd.getText().toString());
                editor.commit();//保存*/

            }
        });

        tv_count=(TextView)findViewById(R.id.tv_count);
        handler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                if(msg.what==0x110)
                {
                    tv_count.setText(String.valueOf(count));
                }
            }
        };

        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    while(count>0)
                    {
                        handler.sendEmptyMessage(0x110);
                        Thread.sleep(1000);
                        count--;
                    }
                    Intent intent = new Intent(MyActivity.this, Main1Activity.class);
                    startActivity(intent);
                    finish();
                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        });
        thread.start();

        //跳过按钮
        btn_skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyActivity.this, Main1Activity.class);
                startActivity(intent);
            }
        });
    }
}

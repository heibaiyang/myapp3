package com.example.myapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.myapp.Main1Activity;
import com.example.myapp.R;
import com.example.myapp.models.ResponseData;
import com.example.myapp.utils.HttpUtils;
import com.example.myapp.utils.JsonUtils;
import okhttp3.*;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.ResponseDate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 李钊颖 on 2016/5/31.
 */
public class LoginActivity extends Activity {
    EditText et_uuid,et_userPwd;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_view);



        et_uuid=(EditText) findViewById(R.id.et_login_id);
        et_userPwd=(EditText) findViewById(R.id.et_login_pwd);

        final Button btn_login=(Button)findViewById(R.id.btn_login);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uuid=et_uuid.getText().toString().trim();
                String userPwd=et_userPwd.getText().toString().trim();
                String url="http://52.38.232.240:8080/jfinal_gradle/sendpost";
                new PostTask().execute(url,uuid,userPwd);
            }
        });
    }

    class PostTask extends AsyncTask<String,Integer,String>
    {
        @Override
        protected String doInBackground(String... strings) {
            List<NameValuePair> params=new ArrayList<>();
            NameValuePair nvp1=new BasicNameValuePair("uuid",strings[1]);
            NameValuePair nvp2=new BasicNameValuePair("userPwd",strings[2]);
            params.add(nvp1);
            params.add(nvp2);
            return HttpUtils.getString(strings[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if(s!=null&&!s.isEmpty())
            {
                ResponseData rd=(ResponseData) JsonUtils.jsonToObject(s,ResponseData.class);
                if(rd.getSuccess())
                {
                    Intent intent=new Intent(LoginActivity.this,Main1Activity.class);
                    startActivity(intent);
                    finish();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),rd.getMsg(),Toast.LENGTH_LONG).show();
                }
            }
        }
    }

}

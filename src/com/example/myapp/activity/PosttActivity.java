package com.example.myapp.activity;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.example.myapp.R;
import com.example.myapp.utils.HttpUtils;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 李钊颖 on 2016/5/30.
 */
public class PosttActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.service_view);
        final Button btn_start_service=(Button)findViewById(R.id.btn_start_service);
        btn_start_service.setText("获取数据");
        final String url="http://52.38.232.240:8080/jfinal_gradle/sendget1?uuid=1001&userPwd=1234";
        final String uuid="1001";
        final String userPwd="1234";

        btn_start_service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
            Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();
        }
    }
}

package com.example.myapp;

import android.app.Activity;
import android.os.*;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

/**
 * Created by 李钊颖 on 2016/5/5.
 */
public class LoadingActivity extends Activity {
    ProgressBar bar_load;
    Button btn_load;
    Handler handler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loading);

        bar_load=(ProgressBar)findViewById(R.id.bar_load);
        btn_load=(Button)findViewById(R.id.btn_load);


        btn_load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new ProgressAsyncTask().execute();

            }
        });
    }

    class ProgressAsyncTask extends AsyncTask<String,Integer,String>
    {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            bar_load.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String... strings) {
            int progress=0;
            while(true)
            {
                int random=(int)(Math.random()*10);
                progress+=random;
                publishProgress(progress);
                if(progress>=100)
                    break;
                SystemClock.sleep(1000);
            }
            return "success";
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            bar_load.setProgress(values[0]);
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            if(result=="success")
            {
                Toast.makeText(LoadingActivity.this,"加载完成",Toast.LENGTH_LONG).show();
            }
        }


    }
}

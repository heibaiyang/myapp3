package com.example.myapp.activity;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RemoteViews;
import android.widget.Toast;
import com.example.myapp.NetworkActivity;
import com.example.myapp.R;

/**
 * Created by 李钊颖 on 2016/5/5.
 */
public class DownLoadActivity extends Activity {
    public ProgressBar bar_download,pb_download;
    Button btn_download;
    RemoteViews rv;
    NotificationCompat.Builder builder;
    NotificationManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.download_view);

        bar_download=(ProgressBar)findViewById(R.id.bar_download);
        btn_download=(Button)findViewById(R.id.btn_start_download);
        pb_download=(ProgressBar)findViewById(R.id.pb_download);

        btn_download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                builder=new NotificationCompat.Builder(getApplicationContext())
                        .setSmallIcon(R.drawable.ic_launcher)
                        .setAutoCancel(true);
                rv=new RemoteViews(getApplicationContext().getPackageName(),R.layout.inform_view);
                rv.setImageViewResource(R.id.inform_img,R.drawable.ic_launcher);
                rv.setTextViewText(R.id.inform_title,"正在下载");
                rv.setProgressBar(R.id.pb_download,100,0,false);
                builder.setContent(rv);
                manager=(NotificationManager) getApplicationContext().getSystemService(getApplicationContext().NOTIFICATION_SERVICE);
                manager.notify(1,builder.build());

                new ProgressAsyncTask().execute();
            }
        });

    }

    class ProgressAsyncTask extends AsyncTask<String,Integer,String>
    {
        public int progress=0;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            bar_download.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String... strings) {

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
            bar_download.setProgress(values[0]);

            rv.setProgressBar(R.id.pb_download,100,values[0],false);
            builder.setContent(rv);
            manager.notify(1,builder.build());

        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            if(result=="success")
            {
                Toast.makeText(DownLoadActivity.this,"下载完成",Toast.LENGTH_LONG).show();

                Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), notification);
                r.play();

                Intent intent=new Intent(getApplication(), DownLoadActivity.class);
                PendingIntent pendingIntent=
                        PendingIntent.getActivity(getApplicationContext(),0x100,intent,PendingIntent.FLAG_CANCEL_CURRENT);
                builder.setContentIntent(pendingIntent);

                rv.setTextViewText(R.id.inform_title,"下载完成");

                builder.setContent(rv);
                manager.notify(1,builder.build());
            }
        }


    }
}

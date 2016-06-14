package com.example.myapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import okhttp3.OkHttpClient;
import okhttp3.Request;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by 李钊颖 on 2016/5/9.
 */
public class NetworkActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.network);
        final ImageView img_network=(ImageView)findViewById(R.id.img_network);
        final ProgressBar pb_load=(ProgressBar)findViewById(R.id.pb_load);


        ConnectInternet connectInternet=new ConnectInternet();
        if(connectInternet.isNetworkConnected(getApplicationContext()))
        {
            Toast.makeText(getApplicationContext(),"当前网络可用",Toast.LENGTH_LONG).show();
        }
        else
        {
            //Toast.makeText(getApplicationContext(),"当前网络不可用",Toast.LENGTH_LONG).show();

            Dialog dialog=new AlertDialog.Builder(NetworkActivity.this)
                    .setTitle("网络问题")
                    .setMessage("是否打开网络设置")
                    .setIcon(R.drawable.ic_launcher)
                    .setCancelable(false)
                    .setNegativeButton("否", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    })
                    .setPositiveButton("是", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent intent=new Intent(Settings.ACTION_SETTINGS);
                            startActivity(intent);
                        }
                    }).create();
            dialog.show();



            return;
        }

        final String url="https://i.imgur.com/tGbaZCY.jpg";


        Button btn_load=(Button)findViewById(R.id.btn_load);
        btn_load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pb_load.setVisibility(View.VISIBLE);
                Picasso.with(getApplicationContext()).load(url).into(img_network, new Callback() {
                    @Override
                    public void onSuccess() {
                        if(pb_load!=null)
                        {
                            pb_load.setVisibility(View.GONE);
                        }
                    }

                    @Override
                    public void onError() {

                    }
                });
                //new NetworkAsyncTask(img_network,pb_load).execute(url);
            }
        });
    }

    class ConnectInternet
    {
        public void ContectInternet()
        {

        }

        public boolean isNetworkConnected(Context context)
        {
            ConnectivityManager cm=(ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo info=cm.getActiveNetworkInfo();
            if(info!=null)
            {
                return info.isAvailable();
            }
            return false;
        }
    }

    class NetworkAsyncTask extends AsyncTask<String,Integer,Bitmap>
    {
        ImageView myImg;
        ProgressBar pb_load;
        public NetworkAsyncTask(ImageView myImg,ProgressBar pb_load) {
            this.myImg=myImg;
            this.pb_load=pb_load;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pb_load.setVisibility(View.VISIBLE);
        }

        @Override
        protected Bitmap doInBackground(String... addresses) {
            Bitmap bitmap=null;
            InputStream is=null;
            OutputStream os=null;

            try{
                URL url=new URL(addresses[0]);
                HttpURLConnection connection=(HttpURLConnection) url.openConnection();
                is=connection.getInputStream();
                ByteArrayOutputStream bos=new ByteArrayOutputStream();
                os=new BufferedOutputStream(bos);
                int total=connection.getContentLength();
                int current=0;
                int read=0;
                byte[] data=new byte[1024];
                while((read=is.read(data))!=-1)
                {
                    os.write(data,0,read);
                    current=current+read;
                    int progress=(int)((float)current/total*100);
                    publishProgress(progress);
                }
                os.flush();
                byte[] bitmapdata=bos.toByteArray();
                bitmap=BitmapFactory.decodeByteArray(bitmapdata,0,bitmapdata.length);
                //bitmap= BitmapFactory.decodeStream(is);
            }catch (IOException e)
            {
                e.printStackTrace();
            }finally {
                if(is!=null)
                {
                    try
                    {
                        is.close();
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
            return bitmap;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            pb_load.setProgress(values[0]);
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            myImg.setImageBitmap(bitmap);
            pb_load.setVisibility(View.GONE);
        }
    }
}

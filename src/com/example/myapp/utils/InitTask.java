package com.example.myapp.utils;

import android.app.Dialog;
import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import com.example.myapp.R;
import com.example.myapp.adapters.UsersAdapter;
import com.example.myapp.models.Users;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 李钊颖 on 2016/5/12.
 */
public class InitTask extends AsyncTask<String,String,String>
{
    ListView lv_faxian;
    ProgressBar pb_load;
    Context context;

    int dataFlag;
    Dialog dialog;

    public InitTask(Context context,ProgressBar pb_load,ListView lv_faxian,int dataFlag,Dialog dialog) {
        this.context=context;
        this.pb_load=pb_load;
        this.lv_faxian=lv_faxian;
        this.dataFlag=dataFlag;
        this.dialog=dialog;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        //pb_load.setVisibility(View.VISIBLE);

        if(dialog!=null)
            dialog.show();
    }

    @Override
    protected String doInBackground(String... addresses) {
        String resultStr = HttpUtils.getString(addresses[0]);

        return resultStr;
    }



    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        InitAdapter initAdapter=new InitAdapter();
        initAdapter.initUserData(context,lv_faxian,result);
        //pb_load.setVisibility(View.GONE);

        if(dialog!=null&&dialog.isShowing())
            dialog.dismiss();
    }
}

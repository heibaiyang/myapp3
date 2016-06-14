package com.example.myapp.fragments;

import android.app.Dialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.example.myapp.R;
import com.example.myapp.adapters.UsersAdapter;
import com.example.myapp.models.Users;
import com.example.myapp.utils.DialogUtils;
import com.example.myapp.utils.HttpUtils;
import com.example.myapp.utils.InitTask;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 李钊颖 on 2016/3/30.
 */
public class FragmentFaxian extends Fragment {

    ListView lv_faxian;
    ProgressBar pb_load;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.faxian, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        /*Toast.makeText(getActivity(),getArguments().getString("key1","")+", "+getArguments().getInt("key2",0),Toast.LENGTH_LONG).show();*/
        lv_faxian=(ListView)view.findViewById(R.id.lv_faxian);
        pb_load=(ProgressBar)view.findViewById(R.id.pb_load);

        Dialog dialog= DialogUtils.getLoadingDialog(getActivity());

        String url="http://52.38.232.240:8080/jfinal_gradle/users";
        new InitTask(getActivity(),pb_load,lv_faxian,1,dialog).execute(url);

        /*List<Users> list=new ArrayList<>();
        for(int i=1;i<=100;i++)
        {
            Users users=new Users();
            users.setUid("100"+i);
            users.setUname("name"+i);
            users.setUpwd("pwd"+i);
            list.add(users);
        }

        ArrayAdapter<Users> adapter=new UsersAdapter(getActivity(),R.layout.faxian_item,list);
        lv_faxian.setAdapter(adapter);*/
    }


}
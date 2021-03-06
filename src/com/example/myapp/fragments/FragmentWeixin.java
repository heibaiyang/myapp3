package com.example.myapp.fragments;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.example.myapp.R;

import java.util.ArrayList;
import java.util.List;


public class FragmentWeixin extends Fragment {

    private ListView lv_weixin;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.weixin, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        lv_weixin=(ListView)view.findViewById(R.id.lv_weixin);

        //组织数据
        Resources res=getResources();
        String[]list=res.getStringArray(R.array.myarray);


       /* String[]list=new String[20];
        for(int i=0;i<20;i++)
        {
            list[i]="李"+(i+1);
        }*/


        /*List<String> list=new ArrayList<>();
        for(int i=1;i<=20;i++)
        {
            list.add("张"+i);
        }*/

        //创建ArrayAdapter对象
        //ArrayAdapter<String> adapter=new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,list);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(getActivity(),R.layout.weixin_item,R.id.tv_show,list);
        lv_weixin.setAdapter(adapter);
    }


}

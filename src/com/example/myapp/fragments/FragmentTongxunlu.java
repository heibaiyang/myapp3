package com.example.myapp.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import com.example.myapp.R;
import com.example.myapp.adapters.ContactAdapter;
import com.example.myapp.models.Contact;
import com.example.myapp.myviews.SideIndex;
import com.example.myapp.utils.MyComparator;

import java.util.*;

/**
 * Created by 李钊颖 on 2016/3/30.
 */
public class FragmentTongxunlu extends Fragment {
    private ListView lv_tongxunlu;
    SideIndex sideIndex;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.tongxunlu, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        lv_tongxunlu=(ListView)view.findViewById(R.id.lv_tongxunlu);
        sideIndex=(SideIndex)view.findViewById(R.id.sideIndex);
        Random random=new Random();
        String str=new String("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        List<Contact> list=new ArrayList<>();
        for(int i=0;i<100;i++)
        {
            char ch=str.charAt(random.nextInt(26));
            Contact contact=new Contact(ch+"",""+ch+i);
            list.add(contact);
        }
        Collections.sort(list,new MyComparator());
        ContactAdapter adapter=new ContactAdapter(getActivity(),list);
        lv_tongxunlu.setAdapter(adapter);
        sideIndex.setListView(lv_tongxunlu);

        /*
        SimpleAdapter

        lv_tongxunlu=(ListView)view.findViewById(R.id.lv_tongxunlu);

        List<Map<String,String>> data=new ArrayList<>();
        //组织数据
        for(int i=1;i<=10;i++)
        {
            Map<String,String> map=new HashMap<>();
            map.put("img",String.valueOf(R.drawable.ic_launcher));
            map.put("name","name"+i);
            map.put("phone","1234567"+i);
            data.add(map);
        }
        //创建from数组
        String[] from=new String[]{"img","name","phone"};
        //定义to整型数组
        int[] to=new int[]{R.id.img_contact,R.id.tv_name,R.id.tv_phone};


        //创建SimpleAdapter对象
        SimpleAdapter adapter=new SimpleAdapter(getActivity(),data,R.layout.tongxunlu_item,from,to);
        //ListView指定数据源
        lv_tongxunlu.setAdapter(adapter);*/



    }
}
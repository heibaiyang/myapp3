package com.example.myapp;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.RadioGroup;
import android.widget.Toast;
import com.example.myapp.fragments.FragmentFaxian;
import com.example.myapp.fragments.FragmentTongxunlu;
import com.example.myapp.fragments.FragmentWeixin;
import com.example.myapp.fragments.FragmentWo;
import okhttp3.*;

import java.io.IOException;


public class Main1Activity extends FragmentActivity implements RadioGroup.OnCheckedChangeListener {
    FragmentWeixin weixin = null;
    FragmentTongxunlu tongxunlu = null;
    FragmentFaxian faxian = null;
    FragmentWo wo = null;
    //FrameLayout fl_container;
    RadioGroup radioGroup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main1);



        ActionBar actionBar=getActionBar();
        actionBar.setTitle("微信");

        //fl_container=(FrameLayout)findViewById(R.id.fl_container);
        radioGroup = (RadioGroup) findViewById(R.id.rg);
        radioGroup.setOnCheckedChangeListener(this );

        weixin = new FragmentWeixin();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

        if (weixin.isAdded()) {
            ft.show(weixin).commit();
        } else {
            ft.add(R.id.fl_container, weixin).commit();
        }

        //ft.replace(R.id.fl_container,weixin,"weixin").commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        if(item.getItemId()==R.id.menu_change_pwd)
        {
            Toast.makeText(Main1Activity.this, "修改密码", Toast.LENGTH_SHORT).show();
        }
        if(item.getItemId()==R.id.menu_change_other)
        {

            Toast.makeText(Main1Activity.this, "修改其它", Toast.LENGTH_SHORT).show();

        }
        return super.onMenuItemSelected(featureId, item);
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int id) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.setCustomAnimations(R.anim.left_in,R.anim.right_out);
        if (weixin != null) {
            if (weixin.isAdded())
                ft.hide(weixin);
        }
        if (tongxunlu != null) {
            if (tongxunlu.isAdded())
                ft.hide(tongxunlu);
        }
        if (faxian != null) {
            if (faxian.isAdded())
                ft.hide(faxian);
        }
        if (wo != null) {
            if (wo.isAdded())
                ft.hide(wo);
        }
        if(id==R.id.weixin)
        {
            if(weixin==null)
                weixin=new FragmentWeixin();
            if(weixin.isAdded())
                ft.show(weixin);
            else
                ft.add(R.id.fl_container,weixin);
        }
        if(id==R.id.tongxunlu)
        {
            if(tongxunlu==null)
                tongxunlu=new FragmentTongxunlu();
            if(tongxunlu.isAdded())
                ft.show(tongxunlu);
            else
                ft.add(R.id.fl_container,tongxunlu);
        }
        if(id==R.id.faxian)
        {
            if(faxian==null) {
                faxian = new FragmentFaxian();
                Bundle bundle=new Bundle();
                bundle.putString("key1","传递参数");
                bundle.putInt("key2",110);
                faxian.setArguments(bundle);
            }
            if(faxian.isAdded())
                ft.show(faxian);
            else
                ft.add(R.id.fl_container,faxian);
        }
        if(id==R.id.wo)
        {
            if(wo==null)
                wo=new FragmentWo();
            if(wo.isAdded())
                ft.show(wo);
            else
                ft.add(R.id.fl_container,wo);
        }
        //确保只有一个提交，提交多次会出错
        ft.commit();
        /*if(id==R.id.weixin)
        {
            if(weixin==null)
                weixin=new FragmentWeixin();
            ft.replace(R.id.fl_container,weixin,"weixin").commit();
        }
        if(id==R.id.tongxunlu)
        {
            if(tongxunlu==null)
                tongxunlu=new FragmentTongxunlu();
            ft.replace(R.id.fl_container,tongxunlu,"weixin").commit();
        }
        if(id==R.id.faxian)
        {
            if(faxian==null)
                faxian=new FragmentFaxian();
            ft.replace(R.id.fl_container,faxian,"weixin").commit();
        }
        if(id==R.id.wo)
        {
            if(wo==null)
                wo=new FragmentWo();
            ft.replace(R.id.fl_container,wo,"weixin").commit();
        }*/
    }
}

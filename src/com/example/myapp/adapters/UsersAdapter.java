package com.example.myapp.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.example.myapp.R;
import com.example.myapp.models.Users;

import java.util.List;

/**
 * Created by 李钊颖 on 2016/4/11.
 */
public class UsersAdapter extends ArrayAdapter<Users> {
    private static class ViewHodler{
        TextView uid;
        TextView uname;
        TextView upwd;
    }

    public UsersAdapter(Context context, int resource, List<Users> list) {
        super(context, resource, list);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Users users=getItem(position);
        ViewHodler viewHodler;
        if(convertView==null)
        {
            viewHodler=new ViewHodler();
            LayoutInflater inflater=LayoutInflater.from(getContext());
            convertView=inflater.inflate(R.layout.faxian_item,parent,false);
            viewHodler.uid=(TextView)convertView.findViewById(R.id.tv_id);
            viewHodler.uname=(TextView)convertView.findViewById(R.id.tv_name);
            viewHodler.upwd=(TextView)convertView.findViewById(R.id.tv_pwd);
            convertView.setTag(viewHodler);
        }
        else
        {
            viewHodler=(ViewHodler)convertView.getTag();
        }
        viewHodler.uid.setText(users.getUid());
        viewHodler.uname.setText(users.getUname());
        viewHodler.upwd.setText(users.getUpwd());
        if(position==6)
        {
            viewHodler.uname.setText("随便");
            viewHodler.uname.setTextColor(Color.RED);
        }
        else
        {
            viewHodler.uname.setTextColor(Color.WHITE);
        }

        return convertView;
    }
}

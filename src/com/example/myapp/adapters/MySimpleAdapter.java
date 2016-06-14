package com.example.myapp.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import com.example.myapp.R;

import java.util.List;
import java.util.Map;

/**
 * Created by 李钊颖 on 2016/4/12.
 */
public class MySimpleAdapter extends SimpleAdapter{
    private List<Map<String,String>> data;
    LayoutInflater inflater;
    private static class ViewHodler{
        ImageView img;
        TextView tv_name;
        TextView tv_phone;
    }

    public MySimpleAdapter(Context context, List<Map<String, String>> data, int resource, String[] from, int[] to) {
        super(context, data, resource, from, to);
        this.data=data;
        inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHodler viewHodler;
        if(convertView==null)
        {
            viewHodler=new ViewHodler();
            convertView=inflater.inflate(R.layout.wo_item,parent,false);
            viewHodler.img=(ImageView)convertView.findViewById(R.id.img_contact);
            viewHodler.tv_name=(TextView)convertView.findViewById(R.id.tv_name);
            viewHodler.tv_phone=(TextView)convertView.findViewById(R.id.tv_phone);
            convertView.setTag(viewHodler);
        }
        else
        {
            viewHodler=(ViewHodler) convertView.getTag();
        }
        viewHodler.img.setImageResource(Integer.parseInt(data.get(position).get("img")));
        viewHodler.tv_name.setText(data.get(position).get("name"));
        viewHodler.tv_phone.setText(data.get(position).get("phone"));

        return super.getView(position, convertView, parent);
    }
}

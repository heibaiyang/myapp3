package com.example.myapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SectionIndexer;
import android.widget.TextView;
import com.example.myapp.R;
import com.example.myapp.models.Contact;

import java.util.List;

/**
 * Created by 李钊颖 on 2016/4/13.
 */
public class ContactAdapter extends BaseAdapter implements SectionIndexer {
    private List<Contact> list;
    private Context context;
    final static class ViewHolder{
        TextView tv_letter;
        TextView tv_name;
    }

    public ContactAdapter(Context context,List<Contact> list) {
        this.context=context;
        this.list=list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView==null)
        {
            viewHolder=new ViewHolder();
            convertView= LayoutInflater.from(context).inflate(R.layout.tongxunlu_item,parent,false);
            viewHolder.tv_letter=(TextView)convertView.findViewById(R.id.tv_letter);
            viewHolder.tv_name=(TextView)convertView.findViewById(R.id.tv_name);
            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder=(ViewHolder)convertView.getTag();
        }
        Contact contact=list.get(position);
        if(position==0)
        {
            viewHolder.tv_letter.setVisibility(View.VISIBLE);
            viewHolder.tv_letter.setText(contact.getLetter());
        }
        else
        {
            String lastLetter=list.get(position-1).getLetter();
            if(contact.getLetter().equals(lastLetter))
            {
                viewHolder.tv_letter.setVisibility(View.GONE);
            }
            else
            {
                viewHolder.tv_letter.setVisibility(View.VISIBLE);
                viewHolder.tv_letter.setText(contact.getLetter());
            }
        }
        viewHolder.tv_name.setText(contact.getName());

        return convertView;
    }

    @Override
    public Object[] getSections() {
        return null;
    }

    @Override
    public int getPositionForSection(int sectionIndex) {
        Contact contact;
        String firstLetter;
        for(int i=0;i<list.size();i++)
        {
            contact=list.get(i);
            firstLetter=contact.getLetter();
            if(sectionIndex==firstLetter.charAt(0))
            {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int getSectionForPosition(int position) {
        return 0;
    }
}

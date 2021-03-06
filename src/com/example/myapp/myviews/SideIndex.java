package com.example.myapp.myviews;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ListView;
import android.widget.SectionIndexer;
import com.example.myapp.adapters.ContactAdapter;

/**
 * Created by 李钊颖 on 2016/4/14.
 */
public class SideIndex extends View {
    char[] letters;
    SectionIndexer sectionIndexer;
    ListView lv_tongxunlu;

    public SideIndex(Context context) {
        super(context);
        initLetters();
    }

    public SideIndex(Context context, AttributeSet attrs) {
        super(context, attrs);
        initLetters();
    }

    public SideIndex(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initLetters();
    }

    private void initLetters(){
        letters=new char[26];
        /*String str=new String("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        letters=str.toCharArray();*/
        for(int i=0;i<26;i++)
        {
            letters[i]=(char)('A'+i);
        }
    }

    public void setListView(ListView listView)
    {
        lv_tongxunlu=listView;
        ContactAdapter adapter=(ContactAdapter) listView.getAdapter();
        sectionIndexer=adapter;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        int y=(int)event.getY();
        int index=y/(getMeasuredHeight()/letters.length);
        if(index>=letters.length)
        {
            index=letters.length-1;
        }
        else if(index<0)
        {
            index=0;
        }
        if(event.getAction()==MotionEvent.ACTION_DOWN||event.getAction()==MotionEvent.ACTION_MOVE)
        {
            if(sectionIndexer==null)
            {
                sectionIndexer=(SectionIndexer) lv_tongxunlu.getAdapter();
            }
            int position=sectionIndexer.getPositionForSection(letters[index]);
            if(position==-1)
            {
                return true;
            }
            lv_tongxunlu.setSelection(position);
        }
        if(event.getAction()==MotionEvent.ACTION_UP)
        {

        }
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Paint paint=new Paint();
        paint.setTextSize(25);

        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);
        paint.setTextAlign(Paint.Align.CENTER);
        int width=getMeasuredWidth()/2;
        int height=getMeasuredHeight()/letters.length;
        for(int i=0;i<letters.length;i++)
        {
            canvas.drawText(String.valueOf(letters[i]),width,(i+1)*height-height/2,paint);
        }

        invalidate();
        super.onDraw(canvas);
    }
}

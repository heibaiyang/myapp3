package com.example.myapp.myviews;

import android.content.Context;
import android.graphics.*;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ListView;
import android.widget.SectionIndexer;
import com.example.myapp.adapters.ContactAdapter;

/**
 * Created by 李钊颖 on 2016/4/14.
 */
public class Round extends View {
    char[] letters;
    SectionIndexer sectionIndexer;
    ListView lv_tongxunlu;

    public Round(Context context) {
        super(context);
        initLetters();
    }

    public Round(Context context, AttributeSet attrs) {
        super(context, attrs);
        initLetters();
    }

    public Round(Context context, AttributeSet attrs, int defStyle) {
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

        paint.setStrokeWidth(5);
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);

        canvas.drawLine(50,50,400,400,paint);
        canvas.drawCircle(500,500,100,paint);
        RectF rectF=new RectF(200,240,400,420);
        canvas.drawRect(rectF,paint);
        canvas.drawArc(rectF,45,180,false,paint);
        Path linePath=new Path();
        linePath.moveTo(20,15);
        linePath.lineTo(50,100);
        linePath.lineTo(100,50);
        linePath.close();
        canvas.drawPath(linePath,paint);
        Path path=new Path();
        float width=getMeasuredWidth()/2;
        float height=getMeasuredHeight()/2;
        RectF rectF1=new RectF(width-200,height-200,width+200,height+200);
        path.addRect(rectF1, Path.Direction.CW);
        canvas.drawPath(path,paint);
        invalidate();
        super.onDraw(canvas);
    }
}

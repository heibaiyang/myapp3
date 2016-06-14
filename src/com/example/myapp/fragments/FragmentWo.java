package com.example.myapp.fragments;

import android.animation.*;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.*;
import com.example.myapp.R;
import com.example.myapp.adapters.MySimpleAdapter;
import com.example.myapp.myviews.MyDraw;
import com.example.myapp.myviews.SideIndex;
import okhttp3.*;

import java.io.IOException;
import java.util.*;

/**
 * Created by 李钊颖 on 2016/3/30.
 */
public class FragmentWo extends Fragment {
    /*private ListView lv_wo;
    SideIndex sideIndex;*/

    private Button btn_alpha,btn_scale,btn_rotate,btn_translate,btn_bgchange;
    //MyDraw myDraw;
    ImageView iv_donghua;
    TextView tv_bg;
    private Animation alphaAnimation,scaleAnimation,rotateAnimation,translateAnimation;

    Button btn_click;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.wo, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btn_click=(Button)view.findViewById(R.id.btn_click);
        btn_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url="http://52.38.232.240:8080/jfinal_gradle/users";
                OkHttpClient client=new OkHttpClient();
                final Request request=new Request.Builder().url(url).build();
                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        Log.v("json",response.body().string());
                    }
                });
            }
        });

        /*btn_alpha=(Button)view.findViewById(R.id.btn_alpha);
        btn_scale=(Button)view.findViewById(R.id.btn_scale);
        btn_rotate=(Button)view.findViewById(R.id.btn_rotate);
        btn_translate=(Button)view.findViewById(R.id.btn_translate);
        btn_bgchange=(Button)view.findViewById(R.id.btn_bgchange);
        iv_donghua=(ImageView)view.findViewById(R.id.iv_donghua);
        tv_bg=(TextView)view.findViewById(R.id.tv_donghua);
        //myDraw=(MyDraw) view.findViewById(R.id.mydraw);

        alphaAnimation= AnimationUtils.loadAnimation(getActivity(),R.anim.alpha_animation);
        scaleAnimation= AnimationUtils.loadAnimation(getActivity(),R.anim.scale_animation);
        rotateAnimation= AnimationUtils.loadAnimation(getActivity(),R.anim.rotate_animation);
        translateAnimation= AnimationUtils.loadAnimation(getActivity(),R.anim.translate_animation);

        *//*final AlphaAnimation alphaAnimation=new AlphaAnimation(1.0f,0.0f);
        alphaAnimation.setDuration(2000);
        alphaAnimation.setRepeatCount(2);
        alphaAnimation.setRepeatMode(Animation.REVERSE);*//*

        btn_alpha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //iv_donghua.startAnimation(alphaAnimation);

                ObjectAnimator objectAnimator=ObjectAnimator.ofFloat(iv_donghua,View.ALPHA,0.0f,1.0f);
                objectAnimator.setRepeatCount(2);
                objectAnimator.setRepeatMode(ValueAnimator.REVERSE);
                objectAnimator.setDuration(2000);
                objectAnimator.start();
            }
        });
        btn_scale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                iv_donghua.setImageResource(R.drawable.rounds);
                AnimationDrawable animationDrawable=(AnimationDrawable)iv_donghua.getDrawable();
                animationDrawable.start();

                //myDraw.startAnimation(scaleAnimation);

                *//*ObjectAnimator objectAnimator1=ObjectAnimator.ofFloat(iv_donghua,View.SCALE_X,0.0f,2.0f,1.5f,1.0f);
                objectAnimator1.setDuration(2000);
                objectAnimator1.start();*//*

                *//*AnimatorSet animatorSet=new AnimatorSet();

                ObjectAnimator objectAnimator1=ObjectAnimator.ofFloat(myDraw,View.SCALE_X,0.0f,1.0f,2.0f,3.0f);
                objectAnimator1.setDuration(2000);

                ObjectAnimator objectAnimator2=ObjectAnimator.ofFloat(myDraw,View.SCALE_Y,0.0f,1.0f,2.0f,3.0f);
                objectAnimator1.setDuration(2000);

                Collection<Animator> collection1=new ArrayList<Animator>();
                collection1.add(objectAnimator1);
                collection1.add(objectAnimator2);
                animatorSet.playTogether(collection1);
                animatorSet.start();*//*

            }
        });
        btn_rotate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //iv_donghua.startAnimation(rotateAnimation);

                ObjectAnimator objectAnimator2=ObjectAnimator.ofFloat(iv_donghua,View.ROTATION,0.0f,360.0f);
                objectAnimator2.setDuration(5000);
                objectAnimator2.start();
            }
        });
        btn_translate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //iv_donghua.startAnimation(translateAnimation);

                ObjectAnimator objectAnimator3=ObjectAnimator.ofFloat(iv_donghua,View.TRANSLATION_X,0.0f,300.0f,100.0f,150.0f);
                objectAnimator3.setDuration(3000);
                objectAnimator3.start();
            }
        });
        btn_bgchange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AnimatorSet animatorSet=new AnimatorSet();

                ObjectAnimator objectAnimator1=ObjectAnimator.ofFloat(tv_bg,View.SCALE_X,0.0f,2.0f,1.5f,1.0f);
                objectAnimator1.setDuration(2000);

                ObjectAnimator objectAnimator2=ObjectAnimator.ofFloat(tv_bg,View.SCALE_Y,0.0f,4.0f,2.5f,1.0f);
                objectAnimator1.setDuration(2000);

                ObjectAnimator objectAnimator4=ObjectAnimator.ofInt(tv_bg,"BackgroundColor",0xffaaffdd,0xffaabbcc,0xffccddee,0xff000011);
                objectAnimator4.setEvaluator(new ArgbEvaluator());
                objectAnimator4.setDuration(3000);

                *//*animatorSet.playTogether(objectAnimator1,objectAnimator2,objectAnimator4);*//*

                *//*Collection collection=new ArrayList();
                collection.add(objectAnimator1);
                collection.add(objectAnimator2);
                collection.add(objectAnimator4);

                animatorSet.playTogether(collection);
                animatorSet.start();*//*

                Collection<Animator> collection1=new ArrayList<Animator>();
                collection1.add(objectAnimator1);
                collection1.add(objectAnimator2);
                collection1.add(objectAnimator4);
                animatorSet.playTogether(collection1);
                animatorSet.start();


            }
        });*/

        /*lv_wo=(ListView)view.findViewById(R.id.lv_wo);
        List<Map<String,String>> data=new ArrayList<>();
        //组织数据
        for(int i=1;i<=50;i++)
        {
            Map<String,String> map=new HashMap<>();
            map.put("img",String.valueOf(R.drawable.radiobtn1));
            map.put("name","name"+i);
            map.put("phone","1234567"+i);
            data.add(map);
        }
        //创建from数组
        String[] from=new String[]{"img","name","phone"};
        //定义to整型数组
        int[] to=new int[]{R.id.img_contact,R.id.tv_name,R.id.tv_phone};
        //创建SmpleAdapter对象
        MySimpleAdapter adapter=new MySimpleAdapter(getActivity(),data,R.layout.tongxunlu_item,from,to);
        //ListView指定数据源
        lv_wo.setAdapter(adapter);
        sideIndex.setListView(lv_wo);*/
    }
}

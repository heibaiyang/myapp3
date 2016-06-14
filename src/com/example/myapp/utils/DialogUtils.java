package com.example.myapp.utils;

import android.app.Dialog;
import android.content.Context;
import android.view.Window;
import com.example.myapp.R;

/**
 * Created by 李钊颖 on 2016/5/18.
 */
public class DialogUtils {
    public static Dialog getLoadingDialog(Context context)
    {
        Dialog dialog=new Dialog(context,R.style.myLoadingStyle);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_view);
        dialog.setCancelable(false);
        return dialog;
    }
}

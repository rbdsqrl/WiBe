package com.dovo.wibe.services;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

/**
 * Created by Narendran on 19-12-2017.
 */

public class CommonMethodsUtil {
    public static void makeToastCentre(String message, Context context){
        Toast t = Toast.makeText(context,message,Toast.LENGTH_SHORT);
        t.setGravity(Gravity.CENTER,0,-50);
        t.show();
    }

    public static void makeToast(String message, Context context){
        Toast.makeText(context,message,Toast.LENGTH_SHORT).show();
    }
}

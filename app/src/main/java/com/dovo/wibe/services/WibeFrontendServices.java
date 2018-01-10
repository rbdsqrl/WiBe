package com.dovo.wibe.services;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Narendran on 19-12-2017.
 */

public class WibeFrontendServices {
    private static WibeFrontendServices wibeFrontendServices;
    private static SharedPreferences sharedPreferencesWibe = null ;
    private static SharedPreferences.Editor editorWibe =null ;

    private WibeFrontendServices(Context context){
        sharedPreferencesWibe = context.getSharedPreferences("WibeLogistics", Context.MODE_PRIVATE);
        editorWibe = sharedPreferencesWibe.edit();
    }

    public static WibeFrontendServices getInstance(Context activity){
        if(wibeFrontendServices == null){
            wibeFrontendServices = new WibeFrontendServices(activity);
        }
        return wibeFrontendServices;
    }

    public void setWellnessScore( int wellnessScore){
        editorWibe.putInt("setWellnessScore", wellnessScore);
        editorWibe.commit();
    }

    public int getWellnessScore(){
        return sharedPreferencesWibe.getInt("setWellnessScore",0);
    }

}

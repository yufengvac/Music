package com.vac.music.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by vac on 16/8/8.
 * ShareUtils类
 */
public class ShareUtil {
    private static final String BASECOLOR = "basecolor";
    private static final String BASECOLOR_A = "a";
    private static final String BASECOLOR_R = "r";
    private static final String BASECOLOR_G = "g";
    private static final String BASECOLOR_B = "b";
    private static final int MODE = Context.MODE_PRIVATE;
    public static void saveBaseColor(Context context,int a,int r,int g,int b){
        SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences(BASECOLOR,MODE);
        sharedPreferences.edit().putInt(BASECOLOR_A,a).putInt(BASECOLOR_R,r).putInt(BASECOLOR_G,g).putInt(BASECOLOR_B,b).apply();
    }

    public static int getBaseColor_A(Context context){
        SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences(BASECOLOR,MODE);
        return sharedPreferences.getInt(BASECOLOR_A,0x00);
    }
    public static int getBaseColor_R(Context context){
        SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences(BASECOLOR,MODE);
        return sharedPreferences.getInt(BASECOLOR_R,0x00);
    }
    public static int getBaseColor_G(Context context){
        SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences(BASECOLOR,MODE);
        return sharedPreferences.getInt(BASECOLOR_G,0x00);
    }
    public static int getBaseColor_B(Context context){
        SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences(BASECOLOR,MODE);
        return sharedPreferences.getInt(BASECOLOR_B,0x00);
    }
}

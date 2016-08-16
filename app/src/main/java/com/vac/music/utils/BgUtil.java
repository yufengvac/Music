package com.vac.music.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.vac.music.R;

/**
 * Created by vac on 16/8/12.
 * 设置 背景
 */
@SuppressWarnings("NewApi"
)
public class BgUtil {

    public static Drawable setHomeBackground(Context mContext, ImageView imageView, String url){
        Drawable drawable = null;

        if (url.equals("drawable://"+R.drawable.default_1)){
            drawable = mContext.getDrawable(R.drawable.default_1);
        }else if (url.equals("drawable://"+R.drawable.default_2)){
            drawable = mContext.getDrawable(R.drawable.default_2);
        }else if (url.equals("drawable://"+R.drawable.default_3)){
            drawable = mContext.getDrawable(R.drawable.default_3);
        }else if (url.equals("drawable://"+R.drawable.default_4)){
            drawable = mContext.getDrawable(R.drawable.default_4);
        }else if (url.equals("drawable://"+R.drawable.default_5)){
            drawable = mContext.getDrawable(R.drawable.default_5);
        }else if (url.equals("drawable://"+R.drawable.default_6)){
            drawable = mContext.getDrawable(R.drawable.default_6);
        }else if (url.equals("drawable://"+R.drawable.default_7)){
            drawable = mContext.getDrawable(R.drawable.default_7);
        }else if (url.equals("drawable://"+R.drawable.default_8)){
            drawable = mContext.getDrawable(R.drawable.default_8);
        }else if (url.equals("drawable://"+R.drawable.default_9)){
            drawable = mContext.getDrawable(R.drawable.default_9);
        }else if (url.equals("drawable://"+R.drawable.default_my_music_bg)){
            drawable = mContext.getDrawable(R.drawable.default_my_music_bg);
        }
        imageView.setBackground(drawable);
        return drawable;
    }
}

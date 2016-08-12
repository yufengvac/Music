package com.vac.music.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.vac.music.R;

/**
 * Created by vac on 16/8/12.
 */
@SuppressWarnings("NewApi"
)
public class BgUtil {

    public static Drawable setHomeBackground(Context mContext, ImageView imageView, String url){
        Drawable drawable = null;
        if (url.endsWith("R.drawable.default_1")){
            imageView.setBackground(mContext.getResources().getDrawable(R.drawable.default_1));
            drawable = mContext.getDrawable(R.drawable.default_1);
        }else if (url.endsWith("R.drawable.default_2")){
            imageView.setBackground(mContext.getResources().getDrawable(R.drawable.default_2));
        }else if (url.endsWith("R.drawable.default_3")){
            imageView.setBackgroundResource(R.drawable.default_3);
        }else if (url.endsWith("R.drawable.default_4")){
            imageView.setBackgroundResource(R.drawable.default_4);
        }else if (url.endsWith("R.drawable.default_5")){
            imageView.setBackgroundResource(R.drawable.default_5);
        }else if (url.endsWith("R.drawable.default_6")){
            imageView.setBackgroundResource(R.drawable.default_6);
        }else if (url.endsWith("R.drawable.default_7")){
            imageView.setBackgroundResource(R.drawable.default_7);
        }else if (url.endsWith("R.drawable.default_8")){
            imageView.setBackgroundResource(R.drawable.default_8);
        }else if (url.endsWith("R.drawable.default_9")){
            imageView.setBackgroundResource(R.drawable.default_9);
        }
        return drawable;
    }
}

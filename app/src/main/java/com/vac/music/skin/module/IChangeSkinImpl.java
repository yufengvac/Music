package com.vac.music.skin.module;

import com.vac.music.R;
import com.vac.music.beans.BaseColor;

import java.util.ArrayList;

/**
 * Created by vac on 16/8/8.
 *
 */
public class IChangeSkinImpl implements IChangeSkin{
    private ArrayList<String> urlList = new ArrayList<>();
    private ArrayList<BaseColor> baseColorList = new ArrayList<>();
    @Override
    public ArrayList<String> getUrlList() {
        urlList.clear();
        urlList.add("drawable://"+ R.drawable.default_1);
        urlList.add("drawable://"+ R.drawable.default_2);
        urlList.add("drawable://"+ R.drawable.default_3);
        urlList.add("drawable://"+ R.drawable.default_4);
        urlList.add("drawable://"+ R.drawable.default_5);
        urlList.add("drawable://"+ R.drawable.default_6);
        urlList.add("drawable://"+ R.drawable.default_7);
        urlList.add("drawable://"+ R.drawable.default_8);
        urlList.add("drawable://"+ R.drawable.default_9);
        return urlList;
    }

    @Override
    public ArrayList<BaseColor> getColorArray() {
        baseColorList.clear();

        BaseColor baseColor0 = new BaseColor(0xff,0xff,0x19,0x00);
        BaseColor baseColor1 = new BaseColor(0xff,0x51,0x87,0xa3);
        BaseColor baseColor2 = new BaseColor(0xff,0xe4,0xb0,0xc8);
        BaseColor baseColor3 = new BaseColor(0xff,0xff,0x87,0x3d);
        BaseColor baseColor4 = new BaseColor(0xff,0x78,0xb2,0xbe);
        BaseColor baseColor5 = new BaseColor(0xff,0x00,0x97,0x67);
        BaseColor baseColor6 = new BaseColor(0xff,0xff,0xc9,0x78);
        BaseColor baseColor7 = new BaseColor(0xff,0x66,0x91,0xdf);
        BaseColor baseColor8 = new BaseColor(0xff,0xc0,0x87,0x68);

        baseColorList.add(baseColor0);baseColorList.add(baseColor1);
        baseColorList.add(baseColor2);baseColorList.add(baseColor3);
        baseColorList.add(baseColor4);baseColorList.add(baseColor5);
        baseColorList.add(baseColor6);baseColorList.add(baseColor7);
        baseColorList.add(baseColor8);

        return baseColorList;
    }
}

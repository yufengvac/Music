package com.vac.music.skin.presenter;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.vac.music.beans.BaseColor;
import com.vac.music.skin.fragments.SkinFragment;
import com.vac.music.skin.listener.OnSkinChangeListener;
import com.vac.music.skin.module.IChangeSkin;
import com.vac.music.skin.module.IChangeSkinImpl;
import com.vac.music.skin.view.IChangeSkinView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vac on 16/8/8.
 *
 */
public class ChangeSkinPresener {
    private static final String TAG = ChangeSkinPresener.class.getSimpleName();
    private Context mContext;
    private IChangeSkinView mListener;
    private IChangeSkin iChangeSkinListener;
    public ChangeSkinPresener(Activity activity){
        this.mContext = activity;
        iChangeSkinListener = new IChangeSkinImpl();
        if (activity instanceof IChangeSkinView){
            this.mListener = (IChangeSkinView)activity;
        }
    }

    public void initFragment(){
        ArrayList<String> urlList = iChangeSkinListener.getUrlList();
        ArrayList<BaseColor> baseColorList = iChangeSkinListener.getColorArray();
        List<OnSkinChangeListener> skinChangeListeners = mListener.getListener();

        List<Fragment> fragmentList = new ArrayList<>();
        Log.e(TAG,"skinChangeListeners.size()="+skinChangeListeners.size());
        for (int i=0;i<3;i++){
            SkinFragment skinFragment = new SkinFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("index",i);
            bundle.putStringArrayList("urlList",urlList);
            bundle.putParcelableArrayList("colorList",baseColorList);
            skinFragment.setArguments(bundle);
            skinFragment.addOnSkinChangeListener(skinChangeListeners);
            fragmentList.add(skinFragment);
        }
        Log.i("p","urlList.size()="+urlList.size());
        mListener.setData(fragmentList);
    }

}

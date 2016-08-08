package com.vac.music.skin.view;

import android.support.v4.app.Fragment;

import com.vac.music.skin.listener.OnSkinChangeListener;

import java.util.List;

/**
 * Created by vac on 16/8/8.
 * 更换皮肤
 */
public interface IChangeSkinView {
    void setData(List<Fragment> list);
    List<OnSkinChangeListener> getListener();
}

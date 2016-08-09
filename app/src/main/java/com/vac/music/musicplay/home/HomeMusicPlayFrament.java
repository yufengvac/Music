package com.vac.music.musicplay.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vac.music.R;
import com.vac.music.fragments.BaseSwipeBackFragment;

/**
 * Created by vac on 16/8/9.
 * 播放音乐的fragment
 */
public class HomeMusicPlayFrament extends BaseSwipeBackFragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.home_music_play_fragment,container,false);
        return attachToSwipeBack(view);
    }
}

package com.vac.music.fragments.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vac.music.R;
import com.vac.music.fragments.BaseSwipeBackFragment;

/**
 * Created by vac on 16/8/5.
 *
 */
public class NetMusicFragment extends BaseSwipeBackFragment {
    public static NetMusicFragment newInstance(Bundle bundle){
        NetMusicFragment netMusicFragment = new NetMusicFragment();
        if (bundle!=null){
            netMusicFragment.setArguments(bundle);
        }
        return netMusicFragment;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.net_music_fragment,container,false);
        return attachToSwipeBack(view);
    }
}

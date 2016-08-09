package com.vac.music.activity;

import android.os.Bundle;

import com.vac.music.R;
import com.vac.music.swipebackfragment.SwipeBackActivity;

public class MusicPlayActivity extends SwipeBackActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_play);
    }
}

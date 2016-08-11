package com.vac.music.activity;

import android.os.Bundle;
import android.view.KeyEvent;

import com.vac.music.R;
import com.vac.music.swipebackfragment.SwipeBackActivity;

public class MusicPlayActivity extends SwipeBackActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_play);
        getSwipeBackLayout().setEnableGesture(false);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode==KeyEvent.KEYCODE_BACK){
            finish();
            overridePendingTransition(R.anim.push_bottom_out,R.anim.push_bottom_out);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}

package com.vac.music.activity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.vac.music.R;
import com.vac.music.swipebackfragment.SwipeBackActivity;
import com.vac.music.utils.ShareUtil;

public class MusicPlayActivity extends SwipeBackActivity {

    private ImageView musicBg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_play);
        getSwipeBackLayout().setEnableGesture(false);

        musicBg = (ImageView)findViewById(R.id.play_music_content);
//        String picUrl = ShareUtil.getMainPicture(this);
//        ImageLoader.getInstance().displayImage(picUrl,musicBg);
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

    public void playMusicBackClick(View view){
        finish();
        overridePendingTransition(R.anim.push_bottom_out,R.anim.push_bottom_out);
    }


    @Override
    protected void onStop() {
        super.onStop();
    }
}

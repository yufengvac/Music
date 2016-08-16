package com.vac.music.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewCompat;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;

import com.vac.music.R;
import com.vac.music.fragments.BaseSwipeBackFragment;
import com.vac.music.fragments.home.HomeMainFragment;
import com.vac.music.musicplay.home.HomeMusicPlayFrament;
import com.vac.music.myview.MyMenuButton;
import com.vac.music.myview.MyProgressbar;
import com.vac.music.myview.MyTriangle;
import com.vac.music.skin.listener.OnSkinChangeListener;
import com.vac.music.swipebackfragment.SwipeBackActivity;
import com.vac.music.swipebackfragment.SwipeBackLayout;
import com.vac.music.utils.ShareUtil;

import java.util.List;

@SuppressWarnings("NewApi")
public class MainActivity extends SwipeBackActivity implements BaseSwipeBackFragment.OnAddFragmentListener
,OnSkinChangeListener{

    private MyTriangle myTriangle;
    private MyMenuButton myMenuButton;
    private MyProgressbar mProgressbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
//        setStautsColor();
        myTriangle = (MyTriangle) findViewById(R.id.main_play_mytriangle);
        myMenuButton = (MyMenuButton) findViewById(R.id.main_menu_mymenubtn);
        mProgressbar = (MyProgressbar) findViewById(R.id.main_progressbar);
        updateColor();

        if (savedInstanceState == null) {
            HomeMainFragment firstFragment = HomeMainFragment.newInstance(null);
            firstFragment.addOnSkinChangeListener(this);
            loadFragment(firstFragment);
        }
    }

    /**
     * 限制SwipeBack的条件,默认栈内Fragment数 <= 1时 , 优先滑动退出Activity , 而不是Fragment
     *
     * @return true: Activity可以滑动退出, 并且总是优先;  false: Activity不允许滑动退出
     */
    @Override
    public boolean swipeBackPriority() {
        return super.swipeBackPriority();
    }


    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() == 1) {
            finish();
        } else {
            super.onBackPressed();
        }
    }
    private void addFragment(Fragment fromFragment, Fragment toFragment) {
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.h_fragment_enter, R.anim.h_fragment_exit, R.anim.h_fragment_pop_enter, R.anim.h_fragment_pop_exit)
                .add(R.id.main_content, toFragment, toFragment.getClass().getName())
                .hide(fromFragment)
                .addToBackStack(toFragment.getClass().getName())
                .commit();
    }

    private void loadFragment(Fragment toFragment) {
        getSupportFragmentManager().beginTransaction()
                .add(R.id.main_content, toFragment, toFragment.getClass().getName())
                .addToBackStack(toFragment.getClass().getName())
                .commit();
    }

    @Override
    public void onAddFragment(Fragment fromFragment, Fragment toFragment) {
        addFragment(fromFragment, toFragment);
    }

    private void setStautsColor() {
        Window window = getWindow();
        //设置透明状态栏,这样才能让 ContentView 向上
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        //需要设置这个 flag 才能调用 setStatusBarColor 来设置状态栏颜色
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        //设置状态栏颜色
        window.setStatusBarColor(getResources().getColor(R.color.light_black));

        ViewGroup mContentView = (ViewGroup) findViewById(Window.ID_ANDROID_CONTENT);
        View mChildView = mContentView.getChildAt(0);
        if (mChildView != null) {
            //注意不是设置 ContentView 的 FitsSystemWindows, 而是设置 ContentView 的第一个子 View . 使其不为系统 View 预留空间.
            ViewCompat.setFitsSystemWindows(mChildView, false);
        }

//        Window window = getWindow();
//        ViewGroup mContentView = (ViewGroup) findViewById(Window.ID_ANDROID_CONTENT);
//
////First translucent status bar.
//        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//        int statusBarHeight = getStatusBarHeight(this);
//
//        View mChildView = mContentView.getChildAt(0);
//        if (mChildView != null) {
//            FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) mChildView.getLayoutParams();
//            //如果已经为 ChildView 设置过了 marginTop, 再次调用时直接跳过
//            if (lp != null && lp.topMargin < statusBarHeight && lp.height != statusBarHeight) {
//                //不预留系统空间
//                ViewCompat.setFitsSystemWindows(mChildView, false);
//                lp.topMargin += statusBarHeight;
//                mChildView.setLayoutParams(lp);
//            }
//        }
//
//        View statusBarView = mContentView.getChildAt(0);
//        if (statusBarView != null && statusBarView.getLayoutParams() != null && statusBarView.getLayoutParams().height == statusBarHeight) {
//            //避免重复调用时多次添加 View
//            statusBarView.setBackgroundColor(getResources().getColor(R.color.light_black));
//            return;
//        }
//        statusBarView = new View(this);
//        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, statusBarHeight);
//        statusBarView.setBackgroundColor(getResources().getColor(R.color.light_black));
//        //向 ContentView 中添加假 View
//        mContentView.addView(statusBarView, 0, lp);

    }

    public static int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen",
                "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    @Override
    public void onSkinChange(int alpha, int red, int green, int blue,String url) {
        int color = Color.argb(alpha,red,green,blue);
        myTriangle.setColor(color);
        myMenuButton.setColor(color);
        mProgressbar.setProgressColor(color);
    }
    private void updateColor(){
        int alpha = ShareUtil.getBaseColor_A(this);
        int red = ShareUtil.getBaseColor_R(this);
        int green = ShareUtil.getBaseColor_G(this);
        int blue = ShareUtil.getBaseColor_B(this);
        if (alpha!=0&&red!=0&&green!=0&&blue!=0){
            onSkinChange(alpha,red,green,blue,"");
        }
    }

    /***
     * 播放music
     * @param view
     */
    public void toPlayActivity(View view){
//        List<Fragment> fragmentList = getSupportFragmentManager().getFragments();
//        int count = getSupportFragmentManager().getBackStackEntryCount();
//        FragmentManager.BackStackEntry backStackEntry = getSupportFragmentManager().getBackStackEntryAt(count-1);

//        Log.e("MainActivity","count个数="+count);
//        if (fragmentList==null){
//            Log.e("MainActivity","fragmentList =null");
//            return;
//        }else {
//            Log.i("MainActivity","fragmentList.size()="+fragmentList.size());
//        }
//        for (int i=0;i<fragmentList.size();i++){
//            if (fragmentList.get(i)==null){
//                Log.e("MainActivity","fragment第"+i+"个为null");
//            }else {
//                Log.i("MainActivity",i+","+fragmentList.get(i).getClass().getSimpleName());
//            }
//
//        }
//        HomeMusicPlayFrament homeMusicPlayFrament = new HomeMusicPlayFrament();
//        getSupportFragmentManager().beginTransaction()
//                .setCustomAnimations(R.anim.v_fragment_enter, R.anim.h_fragment_exit, R.anim.h_fragment_pop_enter, R.anim.v_fragment_pop_exit)
//                .add(R.id.main_all_content,homeMusicPlayFrament , homeMusicPlayFrament.getClass().getSimpleName())
//                .hide(getSupportFragmentManager().findFragmentByTag(backStackEntry.getName()))
//                .addToBackStack(homeMusicPlayFrament.getClass().getSimpleName())
//                .commit();

        startActivity(new Intent(this,MusicPlayActivity.class));
        overridePendingTransition(R.anim.push_bottom_in,R.anim.push_bottom_out);
    }
}

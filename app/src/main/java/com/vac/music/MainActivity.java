package com.vac.music;

import android.content.Context;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.vac.music.fragments.BaseSwipeBackFragment;
import com.vac.music.fragments.home.HomeMainFragment;
import com.vac.music.swipebackfragment.SwipeBackActivity;
@SuppressWarnings("NewApi")
public class MainActivity extends SwipeBackActivity implements BaseSwipeBackFragment.OnAddFragmentListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        setStautsColor();


        if (savedInstanceState == null) {
            HomeMainFragment firstFragment = HomeMainFragment.newInstance(null);
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
                .add(R.id.main_content, toFragment, toFragment.getClass().getSimpleName())
                .hide(fromFragment)
                .addToBackStack(toFragment.getClass().getSimpleName())
                .commit();
    }

    private void loadFragment(Fragment toFragment) {
        getSupportFragmentManager().beginTransaction()
                .add(R.id.main_content, toFragment, toFragment.getClass().getSimpleName())
                .addToBackStack(toFragment.getClass().getSimpleName())
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
}

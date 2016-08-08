package com.vac.music.fragments.home;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.vac.music.R;
import com.vac.music.fragments.BaseSwipeBackFragment;
import com.vac.music.skin.listener.OnSkinChangeListener;
import com.vac.music.utils.ShareUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vac on 16/8/5.
 *
 */
public class HomeMainFragment extends BaseSwipeBackFragment implements LocalMusicFragment.onFragmentScrollViewListener
,OnSkinChangeListener{

    private static final String TAG = HomeMainFragment.class.getSimpleName();
    private ViewPager viewPager;
    private TextView optionView1,optionView2;
    private FragmentPagerAdapter fragmentPagerAdapter;
    private View indicatorView;
    private List<Fragment> fragmentList = new ArrayList<>();
    private RelativeLayout topLaout;
    private int currentAlpha= 0x30, currentRed,currentGreen,currentBlue;
    private int alpha,red,green,blue;

    private int color= Color.argb(0x90, 0xff, 0x00, 0x00);
    private float scale;
    private OnSkinChangeListener mainSkinChangeListener;
    public static HomeMainFragment newInstance(Bundle bundle){
        HomeMainFragment homeMainFragment = new HomeMainFragment();
        if (bundle!=null){
            homeMainFragment.setArguments(bundle);
        }
        return homeMainFragment;
    }
    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_main_fragment,container,false);
        viewPager = (ViewPager) view.findViewById(R.id.home_main_fragment_viewPager);
        optionView1 = (TextView)view.findViewById(R.id.home_main_fragment_option1);
        optionView2 = (TextView)view.findViewById(R.id.home_main_fragment_option2);
        topLaout = (RelativeLayout)view.findViewById(R.id.home_main_top_layout);
        indicatorView = view.findViewById(R.id.home_main_indicator_view);

        LocalMusicFragment localMusicFragment = LocalMusicFragment.newInstance(null);
        localMusicFragment.setOnScrollListener(this);
        localMusicFragment.addOnSkinChangeListener(mainSkinChangeListener,this);
        fragmentList.add(localMusicFragment);
        fragmentList.add(NetMusicFragment.newInstance(null));
        fragmentPagerAdapter = new FragmentPagerAdapter(getFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragmentList.get(position);
            }

            @Override
            public int getCount() {
                return fragmentList.size();
            }
        };
        viewPager.setAdapter(fragmentPagerAdapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                LinearLayout.LayoutParams params = (android.widget.LinearLayout.LayoutParams) indicatorView
                        .getLayoutParams();
                params.setMargins(
                        (int) (optionView1.getWidth() * (positionOffset + position)), 0,
                        0, 0);
                indicatorView.setLayoutParams(params);
                if (positionOffset > 0) {

                    int a = (int) (currentAlpha + (0xff - currentAlpha) * positionOffset);
                    int r = (int) (currentRed +(Math.abs(red - 0x10 )-currentRed) * positionOffset);
                    int g = (int) (currentGreen+(Math.abs(green - 0x10)-currentGreen )* positionOffset);
                    int b =  (int) (currentBlue +(Math.abs(blue - 0x10) - currentBlue) * positionOffset);
                    color = Color.argb(a, r, g, b);
                    topLaout.setBackgroundColor(color);
                }else if(positionOffset==0&&position==0){
                    color = Color.argb(currentAlpha, currentRed, currentGreen, currentBlue);
                    topLaout.setBackgroundColor(color);
                }

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        updateColor();

        return attachToSwipeBack(view);
    }


    int baseHeight = 300;
    @Override
    public void onScroll(int scrollY) {
        if (scrollY<=baseHeight&&scrollY>0){
            scale = scrollY*1f/baseHeight;
            currentAlpha = 0x30+(int)((0xff-0x30)*scale);
            currentRed = (int)(Math.abs(red-0x10)*scale);
            currentGreen = (int)(Math.abs(green-0x10)*scale);
            currentBlue = (int)(Math.abs(blue-0x10)*scale);
        }else if (scrollY>baseHeight){
            scale = 1;
            currentAlpha = 0xff;
            currentRed = Math.abs(red-0x10);
            currentGreen = Math.abs(green-0x10);
            currentBlue = Math.abs(blue-0x10);
        }else if (scrollY==0){
            scale = 0;
            currentAlpha = 0x30;
            currentRed = currentGreen= currentRed = 0x0;
        }
        color = Color.argb(currentAlpha,currentRed,currentGreen,currentBlue);
        topLaout.setBackgroundColor(color);
    }

    @Override
    public void onSkinChange(int a, int r, int g, int b) {
        alpha = a;
        red = r;
        green = g;
        blue = b;

        currentAlpha = 0x30+(int)((a-0x30)*scale);
        currentRed = (int)(Math.abs(r-0x10)*scale);
        currentGreen = (int)(Math.abs(g-0x10)*scale);
        currentBlue = (int)(Math.abs(b-0x10)*scale);
        color = Color.argb(currentAlpha,currentRed,currentGreen,currentBlue);
        topLaout.setBackgroundColor(color);
    }

    public void addOnSkinChangeListener(OnSkinChangeListener listener){
        this.mainSkinChangeListener = listener;
    }

    private void updateColor(){
        alpha = ShareUtil.getBaseColor_A(this.getActivity());
        red = ShareUtil.getBaseColor_R(this.getActivity());
        green = ShareUtil.getBaseColor_G(this.getActivity());
        blue = ShareUtil.getBaseColor_B(this.getActivity());
        color = Color.argb(0x30,0x00,0x00,0x00);
        topLaout.setBackgroundColor(color);
    }
}

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

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vac on 16/8/5.
 *
 */
public class HomeMainFragment extends BaseSwipeBackFragment implements LocalMusicFragment.onFragmentScrollViewListener {

    private static final String TAG = HomeMainFragment.class.getSimpleName();
    private ViewPager viewPager;
    private TextView optionView1,optionView2;
    private FragmentPagerAdapter fragmentPagerAdapter;
    private View indicatorView;
    private List<Fragment> fragmentList = new ArrayList<>();
    private RelativeLayout topLaout;
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
            ColorDrawable colorDrawable= (ColorDrawable) topLaout.getBackground();//获取背景颜色
            int currentColor = colorDrawable.getColor();
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                LinearLayout.LayoutParams params = (android.widget.LinearLayout.LayoutParams) indicatorView
                        .getLayoutParams();
                params.setMargins(
                        (int) (optionView1.getWidth() * (positionOffset + position)), 0,
                        0, 0);
                indicatorView.setLayoutParams(params);

                if (positionOffsetPixels>0){

                    int red = (currentColor & 0xff0000) >> 16;
                    int green = (currentColor & 0x00ff00) >> 8;
                    int blue = (currentColor & 0x0000ff);
                    Log.i(TAG,"red="+red+",green="+green+",blue="+blue);

                    int a = 0x30+(int)((0xff-0x30)*positionOffset);
                    int r = (int)((0xbb)*positionOffset);
                    int g = (int)((0x55)*positionOffset);
                    int b = 0x0;
                    color = Color.argb(a,r,g,b);
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


        return attachToSwipeBack(view);
    }

    int color= Color.argb(0x90, 0xff, 0x00, 0x00);
    int baseColor = 240;
    int baseHeight = 250;
    @Override
    public void onScroll(int scrollY) {
        if (scrollY<=baseHeight&&scrollY>0){
            float scale = scrollY*1f/baseHeight;
            int a = 0x30+(int)((0xff-0x30)*scale);
            int r = (int)((0xbb)*scale);
            int g = (int)((0x55)*scale);
            int b = 0x0;
            color = Color.argb(a,r,g,b);
        }else if (scrollY>baseHeight){
            color= Color.argb(0xff,0xbb,0x55,0x00);
        }else if (scrollY==0){
            color = Color.argb(0x30,0,0,0);
        }
        topLaout.setBackgroundColor(color);
    }
}

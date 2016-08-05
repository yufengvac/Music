package com.vac.music.fragments.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vac.music.R;
import com.vac.music.fragments.BaseSwipeBackFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vac on 16/8/5.
 *
 */
public class HomeMainFragment extends BaseSwipeBackFragment {

    private ViewPager viewPager;
    private TextView optionView1,optionView2;
    private FragmentPagerAdapter fragmentPagerAdapter;
    private List<Fragment> fragmentList = new ArrayList<>();
    public static HomeMainFragment newInstance(Bundle bundle){
        HomeMainFragment homeMainFragment = new HomeMainFragment();
        if (bundle!=null){
            homeMainFragment.setArguments(bundle);
        }
        return homeMainFragment;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_main_fragment,container,false);
        viewPager = (ViewPager) view.findViewById(R.id.home_main_fragment_viewPager);
        optionView1 = (TextView)view.findViewById(R.id.home_main_fragment_option1);
        optionView2 = (TextView)view.findViewById(R.id.home_main_fragment_option2);

        fragmentList.add(LocalMusicFragment.newInstance(null));
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
        return attachToSwipeBack(view);
    }
}

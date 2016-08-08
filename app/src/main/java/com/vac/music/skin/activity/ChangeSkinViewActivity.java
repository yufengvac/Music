package com.vac.music.skin.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.RadioButton;

import com.vac.music.R;
import com.vac.music.skin.listener.OnSkinChangeListener;
import com.vac.music.skin.presenter.ChangeSkinPresener;
import com.vac.music.skin.view.IChangeSkinView;

import java.util.ArrayList;
import java.util.List;

public class ChangeSkinViewActivity extends AppCompatActivity implements IChangeSkinView {

//    private ViewPager viewPager;
    private FragmentPagerAdapter fragmentPagerAdapter;
    private List<Fragment> fragmentList = new ArrayList<>();

    private ChangeSkinPresener changeSkinPresener = new ChangeSkinPresener(this);

    private RadioButton radioButton1,radioButton2,radioButton3;

    private static List<OnSkinChangeListener> skinChangeListenerList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_skin);

        ViewPager viewPager = (ViewPager) findViewById(R.id.change_skin_viewPager);
        fragmentPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
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

        radioButton1 = (RadioButton)findViewById(R.id.change_skin_rb1);
        radioButton2 = (RadioButton)findViewById(R.id.change_skin_rb2);
        radioButton3 = (RadioButton)findViewById(R.id.change_skin_rb3);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                radioButton1.setChecked(false);
                radioButton2.setChecked(false);
                radioButton3.setChecked(false);
                if (position==0){
                    radioButton1.setChecked(true);
                }else if (position==1){
                    radioButton2.setChecked(true);
                }else if (position==2){
                    radioButton3.setChecked(true);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        changeSkinPresener.initFragment();
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        View view = getWindow().getDecorView();
        view.setPadding(0,0,0,0);
        WindowManager.LayoutParams lp = (WindowManager.LayoutParams) view.getLayoutParams();
        lp.gravity = Gravity.BOTTOM;
        lp.x = 0;
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        getWindowManager().updateViewLayout(view,lp);
    }

    @Override
    public void setData(List<Fragment> fragments) {
        fragmentList.clear();
        if (fragments!=null){
            fragmentList.addAll(fragments);
            fragmentPagerAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public List<OnSkinChangeListener> getListener() {
        return skinChangeListenerList;
    }

    public static void addOnSkinChangeListener(OnSkinChangeListener skinChangeListener){
        if (!skinChangeListenerList.contains(skinChangeListener)){
            skinChangeListenerList.add(skinChangeListener);
        }
    }


}

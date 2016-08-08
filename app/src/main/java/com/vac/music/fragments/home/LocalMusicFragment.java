package com.vac.music.fragments.home;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.vac.music.R;
import com.vac.music.fragments.BaseSwipeBackFragment;
import com.vac.music.fragments.test.TestFragment;
import com.vac.music.myview.MyScrollView;
import com.vac.music.skin.activity.ChangeSkinViewActivity;
import com.vac.music.skin.listener.OnSkinChangeListener;
import com.vac.music.utils.ShareUtil;

/**
 * Created by vac on 16/8/5.
 *
 */
public class LocalMusicFragment extends BaseSwipeBackFragment implements OnSkinChangeListener{
    private static final String TAG = LocalMusicFragment.class.getSimpleName();
    private TextView textView;

    private RelativeLayout module_1,module_2,module_3,module_4;
    private LinearLayout favor_layout,favor_songlist_layout,create_songlist_layout;
    private MyScrollView scrollView;
    private onFragmentScrollViewListener onFragmentScrollViewListener;

    private OnSkinChangeListener skinChangeListener1,skinChangeListener2;

    private ImageView bgImageView;
    public static LocalMusicFragment newInstance(Bundle bundle){
        LocalMusicFragment localMusicFragment = new LocalMusicFragment();
        if (bundle!=null){
            localMusicFragment.setArguments(bundle);
        }
        return localMusicFragment;
    }
    public void setOnScrollListener(onFragmentScrollViewListener listener){
        this.onFragmentScrollViewListener = listener;
    }
    public void addOnSkinChangeListener(OnSkinChangeListener listener,OnSkinChangeListener listener1){
        this.skinChangeListener1 = listener;
        this.skinChangeListener2 = listener1;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.local_music_fragment,container,false);

//        textView =(TextView)view.findViewById(R.id.local_music_fragment_textview);
//        textView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (mAddFragmentListener != null) {
//                    mAddFragmentListener.onAddFragment(LocalMusicFragment.this, TestFragment.newInstance(null));
//                }
//            }
//        });
        module_1 =(RelativeLayout) view.findViewById(R.id.local_music_fra_module_1);
        module_2 =(RelativeLayout) view.findViewById(R.id.local_music_fra_module_2);
        module_3 =(RelativeLayout) view.findViewById(R.id.local_music_fra_module_3);
        module_4 =(RelativeLayout) view.findViewById(R.id.local_music_fra_module_4);
        favor_layout = (LinearLayout) view.findViewById(R.id.local_music_fra_module_favor);
        favor_songlist_layout = (LinearLayout) view.findViewById(R.id.local_music_fra_module_songlist_favor);
        create_songlist_layout = (LinearLayout) view.findViewById(R.id.local_music_fra_crate_songlist);

        module_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mAddFragmentListener!=null){
                    TestFragment testFragment = TestFragment.newInstance(null);
                    mAddFragmentListener.onAddFragment(LocalMusicFragment.this,testFragment);
                }
            }
        });

        scrollView = (MyScrollView) view.findViewById(R.id.local_music_fra_scrollView);
        scrollView.setOnScrollListener(new MyScrollView.OnScrollListener() {
            @Override
            public void onScroll(int scrollY) {
               onFragmentScrollViewListener.onScroll(scrollY);
            }
        });

        bgImageView = (ImageView) view.findViewById(R.id.local_music_bg);
        bgImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChangeSkinViewActivity.addOnSkinChangeListener(skinChangeListener1);
                ChangeSkinViewActivity.addOnSkinChangeListener(skinChangeListener2);
                ChangeSkinViewActivity.addOnSkinChangeListener(LocalMusicFragment.this);
                startActivity(new Intent(getActivity(), ChangeSkinViewActivity.class));
            }
        });
        updateUIColor();
        return attachToSwipeBack(view);
    }

    @Override
    public void onSkinChange(int alpha, int red, int green, int blue) {
        changeColor(alpha,red,green,blue);
    }

    public interface onFragmentScrollViewListener{
        void onScroll(int scrollY);
    }

    private void updateUIColor(){
        int alpha = ShareUtil.getBaseColor_A(getActivity());
        int red = ShareUtil.getBaseColor_R(getActivity());
        int green = ShareUtil.getBaseColor_G(getActivity());
        int blue = ShareUtil.getBaseColor_B(getActivity());
        changeColor(alpha,red,green,blue);
    }
    private void changeColor(int alpha,int red,int green,int blue){
        int color = Color.argb(alpha,red,green,blue);
        int lightColor = Color.argb(alpha-0x55,red,green,blue);
        if (!(alpha==0&&red==0&&green==0&&blue==0)){
            module_1.setBackgroundColor(color);
            module_2.setBackgroundColor(lightColor);
            module_3.setBackgroundColor(lightColor);
            module_4.setBackgroundColor(color);

            favor_layout.setBackgroundColor(color);
            favor_songlist_layout.setBackgroundColor(color);
            create_songlist_layout.setBackgroundColor(color);
        }
    }

}

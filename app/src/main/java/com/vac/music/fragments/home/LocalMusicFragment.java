package com.vac.music.fragments.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.vac.music.R;
import com.vac.music.fragments.BaseSwipeBackFragment;
import com.vac.music.fragments.test.TestFragment;
import com.vac.music.myview.MyScrollView;

/**
 * Created by vac on 16/8/5.
 *
 */
public class LocalMusicFragment extends BaseSwipeBackFragment {
    private static final String TAG = LocalMusicFragment.class.getSimpleName();
    private TextView textView;

    private RelativeLayout module_1;
    private MyScrollView scrollView;
    private onFragmentScrollViewListener onFragmentScrollViewListener;
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
        return attachToSwipeBack(view);
    }

    public interface onFragmentScrollViewListener{
        void onScroll(int scrollY);
    }

}

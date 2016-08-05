package com.vac.music.fragments.test;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vac.music.R;
import com.vac.music.fragments.BaseSwipeBackFragment;

/**
 * Created by vac on 16/8/5.
 *
 */
public class TestFragment extends BaseSwipeBackFragment {
    public static TestFragment newInstance(Bundle bundle){
        TestFragment testFragment = new TestFragment();
        if (bundle!=null){
            testFragment.setArguments(bundle);
        }
        return testFragment;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.test_fragment,container,false);
        return attachToSwipeBack(view);
    }
}

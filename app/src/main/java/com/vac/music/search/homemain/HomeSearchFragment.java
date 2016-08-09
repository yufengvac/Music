package com.vac.music.search.homemain;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.vac.music.R;
import com.vac.music.fragments.BaseSwipeBackFragment;
import com.vac.music.utils.ShareUtil;

/**
 * Created by vac on 16/8/9.
 * 搜索fragment
 */
public class HomeSearchFragment extends BaseSwipeBackFragment{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_search_fragment,container,false);
        initView(view);
        return attachToSwipeBack(view);
    }
    private void initView(View view){
        LinearLayout topLayout = (LinearLayout) view.findViewById(R.id.home_search_top_fragment_content);
        int[] color = ShareUtil.getBaseColor(getActivity());
        topLayout.setBackgroundColor(Color.argb(color[0],color[1],color[2],color[3]));

        ImageView backImageView = (ImageView) view.findViewById(R.id.home_search_top_back_imageview);
        backImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });
    }
}

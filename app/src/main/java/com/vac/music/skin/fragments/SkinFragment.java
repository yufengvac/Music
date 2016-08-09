package com.vac.music.skin.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.vac.music.R;
import com.vac.music.beans.BaseColor;
import com.vac.music.skin.listener.OnSkinChangeListener;
import com.vac.music.utils.ShareUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vac on 16/8/8.
 *
 */
public class SkinFragment  extends Fragment implements View.OnClickListener {
    private ImageView imageView1,imageView2,imageView3;
    private ImageLoader imageLoader;
    private ArrayList<BaseColor> baseColorArrayList;
    private List<OnSkinChangeListener> skinChangeListenerList = new ArrayList<>();
    private int index;
    private ArrayList<String> urlList;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.skin_fragment,container,false);
        imageView1 = (ImageView)view.findViewById(R.id.skin_imageview_1);
        imageView2 = (ImageView)view.findViewById(R.id.skin_imageview_2);
        imageView3 = (ImageView)view.findViewById(R.id.skin_imageview_3);
        imageView1.setOnClickListener(this);
        imageView2.setOnClickListener(this);
        imageView3.setOnClickListener(this);

        imageLoader = ImageLoader.getInstance();

        Bundle bundle = getArguments();
        if (bundle!=null){
            urlList = bundle.getStringArrayList("urlList");
            index = bundle.getInt("index");
            imageLoader.displayImage(urlList!=null?urlList.get(index*3):"",imageView1);
            imageLoader.displayImage(urlList!=null?urlList.get(1+index*3):"",imageView2);
            imageLoader.displayImage(urlList!=null?urlList.get(2+index*3):"",imageView3);

            baseColorArrayList = bundle.getParcelableArrayList("colorList");
        }
        return view;
    }

    @Override
    public void onClick(View view) {
        if (baseColorArrayList==null){
            return;
        }
        int id = view.getId();
        BaseColor baseColor=null;
        String picUrl = null;
        if (id==R.id.skin_imageview_1){
            baseColor = baseColorArrayList.get(index*3);
            picUrl = urlList.get(index*3);
        }else if (id == R.id.skin_imageview_2){
            baseColor = baseColorArrayList.get(1+index*3);
            picUrl = urlList.get(1+index*3);
        }else if (id == R.id.skin_imageview_3){
            baseColor = baseColorArrayList.get(2+index*3);
            picUrl = urlList.get(2+index*3);
        }
        if (baseColor==null||picUrl==null){
            return;
        }

        ShareUtil.saveMainPicture(getActivity(),picUrl);
        ShareUtil.saveBaseColor(getActivity(),baseColor.getAlpha(),baseColor.getRed(),baseColor.getGreen(),baseColor.getBlue());
        for (int i=0;i<skinChangeListenerList.size();i++){
            skinChangeListenerList.get(i).onSkinChange(baseColor.getAlpha(),baseColor.getRed()
                    ,baseColor.getGreen(),baseColor.getBlue(),picUrl);
        }
    }

    public void addOnSkinChangeListener(List<OnSkinChangeListener> skinChangeListeners){
        if (skinChangeListeners!=null){
            skinChangeListenerList.clear();
            skinChangeListenerList.addAll(skinChangeListeners);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (skinChangeListenerList!=null){
            skinChangeListenerList.clear();
        }
    }
}

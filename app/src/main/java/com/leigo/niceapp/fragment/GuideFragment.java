package com.leigo.niceapp.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2015/3/20.
 */
public class GuideFragment extends Fragment {

    public static GuideFragment newInstance(int layoutResId) {
        GuideFragment f = new GuideFragment();
        Bundle args = new Bundle();
        args.putInt("LayoutId", layoutResId);
        f.setArguments(args);
        return f;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        int layoutId = getArguments().getInt("LayoutId");
        return inflater.inflate(layoutId, container, false);
    }


}

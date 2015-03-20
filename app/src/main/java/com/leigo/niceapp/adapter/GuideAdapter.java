package com.leigo.niceapp.adapter;


import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentPagerAdapter;

import com.leigo.niceapp.R;
import com.leigo.niceapp.fragment.GuideFragment;

import java.util.ArrayList;

/**
 * Created by Administrator on 2015/3/20.
 */
public class GuideAdapter extends FragmentPagerAdapter {

    private ArrayList<Fragment> fragmentList = new ArrayList<Fragment>();

    public GuideAdapter(FragmentManager fm) {
        super(fm);
        fragmentList.add(GuideFragment.newInstance(R.layout.guide_fragment_1));
        fragmentList.add(GuideFragment.newInstance(R.layout.guide_fragment_2));
        fragmentList.add(GuideFragment.newInstance(R.layout.guide_fragment_3));
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }
}

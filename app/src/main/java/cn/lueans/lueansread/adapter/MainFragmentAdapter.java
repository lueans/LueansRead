package cn.lueans.lueansread.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;

import java.util.ArrayList;

/**
 * Created by 24277 on 2017/2/23.
 * mainactivity 的viewpager适配器
 */

public class MainFragmentAdapter extends FragmentStatePagerAdapter {
    private ArrayList<Fragment> mFragments;
    private String[] mTitle;

    public MainFragmentAdapter(FragmentManager fm, ArrayList<Fragment> fragments, String[] title) {
        super(fm);
        mFragments = fragments;
        mTitle = title;
    }



    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitle[position];
    }

    @Override
    public int getItemPosition(Object object) {
        return PagerAdapter.POSITION_NONE;
    }
}

package com.wonderful.tonybook.chart.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.wonderful.tonybook.chart.fragment.ChartBarFragment;
import com.wonderful.tonybook.chart.fragment.ChartPieFragment;

/**
 * 图表viewPager适配器
 * Created by KXwon on 2016/10/19.
 */

public class ChartPagerAdapter extends FragmentStatePagerAdapter{

    private static final int PAGE_COUNT = 2;  //指示器个数
    private static final String[] CHART_TITLES = {"饼图", "条形图"};
    private static final String CHART_TAG = "chart_tag";

    public ChartPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Bundle bundle = new Bundle();
        Fragment fragment = null;
        switch (position) {
            case 0:
                //饼图
                fragment = new ChartPieFragment();
                bundle.putString(CHART_TAG, CHART_TITLES[0]);
                fragment.setArguments(bundle);
                break;
            case 1:
                //条形图
                fragment = new ChartBarFragment();
                bundle.putString(CHART_TAG, CHART_TITLES[1]);
                fragment.setArguments(bundle);
                break;
            default:
                break;
        }
        return fragment;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return CHART_TITLES[position];
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }
}

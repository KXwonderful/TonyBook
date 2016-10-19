package com.wonderful.tonybook.chart.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.wonderful.tonybook.R;
import com.wonderful.tonybook.chart.adapter.ChartPagerAdapter;
import com.wonderful.tonybook.common.base.fragment.BaseFragment;

import butterknife.BindView;

/**
 * 图表fragment
 * Created by KXwon on 2016/10/19.
 */

public class ChartFragment extends BaseFragment{

    @BindView(R.id.tl_chart_pager_tabs)
    TabLayout mTabLayout;

    @BindView(R.id.vp_chart_pager)
    ViewPager mViewPager;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_chart;
    }

    @Override
    public void initViews(View view) {

        final ChartPagerAdapter adapter = new ChartPagerAdapter(getFragmentManager());
        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    @Override
    public void loadData() {

    }
}

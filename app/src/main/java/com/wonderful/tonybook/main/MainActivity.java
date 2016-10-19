package com.wonderful.tonybook.main;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.wonderful.tonybook.R;
import com.wonderful.tonybook.chart.fragment.ChartFragment;
import com.wonderful.tonybook.common.base.activity.BaseActivity;
import com.wonderful.tonybook.common.utils.ImageLoader;
import com.wonderful.tonybook.common.utils.MyToastUtils;
import com.wonderful.tonybook.home.fragment.HomeFragment;
import com.wonderful.tonybook.tip.fragment.TipFragment;
import com.wonderful.tonybook.turnover.fragment.TurnoverFragment;

import butterknife.BindView;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;           //抽屉

    @BindView(R.id.nav_view)
    NavigationView navigationView;


    private boolean isBackPressed;         //是否双击返回键

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSupportActionBar(mToolbar);

        // 放在onCreate中，toggle点击事件才有效
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        // 设置状态栏
        navViewToTop();

        // 设置进入默认页面
        selectItem(0);
    }


    @Override
    protected int initLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {

        // 设置侧边监听
        navigationView.setNavigationItemSelectedListener(this);

        // 设置圆形头像
        ImageView icon = (ImageView) navigationView.getHeaderView(0).findViewById(R.id.nav_head_icon);
        ImageLoader.loadCircle(mContext, R.mipmap.nav_head_icon, icon);

    }

    @Override
    protected void initData() {

    }

    /**
     * 返回按钮监听
     */
    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if (isBackPressed) {
                super.onBackPressed();
                return;
            }

            isBackPressed = true;

            Snackbar.make(drawer, "双击退出", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    isBackPressed = false;
                }
            }, 2000);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // 首页
            selectItem(0);

        } else if (id == R.id.nav_turnover) {
            // 流水
            selectItem(1);

        } else if (id == R.id.nav_chart) {
            // 图表
            selectItem(2);

        } else if (id == R.id.nav_tip) {
            // 理财
            selectItem(3);

        } else if (id == R.id.nav_setting) {
            // 设置
            selectItem(4);

        } else if (id == R.id.nav_about) {
            // 关于
            selectItem(5);

        }

        mToolbar.setTitle(item.getTitle());

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * 左侧drawer选择事件
     * @param position
     */
    public void selectItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                //首页
                fragment = new HomeFragment();
                break;
            case 1:
                //流水
                fragment = new TurnoverFragment();
                break;
            case 2:
                //图表
                fragment = new ChartFragment();
                break;
            case 3:
                //理财小知识
                fragment = new TipFragment();
                break;
            case 4:
                //设置
                openSetting();
                break;
            case 5:
                //关于
                openAbout();
                break;
            default:
                break;
        }

        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.fl_content, fragment).commit();
        } else {
            //LogUtil.e("MainActivity", "Error in creating fragment");
        }
    }

    /**
     * 打开设置
     */
    private void openSetting() {
        MyToastUtils.showShort(R.string.text_test);
    }

    /**
     * 打开关于
     */
    private void openAbout() {
        MyToastUtils.showShort(R.string.text_test);
    }

    /**
     * 设置状态栏透明
     */
    private void navViewToTop() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            drawer.setFitsSystemWindows(true);
            drawer.setClipToPadding(false);
        }
    }
}

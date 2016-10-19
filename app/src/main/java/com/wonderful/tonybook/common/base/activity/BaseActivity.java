package com.wonderful.tonybook.common.base.activity;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * activity基类
 * Created by KXwon on 2016/10/19.
 */

public abstract class BaseActivity extends AppCompatActivity{

    protected Context mContext;
    protected Unbinder mBinder;

    /**
     * 初始化布局id
     * @return 布局id
     */
    protected abstract int initLayoutId();

    /**
     * 初始化布局
     */
    protected abstract void initView();

    /**
     * 初始化数据
     */
    protected abstract void initData();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        //initLayoutId();
        super.onCreate(savedInstanceState);
        setContentView(initLayoutId());

        mContext = this;
        mBinder = ButterKnife.bind(this);

        translucentStatusBar();

        initData();

        initView();
    }

    /**
     * 状态栏着色
     */
    private void translucentStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//5.0及以上
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {//4.4到5.0
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
        }
    }

    @Override
    protected void onDestroy() {
        // 取消绑定
        mBinder.unbind();
        super.onDestroy();
    }
}

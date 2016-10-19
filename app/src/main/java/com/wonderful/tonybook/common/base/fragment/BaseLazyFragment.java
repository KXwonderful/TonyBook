package com.wonderful.tonybook.common.base.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wonderful.tonybook.common.base.activity.BaseActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 懒加载fragment基类
 * Created by KXwon on 2016/10/19.
 */

public abstract class BaseLazyFragment extends Fragment {

    protected BaseActivity mActivity;
    protected Unbinder mBinder;

    /**
     * 控件是否初始化完成
     */
    private boolean isViewCreated;
    /**
     * 数据是否已加载完毕
     */
    private boolean isLoadDataCompleted;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (BaseActivity) context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), container, false);
        mBinder = ButterKnife.bind(this, view);
        initViews(view);
        isViewCreated = true;
        return view;
    }

    /**
     * 获取布局id
     * @return
     */
    public abstract int getLayoutId();

    /**
     * 初始化布局
     * @param view
     */
    public abstract void initViews(View view);

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && isViewCreated && !isLoadDataCompleted) {
            isLoadDataCompleted = true;
            loadData();
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (getUserVisibleHint()) {
            isLoadDataCompleted = true;
            loadData();
        }
    }

    @Override
    public void onDestroyView() {
        mBinder.unbind();
        super.onDestroyView();
    }

    /**
     * 子类实现加载数据的方法
     */
    public abstract  void loadData();
}

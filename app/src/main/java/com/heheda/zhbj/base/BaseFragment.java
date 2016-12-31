package com.heheda.zhbj.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * leftmenufragment 和 contentfragment 的基类
 * Created by cmm on 2016/12/19.
 */

public abstract class BaseFragment extends Fragment {

    public Context ctx;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ctx=getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return initView();
    }

    /**
     * 初始化一个view ，让子类实现此方法 达到特有的效果
     * @return
     */
    public abstract View initView();


    /**
     *当fragment所依赖的activity被创建时 初始化自己（fragment）的数据
     *
     * @param savedInstanceState
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initData();
    }

    /**
     * 1.如果子页面没有数据，联网请求数据，并且绑定到initView初始化的视图上
     * 2.绑定到initView初始化的视图上
     */
    public  void initData(){

    };
}

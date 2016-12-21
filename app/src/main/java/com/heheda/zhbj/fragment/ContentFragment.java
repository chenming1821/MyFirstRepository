package com.heheda.zhbj.fragment;

import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;


import com.heheda.zhbj.R;
import com.heheda.zhbj.base.BaseFragment;

/**
 * Created by cmm on 2016/12/19.
 */

public class ContentFragment extends BaseFragment {

    private ViewPager viewpager;
    private RadioGroup rdgroup;

    @Override
    public View initView() {

        View view = View.inflate(ctx, R.layout.content_fargment, null);

        viewpager= (ViewPager) view.findViewById(R.id.viewpager);
        rdgroup= (RadioGroup) view.findViewById(R.id.radio_group_bottom);

        return view;
    }

    @Override
    public void initData() {
        super.initData();


         //默认是选中主页面、
        rdgroup.check(R.id.radiobtn_home);
    }
}

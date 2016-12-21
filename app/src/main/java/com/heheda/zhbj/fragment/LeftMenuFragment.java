package com.heheda.zhbj.fragment;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import com.heheda.zhbj.R;
import com.heheda.zhbj.base.BaseFragment;

/**
 * Created by cmm on 2016/12/19.
 */

public class LeftMenuFragment extends BaseFragment {
    private TextView tv;

    @Override
    public  View initView() {

        tv=new TextView(ctx);

        tv.setTextColor(Color.RED);

        tv.setTextSize(20);




        return tv;
    }

    @Override
    public void initData() {
        super.initData();

        tv.setText("我是菜单");
    }
}

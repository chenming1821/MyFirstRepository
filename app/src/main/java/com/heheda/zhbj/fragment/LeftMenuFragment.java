package com.heheda.zhbj.fragment;

import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.heheda.zhbj.R;
import com.heheda.zhbj.activity.MainActivity;
import com.heheda.zhbj.base.BaseFragment;
import com.heheda.zhbj.domain.NewsCenterBean;
import com.heheda.zhbj.pager.NewsCenterPager;

import org.xutils.common.util.DensityUtil;

import java.util.List;

/**
 * Created by cmm on 2016/12/19.
 */

public class LeftMenuFragment extends BaseFragment {
    //private TextView tv;

//    private List<newsCenterJavaBean.DataBean> leftdatas;
    private List<NewsCenterBean.newsData> leftdatas;


    private ListView listView;

    private leftmenuAdapter adapter;


    private int lastPosition=0;

    public List<NewsCenterBean.newsData>  getLeftdatas() {
        return leftdatas;
    }

    public void setLeftdatas(List<NewsCenterBean.newsData> leftdatas) {
        this.leftdatas = leftdatas;



        adapter = new leftmenuAdapter();
        if (listView != null) {
            listView.setAdapter(adapter);
        }

        //记录； 默认选中上次的详情页面
        switchPager(lastPosition);
    }


    @Override
    public View initView() {

        listView = new ListView(ctx);

        listView.setPadding(0, DensityUtil.dip2px(ctx, 40), 0, 0);
        listView.setDividerHeight(0); //设置条目之间的分割线高度为0

        //listView.setCacheColorHint(Color.TRANSPARENT); //

        //设置listview 的item 不变色 2.3以下为黄色
        listView.setSelector(android.R.color.transparent);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // 记录点击的item ,并修改为红色
                lastPosition = position;
                adapter.notifyDataSetChanged(); // adapter 自刷新

                //然后呢 关闭掉侧拉测单
                MainActivity mainActivity = (MainActivity) ctx;
                mainActivity.getSlidingMenu().toggle(); //

                // 打开leftmenu的item 对应的 子页面的详情内容， 新闻，专题，组图，互动，投票

                switchPager(position);


            }
        });

        return listView;
    }


    private void switchPager(int position) {
        MainActivity mainActivity =(MainActivity) ctx;
        ContentFragment contentFragment = mainActivity.getContentFragment();

        NewsCenterPager centerPager = contentFragment.getNewsCenterPager();

        centerPager.switchPager(position);
    }

    @Override
    public void initData() {
        super.initData();
        // tv.setText("我是菜单");
    }


    class leftmenuAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return leftdatas.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView textView = (TextView) View.inflate(ctx, R.layout.leftmenu_item, null);

            textView.setText(leftdatas.get(position).getTitle());


            textView.setEnabled(position == lastPosition); //

            return textView;
        }
    }
}

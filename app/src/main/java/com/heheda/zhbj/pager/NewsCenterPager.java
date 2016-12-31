package com.heheda.zhbj.pager;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.heheda.zhbj.activity.MainActivity;
import com.heheda.zhbj.base.BasePager;
import com.heheda.zhbj.base.LeftMenuItemDetailBasePager;
import com.heheda.zhbj.domain.newsCenterJavaBean;
import com.heheda.zhbj.fragment.LeftMenuFragment;
import com.heheda.zhbj.newsdetail.InterDetailpager;
import com.heheda.zhbj.newsdetail.NewsDetailpager;
import com.heheda.zhbj.newsdetail.PhotoDetailpager;
import com.heheda.zhbj.newsdetail.TopicDetailpager;
import com.heheda.zhbj.utils.LogUtil;
import com.heheda.zhbj.utils.constans;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：尚硅谷-杨光福 on 2016/8/15 09:53
 * 微信：yangguangfu520
 * QQ号：541433511
 * 作用：新闻中心
 */
public class NewsCenterPager extends BasePager {

    private List<LeftMenuItemDetailBasePager> detailPagers;

    public NewsCenterPager(Context context) {
        super(context);

    }

    @Override
    public void initData() {
        super.initData();



        //1.设置标题
        tv_title.setText("新闻中心");
        //2.联网请求，得到数据，创建视图
        TextView textView = new TextView(context);
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(Color.RED);
        textView.setTextSize(25);


        //3.把子视图添加到BasePager的FrameLayout中
        fl_content.addView(textView);
        //4.绑定数据
        textView.setText("新闻中心内容");

        //switchPager(0);

        ibtn.setVisibility(View.VISIBLE);
        ibtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity mainActivity=(MainActivity) context;
                mainActivity.getSlidingMenu().toggle(); //
            }
        });

        //联网请求数据
        getDataFromNet();

    }


    /**
     * xutis联网请求数据
     */
    private void getDataFromNet() {
        RequestParams params = new RequestParams(constans.newsUri);

        x.http().get(params, new Callback.CommonCallback<String>() {


            @Override
            public void onSuccess(String result) {
                LogUtil.e("xutils" + result);

                // 请求成功后 ，需要解析json
                processjson(result);

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                LogUtil.e("xutils" + ex.getMessage()); //请求失败的日志
            }

            @Override
            public void onCancelled(CancelledException cex) {
                //联网请求取消
            }

            @Override
            public void onFinished() {

                LogUtil.e("联网请求数据结束");

            }
        });
    }


    /**
     * 解析并显示数据
     *
     * @param result
     * @return
     */
    private void processjson(String result) {

        //解析json 为javabean
        newsCenterJavaBean newsCenterBean = parserjson(result);


        //给左侧菜单传递数据
        MainActivity mainActivity = (MainActivity) this.context;
        LeftMenuFragment leftMenuFragment = mainActivity.getleftMenuFragment();

        detailPagers = new ArrayList<>();  //初始化详情页面
        detailPagers.add(new NewsDetailpager(context));
        detailPagers.add(new TopicDetailpager(context));
        detailPagers.add(new PhotoDetailpager(context));
        detailPagers.add(new InterDetailpager(context));

        //
         data =   newsCenterBean.getData();
        leftMenuFragment.setLeftdatas(data);


    }
    private List<newsCenterJavaBean.DataBean> data;
    /**
     * 利用Gson解析json为Javabean
     *
     * @param result
     * @return
     */
    private newsCenterJavaBean parserjson(String result) {

        return new Gson().fromJson(result, newsCenterJavaBean.class);
    }

    public void switchPager(int position) {

        tv_title.setText(data.get(position).getTitle());

        fl_content.removeAllViews();
        //添加 子页面详情
        LeftMenuItemDetailBasePager currentDetailpager = detailPagers.get(position);
        View view=currentDetailpager.rootview; //
        currentDetailpager.initData(); //给上面的view填充数据，让其显示出样子

        fl_content.addView(view);


    }
}

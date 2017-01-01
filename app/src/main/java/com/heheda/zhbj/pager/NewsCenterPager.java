package com.heheda.zhbj.pager;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.heheda.zhbj.activity.MainActivity;
import com.heheda.zhbj.base.BasePager;
import com.heheda.zhbj.base.LeftMenuItemDetailBasePager;
import com.heheda.zhbj.domain.NewsCenterBean;
import com.heheda.zhbj.fragment.LeftMenuFragment;
import com.heheda.zhbj.newsdetail.InterDetailpager;
import com.heheda.zhbj.newsdetail.NewsDetailpager;
import com.heheda.zhbj.newsdetail.PhotoDetailpager;
import com.heheda.zhbj.newsdetail.TopicDetailpager;
import com.heheda.zhbj.utils.CacheUtils;
import com.heheda.zhbj.utils.LogUtil;
import com.heheda.zhbj.utils.constans;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
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
                MainActivity mainActivity = (MainActivity) context;
                mainActivity.getSlidingMenu().toggle(); //
            }
        });

        // 从缓存中读取数据
        String newscache = CacheUtils.getNewsCenterCatche(context, constans.newsUri);

        if (!TextUtils.isEmpty(newscache)) {
           processjson(newscache);
        }

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
                //做缓存处理
                CacheUtils.saveNewsCache(context,constans.newsUri,result);

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
     * 解析json并传递和显示解析后的数据
     * @param result
     * @return
     */
    private void processjson(String result) {

        //调用手动解析json方法
        NewsCenterBean newsCenterBean2 = parserjson2(result);

        //给左侧菜单传递数据
        MainActivity mainActivity = (MainActivity) this.context;
        LeftMenuFragment leftMenuFragment = mainActivity.getleftMenuFragment();
        //
        data = newsCenterBean2.getData();

        //初始化详情页面
        detailPagers = new ArrayList<>();
        detailPagers.add(new NewsDetailpager(context,data.get(0)));
        detailPagers.add(new TopicDetailpager(context));
        detailPagers.add(new PhotoDetailpager(context));
        detailPagers.add(new InterDetailpager(context));


        leftMenuFragment.setLeftdatas(data);


    }

    // private List<newsCenterJavaBean.DataBean> data;
    private List<NewsCenterBean.newsData> data;

    /**
     * 利用Gson解析json为Javabean
     *
     * @param result
     * @return
     */
    private NewsCenterBean parserjson(String result) {

        return new Gson().fromJson(result, NewsCenterBean.class);
    }


    /**
     * 手动解析json 、即利用jsonobject解析json
     *
     * @param result
     * @return
     */
    private NewsCenterBean parserjson2(String result) {

        NewsCenterBean bean = new NewsCenterBean();

        try {
            JSONObject object = new JSONObject(result);

            int retcode = object.optInt("retcode");
            bean.setRetcode(retcode);

            JSONArray extend = object.optJSONArray("extend");


            if (extend != null && extend.length() > 0) {
                List<Integer> extendlist = new ArrayList<>();
                for (int k = 0; k < extend.length(); k++) {


                    extendlist.add((Integer) extend.get(k));

                }
                bean.setExtend(extendlist);
            }


            JSONArray data = object.optJSONArray("data");


            if (data != null && data.length() > 0) {
                List<NewsCenterBean.newsData> newsBeanlist = new ArrayList<>();
                bean.setData(newsBeanlist);
                for (int i = 0; i < data.length(); i++) {
                    NewsCenterBean.newsData newsDatabean = new NewsCenterBean.newsData();

                    newsBeanlist.add(newsDatabean);

                    JSONObject objdata = (JSONObject) data.get(i);

                    int id = objdata.optInt("id");
                    newsDatabean.setId(id);

                    int type = objdata.optInt("type");
                    newsDatabean.setType(type);


                    String title = objdata.optString("title");
                    newsDatabean.setTitle(title);

                    String url12 = objdata.optString("url");
                    newsDatabean.setUrl(url12);
                    String url1 = objdata.optString("url1");
                    newsDatabean.setUrl1(url1);
                    String dayurl = objdata.optString("dayurl");
                    newsDatabean.setDayurl(dayurl);
                    String excurl = objdata.optString("excurl");
                    newsDatabean.setExcurl(excurl);
                    String weekurl = objdata.optString("weekurl");
                    newsDatabean.setWeekurl(weekurl);

                    JSONArray children = objdata.optJSONArray("children");


                    if (children != null && children.length() > 0) {
                        List<NewsCenterBean.newsData.DataChildren> childrens = new ArrayList<>();
                        newsDatabean.setChildren(childrens);

                        for (int j = 0; j < children.length(); j++) {

                            JSONObject childobj = (JSONObject) children.get(j);
                            NewsCenterBean.newsData.DataChildren child = new NewsCenterBean.newsData.DataChildren();
                            childrens.add(child);

                            int id1 = childobj.optInt("id");
                            child.setId(id1);

                            int type1 = childobj.optInt("type");
                            child.setType(type1);

                            String title1 = childobj.optString("title");
                            child.setTitle(title1);

                            String url = childobj.optString("url");
                            child.setUrl(url);


                        }
                    }
                }

            }


        } catch (JSONException e) {
            e.printStackTrace();
        }


        return bean;
    }

    public void switchPager(int position) {

        tv_title.setText(data.get(position).getTitle());

        fl_content.removeAllViews();
        //添加 子页面详情
        LeftMenuItemDetailBasePager currentDetailpager = detailPagers.get(position);
        View view = currentDetailpager.rootview; //
        currentDetailpager.initData(); //给上面的view填充数据，让其显示出样子

        fl_content.addView(view);


    }
}

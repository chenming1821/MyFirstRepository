package com.heheda.zhbj.domain;

import java.util.List;

/**
 * Created by cmm on 2016/12/31.
 * 手动书写新闻中心Javabean
 */

public class NewsCenterBean {

    private int retcode;

    private List<newsData> data;

    private List<Integer> extend;


    public void setRetcode(int retcode) {
        this.retcode = retcode;
    }

    public void setExtend(List<Integer> extend) {
        this.extend = extend;
    }

    public void setData(List<newsData> data) {
        this.data = data;
    }

    public List<newsData> getData() {
        return data;
    }

    public int getRetcode() {
        return retcode;
    }

    public List<Integer> getExtend() {
        return extend;
    }

    @Override
    public String toString() {
        return "NewsCenterBean{" +
                "retcode=" + retcode +
                ", data=" + data +
                ", extend=" + extend +
                '}';
    }

    public static class newsData {
        private int id;
        private int type;
        private String title;
        private String url;
        private String url1;
        private String dayurl;
        private String excurl;
        private String weekurl;
        private List<DataChildren> children;


        public void setId(int id) {
            this.id = id;
        }

        public void setChildren(List<DataChildren> children) {
            this.children = children;
        }

        public void setType(int type) {
            this.type = type;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public void setUrl1(String url1) {
            this.url1 = url1;
        }

        public void setDayurl(String dayurl) {
            this.dayurl = dayurl;
        }

        public void setExcurl(String excurl) {
            this.excurl = excurl;
        }

        public void setWeekurl(String weekurl) {
            this.weekurl = weekurl;
        }


        public int getId() {
            return id;
        }

        public int getType() {
            return type;
        }

        public String getTitle() {
            return title;
        }

        public String getUrl() {
            return url;
        }

        public String getUrl1() {
            return url1;
        }

        public String getDayurl() {
            return dayurl;
        }

        public String getExcurl() {
            return excurl;
        }

        public String getWeekurl() {
            return weekurl;
        }

        public List<DataChildren> getChildren() {
            return children;
        }


        @Override
        public String toString() {
            return "newsData{" +
                    "id=" + id +
                    ", type=" + type +
                    ", title='" + title + '\'' +
                    ", url='" + url + '\'' +
                    ", url1='" + url1 + '\'' +
                    ", dayurl='" + dayurl + '\'' +
                    ", excurl='" + excurl + '\'' +
                    ", weekurl='" + weekurl + '\'' +
                    ", children=" + children +
                    '}';
        }

        public static class DataChildren {

            private int id;
            private int type;
            private String title;
            private String url;

            public void setId(int id) {
                this.id = id;
            }

            public void setType(int type) {
                this.type = type;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public int getId() {
                return id;
            }

            public int getType() {
                return type;
            }

            public String getTitle() {
                return title;
            }

            public String getUrl() {
                return url;
            }

            @Override
            public String toString() {
                return "DataChildren{" +
                        "id=" + id +
                        ", type=" + type +
                        ", title='" + title + '\'' +
                        ", url='" + url + '\'' +
                        '}';
            }
        }
    }

}

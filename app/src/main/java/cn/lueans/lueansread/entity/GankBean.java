package cn.lueans.lueansread.entity;

import java.util.ArrayList;

/**
 * Created by 24277 on 2017/2/23.
 * url http://gank.io/api/data/Android/10/1
 * 获取gank api的json数据类
 */
public class GankBean {

    /**
     * error : false
     * results : [{"_id":"58ace75e421aa957ef935316","createdAt":"2017-02-22T09:20:30.844Z","desc":"从Android到React Native开发，帮助你用Android开发来理解React Native","publishedAt":"2017-02-22T11:43:57.286Z","source":"web","type":"Android","url":"http://www.jianshu.com/p/97692b1c451d","used":true,"who":"Shuyu Guo"},{"_id":"58acfb1e421aa95810795b99","createdAt":"2017-02-22T10:44:46.857Z","desc":"Android 切换皮肤方案，轻松实现。","images":["http://img.gank.io/8597e694-d52b-440e-97c7-7faed55fb6cb"],"publishedAt":"2017-02-22T11:43:57.286Z","source":"chrome","type":"Android","url":"https://github.com/ximsfei/Android-skin-support","used":true,"who":"代码家"},{"_id":"58acfb94421aa95810795b9b","createdAt":"2017-02-22T10:46:44.710Z","desc":"通过描述来实现动画方案，好玩儿，实用。","images":["http://img.gank.io/92db4b47-01f1-412f-ae80-076ce700f81f","http://img.gank.io/657e009b-0b9b-4fe7-8ff9-2f5ced2f5032"],"publishedAt":"2017-02-22T11:43:57.286Z","source":"chrome","type":"Android","url":"https://github.com/florent37/ExpectAnim","used":true,"who":"代码家"},{"_id":"58ad0592421aa957ef93531c","createdAt":"2017-02-22T11:29:22.414Z","desc":"一个可以动态修改标记日期和显示预约列表的周日历。","images":["http://img.gank.io/35e80cdb-48e8-437a-bb46-8884076b6552"],"publishedAt":"2017-02-22T11:43:57.286Z","source":"web","type":"Android","url":"https://github.com/loonggg/WeekCalendar","used":true,"who":"loonggg"},{"_id":"58aa8ade421aa93d3a0a866d","createdAt":"2017-02-20T14:21:18.815Z","desc":"探秘Android消息机制（萌新的角度看源码）","publishedAt":"2017-02-21T11:14:09.564Z","source":"web","type":"Android","url":"https://zhuanlan.zhihu.com/p/25222485?refer=levent-j","used":true,"who":"Li Wenjing"},{"_id":"58ab81c7421aa93d3d15aa3e","createdAt":"2017-02-21T07:54:47.295Z","desc":"一款漂亮的每周日历组件。","images":["http://img.gank.io/a708c59d-7949-4ac0-855d-c46a2d1825b2"],"publishedAt":"2017-02-21T11:14:09.564Z","source":"chrome","type":"Android","url":"https://github.com/nomanr/weekcalendar","used":true,"who":"代码家"},{"_id":"58ab9732421aa93d376f74f1","createdAt":"2017-02-21T09:26:10.367Z","desc":"A mini and excellent Router Framwork一款小而美的路由框架。网页动态添加自定义参数启动应用。","images":["http://img.gank.io/fe97f318-5621-417a-9e83-0abc11ff3127"],"publishedAt":"2017-02-21T11:14:09.564Z","source":"web","type":"Android","url":"https://github.com/Jomes/routerSDK","used":true,"who":"Jomeslu"},{"_id":"58ab9fdd421aa93d376f74f3","createdAt":"2017-02-21T10:03:09.90Z","desc":"比原生 Snack 更漂亮的 Bottom Notification 库。","images":["http://img.gank.io/67940797-a360-499c-9972-88af31aeba37","http://img.gank.io/23c7eeca-1ea4-4e92-a521-90610d3a3fc1"],"publishedAt":"2017-02-21T11:14:09.564Z","source":"chrome","type":"Android","url":"https://github.com/matecode/Snacky","used":true,"who":"代码家"},{"_id":"58aba04e421aa93d3a0a8673","createdAt":"2017-02-21T10:05:02.451Z","desc":"Android HTML5，LaTeX 转换器，而且支持自定义标签。","images":["http://img.gank.io/5362b013-e9ed-4eba-85ce-e3fd50cbb3fa"],"publishedAt":"2017-02-21T11:14:09.564Z","source":"chrome","type":"Android","url":"https://github.com/daquexian/FlexibleRichTextView","used":true,"who":"代码家"},{"_id":"58aba446421aa93d3d15aa44","createdAt":"2017-02-21T10:21:58.551Z","desc":"SMS 验证小工具，自动帮你读取短信，然后填写短信验证码，中国同胞们可以贡献些规则上去。","images":["http://img.gank.io/442b635f-a7f4-4bbf-ba18-ecfaedec0ca5"],"publishedAt":"2017-02-21T11:14:09.564Z","source":"chrome","type":"Android","url":"https://github.com/stfalcon-studio/SmsVerifyCatcher","used":true,"who":"代码家"}]
     */

    private boolean error;
    private ArrayList<GankResultsBean> results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public ArrayList<GankResultsBean> getResults() {
        return results;
    }

    public void setResults(ArrayList<GankResultsBean> results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "GankBean{" +
                "error=" + error +
                ", results=" + results +
                '}';
    }

    public static class GankResultsBean {

        /**
         * _id : 58ace75e421aa957ef935316
         * createdAt : 2017-02-22T09:20:30.844Z
         * desc : 从Android到React Native开发，帮助你用Android开发来理解React Native
         * publishedAt : 2017-02-22T11:43:57.286Z
         * source : web
         * type : Android
         * url : http://www.jianshu.com/p/97692b1c451d
         * used : true
         * who : Shuyu Guo
         * images : ["http://img.gank.io/8597e694-d52b-440e-97c7-7faed55fb6cb"]
         */

        private String _id;
        private String createdAt;
        private String desc;
        private String publishedAt;
        private String source;
        private String type;
        private String url;
        private boolean used;
        private String who;
        private ArrayList<String> images;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getPublishedAt() {
            return publishedAt;
        }

        public void setPublishedAt(String publishedAt) {
            this.publishedAt = publishedAt;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public boolean isUsed() {
            return used;
        }

        public void setUsed(boolean used) {
            this.used = used;
        }

        public String getWho() {
            return who;
        }

        public void setWho(String who) {
            this.who = who;
        }

        public ArrayList<String> getImages() {
            return images;
        }

        public void setImages(ArrayList<String> images) {
            this.images = images;
        }

        @Override
        public String toString() {
            return "GankResultsBean{" +
                    "_id='" + _id + '\'' +
                    ", createdAt='" + createdAt + '\'' +
                    ", desc='" + desc + '\'' +
                    ", publishedAt='" + publishedAt + '\'' +
                    ", source='" + source + '\'' +
                    ", type='" + type + '\'' +
                    ", url='" + url + '\'' +
                    ", used=" + used +
                    ", who='" + who + '\'' +
                    ", images=" + images +
                    '}';
        }
    }
}

package cn.lueans.lueansread.entity;

import java.util.List;

/**
 * Created by 24277 on 2017/3/9.
 */

public class NewsListBean {


    /**
     * postid : CF2PNII200097U7R
     * hasCover : false
     * hasHead : 1
     * replyCount : 8918
     * ltitle : "王路飞"出名背后：知乎正走向伪高端！
     * hasImg : 1
     * digest : 前置重磅阅读1：知乎王路飞，你真的不是一个没有故事的男同学！前置重磅阅读2：他"分饰244角色"被封真不冤,因为都抄自网易网友所以在天朝想做严肃的传播知识的网站
     * hasIcon : true
     * docid : CF2PNII200097U7R
     * title : "王路飞"出名背后：知乎正走向伪高端！
     * order : 1
     * priority : 220
     * lmodify : 2017-03-09 12:11:09
     * boardid : tech_bbs
     * ads : [{"docid":"CCOFK3QC00097VKA","title":"网易公开课：让分享知识成为习惯","tag":"doc","imgsrc":"http://cms-bucket.nosdn.127.net/d8351bccafa64cbeb5101ac31b975abc20170302091635.jpeg","subtitle":"","url":"CCOFK3QC00097VKA"}]
     * topic_background : http://img2.cache.netease.com/m/newsapp/reading/cover1/C1348649554552.jpg
     * url_3w : http://tech.163.com/17/0309/07/CF2PNII200097U7R.html
     * template : recommend
     * votecount : 8525
     * alias : Tech
     * cid : C1348649554552
     * url : http://3g.163.com/tech/17/0309/07/CF2PNII200097U7R.html
     * hasAD : 1
     * source : 虎嗅网
     * ename : keji
     * subtitle :
     * imgsrc : http://cms-bucket.nosdn.127.net/8cbf4b91f6164b8092be6fe57cbcb8e520170309091129.jpeg
     * tname : 科技
     * ptime : 2017-03-09 07:51:23
     * skipID : 0AI20009|14641
     * skipType : photoset
     * imgextra : [{"imgsrc":"http://cms-bucket.nosdn.127.net/2e9f69f46d0a4998a0531e5b0fd175e420170309075416.jpeg"},{"imgsrc":"http://cms-bucket.nosdn.127.net/f81b56d91ff74f199fefddeb55188bf720170309075416.jpeg"}]
     * photosetID : 0AI20009|14641
     * imgsum : 14
     * editor : []
     * TAGS : 正在直播 视频
     * imgType : 1
     * live_info : {"mutilVideo":false,"pano":false,"end_time":"2017-03-09 18:00:00","user_count":587434,"roomId":119561,"start_time":"2017-03-09 10:15:00","type":0,"video":true}
     * TAG : 正在直播
     */

    private String postid;
    private boolean hasCover;
    private int hasHead;
    private int replyCount;
    private String ltitle;
    private int hasImg;
    private String digest;
    private boolean hasIcon;
    private String docid;
    private String title;
    private int order;
    private int priority;
    private String lmodify;
    private String boardid;
    private String topic_background;
    private String url_3w;
    private String template;
    private int votecount;
    private String alias;
    private String cid;
    private String url;
    private int hasAD;
    private String source;
    private String ename;
    private String subtitle;
    private String imgsrc;
    private String tname;
    private String ptime;
    private String skipID;
    private String skipType;
    private String photosetID;
    private int imgsum;
    private String TAGS;
    private int imgType;
    private String TAG;
    private List<AdsBean> ads;
    private List<ImgextraBean> imgextra;

    public String getPostid() {
        return postid;
    }

    public void setPostid(String postid) {
        this.postid = postid;
    }

    public boolean isHasCover() {
        return hasCover;
    }

    public void setHasCover(boolean hasCover) {
        this.hasCover = hasCover;
    }

    public int getHasHead() {
        return hasHead;
    }

    public void setHasHead(int hasHead) {
        this.hasHead = hasHead;
    }

    public int getReplyCount() {
        return replyCount;
    }

    public void setReplyCount(int replyCount) {
        this.replyCount = replyCount;
    }

    public String getLtitle() {
        return ltitle;
    }

    public void setLtitle(String ltitle) {
        this.ltitle = ltitle;
    }

    public int getHasImg() {
        return hasImg;
    }

    public void setHasImg(int hasImg) {
        this.hasImg = hasImg;
    }

    public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }

    public boolean isHasIcon() {
        return hasIcon;
    }

    public void setHasIcon(boolean hasIcon) {
        this.hasIcon = hasIcon;
    }

    public String getDocid() {
        return docid;
    }

    public void setDocid(String docid) {
        this.docid = docid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getLmodify() {
        return lmodify;
    }

    public void setLmodify(String lmodify) {
        this.lmodify = lmodify;
    }

    public String getBoardid() {
        return boardid;
    }

    public void setBoardid(String boardid) {
        this.boardid = boardid;
    }

    public String getTopic_background() {
        return topic_background;
    }

    public void setTopic_background(String topic_background) {
        this.topic_background = topic_background;
    }

    public String getUrl_3w() {
        return url_3w;
    }

    public void setUrl_3w(String url_3w) {
        this.url_3w = url_3w;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public int getVotecount() {
        return votecount;
    }

    public void setVotecount(int votecount) {
        this.votecount = votecount;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getHasAD() {
        return hasAD;
    }

    public void setHasAD(int hasAD) {
        this.hasAD = hasAD;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getImgsrc() {
        return imgsrc;
    }

    public void setImgsrc(String imgsrc) {
        this.imgsrc = imgsrc;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public String getPtime() {
        return ptime;
    }

    public void setPtime(String ptime) {
        this.ptime = ptime;
    }

    public String getSkipID() {
        return skipID;
    }

    public void setSkipID(String skipID) {
        this.skipID = skipID;
    }

    public String getSkipType() {
        return skipType;
    }

    public void setSkipType(String skipType) {
        this.skipType = skipType;
    }

    public String getPhotosetID() {
        return photosetID;
    }

    public void setPhotosetID(String photosetID) {
        this.photosetID = photosetID;
    }

    public int getImgsum() {
        return imgsum;
    }

    public void setImgsum(int imgsum) {
        this.imgsum = imgsum;
    }

    public String getTAGS() {
        return TAGS;
    }

    public void setTAGS(String TAGS) {
        this.TAGS = TAGS;
    }

    public int getImgType() {
        return imgType;
    }

    public void setImgType(int imgType) {
        this.imgType = imgType;
    }


    public String getTAG() {
        return TAG;
    }

    public void setTAG(String TAG) {
        this.TAG = TAG;
    }

    public List<AdsBean> getAds() {
        return ads;
    }

    public void setAds(List<AdsBean> ads) {
        this.ads = ads;
    }

    public List<ImgextraBean> getImgextra() {
        return imgextra;
    }

    public void setImgextra(List<ImgextraBean> imgextra) {
        this.imgextra = imgextra;
    }

    @Override
    public String toString() {
        return "NewsListBean{" +
                "ads=" + ads +
                ", postid='" + postid + '\'' +
                ", hasCover=" + hasCover +
                ", hasHead=" + hasHead +
                ", replyCount=" + replyCount +
                ", ltitle='" + ltitle + '\'' +
                ", hasImg=" + hasImg +
                ", digest='" + digest + '\'' +
                ", hasIcon=" + hasIcon +
                ", docid='" + docid + '\'' +
                ", title='" + title + '\'' +
                ", order=" + order +
                ", priority=" + priority +
                ", lmodify='" + lmodify + '\'' +
                ", boardid='" + boardid + '\'' +
                ", topic_background='" + topic_background + '\'' +
                ", url_3w='" + url_3w + '\'' +
                ", template='" + template + '\'' +
                ", votecount=" + votecount +
                ", alias='" + alias + '\'' +
                ", cid='" + cid + '\'' +
                ", url='" + url + '\'' +
                ", hasAD=" + hasAD +
                ", source='" + source + '\'' +
                ", ename='" + ename + '\'' +
                ", subtitle='" + subtitle + '\'' +
                ", imgsrc='" + imgsrc + '\'' +
                ", tname='" + tname + '\'' +
                ", ptime='" + ptime + '\'' +
                ", skipID='" + skipID + '\'' +
                ", skipType='" + skipType + '\'' +
                ", photosetID='" + photosetID + '\'' +
                ", imgsum=" + imgsum +
                ", TAGS='" + TAGS + '\'' +
                ", imgType=" + imgType +
                ", TAG='" + TAG + '\'' +
                ", imgextra=" + imgextra +
                '}';
    }

    public static class AdsBean {
        /**
         * docid : CCOFK3QC00097VKA
         * title : 网易公开课：让分享知识成为习惯
         * tag : doc
         * imgsrc : http://cms-bucket.nosdn.127.net/d8351bccafa64cbeb5101ac31b975abc20170302091635.jpeg
         * subtitle :
         * url : CCOFK3QC00097VKA
         */

        private String docid;
        private String title;
        private String tag;
        private String imgsrc;
        private String subtitle;
        private String url;

        public String getDocid() {
            return docid;
        }

        public void setDocid(String docid) {
            this.docid = docid;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }

        public String getImgsrc() {
            return imgsrc;
        }

        public void setImgsrc(String imgsrc) {
            this.imgsrc = imgsrc;
        }

        public String getSubtitle() {
            return subtitle;
        }

        public void setSubtitle(String subtitle) {
            this.subtitle = subtitle;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        @Override
        public String toString() {
            return "AdsBean{" +
                    "docid='" + docid + '\'' +
                    ", title='" + title + '\'' +
                    ", tag='" + tag + '\'' +
                    ", imgsrc='" + imgsrc + '\'' +
                    ", subtitle='" + subtitle + '\'' +
                    ", url='" + url + '\'' +
                    '}';
        }
    }

    public static class ImgextraBean {
        /**
         * imgsrc : http://cms-bucket.nosdn.127.net/2e9f69f46d0a4998a0531e5b0fd175e420170309075416.jpeg
         */

        private String imgsrc;

        public String getImgsrc() {
            return imgsrc;
        }

        public void setImgsrc(String imgsrc) {
            this.imgsrc = imgsrc;
        }

        @Override
        public String toString() {
            return "ImgextraBean{" +
                    "imgsrc='" + imgsrc + '\'' +
                    '}';
        }
    }
}

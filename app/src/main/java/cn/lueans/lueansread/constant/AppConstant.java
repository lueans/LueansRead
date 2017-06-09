package cn.lueans.lueansread.constant;

/**
 * Created by 24277 on 2017/2/23.
 */

public class AppConstant {
    //gank 的http请求
    public static String GANK_HTTP = "http://gank.io/";
    public static int GAN_DATA_NUM = 10;

    //so 的http请求
    public static String SO_HTTP = "http://image.so.com/";
    public  static String SO_IMAGE_TYPE = "beauty";


    //News 的 http 请求
    public static String NEWS_HTTP = "http://c.m.163.com/";
    //news photo 的http 请求
    public static String NEWS_PHOTO_HTTP = "http://c.3g.163.com/";
    // 头条TYPE
    public static final String HEADLINE_TYPE = "headline";
    // 其他TYPE
    public static final String OTHER_TYPE = "list";



//    http://c.3g.163.com/photo/api/jsonp/set/0003/627052.json?callback=photosetinfo
    public static final String PHOTO_URL = "http://3g.163.com/touch/photoview.html?channel=ent&child=all&offset=7";
    public static final String PHTOT_SETID = "&setid=";
    public static final String PHOTO_CHANNELID = "&channelid=";



    public static String getPhoneURL(String setid,String channelid){
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(PHOTO_URL);
        stringBuffer.append(PHTOT_SETID);
        stringBuffer.append(setid);
        stringBuffer.append(PHOTO_CHANNELID);
        stringBuffer.append(channelid);
        return stringBuffer.toString().trim();
    }

    public static final String AUOUT_DEVELOPER_HTTP = "https://lueans.github.io/";

}

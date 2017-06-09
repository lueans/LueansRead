package cn.lueans.lueansread.utils;

import java.util.List;

import cn.lueans.lueansread.entity.NewsDetailBean;

/**
 * Created by 24277 on 2017/3/12.
 */

public class HtmlUtils  {


    private static final String HTML_HEAD = "<!DOCTYPE html><html lang=\"zh-cn\"><head><meta charset=\"UTF-8\"><style type=\"text/css\">p{font-size: 20px;line-height:30px;}time{font-size: 12px;line-height:30px;}</style></head><body>";
    private static final String HTML_HEAD_NIGHT = "<!DOCTYPE html><html lang=\"zh-cn\"><head><meta charset=\"UTF-8\"><style type=\"text/css\">*{background: #000;color: #eee;}p{font-size: 20px;line-height:30px;}time{font-size: 12px;line-height:30px;}</style></head><body>";
    private static final String HTML_END = "</body></html>";
    private static final String HTML_IMG_BODY = "<img href=\"<!--IMG-->\" src=\"<!--IMG-->\" width=\"100%\"  />";
    private static final String HTML_IMG_KEY = "<!--IMG-->";
    private static final String HTML_VIDEO_OBDY = "<video ><source src=\"<!--VIEDO-->\" type=\"video/mp4\" /> </video>";
    private static final String HTML_VIDEO_KEY = "<!--VIEDO-->";

    /**
     * 生成img标签。根据 src
     * @param src
     * @return
     */

    private static String getImg(String src){
        return HTML_IMG_BODY.toString().replaceAll(HTML_IMG_KEY, src);
    }


    private static String getVideo(String url){
        return HTML_VIDEO_OBDY.toString().replace(HTML_VIDEO_KEY,url);
    }

    public static String getTitles(String title){
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("<h2>");
        stringBuffer.append(title);
        stringBuffer.append("</h2>");
        return stringBuffer.toString();
    }

    public static String getSourecs(String av,String time){
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("<time>");
        stringBuffer.append(av+"&nbsp&nbsp");
        stringBuffer.append(time);
        stringBuffer.append("</time>");
        return stringBuffer.toString();
    }

    /**
     * 组装html代码
     * @param newsDetailBean
     * @return
     */
    public static String getHtml(NewsDetailBean newsDetailBean){
        List<NewsDetailBean.ImgBean> imgBeanLists = newsDetailBean.getImg();
        String body = newsDetailBean.getBody();
        if (imgBeanLists != null && ! imgBeanLists.isEmpty()){
            String src;
            String ref;
            for (NewsDetailBean.ImgBean imgbean: imgBeanLists) {
                src = imgbean.getSrc();
                ref = imgbean.getRef();
                body = body.replace(ref, getImg(src));
            }
        }
       /* List<NewsDetailBean.VideoBean> video = newsDetailBean.getVideo();
        if (video != null && ! video.isEmpty()){
            String url_mp4;
            String ref;
            for (NewsDetailBean.VideoBean videobean: video) {
                url_mp4 = videobean.getUrl_mp4();
                ref = videobean.getRef();
                body = body.replace(ref,getVideo(url_mp4));
            }
        }
*/

        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(HTML_HEAD);
        stringBuffer.append(getTitles(newsDetailBean.getTitle()));
        stringBuffer.append(getSourecs(newsDetailBean.getSource(),newsDetailBean.getPtime()));
        stringBuffer.append(body);
        stringBuffer.append(HTML_END);
        return stringBuffer.toString();
    }


    public static String getHtmlNight(NewsDetailBean newsDetailBean){
        List<NewsDetailBean.ImgBean> imgBeanLists = newsDetailBean.getImg();
        String body = newsDetailBean.getBody();
        if (imgBeanLists != null && ! imgBeanLists.isEmpty()){
            String src;
            String ref;
            for (NewsDetailBean.ImgBean imgbean: imgBeanLists) {
                src = imgbean.getSrc();
                ref = imgbean.getRef();
                body = body.replace(ref, getImg(src));
            }
        }
       /* List<NewsDetailBean.VideoBean> video = newsDetailBean.getVideo();
        if (video != null && ! video.isEmpty()){
            String url_mp4;
            String ref;
            for (NewsDetailBean.VideoBean videobean: video) {
                url_mp4 = videobean.getUrl_mp4();
                ref = videobean.getRef();
                body = body.replace(ref,getVideo(url_mp4));
            }
        }
*/

        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(HTML_HEAD_NIGHT);
        stringBuffer.append(getTitles(newsDetailBean.getTitle()));
        stringBuffer.append(getSourecs(newsDetailBean.getSource(),newsDetailBean.getPtime()));
        stringBuffer.append(body);
        stringBuffer.append(HTML_END);
        return stringBuffer.toString();
    }

}

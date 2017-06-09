package cn.lueans.lueansread.retrofitserver;

/**
 * Created by 24277 on 2017/3/19.
 */

public class NewsPhotoSingle {

    private static NewsPhotoServer newsInstance = null;

    public static NewsPhotoServer getInstance(){
        if (newsInstance == null) {
            synchronized (GankSingle.class){
                if (newsInstance == null) {
                    newsInstance = new NewsPhotoRetrofit().createService(NewsPhotoServer.class);
                }
            }
        }
        return newsInstance;
    }
}

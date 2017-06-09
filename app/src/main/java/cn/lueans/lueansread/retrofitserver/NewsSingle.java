package cn.lueans.lueansread.retrofitserver;

/**
 * Created by 24277 on 2017/3/9.
 */

public class NewsSingle {
    
    private static NewsServer newsRetrofitInstance = null;



    public static NewsServer getInstance(){
        if (newsRetrofitInstance == null) {
            synchronized (GankSingle.class){
                if (newsRetrofitInstance == null) {
                    newsRetrofitInstance = new NewsRetrofit().createService(NewsServer.class);
                }
            }
        }
        return newsRetrofitInstance;
    }


}

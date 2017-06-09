package cn.lueans.lueansread.retrofitserver;

/**
 * Created by 24277 on 2017/1/10.
 */

public class GankSingle {

    private static GankServer gankRetrofitInstance = null;

    public static GankServer getInstance(){
        if (gankRetrofitInstance == null) {
            synchronized (GankSingle.class){
                if (gankRetrofitInstance == null) {
                    gankRetrofitInstance = new GankRetrofit().createService(GankServer.class);
                }
            }
        }
        return gankRetrofitInstance;
    }
}

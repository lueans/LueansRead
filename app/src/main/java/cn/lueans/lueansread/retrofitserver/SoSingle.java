package cn.lueans.lueansread.retrofitserver;

/**
 * Created by 24277 on 2017/2/23.
 */

public class SoSingle {
    private static SoServer soRetrofitInstance = null;
    public static SoServer getInstance(){
        if (soRetrofitInstance == null) {
            synchronized (GankSingle.class){
                if (soRetrofitInstance == null) {
                    soRetrofitInstance = new SoRetrofit().createService(SoServer.class);
                }
            }
        }
        return soRetrofitInstance;
    }
}

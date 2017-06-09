package cn.lueans.lueansread.retrofitserver;

import com.google.gson.Gson;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.concurrent.TimeUnit;

import cn.lueans.lueansread.constant.AppConstant;
import cn.lueans.lueansread.entity.NewsPhotoDetailBean;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;

/**
 * Created by 24277 on 2017/3/18.
 */

public class NewsPhotoRetrofit {

    private static OkHttpClient httpClient = new OkHttpClient.Builder()
            .connectTimeout(8, TimeUnit.SECONDS)
            .build();

    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(AppConstant.NEWS_PHOTO_HTTP)
                    .addConverterFactory(new Converter.Factory() {
                        @Override
                        public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
                            return new Converter<ResponseBody, NewsPhotoDetailBean>() {
                                @Override
                                public NewsPhotoDetailBean convert(ResponseBody value) throws IOException {
                                    String body = value.string();
                                    String jsonData = body.substring(13,body.length()-1);
                                    Gson gson = new Gson();
                                    NewsPhotoDetailBean newsPhotoDetailBean = gson.fromJson(jsonData, NewsPhotoDetailBean.class);
                                    return newsPhotoDetailBean;
                                }
                            };
                        }
                    })
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create());

    public <S> S createService(Class<S> serviceClass) {
        Retrofit retrofit = builder.client(httpClient).build();
        return retrofit.create(serviceClass);
    }
}

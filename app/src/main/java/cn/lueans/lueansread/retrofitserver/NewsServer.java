package cn.lueans.lueansread.retrofitserver;

import java.util.ArrayList;
import java.util.Map;

import cn.lueans.lueansread.entity.NewsDetailBean;
import cn.lueans.lueansread.entity.NewsListBean;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by 24277 on 2017/3/9.
 */

public interface NewsServer {

    @Headers({
            "Cache-Control: max-age=86400000",
            "User-Agent: Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/57.0.2987.98 Mobile Safari/537.36"
    })
    @GET("nc/article/{type}/{id}/{startPage}-20.html")
    Observable<Map<String,ArrayList<NewsListBean>>> getNewsList(
//            @Header("Cache-Control") String cacheControl,
            @Path("type") String type,
            @Path("id") String id,
            @Path("startPage") int startPage
    );


    @Headers({
            "Cache-Control: max-age=86400000",
            "User-Agent: Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/57.0.2987.98 Mobile Safari/537.36"

    })
    @GET("nc/article/{postId}/full.html")
    Observable<Map<String, NewsDetailBean>> getNewDetail(
            @Path("postId") String postId
    );

}

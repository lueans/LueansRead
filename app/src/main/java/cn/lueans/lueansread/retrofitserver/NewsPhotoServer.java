package cn.lueans.lueansread.retrofitserver;

import cn.lueans.lueansread.entity.NewsPhotoDetailBean;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by 24277 on 2017/3/18.
 */

public interface NewsPhotoServer {

    @Headers({
            "Cache-Control: max-age=86400000",
            "User-Agent: Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/57.0.2987.98 Mobile Safari/537.36",
    })

    @GET("photo/api/jsonp/set/{setid}/{channelid}.json")
    Observable<NewsPhotoDetailBean> getNewDetail(
            @Path("setid") String postId,
            @Path("channelid") String channelid,
            @Query("callback") String callback
    );

}

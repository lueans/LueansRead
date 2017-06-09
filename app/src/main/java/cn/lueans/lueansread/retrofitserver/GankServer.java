package cn.lueans.lueansread.retrofitserver;

import cn.lueans.lueansread.entity.GankBean;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by 24277 on 2017/2/23.
 */

public interface GankServer {
    //获取数据api
    //api http://gank.io/api/data/Android/10/1
    @GET("api/data/{type}/{number}/{page}")
    Observable<GankBean> getGankdata(
            @Path("type") String type,
            @Path("number") int number,
            @Path("page") int page
    );

    //搜索api
    //http://gank.io/api/search/query/listview/category/Android/count/10/page/1
    @GET("api/search/query/{body}/category/{type}/count/{number}/page/{page}")
    Observable<GankBean> queryGankData(
            @Path("body") String body,
            @Path("type") String type,
            @Path("number") int number,
            @Path("page") int page
    );

    //    http://gank.io/api/day/2015/08/06
    @GET("api/day/{n}/{m}/{d}")
    Observable<GankBean> GankData(
            @Path("body") String body,
            @Path("type") String type,
            @Path("number") int number,
            @Path("page") int page
    );

}

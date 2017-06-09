package cn.lueans.lueansread.retrofitserver;

import cn.lueans.lueansread.entity.SOListBean;
import cn.lueans.lueansread.entity.SoDetailBean;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by 24277 on 2017/2/23.
 */

public interface SoServer {

    // 获取一个类型的图片列表
    @GET("zj")
    Observable<SOListBean> getSoListData(
        @Query("cd") String imageType,   // 默认 : beauty
        @Query("t1") String type,
        @Query("sn") int sn,
        @Query("listtype") String listtype
    );

    //更具id获取详细信息
    @GET("zvj")
    Observable<SoDetailBean> getSoDetailListData(
            @Query("cd") String imageType,   // 默认 : beauty
            @Query("t1") String type,
            @Query("id") String id
    );

    //更具id获取详细信息
    @GET("zvj")
    Observable<SoDetailBean> getSoDetailListData(
            @Query("id") String id
    );



}

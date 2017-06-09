package cn.lueans.lueansread.utils;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cn.lueans.lueansread.entity.GankBean;
import cn.lueans.lueansread.entity.NewsListBean;
import cn.lueans.lueansread.entity.SOListBean;
import cn.lueans.lueansread.entity.SoDetailBean;

/**
 * Created by 24277 on 2017/2/23.
 */

public class PrintUtils {

    private static final String TAG = "PrintUtils";

    public static  void printGankBean(GankBean bean){
        Log.i(TAG, "printGankBean:  statu-code"+bean.isError());
        if(!bean.isError()){
            List<GankBean.GankResultsBean> results = bean.getResults();
            Log.i(TAG, "---------------------------------------------");
            Log.i(TAG, "printGankBean: ----" + results.size());
            Log.i(TAG, "---------------------------------------------");
            for (GankBean.GankResultsBean rBean : results) {
                Log.i(TAG, "printGankBean: "+rBean.toString());
            }
            Log.i(TAG, "---------------------------------------------");
        }
    }

    public static void printSoList(SOListBean data){
        List<SOListBean.ListBean> list = data.getList();
        Log.i(TAG, "printSoList: --------------------------------------------------");
        Log.i(TAG, "printSoList: ---------------"+list.size());
        Log.i(TAG, "printSoList: --------------------------------------------------");
        for (SOListBean.ListBean sobean:list){
            Log.i(TAG, "printSoList: "+sobean.toString());
        }
        Log.i(TAG, "printSoList: --------------------------------------------------");
    }

    public static void printSoDetail(SoDetailBean data){
        ArrayList<SoDetailBean.DetialListBean> list = data.getList();
        Log.i(TAG, "printSoDetail: -------------------------------------");
        Log.i(TAG, "printSoDetail: ---------------------"+list.size());
        Log.i(TAG, "printSoDetail: -------------------------------------");
        for (SoDetailBean.DetialListBean detailBean : list) {
            Log.i(TAG, "printSoDetail: "+detailBean.toString());
        }
        Log.i(TAG, "printSoDetail: -------------------------------------");
    }

    public static void printNewsList(String id,Map<String, ArrayList<NewsListBean>> newsData){
        List<NewsListBean> newsListBeen = newsData.get(id);
        Log.i(TAG, "setRefreshNewsData: -----------------------------------");
        Log.i(TAG, "setRefreshNewsData: --------"+newsListBeen.size());
        for (NewsListBean bean:newsListBeen) {
            Log.i(TAG, "setRefreshNewsData: "+bean.toString());
        }
        Log.i(TAG, "setRefreshNewsData: -----------------------------------");
    }

}

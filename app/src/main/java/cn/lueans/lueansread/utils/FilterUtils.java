package cn.lueans.lueansread.utils;

import android.text.TextUtils;
import android.util.Log;

import java.util.ArrayList;

import cn.lueans.lueansread.entity.NewsListBean;

/**
 * Created by 24277 on 2017/3/21.
 *
 */

public class FilterUtils {
    private static final String TAG = "FilterUtils";
    public static ArrayList<NewsListBean> filterNews(ArrayList<NewsListBean> newsListBeens){
        ArrayList<NewsListBean> mList = new ArrayList<>();
        for (NewsListBean bean : newsListBeens) {
            if(!TextUtils.isEmpty(bean.getUrl_3w()) && TextUtils.isEmpty(bean.getPhotosetID())){
                mList.add(bean);
            }
        }
        return mList;
    }
}

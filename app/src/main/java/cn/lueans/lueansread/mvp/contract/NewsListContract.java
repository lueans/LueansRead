package cn.lueans.lueansread.mvp.contract;

import java.util.ArrayList;
import java.util.Map;

import cn.lueans.lueansread.entity.NewsListBean;

/**
 * Created by 24277 on 2017/3/9.
 */

public class NewsListContract {

    public interface View {
        void showLoading();
        void hideLoading();
        void showError(Exception e);
        void setMoreNewsData(Map<String,ArrayList<NewsListBean>> newsData);
        void setRefreshNewsData(Map<String,ArrayList<NewsListBean>> newsData);
    }

    public interface Presenter{
        void getNewsDataFromInternet(boolean isTop, String type,String id,int startPage);
        void getNewsDataFromDB(String id);
        void deleteDataToDB(String id);
    }
    public interface Model{
        void getNewsDataFromInternet(boolean isTop, String type,String id,int startPage);
        void getNewsDataFromDB(String id);
        void deleteDataToDB(String id);
        interface NewsLoadListener {
            void onNewsSuccess(boolean isTop,Map<String,ArrayList<NewsListBean>> newsData);
            void onNewsError(Exception e);
        }
    }
}
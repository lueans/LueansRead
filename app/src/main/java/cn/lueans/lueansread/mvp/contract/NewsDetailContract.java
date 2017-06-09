package cn.lueans.lueansread.mvp.contract;

import cn.lueans.lueansread.entity.NewsDetailBean;

/**
 * Created by 24277 on 2017/3/12.
 */

public class NewsDetailContract {
    
    public interface View{
        void hideLoading();
        void showError(Exception e);
        void setRefreshNewsData(NewsDetailBean data);
    }

    public interface Presenter{
        void getNewsDetail(String postId);
    }

    public interface Model{
        void getGankDataFromInternet(String postId);
        interface LoadListener {
            void onNDSuccess(NewsDetailBean data);
            void onNDError(Exception e);
        }
    }


}
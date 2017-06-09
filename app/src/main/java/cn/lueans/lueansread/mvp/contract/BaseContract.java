package cn.lueans.lueansread.mvp.contract;

import cn.lueans.lueansread.entity.GankBean;

/**
 * Created by 24277 on 2017/2/23.
 */

public interface BaseContract {
    public interface View{
        void showLoading();
        void hideLoading();
        void showError(Exception e);
        void setMoreGankData(GankBean data);
        void setRefreshData(GankBean data);
    }

    public interface Presenter{
        void getGankDataFromInternet(boolean isTop, String type,int number,int page);
        void getGankDataFromDB(String type);
        void deleteDataToDB(String type);
    }
    public interface Model{
        void getGankDataFromInternet(boolean isTop, String type,int number,int page);
        void getGankDataFromDB(String type);
        void deleteDataToDB(String type);

        interface LoadListener {
            void onSuccess(boolean isTop, GankBean data);
            void onError(Exception e);
        }
    }
}

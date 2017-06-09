package cn.lueans.lueansread.mvp.contract;

import cn.lueans.lueansread.entity.SOListBean;

/**
 * Created by 24277 on 2017/2/23.
 */

public class SoContract{


    public interface View{
        void showLoading();
        void hideLoading();
        void showError(Exception e);
        void setMoreData(SOListBean data);
        void setRefreshData(SOListBean data);
    }

    public interface Presenter{
        void getSoDataFromInternet(boolean isTop, String type,int sn,String listtype);
        void getSoDataFromDB(String type);
        void deleteDataToDB(String type);
    }

    public interface Model{
        void getSoDataFromInternet(boolean isTop, String type,int sn,String listtype);
        void getSoDataFromDB(String type);
        void deleteDataToDB(String type);

        interface SoLoadListener {
            void onSuccess(boolean isTop, SOListBean data);
            void onError(Exception e);
        }
    }


}
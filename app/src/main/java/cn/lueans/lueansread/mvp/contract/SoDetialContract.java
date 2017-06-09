package cn.lueans.lueansread.mvp.contract;

import cn.lueans.lueansread.entity.SoDetailBean;

/**
 * Created by 24277 on 2017/2/23.
 */

public class SoDetialContract {
    public interface View{
        void showLoading();
        void hideLoading();
        void showError(Exception e);
        void setRefreshData(SoDetailBean data);
    }

    public interface Presenter{
        void getSoDetailDataFromInternet(String id);
        void getSoDetailDataFromDB(String id);
        void deleteDetailDataToDB(String id);
    }

    public interface Model{
        void getSoDetailDataFromInternet(String id);
        void getSoDetailDataFromDB(String id);
        void deleteDetailDataToDB(String id);

        interface SoDetailLoadListener {
            void onSoDetailSuccess(SoDetailBean data);
            void onSoDetailError(Exception e);
        }
    }
}

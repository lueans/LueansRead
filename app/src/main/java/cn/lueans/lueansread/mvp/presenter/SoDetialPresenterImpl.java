package cn.lueans.lueansread.mvp.presenter;

import cn.lueans.lueansread.entity.SoDetailBean;
import cn.lueans.lueansread.mvp.contract.SoDetialContract;
import cn.lueans.lueansread.mvp.model.SoDetialModelImpl;

/**
 * Created by 24277 on 2017/2/23.
 */

public class SoDetialPresenterImpl implements SoDetialContract.Presenter, SoDetialContract.Model.SoDetailLoadListener {

    private SoDetialModelImpl mModel;
    private SoDetialContract.View mView;

    public SoDetialPresenterImpl(SoDetialContract.View view) {
        mView = view;
        mModel = new SoDetialModelImpl();
    }

    public void getSoDetailDataFromInternet(String id) {
        mModel.setMlistener(this);
        mModel.getSoDetailDataFromInternet(id);
    }

    @Override
    public void getSoDetailDataFromDB(String id) {

    }

    @Override
    public void deleteDetailDataToDB(String id) {

    }

    @Override
    public void onSoDetailSuccess(SoDetailBean data) {
        mView.hideLoading();
        mView.setRefreshData(data);
    }

    @Override
    public void onSoDetailError(Exception e) {
        mView.hideLoading();
        mView.showError(e);
    }

    public void unSubscribe(){
        this.mModel.unSubscribe();
    }
}

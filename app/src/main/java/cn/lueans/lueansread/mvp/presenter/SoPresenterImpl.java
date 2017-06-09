package cn.lueans.lueansread.mvp.presenter;

import android.util.Log;

import java.util.concurrent.TimeUnit;

import cn.lueans.lueansread.entity.SOListBean;
import cn.lueans.lueansread.mvp.contract.SoContract;
import cn.lueans.lueansread.mvp.model.SoModelImpl;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * Created by 24277 on 2017/2/23.
 */

public class SoPresenterImpl implements SoContract.Presenter, SoContract.Model.SoLoadListener {

    private static final String TAG = "SoPresenterImpl";
    private SoContract.View mView;
    private SoModelImpl mSoModel;

    public SoPresenterImpl(SoContract.View view) {
        mView = view;
        mSoModel = new SoModelImpl();
    }

    public void getSoDataFromInternet(boolean isTop, String type, int sn, String listtype) {
        mSoModel.setListener(this);
        mSoModel.getSoDataFromInternet(isTop, type, sn, listtype);
    }

    @Override
    public void getSoDataFromDB(String type) {

    }

    @Override
    public void deleteDataToDB(String type) {

    }
    @Override
    public void onSuccess(final boolean isTop, final SOListBean data) {
        Log.i(TAG, "onSuccess: ");


        Observable.timer(1, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        mView.hideLoading();
                        if (isTop) {
                            mView.setRefreshData(data);
                        } else {
                            mView.setMoreData(data);
                        }
                    }
                });

    }

    @Override
    public void onError(final Exception e) {
        Log.i(TAG, "onError: ");
        mView.hideLoading();
        mView.showError(e);
    }

    public void unSubscribe(){
        this.mSoModel.unSubscribe();
    }
}

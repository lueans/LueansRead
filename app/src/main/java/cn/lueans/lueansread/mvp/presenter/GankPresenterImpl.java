package cn.lueans.lueansread.mvp.presenter;

import android.util.Log;

import java.util.concurrent.TimeUnit;

import cn.lueans.lueansread.entity.GankBean;
import cn.lueans.lueansread.mvp.contract.GankContract;
import cn.lueans.lueansread.mvp.model.GankModelImpl;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * Created by 24277 on 2017/02/23
 */

public class GankPresenterImpl implements GankContract.Presenter, GankContract.Model.LoadListener {

    private static final String TAG = "GankPresenterImpl";
    private GankModelImpl mGankModel;
    private GankContract.View mView;

    public GankPresenterImpl(GankContract.View view) {
        mView = view;
        mGankModel = new GankModelImpl();
    }

    public void getGankDataFromInternet(boolean isTop, String type, int number, int page) {
        Log.i(TAG, "getGankDataFromInternet: -----------------------------");
        mGankModel.setNetListener(this);
        mGankModel.getGankDataFromInternet(isTop, type, number, page);
    }

    @Override
    public void getGankDataFromDB(String type) {

    }

    @Override
    public void deleteDataToDB(String type) {

    }

    @Override
    public void onSuccess(boolean isTop, GankBean data) {
        Log.i(TAG, "onSuccess: ----------------------------------");
        if (isTop) {
            mView.setRefreshData(data);
        } else {
            mView.setMoreGankData(data);
        }
        Observable.timer(2, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        mView.hideLoading();
                    }
                });
    }
    @Override
    public void onError(final Exception e) {
        mView.hideLoading();
        mView.showError(e);
    }

    public void unSubscribe(){
       this.mGankModel.unSubscribe();
    }
}
package cn.lueans.lueansread.mvp.model;


import android.util.Log;

import cn.lueans.lueansread.constant.AppConstant;
import cn.lueans.lueansread.entity.SOListBean;
import cn.lueans.lueansread.mvp.contract.SoContract;
import cn.lueans.lueansread.retrofitserver.SoServer;
import cn.lueans.lueansread.retrofitserver.SoSingle;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
* Created by 24277 on 2017/02/23
*/

public class SoModelImpl implements SoContract.Model  {

    private static final String TAG = "SoModelImpl";
    private SoContract.Model.SoLoadListener mListener;
    private Subscription subscription;

    public void setListener(SoContract.Model.SoLoadListener listener) {
        mListener = listener;
    }

    @Override
    public void getSoDataFromInternet(final boolean isTop, String type, int sn, String listtype) {
        SoServer instance = SoSingle.getInstance();
        Observable<SOListBean> soListData = instance.getSoListData(AppConstant.SO_IMAGE_TYPE, type, sn, listtype);
        subscription = soListData.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<SOListBean>() {
                    @Override
                    public void onCompleted() {
                        Log.i(TAG, "onCompleted: ");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i(TAG, "onError: ");
                        if (mListener != null) {
                            mListener.onError(new Exception(e.toString()));
                        }
                    }

                    @Override
                    public void onNext(SOListBean soListBean) {
                        Log.i(TAG, "onNext: ");
                        if (mListener != null) {
                            mListener.onSuccess(isTop, soListBean);
                        }
                    }
                });
    }

    @Override
    public void getSoDataFromDB(String type) {

    }

    @Override
    public void deleteDataToDB(String type) {

    }

    public void unSubscribe(){
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }
}
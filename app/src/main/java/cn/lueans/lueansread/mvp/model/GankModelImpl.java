package cn.lueans.lueansread.mvp.model;

import android.util.Log;

import cn.lueans.lueansread.entity.GankBean;
import cn.lueans.lueansread.mvp.contract.GankContract;
import cn.lueans.lueansread.retrofitserver.GankServer;
import cn.lueans.lueansread.retrofitserver.GankSingle;
import cn.lueans.lueansread.utils.PrintUtils;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
* Created by 24277 on 2017/02/23
*/

public class GankModelImpl implements GankContract.Model{

    private static final String TAG = "GankModelImpl";
    private GankContract.Model.LoadListener mNetListener;
    private Observable<GankBean> gankdata;
    private Subscription subscription;

    public void setNetListener(LoadListener netListener) {
        mNetListener = netListener;
    }

    public void getGankDataFromInternet(final boolean isTop, String type, int number, int page) {
        GankServer gankServer = GankSingle.getInstance();
        Observable<GankBean> gankdata =  gankServer.getGankdata(type, number, page);

        subscription = gankdata.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GankBean>() {
                    @Override
                    public void onCompleted() {
                        Log.i(TAG, "onCompleted: ");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i(TAG, "onError: ");
                        if (mNetListener != null) {
                            mNetListener.onError(new Exception(e.toString()));
                        }
                    }

                    @Override
                    public void onNext(GankBean gankBean) {
                        Log.i(TAG, "onNext: ");
                        PrintUtils.printGankBean(gankBean);
                        if (mNetListener != null) {
                            if (gankBean.isError()) {
                                mNetListener.onError(new Exception("服务器异常！"));
                            } else {
                                mNetListener.onSuccess(isTop, gankBean);
                            }
                        }
                    }
                });

    }

    @Override
    public void getGankDataFromDB(String type) {

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
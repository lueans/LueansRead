package cn.lueans.lueansread.mvp.model;

import android.util.Log;

import cn.lueans.lueansread.entity.SoDetailBean;
import cn.lueans.lueansread.mvp.contract.SoDetialContract;
import cn.lueans.lueansread.retrofitserver.SoServer;
import cn.lueans.lueansread.retrofitserver.SoSingle;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by 24277 on 2017/2/23.
 */

public class SoDetialModelImpl implements SoDetialContract.Model {

    private static final String TAG = "SoDetialModelImpl";
    private SoDetialContract.Model.SoDetailLoadListener mlistener;
    private Subscription subscription;

    public void setMlistener(SoDetailLoadListener mlistener) {
        this.mlistener = mlistener;
    }

    public void getSoDetailDataFromInternet(String id) {
        SoServer instance = SoSingle.getInstance();
        Observable<SoDetailBean> soDetailListData = instance.getSoDetailListData(id);

        subscription = soDetailListData.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<SoDetailBean>() {
                    @Override
                    public void onCompleted() {
                        Log.i(TAG, "onCompleted: -------------");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i(TAG, "onError: --------");
                        if (mlistener != null) {
                            mlistener.onSoDetailError(new Exception(e.toString()));
                        }
                    }

                    @Override
                    public void onNext(SoDetailBean soDetailBean) {
                        Log.i(TAG, "onNext: ---------------------");
                        if (mlistener != null) {
                            mlistener.onSoDetailSuccess(soDetailBean);
                        }
                    }
                });

    }

    @Override
    public void getSoDetailDataFromDB(String id) {

    }

    @Override
    public void deleteDetailDataToDB(String id) {

    }

    public void unSubscribe(){
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }
}

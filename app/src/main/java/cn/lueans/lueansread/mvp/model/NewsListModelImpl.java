package cn.lueans.lueansread.mvp.model;

import android.util.Log;

import java.util.ArrayList;
import java.util.Map;

import cn.lueans.lueansread.entity.NewsListBean;
import cn.lueans.lueansread.mvp.contract.NewsListContract;
import cn.lueans.lueansread.retrofitserver.NewsServer;
import cn.lueans.lueansread.retrofitserver.NewsSingle;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
* Created by 24277 on 2017/03/09
*/

public class NewsListModelImpl implements NewsListContract.Model{

    private static final String TAG = "NewsListModelImpl";
    private NewsListContract.Model.NewsLoadListener  mListener;
    private Subscription subscription;

    public void setListener(NewsLoadListener listener) {
        mListener = listener;
    }

    public void getNewsDataFromInternet(final boolean isTop, String type, String id, int startPage){
        NewsServer instance = NewsSingle.getInstance();
        Observable<Map<String, ArrayList<NewsListBean>>> newsObserbable = instance.getNewsList(type,id, startPage);
        subscription = newsObserbable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Map<String, ArrayList<NewsListBean>>>() {
                    @Override
                    public void onCompleted() {
                        Log.i(TAG, "onCompleted: ");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i(TAG, "onError:   --------------------失败-  " + e.getCause());
                        Log.i(TAG, "onError: ------------------" + e.toString());
                        if (mListener != null) {
                            mListener.onNewsError(new Exception(e.toString()));
                        }
                    }

                    @Override
                    public void onNext(Map<String, ArrayList<NewsListBean>> stringListMap) {
                        if (mListener != null) {
                            mListener.onNewsSuccess(isTop, stringListMap);
                        }
                    }
                });
    }

    @Override
    public void getNewsDataFromDB(String id) {

    }

    @Override
    public void deleteDataToDB(String id) {
        
    }

    public void unSubscribe(){
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }
}
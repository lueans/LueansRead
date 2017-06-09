package cn.lueans.lueansread.mvp.model;

import android.util.Log;

import java.util.Map;

import cn.lueans.lueansread.entity.NewsDetailBean;
import cn.lueans.lueansread.mvp.contract.NewsDetailContract;
import cn.lueans.lueansread.retrofitserver.NewsServer;
import cn.lueans.lueansread.retrofitserver.NewsSingle;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
* Created by 24277 on 2017/03/12
*/

public class NewsDetailModelImpl implements NewsDetailContract.Model{

    private static final String TAG = "NewsDetailModelImpl";

    private NewsDetailContract.Model.LoadListener mLoadListener;
    private Subscription subscription;

    public void setLoadListener(LoadListener loadListener) {
        mLoadListener = loadListener;
    }

    public void getGankDataFromInternet(final String postId) {
        NewsServer instance = NewsSingle.getInstance();
        Observable<Map<String, NewsDetailBean>> newDetail = instance.getNewDetail(postId);
        //没有相关推荐
        subscription = newDetail.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Map<String, NewsDetailBean>>() {
                    @Override
                    public void onCompleted() {
                        Log.i(TAG, "onCompleted: ");
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (mLoadListener != null) {
                            if (e.toString().equals("java.lang.NullPointerException")) {
                                //没有相关推荐
                            } else {
                                mLoadListener.onNDError(new Exception(e.toString()));
                            }
                        }
                    }

                    @Override
                    public void onNext(Map<String, NewsDetailBean> stringNewsDetailBeanMap) {
                        NewsDetailBean newsDetailBean = stringNewsDetailBeanMap.get(postId);
                        if (mLoadListener != null) {
                            mLoadListener.onNDSuccess(stringNewsDetailBeanMap.get(postId));
                        }
                    }
                });

    }

    public void unSubscribe(){
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }
}
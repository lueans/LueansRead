package cn.lueans.lueansread.mvp.presenter;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import cn.lueans.lueansread.entity.NewsListBean;
import cn.lueans.lueansread.mvp.contract.NewsListContract;
import cn.lueans.lueansread.mvp.model.NewsListModelImpl;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * Created by 24277 on 2017/03/09
 */

public class NewsListPresenterImpl implements NewsListContract.Presenter, NewsListContract.Model.NewsLoadListener {

    private static final String TAG = "NewsListPresenterImpl";
    private NewsListContract.View mView;
    private NewsListModelImpl mNewsListModel;

    public NewsListPresenterImpl(NewsListContract.View view) {
        mView = view;
        mNewsListModel = new NewsListModelImpl();
    }

    public void getNewsDataFromInternet(boolean isTop, String type, String id, int startPage) {
        mNewsListModel.setListener(this);
        mNewsListModel.getNewsDataFromInternet(isTop, type, id, startPage);
    }

    @Override
    public void getNewsDataFromDB(String id) {

    }

    @Override
    public void deleteDataToDB(String id) {

    }

    @Override
    public void onNewsSuccess(boolean isTop, Map<String, ArrayList<NewsListBean>> newsData) {
        if (isTop) {
            mView.setRefreshNewsData(newsData);
        } else {
            mView.setMoreNewsData(newsData);
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
    public void onNewsError(final Exception e) {
        mView.hideLoading();
        mView.showError(e);
    }

    public void unSubscribe(){
        this.mNewsListModel.unSubscribe();
    }
}
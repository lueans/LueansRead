package cn.lueans.lueansread.mvp.presenter;

import cn.lueans.lueansread.entity.NewsDetailBean;
import cn.lueans.lueansread.mvp.contract.NewsDetailContract;
import cn.lueans.lueansread.mvp.model.NewsDetailModelImpl;


/**
 * Created by 24277 on 2017/03/12
 */

public class NewsDetailPresenterImpl implements NewsDetailContract.Presenter,NewsDetailContract.Model.LoadListener {

    private NewsDetailModelImpl mDetailModel;
    private NewsDetailContract.View mView;

    public NewsDetailPresenterImpl(NewsDetailContract.View view) {
        mView = view;
        mDetailModel = new NewsDetailModelImpl();
    }

    public void getNewsDetail(String postId) {
        mDetailModel.setLoadListener(this);
        mDetailModel.getGankDataFromInternet(postId);

    }

    @Override
    public void onNDSuccess(NewsDetailBean data) {
        mView.hideLoading();
        mView.setRefreshNewsData(data);
    }

    @Override
    public void onNDError(Exception e) {
        mView.hideLoading();
        mView.showError(e);
    }

    public void unSubscribe(){
        this.mDetailModel.unSubscribe();
    }
}
package cn.lueans.lueansread.ui.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayout;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayoutDirection;

import java.util.ArrayList;
import java.util.Map;

import cn.lueans.lueansread.R;
import cn.lueans.lueansread.adapter.NewsListAdapter;
import cn.lueans.lueansread.db.NewsDao;
import cn.lueans.lueansread.entity.NewsListBean;
import cn.lueans.lueansread.mvp.contract.NewsListContract;
import cn.lueans.lueansread.mvp.presenter.NewsListPresenterImpl;
import cn.lueans.lueansread.ui.temDecoration.SoSpaceItemDecoration;
import cn.lueans.lueansread.utils.FilterUtils;
import cn.lueans.lueansread.utils.NetUtils;
import cn.lueans.lueansread.utils.TItleAndIdUtils;

/**
 * Created by 24277 on 2017/3/9.
 */

public class NewsFragment extends Fragment implements NewsListContract.View {

    private static final String TAG = "NewsFragment";
    private static final String NEWS_TITLE = "title";
    private RecyclerView recycleView;
    private SwipyRefreshLayout swipyRefreshLayout;
    private String mTitle;
    private static boolean isTop = true;
    private NewsListPresenterImpl mNewsPresenter;
    private String mId;
    private String mType;
    private int NOW_PAGE = 0;

    private NewsListAdapter mNewsListAdapter;


    //fragment 生成器
    public static NewsFragment getInstance(String title) {
        NewsFragment newsfra = new NewsFragment();
        Bundle bundle = new Bundle();
        bundle.putString(NEWS_TITLE, title);
        newsfra.setArguments(bundle);
        return newsfra;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        mTitle = bundle.getString(NEWS_TITLE);
        mNewsPresenter = new NewsListPresenterImpl(this);
        mId = TItleAndIdUtils.getValueIdByTitleKey(mTitle);
        if (mTitle.equals("头条")) {
            mType = "headline";
        } else {
            mType = "list";
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_list, container, false);
        Log.i(TAG, "onCreateView: ---------- " + mTitle + " type = " + mType + "  id = " + mId);
        initRecyclerView(v);
        initSwipyRefreshLayout(v);
        return v;
    }


    @Override
    public void onStart() {
        super.onStart();
        if (mNewsListAdapter.getItemCount() <= 0) {
            loadData();
        }
    }

    private void loadData() {
        ArrayList<NewsListBean> newsList = NewsDao.getNewsList(mId);
        if (newsList != null && !newsList.isEmpty()) {
            mNewsListAdapter.upDataAll(newsList);
            hideLoading();
        } else {
            swipyRefreshLayout.setRefreshing(true);
            mNewsPresenter.getNewsDataFromInternet(isTop, mType, mId, (int) (NOW_PAGE * 20));
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        this.mNewsPresenter.unSubscribe();
    }

    private void initRecyclerView(View v) {
        recycleView = (RecyclerView) v.findViewById(R.id.recyclerView);

        recycleView.setBackgroundColor(Color.parseColor("#eeeeee"));

        recycleView.setItemAnimator(new DefaultItemAnimator());
        recycleView.setLayoutManager(new LinearLayoutManager(getContext()));
        //设置分割线
        SoSpaceItemDecoration decoration = new SoSpaceItemDecoration(1);
        recycleView.addItemDecoration(decoration);
        //设置适配器
        mNewsListAdapter = new NewsListAdapter(getContext(), null);
        recycleView.setAdapter(mNewsListAdapter);
    }

    private void initSwipyRefreshLayout(View v) {
        swipyRefreshLayout = (SwipyRefreshLayout) v.findViewById(R.id.swipyrefreshlayout);
        swipyRefreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_light,
                android.R.color.holo_red_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_green_light);
        /*swipyRefreshLayout.measure(0,0);
        swipyRefreshLayout.setRefreshing(true);*/
        swipyRefreshLayout.setDirection(SwipyRefreshLayoutDirection.BOTH);
        swipyRefreshLayout.setOnRefreshListener(new SwipyRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(SwipyRefreshLayoutDirection direction) {
                isTop = (direction == SwipyRefreshLayoutDirection.TOP ? true : false);
                if (isTop) {
                    NOW_PAGE = 0;
                }
                mNewsPresenter.getNewsDataFromInternet(isTop, mType, mId, (int) (NOW_PAGE * 20));
            }
        });

    }

    @Override
    public void showLoading() {
        //暂时没用，如果要调用 ，修改NewsListPresenterImpl 的getNewsDataFromInternet 方法
    }

    @Override
    public void hideLoading() {
        if (swipyRefreshLayout.isRefreshing()) {
            swipyRefreshLayout.setRefreshing(false);
        }
    }

    @Override
    public void showError(Exception e) {

        if (!NetUtils.isNetworkConnected(getContext())) {
            Snackbar.make(recycleView, "你与我的距离，就差一个网络", Snackbar.LENGTH_SHORT).show();
        } else {

            Snackbar.make(recycleView, "数据迷路了，刷新找回！" + e.toString(), Snackbar.LENGTH_SHORT).show();
        }

    }

    @Override
    public void setMoreNewsData(Map<String, ArrayList<NewsListBean>> newsData) {

        ArrayList<NewsListBean> newsListBeen = FilterUtils.filterNews(newsData.get(mId));
        mNewsListAdapter.addMoreData(newsListBeen);
        NOW_PAGE++;


    }

    @Override
    public void setRefreshNewsData(final Map<String, ArrayList<NewsListBean>> newsData) {

        final ArrayList<NewsListBean> newsListBeen = FilterUtils.filterNews(newsData.get(mId));

        mNewsListAdapter.upDataAll(newsListBeen);
        NOW_PAGE++;
        Snackbar.make(recycleView, "最新新已加载！", Snackbar.LENGTH_SHORT).show();

        NewsDao.upDataByAsynchronous(newsListBeen, mId);

    }
}

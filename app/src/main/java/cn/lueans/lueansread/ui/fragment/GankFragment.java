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

import cn.lueans.lueansread.R;
import cn.lueans.lueansread.adapter.GankDataAdapter;
import cn.lueans.lueansread.constant.AppConstant;
import cn.lueans.lueansread.db.GankDao;
import cn.lueans.lueansread.entity.GankBean;
import cn.lueans.lueansread.mvp.contract.GankContract;
import cn.lueans.lueansread.mvp.presenter.GankPresenterImpl;
import cn.lueans.lueansread.ui.temDecoration.SoSpaceItemDecoration;
import cn.lueans.lueansread.utils.NetUtils;
import cn.lueans.lueansread.utils.PrintUtils;

/**
 * Created by 24277 on 2017/2/23.
 */

public class GankFragment extends Fragment implements GankContract.View {
    private static final String TAG = "GankFragment";
    private static final String GANK_TITLE = "title";
    private RecyclerView recycleView;
    private SwipyRefreshLayout swipyRefreshLayout;
    private String mTitle;
    private static int NOW_PAGE_NUM = 1;
    private static boolean isTop = true;
    private GankPresenterImpl gankPresenterImpl;

    private GankDataAdapter gankAdapter;


    //fragment 生成器
    public static GankFragment getInstance(String title) {
        GankFragment gankfra = new GankFragment();
        Bundle bundle = new Bundle();
        bundle.putString(GANK_TITLE, title);
        gankfra.setArguments(bundle);
        return gankfra;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        mTitle = bundle.getString(GANK_TITLE);
        gankPresenterImpl = new GankPresenterImpl(this);
    }

    @Override
    public void onMultiWindowModeChanged(boolean isInMultiWindowMode) {
        super.onMultiWindowModeChanged(isInMultiWindowMode);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_list, container, false);
        Log.i(TAG, "onCreateView: ----------" + mTitle);
        initRecyclerView(v);
        initSwipyRefreshLayout(v);
        Log.i(TAG, "onResume: ---------------------");
        return v;
    }

    private void initRecyclerView(View v) {
        recycleView = (RecyclerView) v.findViewById(R.id.recyclerView);
        recycleView.setItemAnimator(new DefaultItemAnimator());

        SoSpaceItemDecoration decoration = new SoSpaceItemDecoration(1);
        recycleView.addItemDecoration(decoration);

        recycleView.setBackgroundColor(Color.parseColor("#eeeeee"));

        gankAdapter = new GankDataAdapter(this.getActivity(), null);
        recycleView.setLayoutManager(new LinearLayoutManager(getContext()));
        recycleView.setAdapter(gankAdapter);
    }

    private void initSwipyRefreshLayout(View v) {
        swipyRefreshLayout = (SwipyRefreshLayout) v.findViewById(R.id.swipyrefreshlayout);
        swipyRefreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_light,
                android.R.color.holo_red_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_green_light);
        swipyRefreshLayout.setDirection(SwipyRefreshLayoutDirection.BOTH);

        /*swipyRefreshLayout.measure(0, 0);
        swipyRefreshLayout.setRefreshing(true);*/

        swipyRefreshLayout.setOnRefreshListener(new SwipyRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(SwipyRefreshLayoutDirection direction) {
                isTop = (direction == SwipyRefreshLayoutDirection.TOP ? true : false);
                if (isTop) {
                    NOW_PAGE_NUM = 1;
                }
                gankPresenterImpl.getGankDataFromInternet(isTop, mTitle, AppConstant.GAN_DATA_NUM, NOW_PAGE_NUM);

            }
        });

    }


    @Override
    public void onStart() {
        super.onStart();
        Log.i(TAG, "onResume: -------------------------");
        if (gankAdapter.getItemCount() <= 0) {
            Log.i(TAG, "onResume: --------------------   没有数据");
            loadData();
        } else {
            Log.i(TAG, "onResume:----------------------   有数据 ");
        }
        Log.i(TAG, "onResume:            2");
    }


    private void loadData() {
        Log.i(TAG, "loadData: ");
        ArrayList<GankBean.GankResultsBean> gankDataFromType = GankDao.gankDataFromType(mTitle);
        if (gankDataFromType != null && gankDataFromType.size() > 0) {
            gankAdapter.addList(gankDataFromType);
            gankAdapter.notifyDataSetChanged();
            this.hideLoading();
            Log.i(TAG, "onResume:       拉取数据库数据   " + gankDataFromType.size() + "  个数据");
        } else {
            swipyRefreshLayout.setRefreshing(true);
            Log.i(TAG, "onResume:       拉取网络数据");
            gankPresenterImpl.getGankDataFromInternet(true, mTitle, AppConstant.GAN_DATA_NUM, NOW_PAGE_NUM);
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        // 更新数据库数据
        this.gankPresenterImpl.unSubscribe();

    }

    @Override
    public void showLoading() {

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
            Snackbar.make(recycleView, "服务器出问题了，稍后再试！", Snackbar.LENGTH_SHORT).show();
        }
    }

    @Override
    public void setMoreGankData(GankBean data) {
        if (!data.isError()) {
            gankAdapter.addList(data.getResults());
        }
        gankAdapter.notifyDataSetChanged();
        NOW_PAGE_NUM++;
//        Snackbar.make(recycleView,"已经获取更多数据",Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void setRefreshData(GankBean data) {
        Log.i(TAG, "setRefreshData: ------------------------");
        PrintUtils.printGankBean(data);
        //清除当前显示数据
        gankAdapter.clearData();
        //设置最新数据
        if (!data.isError()) {
            gankAdapter.addList(data.getResults());
        }
        gankAdapter.notifyDataSetChanged();
        NOW_PAGE_NUM++;
        Snackbar.make(recycleView, "已经获取最新数据", Snackbar.LENGTH_SHORT).show();
        GankDao.upDataByAsynchronous(gankAdapter.getGankResulits(), mTitle);
    }
}

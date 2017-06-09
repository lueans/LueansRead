package cn.lueans.lueansread.ui.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayout;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayoutDirection;

import java.util.ArrayList;
import java.util.HashMap;

import cn.lueans.lueansread.R;
import cn.lueans.lueansread.adapter.SoListAdapter;
import cn.lueans.lueansread.db.SoDao;
import cn.lueans.lueansread.entity.SOListBean;
import cn.lueans.lueansread.mvp.contract.SoContract;
import cn.lueans.lueansread.mvp.presenter.SoPresenterImpl;
import cn.lueans.lueansread.ui.temDecoration.SoSpaceItemDecoration;
import cn.lueans.lueansread.utils.NetUtils;
import cn.lueans.lueansread.utils.PrintUtils;

import static cn.lueans.lueansread.R.id.recyclerView;

/**
 * Created by 24277 on 2017/2/23.
 */

public class SoFragment extends Fragment implements SoContract.View {

    private static final String TAG = "SoFragment";

    private static final String GANK_TITLE = "title";
    private RecyclerView recycleView;
    private SwipyRefreshLayout swipyRefreshLayout;
    private String mTitle;
    private static boolean isTop = true;
    private SoPresenterImpl mSoPresenter;

    private SoListAdapter mSoListAdapter;

    HashMap<String, String> soType;

    private String soKey[] = {
            "So美女",
            "明星",
            "萌妹",
            "粉嫩",
            "车模",
            "时装秀",
            "街拍",
            "婚纱",
            "showgirl",
            "cosplay"
    };

    private String soValues[] = {
            "",   //全部
            "599",
            "595",
            "625",
            "600",
            "602",
            "603",
            "596",
            "601",
            "598",

    };


    //fragment 生成器
    public static SoFragment getInstance(String title) {
        SoFragment sofra = new SoFragment();
        Bundle bundle = new Bundle();
        bundle.putString(GANK_TITLE, title);
        sofra.setArguments(bundle);
        return sofra;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();
        mTitle = bundle.getString(GANK_TITLE);
        mSoPresenter = new SoPresenterImpl(this);
        initType();
        Log.i(TAG, "onCreate: title = " + mTitle);

    }

    private void initType() {
        soType = new HashMap<String, String>();
        for (int i = 0; i < soKey.length; i++) {
            soType.put(soKey[i], soValues[i]);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_list, container, false);
        Log.i(TAG, "onCreateView: ----------" + mTitle);
        initRecyclerView(v);
        initSwipyRefreshLayout(v);
        return v;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (mSoListAdapter.getItemCount() <= 0) {
            loadData();
        }
    }

    private void loadData() {
        ArrayList<SOListBean.ListBean> soDataByType = SoDao.getSoDataByType(soType.get(mTitle));
        if (soDataByType != null && soDataByType.size() > 0) {
            mSoListAdapter.clearAllData();
            mSoListAdapter.addDataList(soDataByType);
            recycleView.setBackgroundColor(Color.parseColor("#ddaaaaaa"));
            this.hideLoading();
            Log.i(TAG, "onResume: 拉去数据库数据  ，取得 " + soDataByType.size() + "  个数据");
        } else {
            swipyRefreshLayout.setRefreshing(true);
            Log.i(TAG, "onResume: 拉去网络数据");
            mSoPresenter.getSoDataFromInternet(true, soType.get(mTitle), mSoListAdapter.getItemCount(), "host");
        }

    }

    private void initRecyclerView(View v) {
        recycleView = (RecyclerView) v.findViewById(recyclerView);

        recycleView.setItemAnimator(new DefaultItemAnimator());
        mSoListAdapter = new SoListAdapter(this.getContext(), null);
        //设置分割线
        SoSpaceItemDecoration decoration = new SoSpaceItemDecoration(1);
        recycleView.addItemDecoration(decoration);
        //设置布局样式
        recycleView.setLayoutManager(new StaggeredGridLayoutManager(2,
                StaggeredGridLayoutManager.VERTICAL));
        recycleView.setAdapter(mSoListAdapter);
        //设置滚动式 Glide不加载图片
    }

    private void initSwipyRefreshLayout(View v) {
        swipyRefreshLayout = (SwipyRefreshLayout) v.findViewById(R.id.swipyrefreshlayout);
        swipyRefreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_light,
                android.R.color.holo_red_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_green_light);
        swipyRefreshLayout.setDirection(SwipyRefreshLayoutDirection.BOTH);

       /* swipyRefreshLayout.measure(0, 0);
        swipyRefreshLayout.setRefreshing(true);*/

        swipyRefreshLayout.setOnRefreshListener(new SwipyRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(SwipyRefreshLayoutDirection direction) {
                isTop = (direction == SwipyRefreshLayoutDirection.TOP ? true : false);
//                gankPresenterImpl.getGankDataFromInternet(isTop,mTitle, AppConstant.GAN_DATA_NUM,NOW_PAGE_NUM);
                int index = mSoListAdapter.getItemCount();
                if (isTop) {
                    index = 0;
                }
                mSoPresenter.getSoDataFromInternet(isTop, soType.get(mTitle), index, "new");
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        this.mSoPresenter.unSubscribe();
    }


    @Override
    public void showLoading() {
        Log.i(TAG, "showLoading: ");
    }

    @Override
    public void hideLoading() {
        Log.i(TAG, "hideLoading: ");

        if (swipyRefreshLayout.isRefreshing()) {
            swipyRefreshLayout.setRefreshing(false);
        }
    }

    @Override
    public void showError(Exception e) {


        Log.i(TAG, "showError: ");
        if (!NetUtils.isNetworkConnected(getContext())) {
            Snackbar.make(recycleView, "你与我的距离，就差一个网络", Snackbar.LENGTH_SHORT).show();
        } else {
            Snackbar.make(recycleView, "数据迷路了，刷新找回！", Snackbar.LENGTH_SHORT).show();
        }

    }

    @Override
    public void setMoreData(SOListBean data) {

        Log.i(TAG, "setMoreData: ");
        ArrayList<SOListBean.ListBean> list = data.getList();
        mSoListAdapter.addDataList(list);
        Snackbar.make(recycleView, "已经获取更多数据", Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void setRefreshData(SOListBean data) {
        recycleView.setBackgroundColor(Color.parseColor("#aaaaaa"));
        Log.i(TAG, "setRefreshData: ");
        PrintUtils.printSoList(data);
        //清除当前显示数据
        ArrayList<SOListBean.ListBean> list = data.getList();
        mSoListAdapter.upNowsData(list);
        Snackbar.make(recycleView, "已经获取最新数据", Snackbar.LENGTH_SHORT).show();
        SoDao.upDataByAsynchronous(mSoListAdapter.getSoResulits(), soType.get(mTitle));
    }

}

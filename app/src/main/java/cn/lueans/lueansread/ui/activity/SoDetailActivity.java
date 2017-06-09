package cn.lueans.lueansread.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayout;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayoutDirection;

import java.io.File;

import cn.lueans.lueansread.R;
import cn.lueans.lueansread.adapter.SoDetailAdapter;
import cn.lueans.lueansread.entity.SoDetailBean;
import cn.lueans.lueansread.mvp.contract.SoDetialContract;
import cn.lueans.lueansread.mvp.presenter.SoDetialPresenterImpl;
import cn.lueans.lueansread.ui.temDecoration.SoSpaceItemDecoration;
import cn.lueans.lueansread.utils.DownloadImageUtils;
import cn.lueans.lueansread.utils.NetUtils;
import cn.lueans.lueansread.utils.utilslistener.DownloadImageListener;

import static com.raizlabs.android.dbflow.config.FlowManager.getContext;

public class SoDetailActivity extends BaseActivity implements SoDetialContract.View {

    private static final String TAG = "SoDetailActivity";

    private String url;
    private String desc;
    private String _id;
    private static final String EXTER_SODETAIL_URL = "url";
    private static final String EXTER_SODETAIL_DESC = "dese";
    private static final String EXTER_SODETAIL_ID = "_id";

    private RecyclerView recycleView;
    private SwipyRefreshLayout swipyRefreshLayout;
    private ImageView ivHeader;
    private CollapsingToolbarLayout ctl;

    private SoDetialPresenterImpl soDetailPresenter;
    private SoDetailAdapter mDetailAdapter;
    private ProgressBar pbSoDetail;

    public static Intent newIntent(Context context, String url, String desc,String _id){
        Intent intent = new Intent(context, SoDetailActivity.class);
        intent.putExtra(EXTER_SODETAIL_URL,url);
        intent.putExtra(EXTER_SODETAIL_DESC,desc);
        intent.putExtra(EXTER_SODETAIL_ID,_id);
        context.startActivity(intent);
        return intent;
    }

    public void parseIntent(){
        url = getIntent().getStringExtra(EXTER_SODETAIL_URL);
        desc = getIntent().getStringExtra(EXTER_SODETAIL_DESC);
        _id = getIntent().getStringExtra(EXTER_SODETAIL_ID);
        soDetailPresenter = new SoDetialPresenterImpl(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_so_detail);
        //-------------------------------------------------------
        //获取从参数传递的数据
        parseIntent();
        //-------------------------------------------------------
        pbSoDetail = (ProgressBar) findViewById(R.id.pb_sp_detail);
        pbSoDetail.setVisibility(View.GONE);
        pbSoDetail.setMax(100);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {

                pbSoDetail.setVisibility(View.VISIBLE);

                String[] imgUrls = mDetailAdapter.getImgUrls();
                DownloadImageUtils.downloadImageList(SoDetailActivity.this, imgUrls, desc, new DownloadImageListener() {
                    @Override
                    public void onSuccess() {
                        Snackbar.make(view,"下载成功",Snackbar.LENGTH_SHORT).show();
                        pbSoDetail.setVisibility(View.GONE);
                    }

                    @Override
                    public void onProgress(int progress, File positionFile) {
                        pbSoDetail.setProgress(progress);
                    }

                    @Override
                    public void onfial(Exception e) {
                        Snackbar.make(view,"下载失败",Snackbar.LENGTH_SHORT).show();
                    }
                });

            }
        });

        initbar();
        initRecyclerView();
        initSwipyRefreshLayout();

    }

    private void initbar() {

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);
        //----------------------------------------------

        ctl = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        ivHeader = (ImageView) findViewById(R.id.iv_so_detial_head);
        ctl.setTitle(desc);
        Glide.with(this).load(url).listener(new RequestListener<String, GlideDrawable>() {
            @Override
            public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                return false;
            }
        }).into(ivHeader);
    }

    private void initRecyclerView() {
        recycleView = (RecyclerView)findViewById(R.id.recyclerView);
        recycleView.setItemAnimator(new DefaultItemAnimator());

        //设置分割线
        SoSpaceItemDecoration decoration = new SoSpaceItemDecoration(1);
        recycleView.addItemDecoration(decoration);
        //设置布局样式
        recycleView.setLayoutManager(new StaggeredGridLayoutManager(2,
                StaggeredGridLayoutManager.VERTICAL));
        //设置适配器
        mDetailAdapter = new SoDetailAdapter(this,null);
        recycleView.setAdapter(mDetailAdapter);

        soDetailPresenter.getSoDetailDataFromInternet(_id);
    }

    private void initSwipyRefreshLayout() {
        swipyRefreshLayout = (SwipyRefreshLayout)findViewById(R.id.swipyrefreshlayout);
        swipyRefreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_light,
                android.R.color.holo_red_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_green_light);

        swipyRefreshLayout.measure(0,0);
        swipyRefreshLayout.setRefreshing(true);

        swipyRefreshLayout.setOnRefreshListener(new SwipyRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(SwipyRefreshLayoutDirection direction) {
                boolean isTop = (direction == SwipyRefreshLayoutDirection.TOP ? true : false);
                //                gankPresenterImpl.getGankDataFromInternet(isTop,mTitle, AppConstant.GAN_DATA_NUM,NOW_PAGE_NUM);
               soDetailPresenter.getSoDetailDataFromInternet(_id);
            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {
        if (swipyRefreshLayout.isRefreshing()){
            swipyRefreshLayout.setRefreshing(false);
        }
    }

    @Override
    public void showError(Exception e) {
        if (!NetUtils.isNetworkConnected(getContext())){
            Snackbar.make(recycleView,"你与我的距离，就差一个网络",Snackbar.LENGTH_SHORT).show();
        }else{
            Snackbar.make(recycleView,"服务器出问题了，稍后再试！",Snackbar.LENGTH_SHORT).show();
        }
    }

    @Override
    public void setRefreshData(SoDetailBean data) {
        Log.i(TAG, "setRefreshData: ----------------------------");
        mDetailAdapter.clearData();
        mDetailAdapter.addAllData(data.getList());
        Snackbar.make(recycleView,"已经最新数据",Snackbar.LENGTH_SHORT).show();
        swipyRefreshLayout.setEnabled(false);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_so_detail,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        switch(itemId){
            case android.R.id.home:
                finish();
                break;
            case R.id.action_share:
                Snackbar.make(recycleView,"还没有实现分享功能", Snackbar.LENGTH_SHORT).show();
                break;
        }
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.soDetailPresenter.unSubscribe();
    }
}

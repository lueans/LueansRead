package cn.lueans.lueansread.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayout;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayoutDirection;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import cn.lueans.lueansread.R;
import cn.lueans.lueansread.adapter.NewsDetailAdapter;
import cn.lueans.lueansread.entity.NewsDetailBean;
import cn.lueans.lueansread.mvp.contract.NewsDetailContract;
import cn.lueans.lueansread.mvp.presenter.NewsDetailPresenterImpl;
import cn.lueans.lueansread.retrofitserver.NewsServer;
import cn.lueans.lueansread.retrofitserver.NewsSingle;
import cn.lueans.lueansread.ui.temDecoration.SoSpaceItemDecoration;
import cn.lueans.lueansread.utils.HtmlUtils;
import cn.lueans.lueansread.utils.NetUtils;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

import static com.raizlabs.android.dbflow.config.FlowManager.getContext;

public class NewsDetailActivity extends BaseActivity implements NewsDetailContract.View {

    private static final String TAG = "NewsDetailActivity";
    private String postId;

    private String mTitle;
    private String mImageUrl;
    private String w3Url;

    private static final String ID_KEY = "postId";
    private static final String TITLE_KEY = "title";
    private static final String URL_KEY = "imageUrl";
    private static final String URL_3W = "url_3w";

    private WebView mWebView;
    private SwipyRefreshLayout swipyRefreshLayout;
    private NewsDetailPresenterImpl mDetailPresenter;
    private ImageView ivHead;
    private RecyclerView recyclerView;
    private NewsDetailAdapter newsDetailAdapter;
    private TextView tvRecommend;


    public static Intent newIntent(Context context, String title, String imageUrl, String postId, String w3Url) {
        Intent intent = new Intent(context, NewsDetailActivity.class);
        intent.putExtra(TITLE_KEY, title);
        intent.putExtra(URL_KEY, imageUrl);
        intent.putExtra(ID_KEY, postId);
        intent.putExtra(URL_3W, w3Url);
        context.startActivity(intent);
        return intent;
    }

    private void parseIntent() {

        Intent intent = getIntent();
        mTitle = intent.getStringExtra(TITLE_KEY);
        mImageUrl = intent.getStringExtra(URL_KEY);
        postId = intent.getStringExtra(ID_KEY);
        w3Url = intent.getStringExtra(URL_3W);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);



        parseIntent();
        iniTbar();
        initwebView();
        initSwipyRefreshLayout();
        initRecycle();
        mDetailPresenter = new NewsDetailPresenterImpl(this);
        mDetailPresenter.getNewsDetail(postId);
    }

    private void initRecycle() {

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        recyclerView.setBackgroundColor(Color.parseColor("#aaaaaa"));

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        //设置分割线
        SoSpaceItemDecoration decoration = new SoSpaceItemDecoration(1);
        recyclerView.addItemDecoration(decoration);

        newsDetailAdapter = new NewsDetailAdapter(NewsDetailActivity.this, null);
        recyclerView.setAdapter(newsDetailAdapter);

        tvRecommend = (TextView) findViewById(R.id.tv_recommend);

    }

    private void initwebView() {
        mWebView = (WebView) findViewById(R.id.wv_web);
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);  //这句话将图片缩放至屏幕宽度，非常好嘛

    }

    private void iniTbar() {

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);
        getSupportActionBar().setTitle(mTitle);

        ivHead = (ImageView) findViewById(R.id.iv_news_detail_photo);
        Glide.with(getApplicationContext()).load(mImageUrl).into(ivHead);


    }


    private void initSwipyRefreshLayout() {

        swipyRefreshLayout = (SwipyRefreshLayout) findViewById(R.id.swipyrefreshlayout);
        swipyRefreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_light,
                android.R.color.holo_red_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_green_light);
        swipyRefreshLayout.setDirection(SwipyRefreshLayoutDirection.TOP);

        swipyRefreshLayout.measure(0, 0);
        swipyRefreshLayout.setRefreshing(true);

        swipyRefreshLayout.setOnRefreshListener(new SwipyRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(SwipyRefreshLayoutDirection direction) {
                boolean isTop = (direction == SwipyRefreshLayoutDirection.TOP ? true : false);
                mDetailPresenter.getNewsDetail(postId);
            }
        });

    }

    private void initData() {
        NewsServer instance = NewsSingle.getInstance();
        final Observable<Map<String, NewsDetailBean>> newDetail = instance.getNewDetail(postId);
        newDetail.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Map<String, NewsDetailBean>>() {
                    @Override
                    public void onCompleted() {
                        Log.i(TAG, "onCompleted: ");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i(TAG, "onError: ");
                        Log.i(TAG, "onError: " + e.toString());
                    }

                    @Override
                    public void onNext(Map<String, NewsDetailBean> stringNewsDetailBeanMap) {
                        Log.i(TAG, "onNext: ");
                        NewsDetailBean newsDetailBean = stringNewsDetailBeanMap.get(postId);
                        Log.i(TAG, "onNext: " + newsDetailBean.getBody());
                        Log.i(TAG, "onNext: " + newsDetailBean.getSource());
                        Log.i(TAG, "onNext: " + newsDetailBean.getPtime());
                        String html = HtmlUtils.getHtml(newsDetailBean);
                        mWebView.loadDataWithBaseURL("file:///android_asset/", html, "text/html", "utf-8", "");
                        if (swipyRefreshLayout.isRefreshing()) {
                            swipyRefreshLayout.setRefreshing(false);
                        }

                    }
                });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_so_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        switch (itemId) {
            case android.R.id.home:
                finish();
                break;
            case R.id.action_share:
//                ShareUtils.shareText(NewsDetailActivity.this, mTitle + " " + w3Url + "\n来自「简阅」app");
                Toast.makeText(this, "dianji", Toast.LENGTH_SHORT).show();
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
                    mWebView.evaluateJavascript("document.body.style.backgroundColor=\"black\";document.body.style.color=\"white\";", null);
                } else {
                    mWebView.loadUrl("javascript:document.body.style.backgroundColor=\"#black\";document.body.style.color=\"white\";");
                }
                break;
        }
        return true;
    }

    @Override
    public void hideLoading() {
        if (swipyRefreshLayout.isRefreshing()) {
            swipyRefreshLayout.setRefreshing(false);
        }
    }

    @Override
    public void showError(Exception e) {

        Log.i(TAG, "showError: " + e.toString());

        if (!NetUtils.isNetworkConnected(getApplicationContext())) {
            Snackbar.make(mWebView, "你与我的距离，就差一个网络", Snackbar.LENGTH_SHORT).show();
        } else {
            Snackbar.make(mWebView, "数据迷路了，刷新找回！", Snackbar.LENGTH_SHORT).show();
        }
    }

    @Override
    public void setRefreshNewsData(final NewsDetailBean data) {
        String html;
        if (isNightTheme){
            Log.i(TAG, "setRefreshNewsData: ------------night");
            html = HtmlUtils.getHtmlNight(data);
        }else{
            Log.i(TAG, "setRefreshNewsData: ------------day");
            html = HtmlUtils.getHtml(data);
        }
        mWebView.loadDataWithBaseURL("file:///android_asset/", html, "text/html", "utf-8", "");
        if (data.getRelative_sys() != null && data.getRelative_sys().size() > 0) {
            Observable.timer(1, TimeUnit.SECONDS)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Action1<Long>() {
                        @Override
                        public void call(Long aLong) {
                            tvRecommend.setVisibility(View.VISIBLE);
                            ArrayList<NewsDetailBean.RelativeSysBean> relative_sys = data.getRelative_sys();
                            newsDetailAdapter.setmList(relative_sys);
                        }
                    });
        } else {
            tvRecommend.setVisibility(View.GONE);
        }
        swipyRefreshLayout.setEnabled(false);
    }

    @Override
    public void finish() {
        ViewGroup view = (ViewGroup) getWindow().getDecorView();
        view.removeAllViews();
        super.finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.mDetailPresenter.unSubscribe();
    }
}

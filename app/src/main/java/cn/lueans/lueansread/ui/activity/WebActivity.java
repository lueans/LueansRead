package cn.lueans.lueansread.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;

import cn.lueans.lueansread.R;
import cn.lueans.lueansread.utils.BrowserUtils;
import cn.lueans.lueansread.utils.ShareUtils;

import static cn.lueans.lueansread.R.menu.web;

public class WebActivity extends BaseActivity {

    private String url = "";
    private String desc = "";
    public static final String EXTRA_WEB_URL = "url";
    public static final String EXTRA_WEB_DESC = "desc";
    private WebView wvWeb;
    private FrameLayout fl;

    public static Intent newIntent(Context mcontext, String url, String desc) {
        Intent intent = new Intent(mcontext, WebActivity.class);
        intent.putExtra(EXTRA_WEB_URL, url);
        intent.putExtra(EXTRA_WEB_DESC, desc);
        mcontext.startActivity(intent);
        return intent;
    }


    public void parseIntent() {
        url = getIntent().getStringExtra(EXTRA_WEB_URL);
        desc = getIntent().getStringExtra(EXTRA_WEB_DESC);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        parseIntent();
        initBar();
        iniFl();
        initWebView();

    }

    private void iniFl() {
        fl = (FrameLayout) findViewById(R.id.fl_webview_load);
        fl.setVisibility(View.VISIBLE);
    }

    private void initBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);
        getSupportActionBar().setTitle(desc);
    }

    private void initWebView() {
        wvWeb = (WebView) findViewById(R.id.wv_web);
        wvWeb.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                if (newProgress == 100) {
                    fl.setVisibility(View.GONE);
                    url = wvWeb.getUrl();
                } else {
                    fl.setVisibility(View.VISIBLE);
                }
            }
        });

        wvWeb.setWebViewClient(new WebViewClient());
        wvWeb.getSettings().setBuiltInZoomControls(false);
        wvWeb.getSettings().setJavaScriptEnabled(true);
        wvWeb.getSettings().setDomStorageEnabled(true);
        wvWeb.getSettings().setDatabaseEnabled(true);
//        wvWeb.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        wvWeb.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);  //设置 缓存模式
        String cacheDirPath = getFilesDir().getAbsolutePath();
        //      String cacheDirPath = getCacheDir().getAbsolutePath()+Constant.APP_DB_DIRNAME;
        //设置数据库缓存路径
        wvWeb.getSettings().setDatabasePath(cacheDirPath);
        //设置  Application Caches 缓存目录
        wvWeb.getSettings().setAppCachePath(cacheDirPath);
        wvWeb.getSettings().setAppCacheEnabled(true);


        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
            Log.i("log", "initWebView: --------------  1");
            wvWeb.evaluateJavascript("document.body.style.backgroundColor=\"black\";document.body.style.color=\"white\";", null);
        } else {
            Log.i("log", "initWebView: --------------  2");
            wvWeb.loadUrl("javascript:document.body.style.backgroundColor=\"#black\";document.body.style.color=\"white\";");
        }
        wvWeb.loadUrl(url);



    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            if (keyCode == KeyEvent.KEYCODE_BACK) {
                if (wvWeb.canGoBack()) {
                    wvWeb.goBack();
                } else {
                    finish();
                }
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(web, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        switch (itemId) {
            case android.R.id.home:
                finish();
                break;
            case R.id.action_copy:
                BrowserUtils.copyToClipBoard(getApplicationContext(), url);
                break;
            case R.id.action_open:
                BrowserUtils.openInBrowser(WebActivity.this, url);
                break;
            case R.id.action_share:
                String body = desc + "\n" + url + "\n来自「简阅」";
                ShareUtils.shareMsg(WebActivity.this, "简阅", "简阅", body, null);
                break;
        }
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        wvWeb.setVisibility(View.GONE);
    }

    @Override
    public void finish() {
        ViewGroup view = (ViewGroup) getWindow().getDecorView();
        view.removeAllViews();
        super.finish();
        //右边划入动画
//        overridePendingTransition(R.anim.null_anim,R.anim.slide_right_out);
    }
}

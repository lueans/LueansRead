package cn.lueans.lueansread;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

import cn.lueans.lueansread.adapter.MainFragmentAdapter;
import cn.lueans.lueansread.constant.AppConstant;
import cn.lueans.lueansread.ui.activity.AboutAppActivity;
import cn.lueans.lueansread.ui.activity.BaseActivity;
import cn.lueans.lueansread.ui.activity.WebActivity;
import cn.lueans.lueansread.ui.fragment.GankFragment;
import cn.lueans.lueansread.ui.fragment.NewsFragment;
import cn.lueans.lueansread.ui.fragment.SoFragment;
import cn.lueans.lueansread.ui.view.ShowDialog;
import cn.lueans.lueansread.utils.PreferenceUtils;
import cn.lueans.lueansread.utils.ShareUtils;


public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private static final String TAG = "MainActivity";
    private TabLayout tabs;
    String gankTitle[] = {
            "Android",
            "iOS",
            "前端",
            "拓展资源"
    };

    String soTitle[] = {
            "明星",
            "萌妹",
            //            "粉嫩",
            "车模",
            //            "时装秀",
            //            "街拍",
            //            "婚纱",
            //            "showgirl",
            "cosplay"
    };

    String newsTitle[] = {
            "新闻",
//            "娱乐",
//            "财经",
            "科技",
            "电影",
            "笑话",
            "时尚",
//            "情感",
//            "精选",
//            "社会",
//            "军事",
    };

    String indexString;

    //双击推出的实现
    private long firstTime = 0;
    private boolean isUpData = false;


    private ViewPager mViewPager;
    private ArrayList<Fragment> mGankFragment;
    private ArrayList<Fragment> mSoFragment;
    private ArrayList<Fragment> mNewsFragment;
    private MainFragmentAdapter gankFragmentAdapter;
    private MainFragmentAdapter soFragmentAdapter;
    private MainFragmentAdapter newsFragmentAdapter;
    private DrawerLayout mDrawer;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);

                switch (indexString) {
                    case "Gank":
                        mViewPager.setOffscreenPageLimit(mGankFragment.size());
                        mViewPager.setAdapter(gankFragmentAdapter);
                        tabs.setTabMode(TabLayout.MODE_FIXED);
                        tabs.setupWithViewPager(mViewPager);
                        break;

                    case "妹纸":
//                        mViewPager.removeAllViews();
                        mViewPager.setOffscreenPageLimit(mNewsFragment.size());
                        mViewPager.setAdapter(soFragmentAdapter);
                        tabs.setTabMode(TabLayout.MODE_FIXED);
                        tabs.setupWithViewPager(mViewPager);
                        break;

                    case "休闲":
//                        mViewPager.removeAllViews();
                        mViewPager.setOffscreenPageLimit(mNewsFragment.size());
                        mViewPager.setAdapter(newsFragmentAdapter);
                        tabs.setTabMode(TabLayout.MODE_SCROLLABLE);
                        tabs.setupWithViewPager(mViewPager);
                        break;
                }

            }
        };

        mDrawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View drawview = navigationView.inflateHeaderView(R.layout.nav_header_main);
        Drawable background = toolbar.getBackground();
        drawview.setBackground(background);

        final ImageView ivPattern = (ImageView) drawview.findViewById(R.id.iv_nav_pattern);
        ivPattern.setBackgroundResource(isNightTheme ? R.drawable.ic_brightness_high_black_24dp: R.drawable.ic_brightness_4_black_24dp);
        ivPattern.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isNightTheme) {
                    ivPattern.setBackgroundResource(R.drawable.ic_brightness_high_black_24dp);
                    //调用recreate()使设置生效
                    PreferenceUtils.setPreferenceBoolean(MainActivity.this, PreferenceUtils.NIGHT_KEY, false);
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                } else {
                    ivPattern.setBackgroundResource(R.drawable.ic_brightness_4_black_24dp);
                    PreferenceUtils.setPreferenceBoolean(MainActivity.this, PreferenceUtils.NIGHT_KEY, true);
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                }
                PreferenceUtils.setPreferenceString(MainActivity.this,PreferenceUtils.MAIN_INDEX_MENU,indexString);
                getWindow().setWindowAnimations(R.style.WindowAnimationFadeInOut);
                recreate();
                isNightTheme = !isNightTheme;
            }
        });

        //-------------------------------------------------------------------

        indexString = PreferenceUtils.getPreferenceString(MainActivity.this,PreferenceUtils.MAIN_INDEX_MENU,"Gank");

        initFragment();
        initViewPager();
        initTabLayout();
    }

    private void initFragment() {
        mGankFragment = new ArrayList<>();
        mSoFragment = new ArrayList<>();
        mNewsFragment = new ArrayList<>();
        Fragment instance;
        for (String str : gankTitle) {
            instance = GankFragment.getInstance(str);
            mGankFragment.add(instance);
        }

        for (String str : soTitle) {
            instance = SoFragment.getInstance(str);
            mSoFragment.add(instance);
        }
        for (String str : newsTitle) {
            instance = NewsFragment.getInstance(str);
            mNewsFragment.add(instance);
        }
    }

    private void initViewPager() {
        Log.i(TAG, "initViewPager: -------------------------"+ indexString);
        mViewPager = (ViewPager) findViewById(R.id.vp_main);
        gankFragmentAdapter = new MainFragmentAdapter(getSupportFragmentManager(), mGankFragment, gankTitle);
        soFragmentAdapter = new MainFragmentAdapter(getSupportFragmentManager(), mSoFragment, soTitle);
        newsFragmentAdapter = new MainFragmentAdapter(getSupportFragmentManager(), mNewsFragment, newsTitle);
        switch (indexString) {
            case "Gank":
                navigationView.getMenu().getItem(0).setChecked(true);
                mViewPager.setOffscreenPageLimit(mGankFragment.size());
                mViewPager.setAdapter(gankFragmentAdapter);
                break;
            case "妹纸":
                navigationView.getMenu().getItem(1).setChecked(true);
                mViewPager.setOffscreenPageLimit(mNewsFragment.size());
                mViewPager.setAdapter(soFragmentAdapter);
                break;
            case "休闲":
                navigationView.getMenu().getItem(2).setChecked(true);
                mViewPager.setOffscreenPageLimit(mNewsFragment.size());
                mViewPager.setAdapter(newsFragmentAdapter);
                break;
        }
    }

    private void initTabLayout() {
        tabs = (TabLayout) findViewById(R.id.tabs);
        tabs.setupWithViewPager(mViewPager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_app) {
            Intent intent = new Intent(MainActivity.this, AboutAppActivity.class);
            startActivity(intent);
        } else if (id == R.id.action_me) {
            WebActivity.newIntent(MainActivity.this, AppConstant.AUOUT_DEVELOPER_HTTP, "关于开发者");
        }
        return true;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_gank) {
            //干货
            if (!indexString.equals("Gank")) {
                indexString = "Gank";
                isUpData = true;
                setTitle(gankTitle);
                tabs.setTabMode(TabLayout.MODE_FIXED);
                mViewPager.removeAllViews();
            }
        } else if (id == R.id.nav_gallery) {
            //So妹纸
            if (!indexString.equals("妹纸")) {
                indexString = "妹纸";
                isUpData = true;
                setTitle(soTitle);
                tabs.setTabMode(TabLayout.MODE_FIXED);
                mViewPager.removeAllViews();
             
            }
        } else if (id == R.id.nav_relax) {
            //休闲
            if (!indexString.equals("休闲")) {
                indexString = "休闲";
                isUpData = true;
                setTitle(newsTitle);
                tabs.setTabMode(TabLayout.MODE_SCROLLABLE);
                mViewPager.removeAllViews();
            }
        }else if (id == R.id.nav_skin){
            //换皮肤
            boolean preferenceBoolean = PreferenceUtils.getPreferenceBoolean(MainActivity.this, PreferenceUtils.NIGHT_KEY, false);
            if (preferenceBoolean){
                Snackbar.make(mDrawer,"请先关闭夜间模式",Snackbar.LENGTH_LONG).setAction("关闭", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        PreferenceUtils.setPreferenceBoolean(MainActivity.this,PreferenceUtils.NIGHT_KEY,false);
                        recreate();
                    }
                }).show();
                return true;
            }
            ShowDialog builder = ShowDialog.builder();
            builder.showThemeDialog(MainActivity.this,indexString);
            return true;
        } else if (id == R.id.nav_share) {
            ShareUtils.shareText(MainActivity.this, "https://fir.im/r7ts\n[ 简阅App ]");
        }
        mDrawer.closeDrawer(GravityCompat.START);
        return true;
    }


    //双击推出的实现
    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                if (mDrawer.isDrawerOpen(GravityCompat.START)) {
                    mDrawer.closeDrawer(GravityCompat.START);
                    return true;
                }
                long secondTime = System.currentTimeMillis();
                if (secondTime - firstTime > 2000) {
                    Toast.makeText(MainActivity.this, "再按一次退出", Toast.LENGTH_SHORT).show();
                    firstTime = secondTime;
                    return true;
                } else {
                    finish();
                }
                break;
        }
        return super.onKeyUp(keyCode, event);
    }


    @Override
    protected void onStart() {
        super.onStart();
        tabs.setupWithViewPager(mViewPager);
    }

    //----------------------------------------------------------
    public void setTitle(String[] title) {
        tabs.removeAllTabs();
        for (String str : title) {
            tabs.addTab(tabs.newTab().setText(str));
        }
    }



    @Override
    public void finish() {
        PreferenceUtils.setPreferenceString(MainActivity.this,PreferenceUtils.MAIN_INDEX_MENU,indexString);
        super.finish();
    }
}

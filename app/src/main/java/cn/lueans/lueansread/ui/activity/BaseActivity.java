package cn.lueans.lueansread.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;

import cn.lueans.lueansread.utils.PreferenceUtils;
import cn.lueans.lueansread.utils.ThemeUtils;

/**
 * Created by 24277 on 2017/4/7.
 */

public class BaseActivity extends AppCompatActivity {

    public static boolean isNightTheme = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        initTheme();
        super.onCreate(savedInstanceState);
    }

    private void initTheme(){
        ThemeUtils.Theme theme = ThemeUtils.getCurrentTheme(this);
        isNightTheme = PreferenceUtils.getPreferenceBoolean(this,PreferenceUtils.NIGHT_KEY,false);
        ThemeUtils.changTheme(this, theme);
        if (isNightTheme){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }else{
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }

}

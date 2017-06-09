package cn.lueans.lueansread;

import android.app.Application;
import android.support.v7.app.AppCompatDelegate;

import com.raizlabs.android.dbflow.config.FlowManager;

import im.fir.sdk.FIR;

/**
 * Created by 24277 on 2017/2/23.
 */

public class App extends Application {

    private static App mContext;
    @Override
    public void onCreate() {
        FlowManager.init(this);
        FIR.init(this);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        super.onCreate();
    }

    //单例模式
    public static App getInstance(){
        if (mContext == null) {
            mContext = new App();
        }
        return mContext;
    }
}

package cn.lueans.lueansread.db;

import com.raizlabs.android.dbflow.annotation.Database;

/**
 * Created by 24277 on 2017/2/24.
 */
@Database(name = LueansDB.DB_NAME,version = LueansDB.DB_VERSION)
public class LueansDB {
    public static final String DB_NAME = "lueans_db";
    public static final int DB_VERSION = 1;
}

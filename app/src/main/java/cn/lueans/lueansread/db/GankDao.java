package cn.lueans.lueansread.db;

import android.util.Log;

import com.raizlabs.android.dbflow.config.DatabaseDefinition;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.language.Select;
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper;
import com.raizlabs.android.dbflow.structure.database.transaction.ITransaction;
import com.raizlabs.android.dbflow.structure.database.transaction.Transaction;

import java.util.ArrayList;
import java.util.List;

import cn.lueans.lueansread.entity.GankBean;
import cn.lueans.lueansread.entity.SOListBean;

/**
 * Created by 24277 on 2017/2/25.
 */

public class GankDao {
    private static final String TAG = "GankDao";
    public static void saveGankData(ArrayList<GankBean.GankResultsBean> mLists){
        GankTable gankTable;
        ArrayList<String> images;
        for (GankBean.GankResultsBean bean : mLists) {
            gankTable = new GankTable();
            gankTable.who = bean.getWho();
            gankTable.publishedAt = bean.getPublishedAt();
            gankTable.desc = bean.getDesc();
            gankTable.type = bean.getType();
            gankTable.url = bean.getUrl();
            gankTable.used = bean.isUsed()+"";
            gankTable.objectId = bean.get_id();
            gankTable.createdAt = bean .getCreatedAt();
            images = bean.getImages();
            if (images != null){
                if (images.size() > 0){
                    gankTable.imageUrl = images.get(0);
                }
            }
            gankTable.save();
        }
    }

    public static ArrayList<GankBean.GankResultsBean> gankDataFromType(String type){
        ArrayList<GankBean.GankResultsBean> mLists = new ArrayList<>();
        List<GankTable> gankTables = new Select()
                .from(GankTable.class)
                .where(GankTable_Table.type.eq(type))
                .queryList();
        GankBean.GankResultsBean resultsBean;
        ArrayList<String> img;

        for (GankTable bean : gankTables) {
            resultsBean = new GankBean.GankResultsBean();
            resultsBean.setWho(bean.who);
            resultsBean.setPublishedAt(bean.publishedAt);
            resultsBean.setDesc(bean.desc);
            resultsBean.setType(bean.type);
            resultsBean.setUrl(bean.url);
            resultsBean.setUsed(Boolean.parseBoolean(bean.used));
            resultsBean.set_id(bean.objectId);
            resultsBean.setCreatedAt(bean.createdAt);
            String image = bean.imageUrl;
            if (image != null) {
                img = new ArrayList<>();
                img.add(image);
            }else{
                img = null;
            }
            resultsBean.setImages(img);
            mLists.add(resultsBean);
        }
        return mLists;
    }

    public static void deleteDataByType(String type ){
        List<GankTable> gankTables = new Select()
                .from(GankTable.class)
                .where(GankTable_Table.type.eq(type))
                .queryList();
        for (GankTable gank : gankTables) {
            gank.delete();
        }
        Log.i(TAG, "deleteDataByType:   清理成功");
    }

    //异步事物事物更新数据
    public static void upDataByAsynchronous (final ArrayList<GankBean.GankResultsBean> mLists, final String type){
        DatabaseDefinition database = FlowManager.getDatabase(LueansDB.class);
        database.beginTransactionAsync(new ITransaction() {
            @Override
            public void execute(DatabaseWrapper databaseWrapper) {
                deleteDataByType(type);
                saveGankData(mLists);
            }
        }).success(new Transaction.Success() {
            @Override
            public void onSuccess(Transaction transaction) {
                Log.i(TAG, "onSuccess:     成功");
            }
        }).error(new Transaction.Error() {
            @Override
            public void onError(Transaction transaction, Throwable error) {
                Log.i(TAG, "onSuccess:     失败");
            }
        }).build().execute();
    }

}

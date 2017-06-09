package cn.lueans.lueansread.db;

import android.util.Log;

import com.raizlabs.android.dbflow.config.DatabaseDefinition;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper;
import com.raizlabs.android.dbflow.structure.database.transaction.ITransaction;
import com.raizlabs.android.dbflow.structure.database.transaction.Transaction;

import java.util.ArrayList;

import cn.lueans.lueansread.entity.SOListBean;

/**
 * Created by 24277 on 2017/3/30.
 */

public class ShiwuTestDao {


    private static final String TAG = "ShiwuTestDao";

    public static void addSoList(final ArrayList<SOListBean.ListBean> mListBeans, final String type){
        DatabaseDefinition database = FlowManager.getDatabase(LueansDB.class);
        database.beginTransactionAsync(new ITransaction() {
            @Override
            public void execute(DatabaseWrapper databaseWrapper) {
                SoListTable soListTable;
                for (SOListBean.ListBean bean : mListBeans) {
                    soListTable = new SoListTable();
                    soListTable.so_id = bean.getId();
                    soListTable.image_id = bean.getImageid();
                    soListTable.group_title = bean.getGroup_title();
                    soListTable.so_tag = bean.getTag();
                    soListTable.label = bean.getLabel();
                    soListTable.grpseq = bean.getGrpseq();
                    soListTable.cover_imgurl = bean.getCover_imgurl();
                    soListTable.cover_height = bean.getCover_height();
                    soListTable.cover_width = bean.getCover_width();
                    soListTable.total_count = bean.getTotal_count();
                    soListTable.index = bean.getIndex();
                    soListTable.qhimg_url = bean.getQhimg_url();
                    soListTable.qhimg_thumb_url = bean.getQhimg_thumb_url();
                    soListTable.qhimg_height = bean.getQhimg_height();
                    soListTable.qhimg_width = bean.getQhimg_width();
                    soListTable.so_type = type;
                    soListTable.save();
                }
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

    public static void upDataBySynchronous(final ArrayList<SOListBean.ListBean> mListBeans, final String type){

    }

}

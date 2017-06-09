package cn.lueans.lueansread.db;

import android.util.Log;

import com.raizlabs.android.dbflow.config.DatabaseDefinition;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.language.Condition;
import com.raizlabs.android.dbflow.sql.language.Delete;
import com.raizlabs.android.dbflow.sql.language.SQLCondition;
import com.raizlabs.android.dbflow.sql.language.Select;
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper;
import com.raizlabs.android.dbflow.structure.database.transaction.ITransaction;
import com.raizlabs.android.dbflow.structure.database.transaction.Transaction;

import java.util.ArrayList;
import java.util.List;

import cn.lueans.lueansread.entity.SOListBean;

/**
 * Created by 24277 on 2017/2/25.
 */

public class SoDao {

    private static final String TAG = "SoDao";

    public static void saveSoData(ArrayList<SOListBean.ListBean> mListBeans,String type){
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
        Log.i(TAG, "saveSoData: 保存成功");
    }

    public static ArrayList<SOListBean.ListBean> getSoDataByType(String type){
        ArrayList<SOListBean.ListBean> mList = new ArrayList<>();
        List<SoListTable> soListTables = new Select()
                .from(SoListTable.class)
                .where(SoListTable_Table.so_type.eq(type))
                .queryList();
        SOListBean.ListBean listBean;
        for (SoListTable soTable : soListTables) {
            listBean = new SOListBean.ListBean();

            listBean.setId(soTable.so_id);
            listBean.setImageid(soTable.image_id);
            listBean.setGroup_title(soTable.group_title);
            listBean.setTag(soTable.so_tag);
            listBean.setLabel(soTable.label);
            listBean.setGrpseq(soTable.grpseq);
            listBean.setCover_imgurl(soTable.cover_imgurl);
            listBean.setCover_height(soTable.cover_height);
            listBean.setCover_width(soTable.cover_width);
            listBean.setTotal_count(soTable.total_count);
            listBean.setIndex(soTable.index);
            listBean.setQhimg_url(soTable.qhimg_url);
            listBean.setQhimg_thumb_url(soTable.qhimg_thumb_url);
            listBean.setQhimg_height(soTable.qhimg_height);
            listBean.setQhimg_width(soTable.qhimg_width);
            mList.add(listBean);
        }

        return mList;
    }

    public static void deleteSoDdata(String type){
        List<SoListTable> soListTables = new Select()
                .from(SoListTable.class)
                .where(SoListTable_Table.so_type.eq(type))
                .queryList();
        for (SoListTable soTable:soListTables) {
            soTable.delete();
        }

        Log.i(TAG, "deleteSoDdata: 数据清理成功");
    }


    //异步事物事物更新数据
    public static void upDataByAsynchronous (final ArrayList<SOListBean.ListBean> mListBeans, final String type){
        DatabaseDefinition database = FlowManager.getDatabase(LueansDB.class);
        database.beginTransactionAsync(new ITransaction() {
            @Override
            public void execute(DatabaseWrapper databaseWrapper) {
                deleteSoDdata(type);
                saveSoData(mListBeans,type);
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



    //同步事物处理
    public static void upDataBySynchronous(final ArrayList<SOListBean.ListBean> mListBeans, final String type){
        DatabaseDefinition database = FlowManager.getDatabase(LueansDB.class);
        database.executeTransaction(new ITransaction() {
            @Override
            public void execute(DatabaseWrapper databaseWrapper) {
                // do something here
                deleteSoDdata(type);
                saveSoData(mListBeans,type);
            }
        });
    }
}

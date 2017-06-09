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

import cn.lueans.lueansread.entity.NewsListBean;
import cn.lueans.lueansread.entity.SOListBean;

/**
 * Created by 24277 on 2017/3/18.
 */

public class NewsDao {

    private static final String TAG = "NewsDao";

    public static void saveGankData(ArrayList<NewsListBean> mLists, String newType) {

        NewsTable newsTable;
        for (NewsListBean bean : mLists) {
            newsTable = new NewsTable();
            newsTable.postid = bean.getPostid();
            newsTable.replyCount = bean.getReplyCount();
            newsTable.ltitle = bean.getTitle();
            newsTable.docid = bean.getDocid();
            newsTable.priority = bean.getPriority();
            newsTable.lmodify = bean.getLmodify();
            newsTable.url_3w = bean.getUrl_3w();
            newsTable.votecount = bean.getVotecount();
            newsTable.url = bean.getUrl();
            newsTable.source = bean.getSource();
            newsTable.imgsrc = bean.getImgsrc();
            newsTable.ptime = bean.getPtime();
            newsTable.skipID = bean.getSkipID();
            newsTable.skipType = bean.getSkipType();
            newsTable.photosetID = bean.getPhotosetID();
            newsTable.newsType = newType;
            newsTable.save();
        }

    }

    public static ArrayList<NewsListBean> getNewsList(String type) {
        List<NewsTable> newsTableList = new Select()
                .from(NewsTable.class)
                .where(NewsTable_Table.newsType.eq(type))
                .queryList();
        ArrayList<NewsListBean> newsLists = new ArrayList<>();
        NewsListBean newsListBean;
        for (NewsTable newsTable : newsTableList) {
            newsListBean = new NewsListBean();
            newsListBean.setPostid(newsTable.postid);
            newsListBean.setReplyCount(newsTable.replyCount);
            newsListBean.setTitle(newsTable.ltitle);
            newsListBean.setDocid(newsTable.docid);
            newsListBean.setPriority(newsTable.priority);
            newsListBean.setLmodify(newsTable.lmodify);
            newsListBean.setUrl_3w(newsTable.url_3w);
            newsListBean.setVotecount(newsTable.votecount);
            newsListBean.setUrl(newsTable.url);
            newsListBean.setSource(newsTable.source);
            newsListBean.setImgsrc(newsTable.imgsrc);
            newsListBean.setPtime(newsTable.ptime);
            newsListBean.setSkipID(newsTable.skipID);
            newsListBean.setSkipType(newsTable.skipType);
            newsListBean.setPhotosetID(newsTable.photosetID);
            newsLists.add(newsListBean);
        }
        return newsLists;
    }

    public static void deleteNewsByType(String type) {
        List<NewsTable> newsTableList = new Select()
                .from(NewsTable.class)
                .where(NewsTable_Table.newsType.eq(type))
                .queryList();
        ArrayList<NewsListBean> newsLists = new ArrayList<>();
        NewsListBean newsListBean;
        for (NewsTable newsTable : newsTableList) {
            newsTable.delete();
        }
    }

    public static void upDataByAsynchronous (final ArrayList<NewsListBean> mLists, final String  type){
        DatabaseDefinition database = FlowManager.getDatabase(LueansDB.class);
        database.beginTransactionAsync(new ITransaction() {
            @Override
            public void execute(DatabaseWrapper databaseWrapper) {
                deleteNewsByType(type);
                saveGankData(mLists,type);
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

package cn.lueans.lueansread.db;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * Created by 24277 on 2017/3/16.
 */
@Table(database = LueansDB.class)
public class NewsTable extends BaseModel {

   @Column
   @PrimaryKey(autoincrement = true)
   public int _id;
   @Column
   public String postid;
   @Column
   public int replyCount;
   @Column
   public String ltitle;
   @Column
   public String docid;
   @Column
   public int priority;
   @Column
   public String lmodify;
   @Column
   public String url_3w;
   @Column
   public int votecount;
   @Column
   public String url;
   @Column
   public String source;
   @Column
   public String imgsrc;
   @Column
   public String ptime;
   @Column
   public String skipID;
   @Column
   public String skipType;
   @Column
   public String photosetID;
   @Column
   public String newsType;

}

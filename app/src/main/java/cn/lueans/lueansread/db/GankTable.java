package cn.lueans.lueansread.db;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * Created by 24277 on 2017/2/24.
 */

@Table(database = LueansDB.class)
public class GankTable extends BaseModel{
    @Column
    @PrimaryKey(autoincrement = true)
    public int _id;
    @Column
    public String who;
    @Column
    public String publishedAt;
    @Column
    public String desc;
    @Column
    public String type;
    @Column
    public String url;
    @Column
    public String used;
    @Column
    public String objectId;
    @Column
    public String createdAt;
    @Column
    public String updatedAt;
    @Column
    public String imageUrl;
}

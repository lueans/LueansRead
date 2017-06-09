package cn.lueans.lueansread.db;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * Created by 24277 on 2017/2/25.
 */
@Table(database = LueansDB.class)
public class SoListTable extends BaseModel{
    @Column
    @PrimaryKey(autoincrement = true)
    public int _id;
    @Column
    public String so_id;
    @Column
    public String image_id;
    @Column
    public String group_title;
    @Column
    public String so_tag;
    @Column
    public String label;
    @Column
    public int grpseq;
    @Column
    public String cover_imgurl;
    @Column
    public int cover_height;
    @Column
    public int cover_width;
    @Column
    public int total_count;
    @Column
    public int index;
    @Column
    public String qhimg_url;
    @Column
    public String qhimg_thumb_url;
    @Column
    public int qhimg_width;
    @Column
    public int qhimg_height;
    @Column
    public String  so_type;
}

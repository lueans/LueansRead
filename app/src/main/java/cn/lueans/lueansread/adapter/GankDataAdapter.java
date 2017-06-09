package cn.lueans.lueansread.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.lueans.lueansread.R;
import cn.lueans.lueansread.entity.GankBean;
import cn.lueans.lueansread.ui.activity.WebActivity;
import cn.lueans.lueansread.utils.ThemeUtils;
import cn.lueans.lueansread.utils.TimeFormatUtils;

/**
 * Created by 24277 on 2017/2/23.
 */

public class GankDataAdapter extends RecyclerView.Adapter<GankDataAdapter.MyViewHolder> {

    private ArrayList<GankBean.GankResultsBean> mGankResulits;
    private Context mContext;
    private boolean isNight;

    public GankDataAdapter(Context context, ArrayList<GankBean.GankResultsBean> gankResulits) {
        mContext = context;
        if (mGankResulits != null) {
            mGankResulits = gankResulits;
        }else{
            mGankResulits = new ArrayList<GankBean.GankResultsBean>();
        }
        isNight = ThemeUtils.getIsNight(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_gank_list, parent,false);
        MyViewHolder holder = new MyViewHolder(inflate);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final GankBean.GankResultsBean GankResultsBean = mGankResulits.get(position);

        if (isNight){
            holder.view.setBackgroundColor(Color.parseColor("#666666"));
        }

        String who = GankResultsBean.getWho();
        if(who != null){
            holder.tvAuthor.setText(who);

        }else{
            holder.tvAuthor.setText("未知的你");
        }
        String createdAt = GankResultsBean.getCreatedAt();
        if (createdAt != null) {
            holder.tvTime.setText(TimeFormatUtils.getFormatTime(createdAt,TimeFormatUtils.gankFmt));
        }else{
            holder.tvTime.setText("");
        }
        holder.tvBody.setText(GankResultsBean.getDesc());
        List<String> images = GankResultsBean.getImages();


        holder.mImageView.setVisibility(View.GONE);
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                WebActivity.newIntent(mContext,
                        GankResultsBean.getUrl(),
                        GankResultsBean.getDesc()
                );
                // 后边滑出动画
//                ((Activity)mContext).overridePendingTransition(R.anim.slide_right_in,R.anim.null_anim);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mGankResulits.size();
    }

    public ArrayList<GankBean.GankResultsBean> getGankResulits() {
        return mGankResulits;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView mImageView;
        TextView tvBody;
        TextView tvAuthor;
        TextView tvTime;
        View view;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.view = itemView;
            mImageView = (ImageView) itemView.findViewById(R.id.iv_image);
            tvBody = (TextView) itemView.findViewById(R.id.tv_body);
            tvAuthor = (TextView) itemView.findViewById(R.id.tv_author);
            tvTime = (TextView) itemView.findViewById(R.id.tv_time);
        }
    }

    public void clearData(){
        this.mGankResulits.clear();
    }

    public void addList(ArrayList<GankBean.GankResultsBean> results){
        this.mGankResulits.addAll(results);
    }
}

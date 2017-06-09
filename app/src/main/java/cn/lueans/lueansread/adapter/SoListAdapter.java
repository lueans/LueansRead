package cn.lueans.lueansread.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

import cn.lueans.lueansread.R;
import cn.lueans.lueansread.entity.SOListBean;
import cn.lueans.lueansread.ui.activity.PhotoDetailActivity;
import cn.lueans.lueansread.ui.activity.SoDetailActivity;

/**
 * Created by 24277 on 2017/2/23.
 */

public class SoListAdapter extends RecyclerView.Adapter<SoListAdapter.SoViewHolder> {

    private ArrayList<SOListBean.ListBean> mSoResulits;
    private Context mContext;

    public SoListAdapter(Context context, ArrayList<SOListBean.ListBean> soResulits) {
        mContext = context;
        if (soResulits == null) {
            this.mSoResulits = new ArrayList<>();
        } else {
            mSoResulits = soResulits;
        }
    }

    public ArrayList<SOListBean.ListBean> getSoResulits() {
        return mSoResulits;
    }

    public void addDataList(ArrayList<SOListBean.ListBean> lists) {
        this.mSoResulits.addAll(lists);
        notifyDataSetChanged();
    }

    public void clearAllData() {
        this.mSoResulits.clear();
        notifyDataSetChanged();
    }

    public void upNowsData(ArrayList<SOListBean.ListBean> lists) {
        this.mSoResulits.clear();
        this.mSoResulits.addAll(lists);
        notifyDataSetChanged();
    }

    @Override
    public SoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_so, parent, false);
        SoViewHolder soViewHolder = new SoViewHolder(inflate);
        return soViewHolder;

    }

    @Override
    public void onBindViewHolder(final SoViewHolder holder, int position) {
        final SOListBean.ListBean listBean = mSoResulits.get(position);
        holder.bindData(listBean);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listBean.getTotal_count() == 1){
                    //单张图片直接跳转 图片展示activity
                    PhotoDetailActivity.newIntent(
                            mContext,
                            listBean.getGroup_title(),
                            new String[]{
                                    listBean.getQhimg_thumb_url()
                            },
                            0
                    );
                }else{
                    SoDetailActivity.newIntent(
                            mContext,
                            listBean.getQhimg_thumb_url(),
                            listBean.getGroup_title(),
                            listBean.getId());
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return mSoResulits.size();
    }

    public class SoViewHolder extends RecyclerView.ViewHolder {

        private View mView;
        private ImageView mImageView;
        private TextView tvTitle;
        private TextView tvTime;


        public SoViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
            mImageView = (ImageView) itemView.findViewById(R.id.iv_solist);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_solist_title);
            tvTime = (TextView) itemView.findViewById(R.id.tv_solist_time);
        }

        public void bindData(SOListBean.ListBean listBean) {
            if (!listBean.getGroup_title().isEmpty()) {
                this.tvTitle.setText(listBean.getGroup_title());
            }
            this.tvTime.setText("共(" + listBean.getTotal_count() + ")张");
            Glide.with(mContext)
                    .load(listBean.getQhimg_thumb_url())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(this.mImageView);
        }
    }
}

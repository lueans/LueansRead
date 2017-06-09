package cn.lueans.lueansread.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import cn.lueans.lueansread.R;
import cn.lueans.lueansread.entity.SoDetailBean;
import cn.lueans.lueansread.ui.activity.PhotoDetailActivity;

/**
 * Created by 24277 on 2017/2/24.
 */

public class SoDetailAdapter extends RecyclerView.Adapter<SoDetailAdapter.SoDetailHolder>{
    private ArrayList<SoDetailBean.DetialListBean> mListBeen;
    private Context mContext;

    public SoDetailAdapter(Context context, ArrayList<SoDetailBean.DetialListBean> listBeen) {
        mContext = context;
        if (listBeen == null) {
            mListBeen = new ArrayList<>();
        }else{
            mListBeen = listBeen;
        }
    }


    public void clearData(){
        this.mListBeen.clear();
        notifyDataSetChanged();
    }



    public void addAllData(ArrayList<SoDetailBean.DetialListBean> lists){
        this.mListBeen.addAll(lists);
        notifyDataSetChanged();
    }
    public void upNowsData(ArrayList<SoDetailBean.DetialListBean> lists){
        this.mListBeen.clear();
        this.mListBeen.addAll(lists);
        notifyDataSetChanged();
    }

    @Override
    public SoDetailHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_so, parent,false);
        SoDetailHolder soViewHolder = new SoDetailHolder(inflate);
        return soViewHolder;
    }

    @Override
    public void onBindViewHolder(SoDetailHolder holder, int position) {
        SoDetailBean.DetialListBean detialListBean = mListBeen.get(position);
        holder.bindData(detialListBean,position);
    }

    @Override
    public int getItemCount() {
        return mListBeen.size();
    }

    public String[] getImgUrls() {
        String[] imageUrls = new String[mListBeen.size()];
        for (int i = 0; i < mListBeen.size(); i++) {
            imageUrls[i] = mListBeen.get(i).getQhimg_url();
        }
        return imageUrls;
    }

    public class SoDetailHolder extends RecyclerView.ViewHolder{
        private View mView;
        private ImageView mImageView;
        private TextView tvTitle;
        private TextView tvTime;

        public SoDetailHolder(View itemView) {
            super(itemView);
            mView = itemView;
            mImageView = (ImageView) itemView.findViewById(R.id.iv_solist);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_solist_title);
            tvTime = (TextView) itemView.findViewById(R.id.tv_solist_time);
        }

        public void bindData(final SoDetailBean.DetialListBean detialListBean, final int position) {
            Glide.with(mContext).load(detialListBean.getQhimg_url()).into(this.mImageView);
            this.tvTime.setVisibility(View.GONE);
            this.tvTitle.setVisibility(View.GONE);
            this.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    PhotoDetailActivity.newIntent(
                        mContext,
                            detialListBean.getPic_title(),
                            getImgUrls(),
                            position
                    );
                }
            });
        }
    }



}

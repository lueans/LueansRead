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
import cn.lueans.lueansread.entity.NewsDetailBean;
import cn.lueans.lueansread.ui.activity.NewsDetailActivity;
import cn.lueans.lueansread.utils.TimeFormatUtils;

/**
 * Created by 24277 on 2017/3/30.
 */

public class NewsDetailAdapter extends RecyclerView.Adapter<NewsDetailAdapter.NewsDetailHolder> {


    private Context mContext;
    private ArrayList<NewsDetailBean.RelativeSysBean> mList;

    public NewsDetailAdapter(Context mContext, ArrayList<NewsDetailBean.RelativeSysBean> list) {
        this.mContext = mContext;
        if (list == null){
            this.mList = new ArrayList<NewsDetailBean.RelativeSysBean>();
        }else{
            this.mList = list;
        }
    }


    public void setmList(ArrayList<NewsDetailBean.RelativeSysBean> mList) {
        this.mList = mList;
        notifyDataSetChanged();
    }

    @Override
    public NewsDetailHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news_list, parent,false);
        NewsDetailHolder newsDetailHolder = new NewsDetailHolder(inflate);
        return newsDetailHolder;
    }

    @Override
    public void onBindViewHolder(NewsDetailHolder holder, int position) {
        final NewsDetailBean.RelativeSysBean relativeSysBean = mList.get(position);
        holder.bindData(relativeSysBean);
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NewsDetailActivity.newIntent(
                        mContext,
                        relativeSysBean.getTitleX(),
                        relativeSysBean.getImgsrc(),
                        relativeSysBean.getId(),
                        relativeSysBean.getImgsrc()
                );
            }
        });


    }

    @Override
    public int getItemCount() {
        if (mList == null){
            return 0;
        }
        return mList.size();
    }

    public class NewsDetailHolder extends RecyclerView.ViewHolder {
        private ImageView ivNews;
        private TextView tvTitle;
        private TextView tvDesc;
        private TextView tvsource;
        private TextView tvTime;
        private View mView;
        public NewsDetailHolder(View itemView) {
            super(itemView);
            mView = itemView;
            ivNews = (ImageView) itemView.findViewById(R.id.iv_news_list);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_news_title);
            tvDesc = (TextView) itemView.findViewById(R.id.tv_news_desc);
            tvsource = (TextView) itemView.findViewById(R.id.tv_news_source);
            tvTime = (TextView) itemView.findViewById(R.id.tv_news_time);
        }

        public void bindData(NewsDetailBean.RelativeSysBean relativeSysBean) {
            Glide.with(mContext).load(relativeSysBean.getImgsrc()).into(ivNews);
            this.tvTitle.setText(relativeSysBean.getTitleX());
            //隐藏desc
            this.tvDesc.setVisibility(View.GONE);
            this.tvsource.setText(relativeSysBean.getSourceX());
            this.tvTime.setText(TimeFormatUtils.getFormatTime(relativeSysBean.getPtimeX(),TimeFormatUtils.newsFmt));
        }
    }
}

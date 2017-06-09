package cn.lueans.lueansread.adapter;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import cn.lueans.lueansread.R;
import cn.lueans.lueansread.entity.NewsListBean;
import cn.lueans.lueansread.ui.activity.NewsDetailActivity;
import cn.lueans.lueansread.utils.TimeFormatUtils;

/**
 * Created by 24277 on 2017/3/10.
 */

public class NewsListAdapter extends RecyclerView.Adapter<NewsListAdapter.NewsHolder> {

    private Context mContext;
    private ArrayList<NewsListBean> mNewsListBeen;


    public ArrayList<NewsListBean> getmNewsListBeen() {
        return mNewsListBeen;
    }

    public NewsListAdapter(Context context, ArrayList<NewsListBean> newsListBeen) {
        mContext = context;
        if (newsListBeen == null) {
            mNewsListBeen = new ArrayList<>();
        }else{
            mNewsListBeen = newsListBeen;
        }
    }

    public void upDataAll(ArrayList<NewsListBean> newsListBeen){
        this.mNewsListBeen.clear();
        this.mNewsListBeen.addAll(newsListBeen);
        notifyDataSetChanged();
    }

    public void addMoreData(ArrayList<NewsListBean> newsListBeen){
        this.mNewsListBeen.addAll(newsListBeen);
        notifyDataSetChanged();
    }

    @Override
    public NewsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news_list, parent,false);
        NewsHolder newsHolder = new NewsHolder(inflate);
        return newsHolder;
    }

    @Override
    public void onBindViewHolder(NewsHolder holder, int position) {
        final NewsListBean newsListBean = mNewsListBeen.get(position);
        holder.bindData(newsListBean,position);
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url_3w = newsListBean.getUrl_3w();
                if (TextUtils.isEmpty(url_3w)){
                    Snackbar.make(view,"暂时没有实现 "+newsListBean.getPhotosetID(),Snackbar.LENGTH_SHORT).show();

                //                    图片功能已经被过滤了 请查看 FilterUtils
                    return;
                }
                NewsDetailActivity.newIntent(
                        mContext,
                        newsListBean.getTitle(),
                        newsListBean.getImgsrc(),
                        newsListBean.getPostid(),
                        newsListBean.getUrl_3w()
                );
            }
        });
    }

    @Override
    public int getItemCount() {
        return mNewsListBeen.size();
    }

    public class NewsHolder extends RecyclerView.ViewHolder{
        private ImageView ivNews;
        private TextView tvTitle;
        private TextView tvDesc;
        private TextView tvsource;
        private TextView tvTime;
        private View mView;
        public NewsHolder(View itemView) {
            super(itemView);
            mView = itemView;
            ivNews = (ImageView) itemView.findViewById(R.id.iv_news_list);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_news_title);
            tvDesc = (TextView) itemView.findViewById(R.id.tv_news_desc);
            tvsource = (TextView) itemView.findViewById(R.id.tv_news_source);
            tvTime = (TextView) itemView.findViewById(R.id.tv_news_time);
        }

        public void bindData(NewsListBean newsListBean,int position) {
            String url_3w = newsListBean.getUrl_3w();
            Glide.with(mContext).load(newsListBean.getImgsrc()).into(ivNews);
            this.tvTitle.setText(newsListBean.getTitle());
            //隐藏desc
            this.tvDesc.setVisibility(View.GONE);
            this.tvsource.setText(newsListBean.getSource());
            this.tvTime.setText(TimeFormatUtils.getFormatTime(newsListBean.getPtime(),TimeFormatUtils.newsFmt));
        }
    }
}

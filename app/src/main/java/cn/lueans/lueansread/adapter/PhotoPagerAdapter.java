package cn.lueans.lueansread.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import cn.lueans.lueansread.R;
import cn.lueans.lueansread.ui.view.TouchImageView;

/**
 * Created by 24277 on 2017/3/16.
 */

public class PhotoPagerAdapter extends PagerAdapter {

    private static final String TAG = "PhotoPagerAdapter";
    
    private String imgUrls[];
    private Context mContext;
    private ArrayList<View> mPhotoViews;

    public PhotoPagerAdapter(String imgUrls[], Context context) {
        this.imgUrls = imgUrls;
        mContext = context;
        mPhotoViews = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return imgUrls.length;
    }

    @Override
    public View instantiateItem(ViewGroup container, int position) {
        TouchImageView img = new TouchImageView(container.getContext());
        container.addView(img, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
      /*  Picasso.with(mContext)
                .load(imgUrls[position])
                .into(img);*/

        Glide.with(mContext)
                .load(imgUrls[position])
                .dontAnimate()
                .placeholder(R.mipmap.placeholder_bg)
                .into(img);


        return img;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        try{
            container.removeView((View) object);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

}

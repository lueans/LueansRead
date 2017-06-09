package cn.lueans.lueansread.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import cn.lueans.lueansread.R;
import cn.lueans.lueansread.adapter.PhotoPagerAdapter;
import cn.lueans.lueansread.ui.view.PhotoViewPager;

public class PhotoDetailActivity extends BaseActivity {

    private static final String TAG = "PhotoDetailActivity";
    private static final String PHOTO_DETAIL_TITLE = "title";
    private static final String PHOTO_DETAIL_IMGURLS = "imgurls";
    private static final String PHOTO_DETAIL_POSITION = "position";
    private PhotoViewPager pvpDetail;
    private String mTitle;
    private String[] imgUrls;
    private int position;
    private boolean isVisibilityOfBar = true;
    private TextView tvTitle;
    private PhotoPagerAdapter mPhotoPagerAdapter;

    public static Intent newIntent(Context context,String title,String[] imgurls,int position){
        Intent intent = new Intent(context, PhotoDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(PHOTO_DETAIL_TITLE,title);
        bundle.putSerializable(PHOTO_DETAIL_IMGURLS,imgurls);
        bundle.putInt(PHOTO_DETAIL_POSITION,position);
        intent.putExtras(bundle);
        context.startActivity(intent);
        return intent;
    }





    private void parseIntent() {
        Intent intent = getIntent();
        mTitle = intent.getStringExtra(PHOTO_DETAIL_TITLE);

        imgUrls = intent.getStringArrayExtra(PHOTO_DETAIL_IMGURLS);
        position = intent.getIntExtra(PHOTO_DETAIL_POSITION,0);

        Log.i(TAG, "parseIntent: " + mTitle);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_photo_detail);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setVisibility(View.GONE);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int itemPosition = mPhotoPagerAdapter.getItemPosition(pvpDetail);
                if (itemPosition < 0 || itemPosition >imgUrls.length){
                    itemPosition = 0;
                }

            }
        });
        parseIntent();
        initLoad();
        initBar();
        initPager();
    }

    private void initLoad() {



    }

    private void initBar() {
        tvTitle = (TextView) findViewById(R.id.tv_title);
        Log.i(TAG, "initBar: "+mTitle);
        tvTitle.setText(mTitle);
    }

    private void initPager() {
        pvpDetail = (PhotoViewPager) findViewById(R.id.pvp_detail);
        mPhotoPagerAdapter = new PhotoPagerAdapter(imgUrls, PhotoDetailActivity.this);
        pvpDetail.setAdapter(mPhotoPagerAdapter);
        pvpDetail.setCurrentItem(position);
        mPhotoPagerAdapter.notifyDataSetChanged();

    }

}

package cn.lueans.lueansread.ui.temDecoration;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by 24277 on 2017/2/23.
 */

public class SoSpaceItemDecoration extends RecyclerView.ItemDecoration {

    private int space;

    public SoSpaceItemDecoration(int space) {
        this.space=space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {

        outRect.left=space;
        outRect.right=space;
        outRect.bottom=space;
        //注释这两行是为了上下间距相同
        //        if(parent.getChildAdapterPosition(view)==0){
        outRect.top=space;
        //        }
    }
}


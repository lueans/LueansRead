package cn.lueans.lueansread.ui.view;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.Arrays;
import java.util.List;

import cn.lueans.lueansread.R;
import cn.lueans.lueansread.utils.PreferenceUtils;
import cn.lueans.lueansread.utils.ThemeUtils;

/**
 * Created by 24277 on 2017/4/8.
 */

public class ShowDialog {

    private static ShowDialog showDialog;
    private ShowDialog() {
    }
    public static ShowDialog builder(){
        if (showDialog == null){
            synchronized (ShowDialog.class){
                if (showDialog == null){
                    showDialog = new ShowDialog();
                }
            }
        }
        return  showDialog;
    }

    public void showThemeDialog(final Activity context, final String index){
        AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.ChangeThemeDialog);
        builder.setTitle("更换主题");
        Integer[] res = new Integer[]{R.drawable.red_round, R.drawable.brown_round, R.drawable.blue_round,
                R.drawable.blue_grey_round, R.drawable.yellow_round, R.drawable.deep_purple_round,
                R.drawable.pink_round, R.drawable.green_round, R.drawable.deep_orange_round,
                R.drawable.grey_round, R.drawable.cyan_round, R.drawable.amber_round};
        List<Integer> list = Arrays.asList(res);
        ColorsListAdapter adapter = new ColorsListAdapter(context, list);
        adapter.setCheckItem(ThemeUtils.getCurrentTheme(context).getIntValue());
        GridView gridView = (GridView) LayoutInflater.from(context).inflate(R.layout.colors_panel_layout, null);
        gridView.setStretchMode(GridView.STRETCH_COLUMN_WIDTH);
        gridView.setCacheColorHint(0);
        gridView.setAdapter(adapter);
        builder.setView(gridView);
        final AlertDialog dialog = builder.show();
        gridView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        dialog.dismiss();
                        int value = ThemeUtils.getCurrentTheme(context).getIntValue();
                        Log.d("wxl", "value==" + value);
                        if (value != position) {
                            PreferenceUtils.setPreferenceInt(context,PreferenceUtils.THEME_KEY, position);
                            PreferenceUtils.setPreferenceString(context,PreferenceUtils.MAIN_INDEX_MENU,index);
                            context.getWindow().setWindowAnimations(R.style.WindowAnimationFadeInOut);
                            context.recreate();
                        }
                    }
                }
        );
    }
    public class ColorsListAdapter extends BaseAdapter {
        private int checkItem;
        private Context context;
        private List<Integer> list;
        public ColorsListAdapter(Context context, List<Integer> list) {
            this.context = context;
            this.list = list;
        }
        @Override
        public int getCount() {
            return list.size();
        }
        @Override
        public Object getItem(int position) {
            return list.get(position);
        }
        @Override
        public long getItemId(int position) {
            return position;
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Holder holder;
            if (convertView == null) {
                convertView = LayoutInflater.from(context).inflate(R.layout.colors_image_layout, null);
                holder = new Holder();
                holder.imageView1 = (ImageView) convertView.findViewById(R.id.img_1);
                holder.imageView2 = (ImageView) convertView.findViewById(R.id.img_2);
                convertView.setTag(holder);
            } else {
                holder = (Holder) convertView.getTag();
            }
            holder.imageView1.setImageResource(list.get(position));
            if (checkItem == position) {
                holder.imageView2.setImageResource(R.drawable.ic_check_black_24dp);
            }
            return convertView;
        }
        public void setCheckItem(int checkItem) {
            this.checkItem = checkItem;
        }
        class Holder {
            ImageView imageView1;
            ImageView imageView2;
        }
    }

}

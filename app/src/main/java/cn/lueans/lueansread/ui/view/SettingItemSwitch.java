package cn.lueans.lueansread.ui.view;

import android.content.Context;
import android.support.v7.widget.SwitchCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import cn.lueans.lueansread.R;

/**
 * Created by 24277 on 2017/4/8.
 */

public class SettingItemSwitch extends RelativeLayout {
    
    private static final String NAMESPACE = "http://schemas.android.com/apk/res-auto";
    private TextView tvTitle;
    private TextView tvDesc;
    private SwitchCompat switchCompat;
    private String mDescOn;
    private String mDescOff;

    public SettingItemSwitch(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView();
    }

    public SettingItemSwitch(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
        String title = attrs.getAttributeValue(NAMESPACE, "title");
        mDescOn = attrs.getAttributeValue(NAMESPACE, "desc_on");
        mDescOff = attrs.getAttributeValue(NAMESPACE, "desc_off");
        setTitle(title);
    }

    public SettingItemSwitch(Context context) {
        super(context);
        initView();
    }

    /**
     * 初始化布局
     */
    private void initView() {
        View child = View.inflate(getContext(), R.layout.setting_item_switch,
                null);// 初始化组合控件布局
        tvTitle = (TextView) child.findViewById(R.id.tv_title);
        tvDesc = (TextView) child.findViewById(R.id.tv_desc);
        switchCompat= (SwitchCompat) child.findViewById(R.id.sc_switch);
        this.addView(child);// 将布局添加给当前的RelativeLayout对象
        switchCompat.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        switchCompat.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });
    }

    /**
     * 设置标题
     *
     * @param title
     */
    public void setTitle(String title) {
        tvTitle.setText(title);
    }

    /**
     * 设置表述
     *
     * @param desc
     */
    public void setDesc(String desc) {
        tvDesc.setText(desc);
    }

    /**
     * 判断是否勾选
     *
     * @return
     */
    public boolean isChecked() {
        return switchCompat.isChecked();
    }

    /**
     * 设置选中状态
     *
     * @param checked
     */
    public void setChecked(boolean checked) {
        switchCompat.setChecked(checked);
        // 更新描述信息
        if (checked) {
            setDesc(mDescOn);
        } else {
            setDesc(mDescOff);
        }
    }

    
}

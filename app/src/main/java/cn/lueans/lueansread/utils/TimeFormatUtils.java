package cn.lueans.lueansread.utils;

import android.util.Log;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.bumptech.glide.gifdecoder.GifHeaderParser.TAG;

/**
 * Created by 24277 on 2017/3/5.
 */

public class TimeFormatUtils {

    public static String gankFmt = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
//    2017-03-10 08:20:08
    public static String newsFmt = "yyyy-MM-dd HH:mm:ss";

    public static String getFormatTime(String time,String type){
        SimpleDateFormat myFmt=new SimpleDateFormat(type);
        long differenceTime = 0;
        Date nowTime = new Date();
        try {
            differenceTime = nowTime.getTime() - myFmt.parse(time).getTime();
            if (differenceTime < 0){
                Log.i(TAG, "getFormatTime: "+differenceTime);
                return "来自未来";
            }else {
                differenceTime /= (1000*60);
                if(differenceTime < 60){
                    return differenceTime+"分钟前";
                }
                differenceTime /= 60;
                if (differenceTime<24){
                    return differenceTime+"小时前";
                }
                differenceTime /= 24;
                if (differenceTime < 30){
                    if(differenceTime == 1){
                        return "昨天";
                    }
                    return differenceTime + "天前";
                }
                DateFormat df_date = new SimpleDateFormat("MM-dd");
                return df_date.format(myFmt.parse(time));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return time;
    }

}

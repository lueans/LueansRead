package cn.lueans.lueansread.utils;

import java.util.HashMap;

/**
 * Created by 24277 on 2017/3/10.
 */

public class TItleAndIdUtils {

    private static String newsTitleKey[] = {
            "新闻",
            "足球",
            "娱乐",
            "体育",
            "财经",
            "科技",
            "电影",
            "汽车",
            "笑话",
            "游戏",
            "时尚",
            "情感",
            "精选",
            "电台",
            "NBA",
            "数码",
            "移动",
            "彩票",
            "教育",
            "论坛",
            "旅游",
            "手机",
            "博客",
            "社会",
            "家具",
            "暴雪游戏",
            "亲子",
            "CBA",
            "消息",
            "军事",

    };

    private static String newsTitleValues[] = {
            // 新闻id
            "T1348647909107",
            // 足球
            "T1399700447917",
            // 娱乐
            "T1348648517839",
            // 体育
            "T1348649079062",
            // 财经
            "T1348648756099",
            // 科技
            "T1348649580692",
            // 电影
            "T1348648650048",
            // 汽车
            "T1348654060988",
            // 笑话
            "T1350383429665",
            // 游戏
            "T1348654151579",
            // 时尚
            "T1348650593803",
            // 情感
            "T1348650839000",
            // 精选
            "T1370583240249",
            // 电台
            "T1379038288239",
            // nba
            "T1348649145984",
            // 数码
            "T1348649776727",
            // 移动
            "T1351233117091",
            // 彩票
            "T1356600029035",
            // 教育
            "T1348654225495",
            // 论坛
            "T1349837670307",
            // 旅游
            "T1348654204705",
            // 手机
            "T1348649654285",
            // 博客
            "T1349837698345",
            // 社会
            "T1348648037603",
            // 家居
            "T1348654105308",
            // 暴雪游戏
            "T1397016069906",
            // 亲子
            "T1397116135282",
            // CBA
            "T1348649475931",
            // 消息
            "T1371543208049",
            // 军事
            "T1348648141035",
    };
    private static HashMap<String,String> stringHashMap;
    public static void initMap(){
        stringHashMap = new HashMap<>();
        for (int i = 0; i < newsTitleKey.length; i++) {
            stringHashMap.put(newsTitleKey[i],newsTitleValues[i]);
        }
    }
    public static String getValueIdByTitleKey(String titleKey){
        if (stringHashMap == null || stringHashMap.isEmpty()){
            initMap();
        }
        return stringHashMap.get(titleKey);
    }

}

package com.example.spirit.ting.utils;

import com.example.spirit.ting.R;

import java.util.LinkedHashMap;

public class DataHelper {

    private static LinkedHashMap<String, Integer> tabHosts;

    public static LinkedHashMap<String, Integer> getHostData() {
        if (tabHosts == null) {
            tabHosts = new LinkedHashMap<>();
            tabHosts.put("推荐", R.drawable.recommend);
            tabHosts.put("品乐", R.drawable.pinpine);
            tabHosts.put("首页", R.drawable.index);
            tabHosts.put("回音", R.drawable.echo);
            tabHosts.put("我的", R.drawable.us);
        }
        return tabHosts;
    }
}

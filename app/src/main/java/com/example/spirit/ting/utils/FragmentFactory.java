package com.example.spirit.ting.utils;

import android.support.v4.app.Fragment;

import com.example.spirit.ting.fragment.EchoFragment;
import com.example.spirit.ting.fragment.GussYouLikeFragment;
import com.example.spirit.ting.fragment.IndexFragment;
import com.example.spirit.ting.fragment.MyAttentionFragment;
import com.example.spirit.ting.fragment.PinpineFragment;
import com.example.spirit.ting.fragment.ProductArticleFragment;
import com.example.spirit.ting.fragment.ProductImageFragment;
import com.example.spirit.ting.fragment.ProductMenFragment;
import com.example.spirit.ting.fragment.RecommendFragment;
import com.example.spirit.ting.fragment.TeaFragment;
import com.example.spirit.ting.fragment.UsFragment;

import java.util.HashMap;

public class FragmentFactory {
    private static HashMap<String, Fragment> fragmentMap;
    private static HashMap<String, Fragment> recommendFragmentMap;
    private static HashMap<String, Fragment> pinPineFragmentMap;

    public static HashMap<String, Fragment> getTabHostFragment() {
        if (fragmentMap == null) {
            fragmentMap = new HashMap<>();
            fragmentMap.put("推荐", new RecommendFragment());
            fragmentMap.put("品乐", new PinpineFragment());
            fragmentMap.put("首页", new IndexFragment());
            fragmentMap.put("回音", new EchoFragment());
            fragmentMap.put("我的", new UsFragment());
        }
        return fragmentMap;
    }

    public static HashMap<String, Fragment> getRecommendFragment() {
        if (recommendFragmentMap == null) {
            recommendFragmentMap = new HashMap<>();
            recommendFragmentMap.put("早午茶", new TeaFragment());
            recommendFragmentMap.put("猜你喜欢", new GussYouLikeFragment());
            recommendFragmentMap.put("我的关注", new MyAttentionFragment());
        }
        return recommendFragmentMap;
    }

    public static HashMap<String, Fragment> getPinPineFragment() {
        if (pinPineFragmentMap == null) {
            pinPineFragmentMap = new HashMap<>();
            pinPineFragmentMap.put("品像", new ProductImageFragment());
            pinPineFragmentMap.put("品人", new ProductMenFragment());
            pinPineFragmentMap.put("品文", new ProductArticleFragment());
        }
        return pinPineFragmentMap;
    }
}

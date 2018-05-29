package com.example.spirit.ting.utils;

import android.util.TypedValue;

public class EvaluateUtil {
    public static int sp2px(float sp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, Util.getContext()
                .getResources().getDisplayMetrics());
    }

    public static float px2dp(int px) {
        float density = Util.getContext().getResources().getDisplayMetrics().density;
        return px / density;
    }

    public static float sp2dp(float sp) {
        return px2dp(sp2px(sp));
    }

    public static int dp2px(float dp){
        float density = Util.getContext().getResources().getDisplayMetrics().density;
        return (int) (dp*density+0.5);
    }
}

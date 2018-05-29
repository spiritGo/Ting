package com.example.spirit.ting.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.spirit.ting.R;
import com.example.spirit.ting.utils.EvaluateUtil;
import com.example.spirit.ting.utils.FragmentFactory;

import java.util.HashMap;

public class RecommendFragment extends IndicatorBaseFragment {

    private View line;
    private FrameLayout fl_container;
    private LinearLayout ll_container;
    private LinearLayout.LayoutParams layoutParams;

    @Override
    protected int inflateLayout() {
        return R.layout.fragment_recommend;
    }

    @Override
    protected void init() {
        initView();
        initUI();
    }

    private void initUI() {
        final int childCount = ll_container.getChildCount();
        final HashMap<String, Fragment> recommendFragment = FragmentFactory.getRecommendFragment();
        final FragmentManager fragmentManager = getChildFragmentManager();
        System.out.println(fl_container.getChildCount());
        fragmentManager.beginTransaction().replace(R.id.fl_container, recommendFragment.get("早午茶"))
                .commit();
        System.out.println(fl_container.getChildCount());

        layoutParams = (LinearLayout.LayoutParams) line.getLayoutParams();
        ll_container.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver
                .OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                final int itemWidth = ll_container.getWidth() / childCount;
                for (int i = 0; i < childCount - 1; i++) {
                    ll_container.getChildAt(i).setTag(i);
                    ll_container.getChildAt(i).setSelected(false);
                    ll_container.getChildAt(i).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            TextView textView = (TextView) v;
                            int tag = (int) v.getTag();
                            String s = textView.getText().toString();
                            for (int j = 0; j < childCount - 1; j++) {
                                ll_container.getChildAt(j).setSelected(false);
                            }
                            selectedTab(textView, tag, itemWidth);
                            fragmentManager.beginTransaction().replace(R.id.fl_container,
                                    recommendFragment.get(s)).commit();
                        }
                    });
                }
                TextView textView = (TextView) ll_container.getChildAt(0);
                selectedTab(textView, 0, itemWidth);
                ll_container.getViewTreeObserver().removeGlobalOnLayoutListener(this);
            }
        });

        ll_container.getChildAt(0).setSelected(true);

    }

    private void selectedTab(TextView textView, int tag, int itemWidth) {
        int length = textView.getText().length();
        int sp2px = EvaluateUtil.sp2px(length * 14);
        int padding = (itemWidth - sp2px) / 2;
        layoutParams.width = sp2px;
        layoutParams.leftMargin = tag * itemWidth + padding;
        line.setLayoutParams(layoutParams);
        ll_container.getChildAt(tag).setSelected(true);
    }

    private void initView() {
        line = view.findViewById(R.id.line);
        fl_container = view.findViewById(R.id.fl_container);
        ll_container = view.findViewById(R.id.ll_container);
    }
}

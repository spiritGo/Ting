package com.example.spirit.ting.fragment;

import android.animation.ValueAnimator;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.spirit.ting.R;
import com.example.spirit.ting.utils.EvaluateUtil;
import com.example.spirit.ting.utils.FragmentFactory;

public class PinpineFragment extends IndicatorBaseFragment {
    private RelativeLayout rlContainer;
    private TextView tvTitle;
    private ImageView ivArrow;
    private ImageView add;
    private LinearLayout llContainer;
    private final int CLOSE = -1;
    private final int OPEN = 1;
    private int state = CLOSE;
    private int childCount;
    private FrameLayout fl_container;
    private FragmentManager fragmentManager;

    @Override
    protected int inflateLayout() {
        return R.layout.fragment_pinpine;
    }

    @Override
    protected void init() {
        initView();
        initUI();
    }

    private void initUI() {
        ivArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLLcontainer();
            }
        });
        childCount = llContainer.getChildCount();
        updateTitle();
        fragmentManager = getChildFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fl_container, FragmentFactory
                .getPinPineFragment().get("品像")).commit();
    }

    private void updateTitle() {
        for (int i = 0; i < childCount; i++) {
            TextView textView = (TextView) llContainer.getChildAt(i);
            textView.setTag(i);
            //textView.setTextColor(getResources().getColor(R.color.text_selector));
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (state == OPEN) {
                        TextView v1 = (TextView) v;
                        String s = v1.getText().toString();
                        tvTitle.setText(s);
                        fragmentManager.beginTransaction().replace(R.id.fl_container,
                                FragmentFactory.getPinPineFragment().get(s)).commit();
                        tvSelected(false);

                        v.setSelected(true);
                    }
                }
            });
        }

        tvSelected(false);
        llContainer.getChildAt(0).setSelected(true);
    }

    private void tvSelected(boolean isSelected) {
        for (int j = 0; j < childCount; j++) {
            llContainer.getChildAt(j).setSelected(isSelected);
        }
    }

    private void showLLcontainer() {
        final int height = rlContainer.getHeight();
        ValueAnimator valueAnimator = null;
        final RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) llContainer
                .getLayoutParams();
        layoutParams.height = height;
        if (state == CLOSE) {
            valueAnimator = ValueAnimator.ofInt(0, EvaluateUtil.dp2px(height));
            state = OPEN;
        } else {
            valueAnimator = ValueAnimator.ofInt(EvaluateUtil.dp2px(height), 0);
            state = CLOSE;
        }
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                layoutParams.topMargin = (int) animation.getAnimatedValue();
                llContainer.setLayoutParams(layoutParams);
            }
        });
        valueAnimator.setDuration(1000);
        valueAnimator.setInterpolator(new BounceInterpolator());
        valueAnimator.start();
    }

    private void initView() {
        rlContainer = view.findViewById(R.id.rl_container);
        tvTitle = view.findViewById(R.id.tv_title);
        ivArrow = view.findViewById(R.id.iv_arrow);
        add = view.findViewById(R.id.add);
        llContainer = view.findViewById(R.id.ll_container);
        fl_container = view.findViewById(R.id.fl_container);
    }
}

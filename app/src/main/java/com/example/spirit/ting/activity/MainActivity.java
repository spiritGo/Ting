package com.example.spirit.ting.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.spirit.ting.R;
import com.example.spirit.ting.utils.DataHelper;
import com.example.spirit.ting.utils.FragmentFactory;

public class MainActivity extends FragmentActivity {

    private FragmentTabHost ftHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_main);

        initView();
        initVariable();
        initUI();
    }

    private void initVariable() {

    }

    private void initUI() {
        ftHost.setup(this, getSupportFragmentManager(), R.id.fl_container);
        ftHost.getTabWidget().setShowDividers(LinearLayout.SHOW_DIVIDER_NONE);
        createTabHost();
    }

    private void createTabHost() {
        for (String key : DataHelper.getHostData().keySet()) {
            View view = View.inflate(this, R.layout.indicator_layout, null);
            TextView tv_indicator = view.findViewById(R.id.tv_indicator);
            ImageView iv_indicator = view.findViewById(R.id.iv_indicator);
            if (!"首页".equals(key)) {
                iv_indicator.setBackgroundResource(DataHelper.getHostData().get(key));
                tv_indicator.setText(key);
                tv_indicator.setVisibility(View.VISIBLE);
            } else {
                tv_indicator.setVisibility(View.GONE);
                iv_indicator.setBackgroundResource(DataHelper.getHostData().get(key));
                LinearLayout ll_container = view.findViewById(R.id.ll_container);
                ll_container.setBackgroundResource(R.drawable.index_selector);
            }
            ftHost.addTab(ftHost.newTabSpec(key).setIndicator(view), FragmentFactory
                    .getTabHostFragment().get(key).getClass(), null);
        }
    }

    private void initView() {
        ftHost = findViewById(R.id.ft_host);
    }
}

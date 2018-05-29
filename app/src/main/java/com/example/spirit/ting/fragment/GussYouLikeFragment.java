package com.example.spirit.ting.fragment;

import android.view.View;
import android.widget.ListView;

import com.example.spirit.ting.R;
import com.example.spirit.ting.adapter.MyAdapter;
import com.example.spirit.ting.utils.EvaluateUtil;

import java.util.ArrayList;

public class GussYouLikeFragment extends RecommendBaseFragment {

    private ListView lv_guss;
    private ArrayList<Integer> imgList;

    @Override
    protected int inflateView() {
        return R.layout.fragment_guss_you;
    }

    @Override
    public void init() {
        initView();

        initUI();
    }

    private void initUI() {
        if (imgList == null) {
            imgList = new ArrayList<>();
            imgList.add(R.mipmap.p1);
            imgList.add(R.mipmap.p2);
            imgList.add(R.mipmap.p3);
            imgList.add(R.mipmap.p5);
        }
        //System.out.println(EvaluateUtil.sp2px(16));
        MAdapter mAdapter = new MAdapter(imgList);
        lv_guss.setAdapter(mAdapter);
    }

    private void initView() {
        lv_guss = view.findViewById(R.id.lv_guss);
    }

    class MAdapter extends MyAdapter<Integer> {

        public MAdapter(ArrayList<Integer> list) {
            super(list);
        }

        @Override
        protected View getMyView(View convertView, int position) {
            if (convertView == null) {
                convertView = View.inflate(getContext(), R.layout.article_layout, null);
            }
            return convertView;
        }
    }

}

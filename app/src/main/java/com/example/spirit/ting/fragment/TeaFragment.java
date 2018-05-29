package com.example.spirit.ting.fragment;

import android.annotation.SuppressLint;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.spirit.ting.R;
import com.example.spirit.ting.adapter.MyAdapter;
import com.example.spirit.ting.utils.Util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class TeaFragment extends RecommendBaseFragment {

    private GridView gv_view;
    private TextView tv_tea;
    private String currentDate;
    private ArrayList<Integer> imgList;

    @Override
    protected int inflateView() {
        return R.layout.fragment_tea;
    }

    @SuppressLint("SimpleDateFormat")
    @Override
    public void init() {
        initView();
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd", Locale.CHINA);
        String format = dateFormat.format(date);
        System.out.println(date.getHours() + "hours");
        if (date.getHours() > 7 && date.getHours() < 9) {
            currentDate = "早茶";
        } else if (date.getHours() > 12 && date.getHours() < 15) {
            currentDate = "午茶";
        } else {
            currentDate = "";
        }
        tv_tea.setText(currentDate + format);

        if (imgList == null) {
            imgList = new ArrayList<>();
            imgList.add(R.mipmap.p1);
            imgList.add(R.mipmap.p2);
            imgList.add(R.mipmap.p3);
            imgList.add(R.mipmap.p5);
        }

        DisplayMetrics dm = Util.getContext().getResources().getDisplayMetrics();
        int columnWidth = dm.widthPixels - 100;//由一屏幕显示的项数决定
        LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(
                imgList.size() * columnWidth + imgList.size(), LinearLayout.LayoutParams
                .WRAP_CONTENT);//
        gv_view.setLayoutParams(params1);//设置高和宽
        gv_view.setColumnWidth(columnWidth);//根据你一屏显示的项数决定
        gv_view.setHorizontalSpacing(1);
        gv_view.setStretchMode(GridView.NO_STRETCH);
        gv_view.setNumColumns(imgList.size());//设置一行显示的总列数

        MAdapter mAdapter = new MAdapter(imgList);
        gv_view.setAdapter(mAdapter);
        gv_view.requestDisallowInterceptTouchEvent(true);
        gv_view.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getContext(), "hello", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void initView() {
        tv_tea = view.findViewById(R.id.tv_tea);
        gv_view = view.findViewById(R.id.gv_view);
    }

    class MAdapter extends MyAdapter<Integer> {

        public MAdapter(ArrayList<Integer> list) {
            super(list);
        }

        @Override
        protected View getMyView(View convertView, int position) {
            ViewHolder holder;
            if (convertView == null) {
                convertView = View.inflate(getContext(), R.layout.grid_img, null);
                holder = new ViewHolder();
                holder.ivImage = convertView.findViewById(R.id.iv_image);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.ivImage.setImageBitmap(Util.scaleBitmap(imgList.get(position)));
            holder.ivImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getContext(), "hello", Toast.LENGTH_LONG).show();
                }
            });
            System.out.println(position);
            return convertView;
        }
    }

    class ViewHolder {
        ImageView ivImage;
    }
}

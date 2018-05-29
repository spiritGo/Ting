package com.example.spirit.ting.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

public abstract class MyAdapter<T> extends BaseAdapter {

    private ArrayList<T> list;

    public MyAdapter(ArrayList<T> list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = getMyView(convertView, position);
        return convertView;
    }

    protected abstract View getMyView(View convertView, int position);

}

package com.example.spirit.ting.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class IndicatorBaseFragment extends Fragment {

    public View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = View.inflate(getContext(), inflateLayout(), null);
        }

        ViewGroup parent = (ViewGroup) view.getParent();
        if (parent != null) {
            parent.removeView(view);
        }

        init();

        return view;
    }

    protected abstract int inflateLayout();

    protected abstract void init();
}

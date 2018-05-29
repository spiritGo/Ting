package com.example.spirit.ting.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class RecommendBaseFragment extends Fragment {

    public View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        view = View.inflate(getContext(), inflateView(), null);
        init();
        return view;
    }

    protected abstract int inflateView();

    public abstract void init();
}

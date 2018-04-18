package com.caelan.superrecycle.tabfragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.caelan.superrecycle.R;

/**
 * Created by yangjiacheng on 2018/4/18.
 * ...
 */
public class BlueFragment extends Fragment {
    public BlueFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.view_blue, container, false);
        return rootView;
    }

}

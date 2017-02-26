package com.luanluuhauigmail.luanluu.amthucvietnam.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.luanluuhauigmail.luanluu.amthucvietnam.R;

/**
 * Created by LuanLuu on 11/19/2016.
 */
public class FM_Content_Post extends Fragment {

    private View v;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fm_content_post, container, false);



        return v;
    }
}

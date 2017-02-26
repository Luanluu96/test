package com.luanluuhauigmail.luanluu.amthucvietnam.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.luanluuhauigmail.luanluu.amthucvietnam.R;

/**
 * Created by LuanLuu on 11/20/2016.
 */
public class FM_Item_View_Pager extends Fragment {

    private View v;
    private int resource;
    private ImageView image_view;

    public FM_Item_View_Pager() {}

    public FM_Item_View_Pager(int resource) {
        this.resource = resource;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.item_view_pager, container, false);

        this.image_view = (ImageView) v.findViewById(R.id.image_item);
        this.image_view.setImageResource(resource);

        return v;
    }
}

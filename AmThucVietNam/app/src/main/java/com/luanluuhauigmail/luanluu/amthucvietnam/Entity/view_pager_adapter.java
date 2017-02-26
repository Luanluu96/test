package com.luanluuhauigmail.luanluu.amthucvietnam.Entity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.luanluuhauigmail.luanluu.amthucvietnam.fragment.FM_Item_View_Pager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LuanLuu on 11/20/2016.
 */
public class view_pager_adapter extends FragmentPagerAdapter {

    List<FM_Item_View_Pager> ds_anh = new ArrayList<>();

    public view_pager_adapter(FragmentManager fm, List<FM_Item_View_Pager> ds_anh) {
        super(fm);
        this.ds_anh = ds_anh;
    }

    @Override
    public Fragment getItem(int position) {
        return this.ds_anh.get(position);
    }

    @Override
    public int getCount() {
        return this.ds_anh.size();
    }
}

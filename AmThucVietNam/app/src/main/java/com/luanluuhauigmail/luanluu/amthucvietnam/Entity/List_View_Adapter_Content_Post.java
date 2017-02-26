package com.luanluuhauigmail.luanluu.amthucvietnam.Entity;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.luanluuhauigmail.luanluu.amthucvietnam.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LuanLuu on 11/19/2016.
 */
public class List_View_Adapter_Content_Post extends BaseAdapter {

    private List<Item_Content_Post> ds_noi_dung = new ArrayList<>();
    private Context context;

    public List_View_Adapter_Content_Post(Context context, List<Item_Content_Post> ds_noi_dung) {
        this.context = context;
        this.ds_noi_dung = ds_noi_dung;
    }

    @Override
    public int getCount() {
        return ds_noi_dung.size();
    }

    @Override
    public Object getItem(int position) {
        return ds_noi_dung.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View v = inflater.inflate(R.layout.item_content_post, parent, false);
        TextView tv_ten_nguoi_dang = (TextView) v.findViewById(R.id.tv_ten_nguoi_dang);
        ViewPager view_pager = (ViewPager) v.findViewById(R.id.ds_anh);
        TextView tv_noi_dung = (TextView) v.findViewById(R.id.tv_noi_dung);

        Item_Content_Post item = (Item_Content_Post) ds_noi_dung.get(position);

        tv_ten_nguoi_dang.setText(item.getTen_nsd());
        tv_noi_dung.setText(item.getNoi_dung());

        view_pager.setAdapter(item.getAdapter());

        return v;
    }
}

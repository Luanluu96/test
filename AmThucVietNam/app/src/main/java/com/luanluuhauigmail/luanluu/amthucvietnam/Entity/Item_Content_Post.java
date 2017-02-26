package com.luanluuhauigmail.luanluu.amthucvietnam.Entity;

/**
 * Created by LuanLuu on 11/19/2016.
 */
public class Item_Content_Post {
    private String noi_dung, ten_nsd;
    private int dc_x, dc_y;
    private int so_luong_danh_gia, so_luong_binh_luan;
    private view_pager_adapter adapter;

    public Item_Content_Post() {}

    public Item_Content_Post(String noi_dung, String ten_nsd,
                             int dc_x, int dc_y, int so_luong_danh_gia, int so_luong_binh_luan, view_pager_adapter adapter) {
        this.noi_dung = noi_dung;
        this.ten_nsd = ten_nsd;
        this.dc_x = dc_x;
        this.dc_y = dc_y;
        this.so_luong_danh_gia = so_luong_danh_gia;
        this.so_luong_binh_luan = so_luong_binh_luan;
        this.adapter = adapter;
    }

    public String getNoi_dung() {
        return this.noi_dung;
    }

    public String getTen_nsd() {
        return this.ten_nsd;
    }

    public int getDc_x() {
        return this.dc_x;
    }

    public int getDc_y() {
        return this.dc_y;
    }

    public int getSo_luong_danh_gia() {
        return this.so_luong_danh_gia;
    }

    public int getSo_luong_binh_luan() {
        return this.so_luong_binh_luan;
    }

    public view_pager_adapter getAdapter() {
        return this.adapter;
    }
}

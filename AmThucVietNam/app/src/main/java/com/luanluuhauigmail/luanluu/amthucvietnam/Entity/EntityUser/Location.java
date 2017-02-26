package com.luanluuhauigmail.luanluu.amthucvietnam.Entity.EntityUser;

/**
 * Created by LuanLuu on 12/10/2016.
 */
// lưu trữ địa chỉ của quán ăn
public class Location {
    public double x, y;

    public Location(){}
    public Location(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public String toString() {
        return this.x + "/" + this.y;
    }
}
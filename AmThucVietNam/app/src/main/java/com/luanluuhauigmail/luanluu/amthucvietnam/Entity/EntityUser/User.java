package com.luanluuhauigmail.luanluu.amthucvietnam.Entity.EntityUser;

import java.util.Date;

/**
 * Created by LuanLuu on 11/19/2016.
 */
public class User {
    private String name;
    private String email;
    private String password;
    private String sex;
    private String phone;
    private String name_restaurant;
    private String my_address;
    private Location address_resstaurant;
    private Date date_register;
    private Date birthday;
    private Boolean isOnline;

    public User() {}

    public User(String name, String email, String password, String sex, String phone, String name_restaurant, String my_address,
                Date birthday, Date date_register, boolean isOnline, double addres_restaurant_x, double addres_restaurant_y) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.sex = sex;
        this.phone = phone;
        this.name_restaurant = name_restaurant;
        this.my_address = my_address;
        this.birthday = birthday;
        this.date_register = date_register;
        this.isOnline = isOnline;
        this.address_resstaurant.x = addres_restaurant_x;
        this.address_resstaurant.y = addres_restaurant_y;
    }

    public User(String name, String email, String password, String sex, String phone, String name_restaurant, String my_address,
                Date birthday, Date date_register, boolean isOnline, Location address_restaurant) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.sex = sex;
        this.phone = phone;
        this.name_restaurant = name_restaurant;
        this.my_address = my_address;
        this.birthday = birthday;
        this.date_register = date_register;
        this.isOnline = isOnline;
        this.address_resstaurant = address_restaurant;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName_restaurant() {
        return name_restaurant;
    }

    public void setName_restaurant(String name_restaurant) {
        this.name_restaurant = name_restaurant;
    }

    public String getMy_address() {
        return my_address;
    }

    public void setMy_address(String my_address) {
        this.my_address = my_address;
    }

    public Location getAddress_resstaurant() {
        return address_resstaurant;
    }

    public void setAddress_resstaurant(Location address_resstaurant) {
        this.address_resstaurant = address_resstaurant;
    }

    public Date getDate_register() {
        return date_register;
    }

    public void setDate_register(Date date_register) {
        this.date_register = date_register;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Boolean getOnline() {
        return isOnline;
    }

    public void setOnline(Boolean online) {
        isOnline = online;
    }

    public String toString() {
        return this.name + "/" + this.email + "/" + this.password + "/" + this.sex + "/" + this.phone + "/" + this.name_restaurant + "/" + this.my_address +
                "/" + this.birthday.toString() + "/" + this.date_register.toString() + "/" + this.address_resstaurant.toString();
    }
}

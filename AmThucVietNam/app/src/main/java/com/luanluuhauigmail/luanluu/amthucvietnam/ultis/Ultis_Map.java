package com.luanluuhauigmail.luanluu.amthucvietnam.ultis;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;

import java.io.IOException;
import java.util.List;

/**
 * Created by LuanLuu on 12/10/2016.
 */
public class Ultis_Map {

    public static List<Address> get_List_Address(Context context, String text) throws IOException {
        List<Address> addressList = null;
        // khai bao lop xu ly vi tri dia ly
        Geocoder geocoder = new Geocoder(context);

        // lay ve danh sach address
        addressList = geocoder.getFromLocationName(text, 1000);

        return addressList;
    }
}

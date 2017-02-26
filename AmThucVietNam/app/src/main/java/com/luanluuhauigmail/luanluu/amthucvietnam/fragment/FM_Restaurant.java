package com.luanluuhauigmail.luanluu.amthucvietnam.fragment;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.luanluuhauigmail.luanluu.amthucvietnam.Entity.EntityMap.RowLocationAdapter;
import com.luanluuhauigmail.luanluu.amthucvietnam.Entity.EntityUser.Location;
import com.luanluuhauigmail.luanluu.amthucvietnam.Entity.EntityUser.User;
import com.luanluuhauigmail.luanluu.amthucvietnam.R;
import com.luanluuhauigmail.luanluu.amthucvietnam.ultis.Create_Account;
import com.luanluuhauigmail.luanluu.amthucvietnam.ultis.Ultis_Map;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by LuanLuu on 12/9/2016.
 */
public class FM_Restaurant extends Fragment implements OnMapReadyCallback {

    private View v;
    private SupportMapFragment supportMapFragment;
    private GoogleMap map;
    private AutoCompleteTextView auto_text_view;
    private EditText editText_name_restaurant;
    private User my_user;
    private Button btn_done;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fm_restaurant, container, false);
        // lay du lieu cua user
        get_values_bundle(getArguments());

        // anh sạ
        this.supportMapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.dialog_map);
        this.auto_text_view = (AutoCompleteTextView) v.findViewById(R.id.auto_text_view);
        this.editText_name_restaurant = (EditText) this.v.findViewById(R.id.editText_name_restaurant);
        this.btn_done = (Button) this.v.findViewById(R.id.btn_done);

        // Cài đặt map
        if (this.supportMapFragment == null) {
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            supportMapFragment = SupportMapFragment.newInstance();
            fragmentTransaction.replace(R.id.dialog_map, supportMapFragment).commit();
        }
        this.supportMapFragment.getMapAsync(this);

        this.auto_text_view.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                set_Adapter_AutoComplaceTextView(auto_text_view.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        // Hoàn thành Đăng kí
        this.btn_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(confirm_info()) {
                    my_user.setName_restaurant(editText_name_restaurant.getText().toString());
                    double x = map.getCameraPosition().target.latitude;
                    double y = map.getCameraPosition().target.longitude;
                    my_user.setAddress_resstaurant(new Location( x, y));
                    Toast(Create_Account.New().Create(getActivity(), my_user));
                }
            }
        });

        return v;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        LatLng sydney = new LatLng(21.045822, 105.748860);
        map.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 18));

        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        map.setMyLocationEnabled(true);
    }

    private void set_Adapter_AutoComplaceTextView(String text) {
        try {
            List<Address> addressList = Ultis_Map.get_List_Address(getActivity(), text);
            List<String> stringList = new ArrayList<>();
            for(Address address : addressList) {
                stringList.add(address.getCountryName() +", "+ address.getFeatureName() + ", " + address.getLocality());
            }
            RowLocationAdapter arrayAdapter = new RowLocationAdapter(getActivity() ,stringList);
            this.auto_text_view.setAdapter(arrayAdapter);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void get_values_bundle(Bundle bundle) {

        Date birthday = new Date(bundle.getInt("year"), bundle.getInt("month"), bundle.getInt("day"));

        this.my_user = new User(bundle.getString("name"), bundle.getString("email"), bundle.getString("password"), bundle.getString("sex")
                                , bundle.getString("phone"), "", bundle.getString("my_address"), birthday, new Date(), true, null);
    }

    //xác nhận tên quán và địa điểm
    private boolean confirm_info() {

        if("".equals(this.editText_name_restaurant.getText().toString()) ) {
            Toast("Bạn đã để trống tên quán");
            return false;
        }
        return true;
    }

    private void Toast(String text) {
        Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT).show();
    }
}

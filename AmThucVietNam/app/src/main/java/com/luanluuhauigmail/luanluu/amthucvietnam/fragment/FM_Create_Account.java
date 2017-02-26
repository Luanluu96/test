package com.luanluuhauigmail.luanluu.amthucvietnam.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.luanluuhauigmail.luanluu.amthucvietnam.Entity.EntityUser.User;
import com.luanluuhauigmail.luanluu.amthucvietnam.R;
import com.luanluuhauigmail.luanluu.amthucvietnam.ultis.Create_Account;

import java.util.Date;

/**
 * Created by LuanLuu on 11/19/2016.
 */
public class FM_Create_Account extends Fragment{

    private View v;
    private Button btn_done;
    private EditText editText_name, editText_email, editText_password, editText_confirm_password, editText_phone, editText_my_address,
                        editText_date, editText_month, editText_year;
    private RadioButton radioButton_nam, radioButton_nu;
    private CheckBox checkBox_restaurant;
    private User my_user;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fm_create_account, container, false);

        // ánh sạ
        initailize();

        //done
        this.btn_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirm_info();
            }
        });

        return v;
    }

    // ánh sạ
    private void initailize() {
        // button
        this.btn_done = (Button) this.v.findViewById(R.id.btn_done);
        // edit Text
        this.editText_name = (EditText) this.v.findViewById(R.id.editText_name);
        this.editText_email = (EditText) this.v.findViewById(R.id.editText_email);
        this.editText_password = (EditText) this.v.findViewById(R.id.editText_password);
        this.editText_confirm_password = (EditText) this.v.findViewById(R.id.editText_confirm_password);
        this.editText_phone = (EditText) this.v.findViewById(R.id.editText_phone);
        this.editText_my_address = (EditText) this.v.findViewById(R.id.editText_my_address);
        this.editText_date = (EditText) this.v.findViewById(R.id.editText_date);
        this.editText_month = (EditText) this.v.findViewById(R.id.editText_month);
        this.editText_year = (EditText) this.v.findViewById(R.id.editText_year);
        // Radio Button
        this.radioButton_nam = (RadioButton) this.v.findViewById(R.id.radio_nam);
        this.radioButton_nu = (RadioButton) this.v.findViewById(R.id.radio_nu);
        //checkBox
        this.checkBox_restaurant = (CheckBox) this.v.findViewById(R.id.checkBox_restaurant);
    }

    private boolean confirm_info() {
        boolean confirm_name = !"".equals(this.editText_name.getText().toString());
        boolean confirm_email = !"".equals(this.editText_email.getText().toString());
        boolean confirm_password = !"".equals(this.editText_password.getText().toString());
        boolean confirm_checked_restaurant = this.checkBox_restaurant.isChecked();
        if(confirm_password) {
            confirm_password = this.editText_password.getText().toString().equals(this.editText_confirm_password.getText().toString());
            if(!confirm_password) {
                this.Toast("Xác nhận lại mật khẩu không khớp");
            }
        }

        if(confirm_name && confirm_email && confirm_password) {
            create_account();
            if(!confirm_checked_restaurant) {
                // tạo tài khoản Thường
                Toast(Create_Account.New().Create(getActivity(), my_user));

            }else{
                Bundle bundle = create_bundle();

                FM_Restaurant fm_restaurant = new FM_Restaurant();
                fm_restaurant.setArguments(bundle);
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                fragmentTransaction.setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit);
                fragmentTransaction.replace(android.R.id.content, fm_restaurant)
                        .addToBackStack(null)
                        .commit();
            }
        }

        return true;
    }

    private void create_account() {
        String name = this.editText_name.getText().toString();
        String email = this.editText_email.getText().toString();
        String password = this.editText_password.getText().toString();
        String sex = "";
        if(this.radioButton_nu.isChecked()) {
            sex = "nữ";
        } else if(this.radioButton_nam.isChecked()){
            sex = "nam";
        }

        String phone = this.editText_phone.getText().toString();
        String name_restaurant = "";
        String my_address = this.editText_my_address.getText().toString();
        int day = Integer.valueOf(this.editText_date.getText().toString());
        int month = Integer.valueOf(this.editText_month.getText().toString());
        int year = Integer.valueOf(this.editText_year.getText().toString());
        Date birthday = new Date(year, month, day);

        Date date_register = new Date();

        this.my_user = new User(name, email, password, sex, phone, name_restaurant, my_address, birthday, date_register, true, null);
    }

    private Bundle create_bundle() {

        Bundle bundle = new Bundle();

        bundle.putString("name", this.editText_name.getText().toString());
        bundle.putString("email", this.editText_email.getText().toString());
        bundle.putString("password", this.editText_password.getText().toString());
        if(this.radioButton_nu.isChecked()) {
            bundle.putString("sex", "nữ");
        } else {
            bundle.putString("sex", "nam");
        }

        bundle.putString("phone", this.editText_phone.getText().toString());

        bundle.putString("my_address", this.editText_my_address.getText().toString());
        bundle.putInt("day", Integer.valueOf(this.editText_date.getText().toString()));
        bundle.putInt("month", Integer.valueOf(this.editText_month.getText().toString()));
        bundle.putInt("year",Integer.valueOf(this.editText_year.getText().toString()));

        return bundle;
    }

    private void Toast(String text) {
        Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT).show();
    }
}

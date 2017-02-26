package com.luanluuhauigmail.luanluu.amthucvietnam.activity;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TabHost;

import com.luanluuhauigmail.luanluu.amthucvietnam.R;
import com.luanluuhauigmail.luanluu.amthucvietnam.activity.tab_host.Tab_Account;
import com.luanluuhauigmail.luanluu.amthucvietnam.activity.tab_host.Tab_Home_Page;
import com.luanluuhauigmail.luanluu.amthucvietnam.activity.tab_host.Tab_Login;

public class Home_Page extends TabActivity {
    private RelativeLayout rl;
    private String tai_khoan, mat_khau;
    private TabHost tabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        // ánh sạ
        connect_id();

        // cài đặt Tab Host
        setTabHost();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        // hiện nội dung trang chủ
        this.rl.setVisibility(View.VISIBLE);
    }

    // ánh sạ
    private void connect_id() {
        this.rl = (RelativeLayout) findViewById(R.id.home_page);
        this.tabHost = (TabHost) findViewById(android.R.id.tabhost);
    }

    // khởi tạo 1 tab Host chứa Trang chủ và tài Khoản
    private void setTabHost() {

        tabHost.setup();

        TabHost.TabSpec tab_home_page = tabHost.newTabSpec("TAB_HOME_PAGE");
        TabHost.TabSpec tab_account = tabHost.newTabSpec("TAB_ACCOUNT");

        // khởi tạo Tab Trang Chủ
        tab_home_page.setIndicator("Trang Chủ");
        tab_home_page.setContent(new Intent(Home_Page.this, Tab_Home_Page.class));
        tabHost.addTab(tab_home_page);

        if(check_login()) {
            // nếu chưa đăng nhập thì hiện thị màn hình login
            tab_account.setIndicator("Đăng Nhập");
            tab_account.setContent(new Intent(Home_Page.this, Tab_Login.class));
            tabHost.addTab(tab_account);
        } else {
            // cài đặt tên Tài khoản làm tên Tab
            tab_account.setIndicator("Tài Khoản");
            tab_account.setContent(new Intent(Home_Page.this, Tab_Account.class));
            tabHost.addTab(tab_account);
        }

    }

    // kiểm tra xem đã login hay chưa
    private boolean check_login() {
        Bundle bundle = this.getIntent().getExtras();
        this.tai_khoan = bundle.getString("tai_khoan");
        this.mat_khau = bundle.getString("mat_khau");

        if ("".equals(this.mat_khau) && "".equals(this.tai_khoan)) {
            return true;
        }
        return false;
    }

}

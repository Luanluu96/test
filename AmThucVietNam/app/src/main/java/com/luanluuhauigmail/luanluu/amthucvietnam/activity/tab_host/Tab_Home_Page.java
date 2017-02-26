package com.luanluuhauigmail.luanluu.amthucvietnam.activity.tab_host;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import com.luanluuhauigmail.luanluu.amthucvietnam.Entity.Item_Content_Post;
import com.luanluuhauigmail.luanluu.amthucvietnam.Entity.List_View_Adapter_Content_Post;
import com.luanluuhauigmail.luanluu.amthucvietnam.Entity.view_pager_adapter;
import com.luanluuhauigmail.luanluu.amthucvietnam.R;
import com.luanluuhauigmail.luanluu.amthucvietnam.fragment.FM_Content_Post;
import com.luanluuhauigmail.luanluu.amthucvietnam.fragment.FM_Item_View_Pager;

import java.util.ArrayList;
import java.util.List;

public class Tab_Home_Page extends AppCompatActivity {

    private Spinner spinner;
    private ListView list_view_content_post;
    private RelativeLayout tab_home_page;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_home_page);

        // ánh sạ
        connect_id();

        //cài đặt adapter cho spinner
        set_adapter_spinner();

        // cài đặt adapter cho listview
        set_adapter_list_view();
    }


    // ánh sạ
    private void connect_id() {
        this.spinner = (Spinner) findViewById(R.id.spinner_lua_chon);
        this.list_view_content_post = (ListView) findViewById(R.id.list_view_content_post);
        this.tab_home_page = (RelativeLayout) findViewById(R.id.tab_home_page);
    }

    // cài đặt adapter cho spinner
    private void set_adapter_spinner() {
        String[] objects = {"Thời Gian", "Yêu Thích", "Món Ăn", "Quan Tâm"};

        ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, objects);

        this.spinner.setAdapter(adapter);
    }

    // cài đặt adapter cho listview
    private void set_adapter_list_view() {
        List<Item_Content_Post> ds_noi_dung = new ArrayList<>();
        for(int i = 0 ; i < 20; i++) {
            List<FM_Item_View_Pager> ds_anh = new ArrayList<>();
            for(int j = 0 ; j < 4; j++) {
                FM_Item_View_Pager item_view_pager = new FM_Item_View_Pager(R.drawable.image);
                ds_anh.add(item_view_pager);
            }

            view_pager_adapter pager_adapter = new view_pager_adapter(getSupportFragmentManager(), ds_anh);

            Item_Content_Post item = new Item_Content_Post(getString(R.string.noidung), "luận Lưu", 21, 22, 3, 3, pager_adapter);
            ds_noi_dung.add(item);
        }

        List_View_Adapter_Content_Post adapter_content_post = new List_View_Adapter_Content_Post(this, ds_noi_dung);

        this.list_view_content_post.setAdapter(adapter_content_post);


        // chuyển đến fragment chứa nội dung đầy đủ của bài viết
        this.list_view_content_post.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                FM_Content_Post fm_content_post = new FM_Content_Post();

                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

                transaction.setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit);
                transaction.replace(android.R.id.content, fm_content_post)
                        .addToBackStack(null)
                        .commit();
                tab_home_page.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        tab_home_page.setVisibility(View.VISIBLE);
    }
}

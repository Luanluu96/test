package com.luanluuhauigmail.luanluu.amthucvietnam.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.luanluuhauigmail.luanluu.amthucvietnam.R;
import com.luanluuhauigmail.luanluu.amthucvietnam.fragment.FM_Create_Account;

public class Login extends AppCompatActivity {

    private RelativeLayout rl;
    private EditText edt_tai_khoan, edt_mat_khau;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener  mAuthListenner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        this.mAuth = FirebaseAuth.getInstance();
        this.mAuthListenner = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user  = firebaseAuth.getCurrentUser();
            }
        };

        // ánh sạ
        connect_id();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListenner);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(mAuthListenner != null) {
            mAuth.removeAuthStateListener(mAuthListenner);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if(getSupportFragmentManager().getBackStackEntryCount() ==0) {
            setVisibitity(View.VISIBLE);
        }
    }

    //Signin vs tài khoản mật khẩu
    private void Sign_In() throws IllegalStateException{
        String email = this.edt_tai_khoan.getText().toString();
        String mat_khau = this.edt_mat_khau.getText().toString();
        if("".equals(email) || "".equals(mat_khau)) {
            throw new IllegalStateException("Hãy Nhập Tài Khoản Mật Khẩu.");
        } else {
            mAuth.signInWithEmailAndPassword(email, mat_khau)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (!task.isSuccessful()) {
                                // nếu đăng nhập thất bại
                                Toast.makeText(Login.this, "Tài Khoản Mật Khẩu Không Chính Xác.", Toast.LENGTH_SHORT).show();
                            }

                            // ...
                        }
                    });
        }
    }

    // ánh sạ
    private void connect_id() {
        this.rl = (RelativeLayout) findViewById(R.id.main_layout);
        this.edt_tai_khoan = (EditText) findViewById(R.id.edt_tai_khoan);
        this.edt_mat_khau = (EditText) findViewById(R.id.edt_mat_khau);

    }

    // làm hiện hoặc ẩn layout login
    public void setVisibitity(int visibility) {
        this.rl.setVisibility(visibility);
    }

    //bắt sự kiện onclick Login
    public void login(View view) {
        try {
            Sign_In();
        }catch (IllegalStateException ise) {
            Toast.makeText(this, ise.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public void di_tiep(View view) {
        Intent intent = new Intent(Login.this, Home_Page.class);

        Bundle bundle = new Bundle();
        bundle.putString("tai_khoan", "");
        bundle.putString("mat_khau", "");
        intent.putExtras(bundle);

        startActivity(intent);
    }

    public void tao_tai_khoan(View view) {
        FM_Create_Account fm_create_account = new FM_Create_Account();
        FragmentManager fragmentManager = this.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit);
        fragmentTransaction.replace(android.R.id.content, fm_create_account)
                .addToBackStack(null)
                .commit();
        this.rl.setVisibility(View.GONE);
    }
}

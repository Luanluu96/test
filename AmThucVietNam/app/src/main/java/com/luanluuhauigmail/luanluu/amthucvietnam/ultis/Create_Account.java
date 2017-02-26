package com.luanluuhauigmail.luanluu.amthucvietnam.ultis;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.luanluuhauigmail.luanluu.amthucvietnam.Entity.EntityUser.User;

/**
 * Created by LuanLuu on 12/31/2016.
 */
public class Create_Account{

    private String _check_create_successful;
    private User _user;
    private Activity _activity;

    public static Create_Account New() {
        return new Create_Account();
    }

    public String Create(Activity activity, User user) {
        this._user = user;
        this._activity = activity;

        this._check_create_successful = "";
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.createUserWithEmailAndPassword(user.getEmail(), user.getPassword())
                .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                                    _check_create_successful = "Email Đã được Sử dụng!";
                                    Log.d("Check Account:", "User");
                                }else {
                                    _check_create_successful = "không thành công";
                                    Log.d("Check Account:", "not Success");
                                }
                            } else {
                                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
                                databaseReference.child(Name.NODE_USERS).child(task.getResult().getUser().getUid()).setValue(_user);
                                _check_create_successful = "Thành Công";
                                Log.d("Check Account:", "Success");
                            }
                        }
                });

        return _check_create_successful;
    }
}

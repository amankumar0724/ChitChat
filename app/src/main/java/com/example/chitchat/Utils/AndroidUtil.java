package com.example.chitchat.Utils;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.example.chitchat.Models.UserModel;

public class AndroidUtil {
    public static void showToast(Context context,String message){
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }
    public static void passingUserdataByIntent(Intent intent, UserModel model){
        intent.putExtra("username",model.getUsername());
        intent.putExtra("phone",model.getPhone());
        intent.putExtra("userId",model.getUserId());
        intent.putExtra("fcmToken",model.getFcmToken());
    }
    public static UserModel getUserdataFromIntent(Intent intent){
        UserModel user = new UserModel();
        user.setUsername(intent.getStringExtra("username"));
        user.setPhone(intent.getStringExtra("phone"));
        user.setUserId(intent.getStringExtra("userId"));
        user.setFcmToken(intent.getStringExtra("fcmToken"));
        return user;
    }
}

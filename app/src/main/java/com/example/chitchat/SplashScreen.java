package com.example.chitchat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.chitchat.Models.UserModel;
import com.example.chitchat.Utils.AndroidUtil;
import com.example.chitchat.Utils.FirebaseUtil;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String userId = getIntent().getStringExtra("userId");
        if(FirebaseUtil.isLoggedIn() && getIntent().getStringExtra("userId") != null){
            //intent from notification

            FirebaseUtil.allUserCollectionReference().document(userId).get()
                    .addOnCompleteListener(task -> {
                        if(task.isSuccessful()){
                            UserModel userModel = task.getResult().toObject(UserModel.class);

                            Intent m_intent = new Intent(this,MainActivity.class);
                            m_intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                            startActivity(m_intent);

                            Intent intent = new Intent(this,ChatActivity.class);
                            AndroidUtil.passingUserdataByIntent(intent,userModel);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);

                            finish();
                        }
                    });
        }else{
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    if(FirebaseUtil.isLoggedIn()){
                        Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }else {
                        Intent intent = new Intent(SplashScreen.this, LoginPhoneNumberActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
            },1000);
        }
        setContentView(R.layout.activity_splash_screen);

    }
}
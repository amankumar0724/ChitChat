package com.example.chitchat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageButton;

import com.example.chitchat.Utils.FirebaseUtil;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingService;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavBar;
    ImageButton searchBtn;
    chatFragment chatFrgt;
    profileFragment profileFrgt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chatFrgt = new chatFragment();
        profileFrgt = new profileFragment();
        bottomNavBar = findViewById(R.id.bottom_navigation);
        searchBtn = findViewById(R.id.search_btn);

        searchBtn.setOnClickListener(v->{
            Intent intent = new Intent(MainActivity.this,searchActivity.class);
            startActivity(intent);
        });
        bottomNavBar.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId() == R.id.chat_option){
                    getSupportFragmentManager().beginTransaction().replace(R.id.main_frame_layout,chatFrgt).commit();
                }
                if(item.getItemId() == R.id.profile_option){
                    getSupportFragmentManager().beginTransaction().replace(R.id.main_frame_layout,profileFrgt).commit();
                }
                return true;
            }
        });
        bottomNavBar.setSelectedItemId(R.id.chat_option);

        getFCMToken();

    }

    private void getFCMToken() {
        FirebaseMessaging.getInstance().getToken().addOnCompleteListener(task->{
            if(task.isSuccessful()){
                String token = task.getResult();
//                Log.i("MY TOKEN",token);
                FirebaseUtil.currentUserDetails().update("fcmToken",token);
            }
        });
    }
}
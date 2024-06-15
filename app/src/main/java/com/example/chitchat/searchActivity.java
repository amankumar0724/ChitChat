package com.example.chitchat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.chitchat.Adapters.SearchRecyclerAdapter;
import com.example.chitchat.Models.UserModel;
import com.example.chitchat.Utils.FirebaseUtil;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.Query;

public class searchActivity extends AppCompatActivity {

    EditText searchInput;
    ImageButton searchBtn;
    ImageButton backBtn;
    RecyclerView recyclerView;
    SearchRecyclerAdapter myAdapter;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        searchInput = findViewById(R.id.search_username);
        searchBtn = findViewById(R.id.search_user_btn);
        backBtn = findViewById(R.id.back_btn);
        recyclerView = findViewById(R.id.search_user_recycler_view);

        searchInput.requestFocus();//to open keyboard automatically for typing

        backBtn.setOnClickListener(v->{
            onBackPressed();//inbuilt function- returns back
        });
        searchBtn.setOnClickListener(v->{
            String searchedName = searchInput.getText().toString();
            if(searchedName.isEmpty() || searchedName.length() < 4){
                searchInput.setError("Invalid username.");
                return;
            }
            createSearchRecyclerView(searchedName);
        });
    }

    void createSearchRecyclerView(String searchedName) {
        Query query = FirebaseUtil.allUserCollectionReference().whereGreaterThanOrEqualTo("username",searchedName);
        FirestoreRecyclerOptions<UserModel> options = new FirestoreRecyclerOptions.Builder<UserModel>().setQuery(query, UserModel.class).build();
        myAdapter = new SearchRecyclerAdapter(options,getApplicationContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(myAdapter);
        myAdapter.startListening();
    }
    @Override
    protected void onStart() {
        super.onStart();
        if(myAdapter != null){
            myAdapter.startListening();
        }
    }
    @Override
    protected void onStop() {
        super.onStop();
        if(myAdapter != null){
            myAdapter.stopListening();
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        if(myAdapter != null){
            myAdapter.startListening();
        }
    }
}
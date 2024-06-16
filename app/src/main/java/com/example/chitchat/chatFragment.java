package com.example.chitchat;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.chitchat.Adapters.ChatFragmentRecyclerAdapter;
import com.example.chitchat.Models.ChatMessageModel;
import com.example.chitchat.Models.ChatboxModel;
import com.example.chitchat.Utils.FirebaseUtil;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.Query;

public class chatFragment extends Fragment {

    RecyclerView recyclerView;
    ChatFragmentRecyclerAdapter myAdapter;
    public chatFragment(){

    }
    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_chat, container, false);

        recyclerView = view.findViewById(R.id.recycler_view);
        createRecyclerView();
        return view;
    }
    void createRecyclerView() {
        Query query = FirebaseUtil.allChatboxCollectionReference()
                .whereArrayContains("userIds",FirebaseUtil.currentUserId())
                .orderBy("lastMesTimestamp",Query.Direction.DESCENDING);
        FirestoreRecyclerOptions<ChatboxModel> options = new FirestoreRecyclerOptions.Builder<ChatboxModel>()
                .setQuery(query,ChatboxModel.class).build();
        myAdapter = new ChatFragmentRecyclerAdapter(options,getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(myAdapter);
        myAdapter.startListening();
    }
    @Override
    public void onStart() {
        super.onStart();
        if(myAdapter != null){
            myAdapter.startListening();
        }
    }
    @Override
    public void onStop() {
        super.onStop();
        if(myAdapter != null){
            myAdapter.stopListening();
        }
    }
    @Override
    public void onResume() {
        super.onResume();
        if(myAdapter != null){
            myAdapter.notifyDataSetChanged();
        }
    }
}
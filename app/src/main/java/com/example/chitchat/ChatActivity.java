package com.example.chitchat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.chitchat.Adapters.ChatRecyclerAdapter;
import com.example.chitchat.Models.ChatMessageModel;
import com.example.chitchat.Models.ChatboxModel;
import com.example.chitchat.Models.UserModel;
import com.example.chitchat.Utils.AndroidUtil;
import com.example.chitchat.Utils.FirebaseUtil;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.Query;

import java.util.Arrays;

public class ChatActivity extends AppCompatActivity {
    UserModel otherUser;
    String chatboxId;
    ChatboxModel chatboxModel;
    EditText messInput;
//    CardView sendMessBtn;

    ImageButton sendMessBtn;
    ImageButton backBtn;
    TextView otherUsername;
    RecyclerView recyclerView;
    ChatRecyclerAdapter myAdapter;
//    ImageView profPic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        //get UserModel
        otherUser = AndroidUtil.getUserdataFromIntent(getIntent());
        chatboxId = FirebaseUtil.getChatboxId(FirebaseUtil.currentUserId(),otherUser.getUserId());
//
        messInput = findViewById(R.id.message_input);
        sendMessBtn = findViewById(R.id.mes_send_btn);
        backBtn = findViewById(R.id.back_btn);
        otherUsername = findViewById(R.id.other_username);
        recyclerView = findViewById(R.id.chat_recyclerview);
//        profPic = findViewById(R.id.profile_pic);

        backBtn.setOnClickListener((v)->{
            onBackPressed();
        });
        otherUsername.setText(otherUser.getUsername());

        sendMessBtn.setOnClickListener((v->{
            String message = messInput.getText().toString().trim();
            if(message.isEmpty()){
                return;
            }
            sendMessage(message);
        }));
        createChatterboxModel();
        createChatRecyclerView();
    }

    void createChatRecyclerView() {
        Query query = FirebaseUtil.getChatboxMessageReference(chatboxId).orderBy("timestamp",Query.Direction.DESCENDING);
        FirestoreRecyclerOptions<ChatMessageModel> options = new FirestoreRecyclerOptions.Builder<ChatMessageModel>().setQuery(query,ChatMessageModel.class).build();

        myAdapter = new ChatRecyclerAdapter(options,getApplicationContext());
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setReverseLayout(true);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(myAdapter);
        myAdapter.startListening();
        //Below method is used to avoid scrolling in the chatroom
        myAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                super.onItemRangeInserted(positionStart, itemCount);
                recyclerView.smoothScrollToPosition(0);
            }
        });
    }

    //
    void sendMessage(String message) {
        chatboxModel.setLastMesTimestamp(Timestamp.now());
        chatboxModel.setLastMesSenderId(FirebaseUtil.currentUserId());
        chatboxModel.setLastMessage(message);
        FirebaseUtil.getChatboxReference(chatboxId).set(chatboxModel);
        ChatMessageModel chatMessageModel = new ChatMessageModel(message,FirebaseUtil.currentUserId(),Timestamp.now());
        FirebaseUtil.getChatboxMessageReference(chatboxId).add(chatMessageModel).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@NonNull Task<DocumentReference> task) {
                if(task.isSuccessful()){
                    messInput.setText("");
//                    sendNotification(message);
                }
            }
        });
    }


    void createChatterboxModel(){
        FirebaseUtil.getChatboxReference(chatboxId).get().addOnCompleteListener(task -> {
            if(task.isSuccessful()){
                chatboxModel = task.getResult().toObject(ChatboxModel.class);
                if(chatboxModel == null){
                    //first time chat
                    chatboxModel = new ChatboxModel(
                            chatboxId,
                            Arrays.asList(FirebaseUtil.currentUserId(),otherUser.getUserId()),
                            Timestamp.now(),
                            ""
                    );
                    FirebaseUtil.getChatboxReference(chatboxId).set(chatboxModel);
                }
            }
        });
    }
}
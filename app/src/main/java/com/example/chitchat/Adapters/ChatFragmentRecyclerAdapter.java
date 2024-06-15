package com.example.chitchat.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.AndroidException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chitchat.ChatActivity;
import com.example.chitchat.Models.ChatboxModel;
import com.example.chitchat.Models.UserModel;
import com.example.chitchat.R;
import com.example.chitchat.Utils.AndroidUtil;
import com.example.chitchat.Utils.FirebaseUtil;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

import org.w3c.dom.Text;

import java.util.Objects;

public class ChatFragmentRecyclerAdapter extends FirestoreRecyclerAdapter<ChatboxModel, ChatFragmentRecyclerAdapter.ChatboxModelViewHolder> {

    Context context;
    public ChatFragmentRecyclerAdapter(@NonNull FirestoreRecyclerOptions<ChatboxModel> options,Context context) {
        super(options);
        this.context = context;
    }
    @SuppressLint("SetTextI18n")
    @Override
    protected void onBindViewHolder(@NonNull ChatboxModelViewHolder holder, int position, @NonNull ChatboxModel model) {
        FirebaseUtil.getOtheruserFromChatbox(model.getUserIds())
                .get().addOnCompleteListener(task -> {
                    if(task.isSuccessful()) {
                        UserModel otherUserModel = task.getResult().toObject(UserModel.class);
                        holder.usernameText.setText(otherUserModel.getUsername());
                        holder.lastMessageText.setText(model.getLastMessage());
                        holder.lastMessageTime.setText(FirebaseUtil.timestampToString(model.getLastMesTimestamp()));
                    }
                });
    }
    @NonNull
    @Override
    public ChatboxModelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.chat_fragment_recycler_element,parent,false);
        return new ChatboxModelViewHolder(view);
    }

    static class ChatboxModelViewHolder extends RecyclerView.ViewHolder{

        TextView usernameText;
        TextView lastMessageText;
        TextView lastMessageTime;
        ImageView profilePic;
        public ChatboxModelViewHolder(@NonNull View itemView) {
            super(itemView);
            usernameText = itemView.findViewById(R.id.user_name_text);
            lastMessageText = itemView.findViewById(R.id.last_mes_text);
            lastMessageTime = itemView.findViewById(R.id.last_mes_time);
            profilePic = itemView.findViewById(R.id.profile_pic);
        }
    }
}

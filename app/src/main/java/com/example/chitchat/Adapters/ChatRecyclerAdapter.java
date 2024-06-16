package com.example.chitchat.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.AndroidException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chitchat.ChatActivity;
import com.example.chitchat.Models.ChatMessageModel;
import com.example.chitchat.R;
import com.example.chitchat.Utils.AndroidUtil;
import com.example.chitchat.Utils.FirebaseUtil;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

import org.w3c.dom.Text;

import java.util.Objects;

public class ChatRecyclerAdapter extends FirestoreRecyclerAdapter<ChatMessageModel, ChatRecyclerAdapter.ChatModelViewHolder> {

    Context context;
    public ChatRecyclerAdapter(@NonNull FirestoreRecyclerOptions<ChatMessageModel> options,Context context) {
        super(options);
        this.context = context;
    }
    @Override
    protected void onBindViewHolder(@NonNull ChatModelViewHolder holder, int position, @NonNull ChatMessageModel model) {
        if(model.getSenderId().equals((FirebaseUtil.currentUserId()))){
            holder.senderChatLayout.setVisibility(View.GONE);
            holder.recieverChatLayout.setVisibility(View.VISIBLE);
            holder.reciverChatText.setText(model.getMessage());
        }else{
            holder.recieverChatLayout.setVisibility(View.GONE);
            holder.senderChatLayout.setVisibility(View.VISIBLE);
            holder.senderChatText.setText(model.getMessage());
        }

    }
    @NonNull
    @Override
    public ChatModelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.chat_message_recycler_element,parent,false);
        return new ChatModelViewHolder(view);
    }

    static class ChatModelViewHolder extends RecyclerView.ViewHolder{
        LinearLayout senderChatLayout,recieverChatLayout;
        TextView senderChatText,reciverChatText;

        public ChatModelViewHolder(@NonNull View itemView) {
            super(itemView);
            senderChatLayout = itemView.findViewById(R.id.sender_chat_layout);
            recieverChatLayout = itemView.findViewById(R.id.reciever_chat_layout);
            senderChatText = itemView.findViewById(R.id.sender_chat_text);
            reciverChatText = itemView.findViewById(R.id.reciever_chat_text);
        }
    }
}

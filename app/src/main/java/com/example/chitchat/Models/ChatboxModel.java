package com.example.chitchat.Models;

import com.google.firebase.Timestamp;

import java.util.List;

public class ChatboxModel {
   String chatboxId;
   List<String> userIds;
   Timestamp lastMesTimestamp;
    String lastMesSenderId;
    String lastMessage;

    public ChatboxModel() {
    }

    public ChatboxModel(String chatboxId, List<String> userIds, Timestamp lastMesTimestamp, String lastMesSenderId) {
        this.chatboxId = chatboxId;
        this.userIds = userIds;
        this.lastMesTimestamp = lastMesTimestamp;
        this.lastMesSenderId = lastMesSenderId;
    }

    public String getChatboxId() {
        return chatboxId;
    }

    public void setChatboxId(String chatboxId) {
        this.chatboxId = chatboxId;
    }

    public List<String> getUserIds() {
        return userIds;
    }

    public void setUserIds(List<String> userIds) {
        this.userIds = userIds;
    }

    public Timestamp getLastMesTimestamp() {
        return lastMesTimestamp;
    }

    public void setLastMesTimestamp(Timestamp lastMesTimestamp) {
        this.lastMesTimestamp = lastMesTimestamp;
    }

    public String getLastMesSenderId() {
        return lastMesSenderId;
    }

    public void setLastMesSenderId(String lastMesSenderId) {
        this.lastMesSenderId = lastMesSenderId;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }
}

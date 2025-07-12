# 💬 ChitChat - Android Chatting App

Below is the vedio link of running application:(at 1.5x speed): 
https://github.com/amankumar0724/ChitChat/assets/128999710/3b2d484b-f809-480e-87e1-7da12d42e339

**ChitChat** is a sleek and lightweight real-time messaging app designed to simplify communication between users. Built with modern Android development practices, it offers seamless chat features, secure login, and an intuitive user experience.

---

## 📱 Tech Stack

- **Language**: Java  
- **Framework**: Android SDK  
- **Backend**: Firebase (Authentication, Firestore, Realtime Database)  
- **Push Notifications**: Firebase Cloud Messaging (FCM)  
- **Media Storage**: Firebase Storage  

---

## 🚀 Overview

ChitChat allows users to register, log in securely, and start chatting with other users instantly. With real-time sync and clean UI, it delivers a modern messaging experience optimized for Android devices.

---

## 🔑 Features

### 👤 Authentication

- Register and login with email/password  
- Firebase Authentication with secure session handling  
- Optionally supports Google Sign-In (if implemented)

### 💬 Messaging

- Send and receive messages in real-time using Firebase Realtime Database or Firestore  
- One-to-one chat with persistent history  
- Timestamps for every message  
- Typing indicator and read receipts (optional)

### 🖼️ Media Sharing (Optional if implemented)

- Send images in chats using Firebase Storage  
- Preview and zoom image support

### 🛠️ Additional Features

- User presence (online/offline)  
- Last seen tracking  
- Profile customization (name, profile picture)  
- Push notifications with Firebase Cloud Messaging (FCM)

---

## 📂 Folder Structure

- /app
- ├── activities # Login, Register, Chat, Main
- ├── adapters # RecyclerView adapters for chat messages
- ├── models # Data classes for User, Message, etc.
- ├── utils # Firebase helper classes, constants
- └── layouts # XML UI layouts


---

## 🧪 Getting Started

### Prerequisites

- Android Studio installed  
- Firebase project set up  
- Firebase SDK added to your project  

### Setup Instructions

1. Clone the repository:
   ```bash
   git clone https://github.com/amankumar0724/ChitChat.git


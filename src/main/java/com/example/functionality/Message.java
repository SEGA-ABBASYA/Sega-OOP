package com.example.functionality;

public interface Message {
    String getTitle();
    String getContent();
    String getDate();
    boolean isRead();
    void markAsRead();
    void sendMessage(Person p,Message n);


}

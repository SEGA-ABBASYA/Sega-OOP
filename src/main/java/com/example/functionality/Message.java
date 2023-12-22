package com.example.functionality;

import java.io.Serializable;

public abstract class Message implements Serializable
{
    protected Employee sender = null;
    boolean messageReadStatus;
    protected String content = null;
    protected String category = null;
    protected String date = null;

    public Employee getSender() {
        return sender;
    }


    public boolean getMessageReadStatus() {
        return messageReadStatus;
    }

    public void setAsRead() {
        this.messageReadStatus = true;
    }

    public String getContent() {
        return content;
    }



    public String getCategory() {
        return category;
    }



    public String getDate() {
        return date;
    }


}

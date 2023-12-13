package com.example.functionality;

public abstract class Message
{
    protected Employee sender = null;
    protected Account receiver = null;
    boolean messageReadStatus;
    protected String content = null;
    protected String category = null;
    protected String date = null;

    public Employee getSender() {
        return sender;
    }


    public Account getReceiver() {
        return receiver;
    }


    public boolean getMessageReadStatus() {
        return messageReadStatus;
    }

    public void setAtRead() {
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

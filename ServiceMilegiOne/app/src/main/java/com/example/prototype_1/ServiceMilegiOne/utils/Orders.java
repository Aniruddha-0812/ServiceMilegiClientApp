package com.example.prototype_1.ServiceMilegiOne.utils;

import com.google.firebase.Timestamp;

public  class Orders {

    private String job;
    private Timestamp messageTime;
    private  boolean cancel , isComplete;
   public Orders(){

    }

    public  Orders(String job , Timestamp messageTime, boolean cancel , boolean isComplete){

        this.job = job;
        this.cancel = cancel;
        this.isComplete = isComplete;
        this.messageTime = messageTime;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public boolean isCancel() {
        return cancel;
    }

    public void setCancel(boolean cancel) {
        this.cancel = cancel;
    }

    public Timestamp getMessageTime() {
        return messageTime;
    }

    public void setMessageTime(Timestamp messageTime) {
        this.messageTime = messageTime;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public void setComplete(boolean complete) {
        isComplete = complete;
    }
}

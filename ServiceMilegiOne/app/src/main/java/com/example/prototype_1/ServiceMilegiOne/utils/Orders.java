package com.example.prototype_1.ServiceMilegiOne.utils;

public  class Orders {

    private String job;
    private  boolean cancel , status;
   public Orders(){

    }

    public  Orders(String job , boolean cancel , boolean status){

        this.job = job;
        this.cancel = cancel;
        this.status = status;
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

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}

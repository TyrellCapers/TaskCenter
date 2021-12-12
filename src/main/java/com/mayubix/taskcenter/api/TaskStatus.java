package com.mayubix.taskcenter.api;

public class TaskStatus {

    private String id;
    private String status;
    private Task task;
    private Long createTime;

    public TaskStatus(){
        this.createTime = System.currentTimeMillis();
    }

    public Long getCreateTime(){
        return this.createTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

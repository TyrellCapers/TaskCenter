package com.mayubix.taskcenter.api;

public class TaskTag {
    private String id;
    private String name;
    private Task task;
    private Long createTime;

    public TaskTag(){
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

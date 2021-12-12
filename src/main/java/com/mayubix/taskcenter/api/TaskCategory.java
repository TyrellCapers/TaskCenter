package com.mayubix.taskcenter.api;

public class TaskCategory {

    private String id;
    private String name;
    private Task task;
    private Long createTime;

    public TaskCategory(){

    }

    public Long getCreateTime(){
        return this.createTime;
    }

    public String getId(){
        return this.id;
    }

    public void setId(String val){
        this.id = val;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String val){
        this.name = val;
    }

    public Task getTask(){
        return this.task;
    }

    public void setTask(Task val){
        this.task = val;
    }
}

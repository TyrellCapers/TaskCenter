package com.mayubix.taskcenter.api;

public class TaskStep {
    private String id;
    private String name;
    private String description;
    private Long createTime;
    private Task task;

    public TaskStep(){

    }

    public Task getTask(){
        return this.task;
    }

    public void setTask(Task val){
        this.task = val;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }
}

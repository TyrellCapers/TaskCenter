package com.mayubix.taskcenter.api;

public class TaskStatus {

    public static final String OBJECT_NAME = "TaskStatus";
    private static Integer s_objectCounter = 1;

    private String id;
    private String status;
    private Task task;
    private Long createTime;

    public TaskStatus(){
        //Initialize the createTime
        this.createTime = System.currentTimeMillis();

        //Initialize the id
        this.id = OBJECT_NAME + ":" + s_objectCounter;
        s_objectCounter++;
    }

    public Long getCreateTime(){
        return this.createTime;
    }

    public String getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

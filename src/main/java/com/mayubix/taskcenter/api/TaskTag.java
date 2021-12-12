package com.mayubix.taskcenter.api;

public class TaskTag {

    public static final String OBJECT_NAME = "TaskTag";
    private static Integer s_objectCounter = 1;

    private String id;
    private String name;
    private Task task;
    private Long createTime;

    public TaskTag(){
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

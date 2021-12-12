package com.mayubix.taskcenter.api;

public class TaskCategory {

    public static final String OBJECT_NAME = "TaskCategory";
    private static Integer s_objectCounter = 1;

    private String id;
    private String name;
    private Task task;
    private Long createTime;

    public TaskCategory(){
        //Initialize the createTime
        this.createTime = System.currentTimeMillis();

        //Initialize the id
        this.id = OBJECT_NAME + ":" + s_objectCounter;
        s_objectCounter++;
    }

    public Long getCreateTime(){
        return this.createTime;
    }

    public String getId(){
        return this.id;
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

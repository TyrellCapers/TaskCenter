package com.mayubix.taskcenter.api;

public class TaskTag {

    public static final String OBJECT_NAME = "TaskTag";
    private static Integer s_objectCounter = 1;

    private final String id;
    private String name;
    private Task task;
    private final Long createTime;

    public TaskTag(Task task){
        //Initialize task
        this.task = task;

        //Initialize the createTime
        this.createTime = System.currentTimeMillis();

        //Initialize the id
        this.id = OBJECT_NAME + ":" + s_objectCounter;
        s_objectCounter++;
    }

    public TaskTag(String id, Long createTime, Task task){
        //Initialize task
        this.task = task;

        //Initialize the createTime
        this.createTime = createTime;

        //Initialize the id
        this.id = id;
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

    public Task getTask(){
        return this.task;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString(){
        return this.name;
    }
}

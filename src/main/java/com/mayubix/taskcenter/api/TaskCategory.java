package com.mayubix.taskcenter.api;

public class TaskCategory {

    public static final String OBJECT_NAME = "TaskCategory";
    private static Integer s_objectCounter = 1;

    private final String id;
    private String name;
    private Task task;
    private final Long createTime;

    public TaskCategory(String id, Long createTime, Task task){
        //Initialize the task
        this.task = task;

        //Initialize the createTime
        this.createTime = createTime;

        //Initialize the id
        this.id = id;
        s_objectCounter++;

        //Default Category Name
        this.setName("");

    }

    public TaskCategory(Task task){
        //Initialize the task
        this.task = task;

        //Initialize the createTime
        this.createTime = System.currentTimeMillis();

        //Initialize the id
        this.id = OBJECT_NAME + ":" + s_objectCounter;
        s_objectCounter++;

        //Default Category Name
        this.setName("");

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

    @Override
    public String toString(){
        return this.getName();
    }
}

package com.mayubix.taskcenter.api;

import java.util.Date;

public class TaskNote {

    public static final String OBJECT_NAME = "TaskNote";
    private static Integer s_objectCounter = 1;

    private final String id;
    private String name;
    private String content;
    private final Long createTime;
    private Task task;

    public TaskNote(Task task){
        //Initialize task
        this.task = task;

        //Initialize the createTime
        this.createTime = System.currentTimeMillis();

        //Initialize the id
        this.id = OBJECT_NAME + ":" + s_objectCounter;
        s_objectCounter++;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }
}

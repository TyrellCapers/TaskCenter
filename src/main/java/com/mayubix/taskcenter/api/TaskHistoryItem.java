package com.mayubix.taskcenter.api;

import java.util.Date;

public class TaskHistoryItem {

    public static final String OBJECT_NAME = "TaskHistoryItem";
    private static Integer s_objectCounter = 1;

    private final String  id;
    private String  description;
    private String  eventName;
    private Long    eventTime;
    private Task    task;
    private final Long    createTime;

    public TaskHistoryItem(Task task){
        //Initialize task
        this.task = task;

        //Initialize the createTime
        this.createTime = System.currentTimeMillis();

        //Initialize the id
        this.id = OBJECT_NAME + ":" + s_objectCounter;
        s_objectCounter++;

    }

    public Long getCreateTime(){
        return this.createTime;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public Long getEventTime() {
        return eventTime;
    }

    public void setEventTime(Long eventTime) {
        this.eventTime = eventTime;
    }
}

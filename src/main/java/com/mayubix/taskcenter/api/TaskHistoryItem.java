package com.mayubix.taskcenter.api;

import java.util.Date;

public class TaskHistoryItem {

    private String  id;
    private String  description;
    private String  eventName;
    private Long    eventTime;
    private Task    task;
    private Long    createTime;

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

    public void setId(String id) {
        this.id = id;
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

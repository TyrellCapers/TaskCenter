package com.mayubix.taskcenter.api;

import java.util.Date;

public class TaskStatus {

    public static final String OBJECT_NAME = "TaskStatus";
    private static Integer s_objectCounter = 1;

    private final String id;
    private TaskStatusValue status;
    private Task task;
    private final Long createTime;

    public TaskStatus(Task task){
        //Initialize task
        this.task = task;

        //Initialize the createTime
        this.createTime = System.currentTimeMillis();

        //Initialize the id
        this.id = OBJECT_NAME + ":" + s_objectCounter;
        s_objectCounter++;
    }

    public void setTask(Task val){
        this.task = val;
    }

    public Task getTask(){
        return this.task;
    }

    public Long getCreateTime(){
        return this.createTime;
    }

    public String getId() {
        return id;
    }

    public TaskStatusValue getStatus() {
        return status;
    }

    public void setStatus(TaskStatusValue status) {
        TaskStatusValue origValue = this.status;
        this.status = status;

        //[2.2] When a task is completed, the task's completion date is automatically set to the current date
        if(origValue != TaskStatusValue.COMPLETED && this.status == TaskStatusValue.COMPLETED){
            this.task.setCompletionDate(new Date(System.currentTimeMillis()));
        }

        //[2.3] When a task status changes, history item is created on the task
        if(origValue != this.status){
            task.createHistoryItem("Status Changed", "Task status changed to " + this.status, System.currentTimeMillis());
        }
    }
}

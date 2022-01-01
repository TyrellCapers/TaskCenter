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

    public TaskStatus(Task task, TaskStatusValue value){
        //Initialize task
        this.task = task;

        //Initialize the createTime
        this.createTime = System.currentTimeMillis();

        //Initialize the id
        this.id = OBJECT_NAME + ":" + s_objectCounter;
        s_objectCounter++;

        //Set Status
        this.setStatus(value);
    }

    public TaskStatus(String id, Long createTime, Task task, String value){
        //Initialize task
        this.task = task;

        //Initialize the createTime
        this.createTime = createTime;

        //Initialize the id
        this.id = id;
        s_objectCounter++;

        //Set Status
        this.parseTaskStatusValue(value);
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
            this.task.setCompletionDate(System.currentTimeMillis());
        }

        //[2.3] When a task status changes, history item is created on the task
        if(origValue != this.status){
            task.createHistoryItem("Status Changed", "Task status changed to " + this.status, System.currentTimeMillis());
        }

        if(origValue != TaskStatusValue.NOT_STARTED && this.status == TaskStatusValue.NOT_STARTED){
            this.task.setTimeElapsed(0L);
            this.task.setTimeWorked(0L);
            this.task.setTimePending(0L);
        }
    }

    public void parseTaskStatusValue(String val){
        this.status = switch(val){
            case "Not Started" -> TaskStatusValue.NOT_STARTED;
            case "Idle"        -> TaskStatusValue.IDLE;
            case "Pending"     -> TaskStatusValue.PENDING;
            case "Working"     -> TaskStatusValue.WORKING;
            case "Completed"   -> TaskStatusValue.COMPLETED;
            default            -> TaskStatusValue.NOT_STARTED; //todo replace with logic to infer default value
        };
    }

    @Override
    public String toString(){
        return switch(this.getStatus()){
            case NOT_STARTED -> "Not Started";
            case IDLE        -> "Idle";
            case PENDING     -> "Pending";
            case WORKING     -> "Working";
            case COMPLETED   -> "Completed";
            default          -> "Unknown Status";
        };
    }

    public static String parseTaskStatusString(TaskStatusValue val){
        return switch(val){
            case NOT_STARTED -> "Not Started";
            case PENDING     -> "Pending";
            case COMPLETED   -> "Completed";
            case WORKING     -> "Working";
            case IDLE        -> "Idle";
        };
    }
}

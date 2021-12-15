package com.mayubix.taskcenter.api;

public class TaskStep {
    public static final String OBJECT_NAME = "TaskStep";
    private static Integer s_objectCounter = 1;

    private final String id;
    private String name;
    private String description;
    private final Long createTime;
    private TaskStepStatusValue statusValue;
    private Task task;

    public TaskStep(Task task){
        //Initialize task
        this.task = task;

        //Initialize the createTime
        this.createTime = System.currentTimeMillis();

        //Initialize the id
        this.id = OBJECT_NAME + ":" + s_objectCounter;
        s_objectCounter++;
    }

    public Task getTask(){
        return this.task;
    }

    public void setTask(Task val){
        this.task = val;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public TaskStepStatusValue getStatusValue() {
        return statusValue;
    }

    public void setStatusValue(TaskStepStatusValue statusValue) {
        this.statusValue = statusValue;
    }
}

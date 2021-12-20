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

    public TaskStep(String id, Long createTime, Task task){
        //Initialize task
        this.task = task;

        //Initialize the createTime
        this.createTime = createTime;

        //Initialize the id
        this.id = id;
        s_objectCounter++;

        //Initialize status value
        this.setStatusValue(TaskStepStatusValue.INCOMPLETE);
    }

    public TaskStep(Task task){
        //Initialize task
        this.task = task;

        //Initialize the createTime
        this.createTime = System.currentTimeMillis();

        //Initialize the id
        this.id = OBJECT_NAME + ":" + s_objectCounter;
        s_objectCounter++;

        //Initialize status value
        this.setStatusValue(TaskStepStatusValue.INCOMPLETE);
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

    public String getStatusValueString(){
        return switch(this.statusValue){
            case INCOMPLETE -> "Incomplete";
            case COMPLETE   -> "Complete";
            default         -> "No Value";
        };
    }

    public void parseStatusString(String val){
        this.statusValue = switch(val){
            case "Incomplete" -> TaskStepStatusValue.INCOMPLETE;
            case "Complete"   -> TaskStepStatusValue.COMPLETE;
            default           -> TaskStepStatusValue.INCOMPLETE;
        };
    }

    @Override
    public String toString(){
        return this.getName();
    }
}

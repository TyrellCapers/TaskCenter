package com.mayubix.taskcenter.api;

import java.util.Date;
import java.util.ArrayList;

public class Task {

    public static final String OBJECT_NAME = "Task";
    private static Integer s_objectCounter = 1;

    private String id;
    private String name;
    private String description;
    private Date targetDate;
    private Date createDate;
    private Long timeElapsed;
    private Long timeWorked;
    private Date completionDate;
    private Long timePassedTargetDate;
    private TaskStep currentStep;
    private ArrayList<TaskStep> steps;
    private ArrayList<TaskNote> notes;
    private Short size;
    private Short priority;
    private TaskStatus status;
    private ArrayList<TaskTag> tags;
    private Long timePending;
    private TaskCategory category;
    private ArrayList<TaskHistoryItem> taskHistoryItems;
    private Long createTime;

    public Task(){
        //Initialize the createTime
        this.createTime = System.currentTimeMillis();

        //Initialize the id
        this.id = OBJECT_NAME + ":" + s_objectCounter;
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

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getTargetDate() {
        return targetDate;
    }

    public void setTargetDate(Date targetDate) {
        this.targetDate = targetDate;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Long getTimeElapsed() {
        return timeElapsed;
    }

    public void setTimeElapsed(Long timeElapsed) {
        this.timeElapsed = timeElapsed;
    }

    public Long getTimeWorked() {
        return timeWorked;
    }

    public void setTimeWorked(Long timeWorked) {
        this.timeWorked = timeWorked;
    }

    public Date getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(Date completionDate) {
        this.completionDate = completionDate;
    }

    public Long getTimePassedTargetDate() {
        return timePassedTargetDate;
    }

    public void setTimePassedTargetDate(Long timePassedTargetDate) {
        this.timePassedTargetDate = timePassedTargetDate;
    }

    public TaskStep getCurrentStep() {
        return currentStep;
    }

    public void setCurrentStep(TaskStep currentStep) {
        this.currentStep = currentStep;
    }

    public Short getSize() {
        return size;
    }

    public void setSize(Short size) {
        this.size = size;
    }

    public Short getPriority() {
        return priority;
    }

    public void setPriority(Short priority) {
        this.priority = priority;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public Long getTimePending() {
        return timePending;
    }

    public void setTimePending(Long timePending) {
        this.timePending = timePending;
    }

    public TaskCategory getCategory() {
        return category;
    }

    public void setCategory(TaskCategory category) {
        this.category = category;
    }
}

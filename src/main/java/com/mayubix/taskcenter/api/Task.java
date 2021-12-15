package com.mayubix.taskcenter.api;

import com.mayubix.taskcenter.updateloops.TaskUpdateLoop;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Task {

    public static HashMap<String, Task> s_taskObjects = new HashMap<>(); //[Important Note]: Collection might prevent unreferenced tasks from being garbage collected.
    public static final Integer THREAD_POOL_SIZE = 10;
    public static final String OBJECT_NAME = "Task";
    public static final Long UPDATE_INTERVAL = 100L;

    private static Integer s_objectCounter = 1;
    private static final ScheduledThreadPoolExecutor s_updateWorkQueue = new ScheduledThreadPoolExecutor(THREAD_POOL_SIZE);

    private final String id;
    private String name;
    private String description;
    private Long targetDate;
    private Long timeElapsed;
    private Long timeWorked;
    private Long completionDate;
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
    private final Long createTime;
    private ScheduledFuture<?> future;

    public Task(){
        //Initialize the createTime
        this.createTime = System.currentTimeMillis();

        //Initialize the id
        this.id = OBJECT_NAME + ":" + s_objectCounter;
        s_objectCounter++;

        //Add to the s_taskObjects map
        s_taskObjects.put(this.id, this);

        //Schedule the Task Update Loop
        this.future = s_updateWorkQueue.scheduleWithFixedDelay(new TaskUpdateLoop(this,  UPDATE_INTERVAL), UPDATE_INTERVAL, UPDATE_INTERVAL, TimeUnit.MILLISECONDS);
    }

    public TaskStep createTaskStep(String name, String description){
        TaskStep step = new TaskStep(this);
        step.setName(name);
        step.setDescription(description);
        step.setStatusValue(TaskStepStatusValue.INCOMPLETE);
        this.steps.add(step);
        return step;
    }

    //Clean method should be run whenever a task is 'deleted'
    public void clean(){
        s_taskObjects.put(this.id, null);
        this.future.cancel(false);
    }

    public void createHistoryItem(String eventName, String description, Long eventTime){
        TaskHistoryItem item = new TaskHistoryItem(this);
        item.setEventName(eventName);
        item.setDescription(description);
        item.setEventTime(eventTime);
        taskHistoryItems.add(item);
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

    public Long getTargetDate() {
        return targetDate;
    }

    public void setTargetDate(Long targetDate) {
        this.targetDate = targetDate;
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

    public Long getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(Long completionDate) {
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

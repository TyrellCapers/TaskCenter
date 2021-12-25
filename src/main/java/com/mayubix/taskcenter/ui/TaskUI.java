package com.mayubix.taskcenter.ui;

import com.mayubix.taskcenter.TimeFormatter;
import com.mayubix.taskcenter.api.Task;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class TaskUI {
    private final StringProperty taskID;
    private final StringProperty name;
    private final StringProperty description;
    private final StringProperty targetDate;
    private final StringProperty timeElapsed;
    private final StringProperty timeWorked;
    private final StringProperty completionDate;
    private final StringProperty timePassedTargetDate;
    private final StringProperty currentStep;
    private final StringProperty size;
    private final StringProperty priority;
    private final StringProperty status;
    private final StringProperty timePending;
    private final StringProperty category;
    private final StringProperty createTime;

    private Task task;

    public TaskUI(){
        this.taskID = new SimpleStringProperty();
        this.name = new SimpleStringProperty();
        this.description = new SimpleStringProperty();
        this.targetDate = new SimpleStringProperty();
        this.timeElapsed = new SimpleStringProperty();
        this.timeWorked = new SimpleStringProperty();
        this.completionDate = new SimpleStringProperty();
        this.timePassedTargetDate = new SimpleStringProperty();
        this.currentStep = new SimpleStringProperty();
        this.size = new SimpleStringProperty();
        this.priority = new SimpleStringProperty();
        this.status = new SimpleStringProperty();
        this.timePending = new SimpleStringProperty();
        this.category = new SimpleStringProperty();
        this.createTime = new SimpleStringProperty();
    }

    public TaskUI(Task task){
        this.taskID = new SimpleStringProperty(task.getId());
        this.name = new SimpleStringProperty(task.getName());
        this.description = new SimpleStringProperty(task.getDescription());
        this.targetDate = new SimpleStringProperty(TimeFormatter.dateFormat(task.getTargetDate()));
        this.timeElapsed = new SimpleStringProperty(TimeFormatter.timerFormat(task.getTimeElapsed()));
        this.timeWorked = new SimpleStringProperty(TimeFormatter.timerFormat(task.getTimeWorked()));
        this.completionDate = new SimpleStringProperty(TimeFormatter.dateFormat(task.getCompletionDate()));
        this.timePassedTargetDate = new SimpleStringProperty(TimeFormatter.timerFormat(task.getTimePassedTargetDate()));
        this.currentStep = new SimpleStringProperty(task.getCurrentStep() != null ? task.getCurrentStep().toString() : "N/A");
        this.size = new SimpleStringProperty(task.getSize().toString());
        this.priority = new SimpleStringProperty(task.getPriority().toString());
        this.status = new SimpleStringProperty(task.getStatus().toString());
        this.timePending = new SimpleStringProperty(TimeFormatter.timerFormat(task.getTimePending()));
        this.category = new SimpleStringProperty(task.getCategory().toString());
        this.createTime = new SimpleStringProperty(TimeFormatter.dateFormat(task.getCreateTime()));

        this.task = task;
    }

    public void setTask(Task task){
        this.task = task;
    }

    public Task getTask(){
        return this.task;
    }

    public String getTaskID(){
        return taskID.get();
    }

    public void setTaskID(String val){
        this.taskID.set(val);
    }

    public StringProperty taskIDProperty(){
        return this.taskID;
    }

    public String getName(){
        return this.name.get();
    }

    public void setName(String val){
        this.name.set(val);
    }

    public StringProperty nameProperty(){
        return this.name;
    }

    public String getDescription(){
        return this.description.get();
    }

    public void setDescription(String val){
        this.description.set(val);
    }

    public StringProperty descriptionProperty(){
        return this.description;
    }

    public String getTargetDate(){
        return this.targetDate.get();
    }

    public void setTargetDate(String val){
        this.targetDate.set(val);
    }

    public StringProperty targetDateProperty(){
        return this.targetDate;
    }

    public String getTimeElapsed(){
        return this.timeElapsed.get();
    }

    public void setTimeElapsed(String val){
        this.timeElapsed.set(val);
    }

    public StringProperty timeElapsedProperty(){
        return this.timeElapsed;
    }

    public String getTimeWorked(){
        return this.timeWorked.get();
    }

    public void setTimeWorked(String val){
        this.timeWorked.set(val);
    }

    public StringProperty timeWorkedProperty(){
        return this.timeWorked;
    }

    public String getCompletionDate(){
        return this.completionDate.get();
    }

    public void setCompletionDate(String val){
        this.completionDate.set(val);
    }

    public StringProperty completionDateProperty(){
        return this.completionDate;
    }

    public String getTimePassedTargetDate(){
        return this.timePassedTargetDate.get();
    }

    public void setTimePassedTargetDate(String val){
        this.timePassedTargetDate.set(val);
    }

    public StringProperty timePassedTargetDateProperty(){
        return timePassedTargetDate;
    }

    public String getCurrentStep(){
        return this.currentStep.get();
    }

    public void setCurrentStep(String val){
        this.currentStep.set(val);
    }

    public StringProperty currentStepProperty(){
        return this.currentStep;
    }

    public String getSize(){
        return this.size.get();
    }

    public void setSize(String val){
        this.size.set(val);
    }

    public StringProperty sizeProperty(){
        return this.size;
    }

    public String getPriority(){
        return this.priority.get();
    }

    public void setPriority(String val){
        this.priority.set(val);
    }

    public StringProperty priorityProperty(){
        return this.priority;
    }

    public String getStatus(){
        return this.status.get();
    }

    public void setStatus(String val){
        this.status.set(val);
    }

    public StringProperty statusProperty(){
        return this.status;
    }

    public String getTimePending(){
        return this.timePending.get();
    }

    public void setTimePending(String val){
        this.timePending.set(val);
    }

    public StringProperty timePendingProperty(){
        return this.timePending;
    }

    public String getCategory(){
        return this.category.get();
    }

    public void setCategory(String val){
        this.category.set(val);
    }

    public StringProperty categoryProperty(){
        return this.category;
    }

    public String getCreateTime(){
        return this.createTime.get();
    }

    public void setCreateTime(String val){
        this.createTime.set(val);
    }

    public StringProperty createTimeProperty(){
        return this.createTime;
    }

    public void refresh(){
        this.setTaskID(task.getId());
        this.setName(task.getName());
        this.setDescription(task.getDescription());
        this.setTargetDate(TimeFormatter.dateFormat(task.getTargetDate()));
        this.setTimeElapsed(TimeFormatter.timerFormat(task.getTimeElapsed()));
        this.setTimeWorked(TimeFormatter.timerFormat(task.getTimeWorked()));
        this.setCompletionDate(TimeFormatter.dateFormat(task.getCompletionDate()));
        this.setTimePassedTargetDate(TimeFormatter.timerFormat(task.getTimePassedTargetDate()));
        this.setCurrentStep(task.getCurrentStep() != null ? task.getCurrentStep().toString() : "N/A");
        this.setSize(task.getSize().toString());
        this.setPriority(task.getPriority().toString());
        this.setStatus(task.getStatus().toString());
        this.setTimePending(TimeFormatter.timerFormat(task.getTimePending()));
        this.setCategory(task.getCategory().toString());
        this.setCreateTime(TimeFormatter.dateFormat(task.getCreateTime()));
    }


}

package com.mayubix.taskcenter.ui;

import com.mayubix.taskcenter.TimeFormatter;
import com.mayubix.taskcenter.api.Task;
import javafx.beans.property.SimpleStringProperty;

public class TaskUI {
    private SimpleStringProperty taskID;
    private SimpleStringProperty name;
    private SimpleStringProperty description;
    private SimpleStringProperty targetDate;
    private SimpleStringProperty timeElapsed;
    private SimpleStringProperty timeWorked;
    private SimpleStringProperty completionDate;
    private SimpleStringProperty timePassedTargetDate;
    private SimpleStringProperty currentStep;
    private SimpleStringProperty size;
    private SimpleStringProperty priority;
    private SimpleStringProperty status;
    private SimpleStringProperty timePending;
    private SimpleStringProperty category;
    private SimpleStringProperty createTime;

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
        this.currentStep = new SimpleStringProperty(task.getCurrentStep().toString());
        this.size = new SimpleStringProperty(task.getSize().toString());
        this.priority = new SimpleStringProperty(task.getPriority().toString());
        this.status = new SimpleStringProperty(task.getStatus().toString());
        this.timePending = new SimpleStringProperty(TimeFormatter.timerFormat(task.getTimePending()));
        this.category = new SimpleStringProperty(task.getCategory().toString());
        this.createTime = new SimpleStringProperty(TimeFormatter.dateFormat(task.getCreateTime()));
    }

    public String getTaskID(){
        return taskID.get();
    }

    public void setTaskID(String val){
        this.taskID.set(val);
    }

    public String getName(){
        return this.name.get();
    }

    public void setName(String val){
        this.name.set(val);
    }

    public String getDescription(){
        return this.description.get();
    }

    public void setDescription(String val){
        this.description.set(val);
    }

    public String getTargetDate(){
        return this.targetDate.get();
    }

    public void setTargetDate(String val){
        this.targetDate.set(val);
    }

    public String getTimeElapsed(){
        return this.timeElapsed.get();
    }

    public void setTimeElapsed(String val){
        this.timeElapsed.set(val);
    }

    public String getTimeWorked(){
        return this.timeWorked.get();
    }

    public void setTimeWorked(String val){
        this.timeWorked.set(val);
    }

    public String getCompletionDate(){
        return this.completionDate.get();
    }

    public void setCompletionDate(String val){
        this.completionDate.set(val);
    }

    public String getTimePassedTargetDate(){
        return this.timePassedTargetDate.get();
    }

    public void setTimePassedTargetDate(String val){
        this.timePassedTargetDate.set(val);
    }

    public String getCurrentStep(){
        return this.currentStep.get();
    }

    public void setCurrentStep(String val){
        this.currentStep.set(val);
    }

    public String getSize(){
        return this.size.get();
    }

    public void setSize(String val){
        this.size.set(val);
    }

    public String getPriority(){
        return this.priority.get();
    }

    public void setPriority(String val){
        this.priority.set(val);
    }

    public String getStatus(){
        return this.status.get();
    }

    public void setStatus(String val){
        this.status.set(val);
    }

    public String getTimePending(){
        return this.timePending.get();
    }

    public void setTimePending(String val){
        this.timePending.set(val);
    }

    public String getCategory(){
        return this.category.get();
    }

    public void setCategory(String val){
        this.category.set(val);
    }

    public String getCreateTime(){
        return this.createTime.get();
    }

    public void setCreateTime(String val){
        this.createTime.set(val);
    }


}

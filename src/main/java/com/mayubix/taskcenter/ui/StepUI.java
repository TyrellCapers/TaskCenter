package com.mayubix.taskcenter.ui;

import com.mayubix.taskcenter.TimeFormatter;
import com.mayubix.taskcenter.api.TaskStep;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class StepUI {
    private StringProperty stepId;
    private StringProperty stepName;
    private StringProperty stepDescription;
    private StringProperty stepStatus;
    private StringProperty createTime;

    private TaskStep step;

    public StepUI(TaskStep step){
        this.step = step;

        stepId   = new SimpleStringProperty(this.step.getId());
        stepName = new SimpleStringProperty(this.step.getName());
        stepDescription = new SimpleStringProperty(this.step.getDescription());
        stepStatus = new SimpleStringProperty(this.step.getStatusValueString());
        createTime = new SimpleStringProperty(TimeFormatter.dateFormat(this.step.getCreateTime()));
    }

    public TaskStep getStep(){
        return this.step;
    }

    public void setStepId(String stepId) {
        this.stepId.set(stepId);
    }

    public String getStepId(){
        return this.stepId.get();
    }

    public StringProperty stepIdProperty(){
        return this.stepId;
    }

    public void setStepName(String val){
        this.stepName.set(val);
    }

    public String getStepName(){
        return this.stepName.get();
    }

    public StringProperty stepNameProperty(){
        return this.stepName;
    }

    public void setStepDescription(String val){
        this.stepDescription.set(val);
    }

    public String getStepDescription(){
        return this.stepDescription.get();
    }

    public StringProperty stepDescriptionProperty(){
        return this.stepDescription;
    }

    public void setStepStatus(String val){
        this.stepStatus.set(val);
    }

    public String getStepStatus(){
        return this.stepStatus.get();
    }

    public StringProperty stepStatusProperty(){
        return this.stepStatus;
    }

    public void setCreateTime(String val){
        this.createTime.set(val);
    }

    public String getCreateTime(){
        return this.createTime.get();
    }

    public StringProperty createTimeProperty(){
        return this.createTime;
    }
}

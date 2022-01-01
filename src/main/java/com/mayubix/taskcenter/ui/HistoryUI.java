package com.mayubix.taskcenter.ui;

import com.mayubix.taskcenter.TimeFormatter;
import com.mayubix.taskcenter.api.TaskHistoryItem;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class HistoryUI {
    private StringProperty id;
    private StringProperty description;
    private StringProperty eventName;
    private StringProperty eventTime;
    private StringProperty createTime;

    private TaskHistoryItem historyItem;

    public HistoryUI(TaskHistoryItem historyItem){
        this.historyItem = historyItem;

        id = new SimpleStringProperty(historyItem.getId());
        description = new SimpleStringProperty(historyItem.getDescription());
        eventName = new SimpleStringProperty(historyItem.getEventName());
        eventTime = new SimpleStringProperty(TimeFormatter.dateFormat(historyItem.getEventTime()));
        createTime = new SimpleStringProperty(TimeFormatter.dateFormat(historyItem.getCreateTime()));
    }

    public TaskHistoryItem getHistoryItem(){
        return this.historyItem;
    }

    public String getId(){
        return this.id.get();
    }

    public void setId(String val){
        this.id.set(val);
    }

    public StringProperty idProperty(){
        return this.id;
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

    public String getEventName(){
        return this.eventName.get();
    }

    public void setEventName(String val){
        this.eventName.set(val);
    }

    public StringProperty eventNameProperty(){
        return this.eventName;
    }

    public String getEventTime(){
        return this.eventTime.get();
    }

    public void setEventTime(String val){
        this.eventTime.set(val);
    }

    public StringProperty eventTimeProperty(){
        return this.eventTime;
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






}

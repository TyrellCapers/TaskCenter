package com.mayubix.taskcenter.formmodels;

import com.mayubix.taskcenter.api.Task;
import com.mayubix.taskcenter.api.TaskList;
import com.mayubix.taskcenter.api.TaskStepStatusValue;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.ArrayList;
import java.util.List;

public class AddStepFormModel {
    private StringProperty nameProperty;
    private StringProperty descriptionProperty;
    private List<String>   statusList;
    private List<Task>     tasks;

    private TaskList taskList;

    public AddStepFormModel(TaskList taskList){
        nameProperty = new SimpleStringProperty();
        descriptionProperty = new SimpleStringProperty();

        statusList = new ArrayList<>();

        for(TaskStepStatusValue statusValue : TaskStepStatusValue.values()){
            statusList.add(statusValue.displayName());
        }

        this.taskList = taskList;
        tasks = new ArrayList<>();
        tasks.addAll(taskList.getTasks());

    }


    public StringProperty nameProperty(){
        return this.nameProperty;
    }

    public StringProperty descriptionProperty(){
        return this.descriptionProperty;
    }

    public List<String> statusList(){
        return this.statusList;
    }

    public List<Task> taskList(){
        return this.tasks;
    }



}

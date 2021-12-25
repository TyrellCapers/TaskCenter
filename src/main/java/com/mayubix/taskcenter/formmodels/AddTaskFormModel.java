package com.mayubix.taskcenter.formmodels;

import com.mayubix.taskcenter.api.TaskStatusValue;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AddTaskFormModel {
    private StringProperty nameProperty;
    private StringProperty descriptionProperty;
    private LocalDate targetDate;
    private IntegerProperty sizeProperty;
    private IntegerProperty priorityProperty;
    private List<String>    statusList;
    private StringProperty  categoryProperty;

    public AddTaskFormModel(){
        nameProperty = new SimpleStringProperty();
        descriptionProperty = new SimpleStringProperty();
        targetDate = LocalDate.now();
        sizeProperty = new SimpleIntegerProperty();
        priorityProperty = new SimpleIntegerProperty();

        statusList = new ArrayList<>();

        for(TaskStatusValue val : TaskStatusValue.values()){
            statusList.add(val.displayName());
        }

        categoryProperty = new SimpleStringProperty();
    }

    public StringProperty nameProperty(){
        return this.nameProperty;
    }

    public StringProperty descriptionProperty(){
        return this.descriptionProperty;
    }

    public LocalDate targetDate(){
        return this.targetDate;
    }

    public IntegerProperty sizeProperty(){
        return this.sizeProperty;
    }

    public IntegerProperty priorityProperty(){
        return this.priorityProperty;
    }


    public StringProperty categoryProperty(){
        return this.categoryProperty;
    }

    public List<String> statusList(){
        return this.statusList;
    }

}

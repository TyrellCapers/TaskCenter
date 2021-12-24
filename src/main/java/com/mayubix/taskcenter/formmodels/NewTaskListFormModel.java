package com.mayubix.taskcenter.formmodels;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class NewTaskListFormModel {
    private StringProperty name;

    public NewTaskListFormModel(){
        name = new SimpleStringProperty();
    }

    public StringProperty nameProperty(){
        return this.name;
    }
}

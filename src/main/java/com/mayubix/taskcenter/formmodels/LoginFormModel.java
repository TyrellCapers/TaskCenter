package com.mayubix.taskcenter.formmodels;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class LoginFormModel {
    private StringProperty username;
    private StringProperty password;

    public LoginFormModel(){
        username = new SimpleStringProperty();
        password = new SimpleStringProperty();
    }

    public StringProperty usernameProperty() {
        return username;
    }

    public StringProperty passwordProperty(){
        return password;
    }
}

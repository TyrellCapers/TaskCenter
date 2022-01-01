package com.mayubix.taskcenter.api;

public enum TaskStepStatusValue {
    INCOMPLETE ("Incomplete")
    , COMPLETE ("Complete");

    private String displayName;


    TaskStepStatusValue(String displayName){
        this.displayName = displayName;
    }

    public String displayName(){
        return this.displayName;
    }
}

package com.mayubix.taskcenter.api;

public enum TaskStatusValue {
    NOT_STARTED ("Not Started"),
    WORKING ("Working"),
    IDLE ("Idle"),
    PENDING ("Pending"),
    COMPLETED ("Completed");

    private final String displayName;

    TaskStatusValue(String displayName){
        this.displayName = displayName;
    }

    public String displayName(){
        return this.displayName;
    }

}

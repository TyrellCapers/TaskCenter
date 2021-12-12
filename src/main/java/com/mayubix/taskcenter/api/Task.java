package com.mayubix.taskcenter.api;

import java.util.Date;
import java.util.ArrayList;

public class Task {
    private String id;
    private String name;
    private String description;
    private Date targetDate;
    private Date createDate;
    private Integer timeElapsed;
    private Integer timeWorked;
    private Date completionDate;
    private Integer timePassedTargetDate;
    private TaskStep currentStep;
    private ArrayList<TaskStep> steps;
    private ArrayList<TaskNote> notes;

}

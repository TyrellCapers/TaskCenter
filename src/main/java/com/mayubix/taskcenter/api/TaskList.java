package com.mayubix.taskcenter.api;

import java.util.ArrayList;
import java.util.Date;

public class TaskList {
    public static final String OBJECT_NAME = "TaskList";
    private static Integer s_ObjectCounter = 1;

    private final String id;
    private final Long createTime;
    private ArrayList<Task> tasks;

    public TaskList(){
        this.id = OBJECT_NAME + ":" + s_ObjectCounter;
        s_ObjectCounter++;

        this.createTime = System.currentTimeMillis();

        tasks = new ArrayList<>();
    }

    public String getId(){
        return this.id;
    }

    public Long getCreateTime(){
        return this.createTime;
    }

    public ArrayList<Task> getTasks(){
        return this.tasks;
    }

    public Task addTask(String taskName){
        Task task = new Task();
        task.setName(taskName);
        tasks.add(task);
        return task;
    }

    public Task removeTask(Task task){
        tasks.remove(task);
        return task;
    }

    public void editTaskName(Task task, String newName){
        task.setName(newName);
    }

    public void editTaskDescription(Task task, String newDescription){
        task.setDescription(newDescription);
    }

    public void editTaskTargetDate(Task task, Long date){
        task.setTargetDate(date);
    }

    public void setTaskCurrentStep(Task task, TaskStep step){
        task.setCurrentStep(step);
    }

    public void editTaskSize(Task task, Short size){

    }

}

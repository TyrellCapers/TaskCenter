package com.mayubix.taskcenter.api;

import java.util.ArrayList;

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

    public TaskStep findStepById(Task task, String id){
        int count = 0;
        boolean found = false;
        TaskStep step = null;

        while(!found && count < task.getSteps().size()){
            step = task.getSteps().get(count);
            if(step.getId().equals(id)){
                found = true;
            }
            else{
                count++;
            }
        }

        return step;
    }

    public TaskNote findNoteById(Task task, String id){
        int count = 0;
        boolean found = false;
        TaskNote note = null;

        while(!found && count < task.getNotes().size()){
            note = task.getNotes().get(count);
            if(note.getId().equals(id)){
                found = true;
            }
            else{
                count++;
            }
        }

        return note;
    }

    public TaskHistoryItem findHistoryItemById(Task task, String id){
        int count = 0;
        boolean found = false;
        TaskHistoryItem historyItem = null;

        while(!found && count < task.getTaskHistoryItems().size()){
            historyItem = task.getTaskHistoryItems().get(count);
            if(historyItem.getId().equals(id)){
                found = true;
            }
            else{
                count++;
            }
        }

        return historyItem;
    }

    public TaskTag findTagById(Task task, String id){
        int count = 0;
        boolean found = false;
        TaskTag tag = null;

        while(!found && count < task.getTags().size()){
            tag = task.getTags().get(count);
            if(tag.getId().equals(id)){
                found = true;
            }
            else{
                count++;
            }
        }

        return tag;
    }

    public Task findTaskById(String id){
        return Task.s_taskObjects.get(id);
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
        task.setSize(size);
    }

    public void editTaskPriority(Task task, Short priority){
        task.setPriority(priority);
    }

    public void initTaskStatus(Task task, TaskStatusValue statusValue){
        TaskStatus status = new TaskStatus(task);
        status.setStatus(statusValue);
        task.setStatus(status);
    }

    public void initTaskCategory(Task task, String categoryValue){
        TaskCategory category = new TaskCategory(task);
        category.setName(categoryValue);
        task.setCategory(category);
    }

    public void addTaskStep(Task task, String name, String description){
        task.createTaskStep(name, description);
    }

    public void editTaskStepDescription(Task task, TaskStep taskStep, String description){
        ArrayList<TaskStep> steps = task.getSteps();
        int index = steps.indexOf(taskStep);
        TaskStep step = steps.get(index);
        step.setDescription(description);
    }

    public void editTaskStepName(Task task, TaskStep taskStep, String name){
        ArrayList<TaskStep> steps = task.getSteps();
        int index = steps.indexOf(taskStep);
        TaskStep step = steps.get(index);
        step.setName(name);
    }

    public void removeTaskStep(Task task, TaskStep taskStep){
        ArrayList<TaskStep> steps = task.getSteps();
        steps.remove(taskStep);
    }

    public void addTaskNote(Task task, String name, String content){
        task.createTaskNote(name, content);
    }

    public void removeTaskNote(Task task, TaskNote note){
        ArrayList<TaskNote> notes = task.getNotes();
        notes.remove(note);
    }

    public void addTaskTag(Task task, String name){
        task.createTaskTag(name);
    }

    public void removeTaskTag(Task task, TaskTag tag){
        task.getTags().remove(tag);
    }

    public void addTaskHistoryItem(Task task,  String eventName, String eventDescription){
        task.createHistoryItem(eventName, eventDescription, System.currentTimeMillis());
    }

    public void removeTaskHistoryItem(Task task, TaskHistoryItem item){
        task.getTaskHistoryItems().remove(item);
    }

}

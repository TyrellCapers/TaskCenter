package com.mayubix.taskcenter.ui;

public class TaskUIUpdateLoop implements Runnable {
    private final TaskUI taskUI;
    private final Long updateInterval;

    public TaskUIUpdateLoop(TaskUI taskUI, Long updateInterval){
        this.taskUI = taskUI;
        this.updateInterval = updateInterval;
    }

    public void run(){
        updateTaskUI(this.taskUI);
    }

    private void updateTaskUI(TaskUI taskUI){
        taskUI.refresh();
    }
}

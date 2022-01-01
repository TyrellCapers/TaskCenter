package com.mayubix.taskcenter.updateloops;
import com.mayubix.taskcenter.api.Task;
import com.mayubix.taskcenter.api.TaskStatusValue;

public class TaskUpdateLoop implements Runnable {
    private final Task task;
    private final Long updateInterval;

    public TaskUpdateLoop(Task task, Long updateInterval){
        this.task = task;
        this.updateInterval = updateInterval;
    }

    public void run(){
        updateTask(task);
    }

    //[3.1] The Update Loop Logic 1
    private void updateTask(Task task){
        //Calculate time elapsed
        task.setTimeElapsed(System.currentTimeMillis() - task.getCreateTime());

        //Calculate time pending and time worked
        if(task.getStatus().getStatus() == TaskStatusValue.PENDING){
            task.setTimePending(task.getTimePending() + updateInterval);
        }
        else if(task.getStatus().getStatus() == TaskStatusValue.WORKING){
            task.setTimeWorked(task.getTimeWorked() + updateInterval);
            task.setTimePending(0L);
        }
        else if(task.getStatus().getStatus() != TaskStatusValue.IDLE){
            task.setTimePending(0L);
        }

        //Calculate time passed target date
        task.setTimePassedTargetDate(System.currentTimeMillis() <= task.getTargetDate() ? 0 : System.currentTimeMillis() - task.getTargetDate());

    }
}

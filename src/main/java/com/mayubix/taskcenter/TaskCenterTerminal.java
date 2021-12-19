package com.mayubix.taskcenter;

import com.mayubix.taskcenter.api.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.HashMap;

public class TaskCenterTerminal {
    public static void main(String[] args){
        boolean run = true;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        TaskList taskList = new TaskList();

        HashMap<String, String> columnFormatMap = new HashMap<>();
        columnFormatMap.put("TaskID", "%-20.20s");
        columnFormatMap.put("Name", "%-30.30s");
        columnFormatMap.put("Description", "%-60.60s");
        columnFormatMap.put("TargetDate", "%-20.20s");
        columnFormatMap.put("TimeElapsed", "%-20.20s");
        columnFormatMap.put("TimeWorked", "%-20.20s");
        columnFormatMap.put("CompletionDate", "%-20.20s");
        columnFormatMap.put("TimePassedTargetDate", "%-40.40s");
        columnFormatMap.put("CurrentStep", "%-30.30s");
        columnFormatMap.put("Size", "%-20.20s");
        columnFormatMap.put("Priority", "%-20.20s");
        columnFormatMap.put("Status", "%-15.15s");
        columnFormatMap.put("TimePending", "%-20.20s");
        columnFormatMap.put("Category", "%-30.30s");
        columnFormatMap.put("CreateTime", "%-20.20s");

        while(run){
            try{
                System.out.print("TaskCenterTerminal$ ");
                String input = reader.readLine();
                String[] inputArgs = input.split(" ");

                String command = inputArgs[0];

                switch (command){
                    case "display_task_history_items" :
                        Task dthiTask = taskList.findTaskById(inputArgs[1]);

                        System.out.printf("%-20.20s %-50.50s %-100.100s %-20.20s %-20.20s %-20.20s\n",
                                "HistoryItemID", "EventName", "Description", "EventTime", "TaskID", "CreateTime");

                        for(TaskHistoryItem dthiItem : dthiTask.getTaskHistoryItems()){
                            System.out.printf("%-20.20s %-50.50s %-100.100s %-20.20s %-20.20s %-20.20s\n",
                                    dthiItem.getId(), dthiItem.getEventName(), dthiItem.getDescription(),
                                    TimeFormatter.dateFormat(dthiItem.getEventTime()), dthiItem.getTask().getId(),
                                    TimeFormatter.dateFormat(dthiItem.getCreateTime()));
                        }

                        break;

                    case "display_task_tags":
                        Task dttTask = taskList.findTaskById(inputArgs[1]);

                        System.out.printf("%-20.20s %-50.50s %-20.20s %-20.20s\n",
                                "TagID", "Name", "TaskID", "CreateTime");

                        for(TaskTag dttTag : dttTask.getTags()){
                            System.out.printf("%-20.20s %-50.50s %-20.20s %-20.20s\n",
                                    dttTag.getId(), dttTag.getName() ,dttTag.getTask().getId(), TimeFormatter.dateFormat(dttTag.getCreateTime()));
                        }

                        break;

                    case "display_task_notes" :
                        Task dtnTask = taskList.findTaskById(inputArgs[1]);

                        System.out.printf("%-20.20s %-50.50s %-100.100s %-20.20s %-20.20s\n",
                                "NoteID", "Name", "Content", "CreateTime", "Task");

                        for(TaskNote dtnNote : dtnTask.getNotes()){
                            System.out.printf("%-20.20s %-50.50s %-100.100s %-20.20s %-20.20s\n",
                                    dtnNote.getId(), dtnNote.getName(), dtnNote.getContent(),
                                    TimeFormatter.dateFormat(dtnNote.getCreateTime()), dtnNote.getTask().getId());
                        }

                        break;

                    case "display_task_steps" :
                        Task dtsTask = taskList.findTaskById(inputArgs[1]);

                        System.out.printf("%-20.20s %-50.50s %-100.100s %-20.20s %-20.20s %-20.20s\n",
                                "StepID", "Name", "Description", "CreateTime", "StatusValue", "TaskID");

                        for(TaskStep dtsStep : dtsTask.getSteps()){
                            System.out.printf("%-20.20s %-50.50s %-100.100s %-20.20s %-20.20s %-20.20s\n",
                                    dtsStep.getId(), dtsStep.getName(), dtsStep.getDescription(),
                                    TimeFormatter.dateFormat(dtsStep.getCreateTime()),
                                    dtsStep.getStatusValueString(), dtsStep.getTask().getId());
                        }

                        break;

                    case "remove_task_tag" :
                        Task rttTask = taskList.findTaskById(inputArgs[1]);
                        TaskTag rttTag = taskList.findTagById(rttTask, inputArgs[2]);
                        taskList.removeTaskTag(rttTask, rttTag);
                        break;

                    case "add_task_tag" :
                        Task attTask = taskList.findTaskById(inputArgs[1]);
                        taskList.addTaskTag(attTask, inputArgs[2]);
                        break;

                    case "remove_task_history_item" :
                        Task rthiTask = taskList.findTaskById(inputArgs[1]);
                        TaskHistoryItem rthiHistoryItem = taskList.findHistoryItemById(rthiTask, inputArgs[2]);
                        taskList.removeTaskHistoryItem(rthiTask, rthiHistoryItem);
                        break;

                    case "add_task_history_item" :
                        Task athiTask = taskList.findTaskById(inputArgs[1]);
                        taskList.addTaskHistoryItem(athiTask, inputArgs[2], inputArgs[3]);
                        break;

                    case "remove_task_note" :
                        Task rtnTask = taskList.findTaskById(inputArgs[1]);
                        TaskNote rtnNote = taskList.findNoteById(rtnTask, inputArgs[2]);
                        taskList.removeTaskNote(rtnTask, rtnNote);
                        break;

                    case "add_task_note" :
                        Task atnTask = taskList.findTaskById(inputArgs[1]);
                        taskList.addTaskNote(atnTask, inputArgs[2], inputArgs[3]);
                        break;

                    case "remove_task_step" :
                        Task rtsTask = taskList.findTaskById(inputArgs[1]);
                        TaskStep rtsTaskStep = taskList.findStepById(rtsTask, inputArgs[2]);
                        taskList.removeTaskStep(rtsTask, rtsTaskStep);
                        break;

                    case "edit_task_step_name" :
                        Task etsnTask = taskList.findTaskById(inputArgs[1]);
                        TaskStep etsnTaskStep = taskList.findStepById(etsnTask, inputArgs[2]);
                        taskList.editTaskStepName(etsnTask, etsnTaskStep, inputArgs[3]);
                        break;

                    case "edit_task_step_description" :
                        Task etsdTask = taskList.findTaskById(inputArgs[1]);
                        TaskStep etsdTaskStep = taskList.findStepById(etsdTask, inputArgs[2]);
                        taskList.editTaskStepDescription(etsdTask, etsdTaskStep, inputArgs[3]);
                        break;

                    case "add_task_step" :
                        Task atsTask = taskList.findTaskById(inputArgs[1]);
                        taskList.addTaskStep(atsTask, inputArgs[2], inputArgs[3]);
                        break;

                    case "edit_task_priority" :
                        Task etpTask = taskList.findTaskById(inputArgs[1]);
                        taskList.editTaskPriority(etpTask, Short.parseShort(inputArgs[2]));
                        break;

                    case "edit_task_size" :
                        Task etsTask = taskList.findTaskById(inputArgs[1]);
                        taskList.editTaskSize(etsTask, Short.parseShort(inputArgs[2]));
                        break;

                    case "set_task_current_step" :
                        Task stcsTask = taskList.findTaskById(inputArgs[1]);
                        TaskStep stcsStep = taskList.findStepById(stcsTask, inputArgs[2]);
                        taskList.setTaskCurrentStep(stcsTask, stcsStep);
                        break;

                    case "edit_task_target_date" :
                        taskList.editTaskTargetDate(taskList.findTaskById(inputArgs[1]), new Date(inputArgs[2]).getTime());
                        break;
                    case "edit_task_description" :
                        taskList.editTaskDescription(taskList.findTaskById(inputArgs[1]), inputArgs[2]);
                        break;
                    case "edit_task_name" :
                        taskList.editTaskName(taskList.findTaskById(inputArgs[1]), inputArgs[2]);
                        break;
                    case "remove_task" :
                         taskList.removeTask(taskList.findTaskById(inputArgs[1]));
                        break;

                    case "add_task" :
                        String taskName = inputArgs[1];
                        taskList.addTask(taskName);
                        break;

                    case "quit" :
                        run = false;
                        System.exit(0);
                        break;

                    case "say_hello":
                        System.out.println("Hello World!");
                        break;

                    case "display_tasks":
                        if(inputArgs.length > 1) {
                            String[] columnArray = new String[inputArgs.length - 1];
                            for (int i = 0; i < columnArray.length; i++) {
                                columnArray[i] = inputArgs[i + 1];
                            }

                            String[] formatStringArray = new String[columnArray.length];
                            for(int i = 0; i < formatStringArray.length; i++){
                                formatStringArray[i] = columnFormatMap.get(columnArray[i]);
                            }

                            StringBuilder formatStringBuilder = new StringBuilder();
                            for(String s : formatStringArray){
                                formatStringBuilder.append(s).append(" ");
                            }

                            System.out.printf( formatStringBuilder + "\n",
                                    columnArray);

                            for (Task task : taskList.getTasks()) {

                                HashMap<String, String> map = new HashMap<>();
                                map.put("TaskID", task.getId());
                                map.put("Name", task.getName());
                                map.put("Description", task.getDescription());
                                map.put("TargetDate", TimeFormatter.dateFormat(task.getTargetDate()));
                                map.put("TimeElapsed", TimeFormatter.timerFormat(task.getTimeElapsed()));
                                map.put("TimeWorked", TimeFormatter.timerFormat(task.getTimeWorked()));
                                map.put("CompletionDate", TimeFormatter.dateFormat(task.getCompletionDate()));
                                map.put("TimePassedTargetDate", TimeFormatter.timerFormat(task.getTimePassedTargetDate()));
                                map.put("CurrentStep", task.getCurrentStep() != null ? task.getCurrentStep().toString() : null);
                                map.put("Size", task.getSize().toString());
                                map.put("Priority", task.getPriority().toString());
                                map.put("Status", task.getStatus().toString());
                                map.put("TimePending", task.getStatus().toString());
                                map.put("Category", task.getCategory().toString());
                                map.put("CreateTime", TimeFormatter.dateFormat(task.getCreateTime()));

                                String[] dataArray = new String[columnArray.length];

                                for(int i = 0; i < columnArray.length; i++){
                                    dataArray[i] = map.get(columnArray[i]);
                                }


                                System.out.printf( formatStringBuilder + "\n",
                                        dataArray);
                            }
                            System.out.println("");


                        }else {

                        System.out.printf("%-20.20s  %-30.30s %-60.60s %-20.20s %-20.20s %-20.20s %-20.20s %-40.40s " +
                                "%-30.30s %-20.20s %-20.20s %-15.15s %-20.20s %-30.30s %-20.20s\n",
                                "TaskID" , "Name", "Description", "TargetDate", "TimeElapsed", "TimeWorked",
                                "CompletionDate", "TimePassedTargetDate", "CurrentStep", "Size",
                                "Priority", "Status", "TimePending", "Category", "CreateTime");


                            for (Task task : taskList.getTasks()) {
                                System.out.printf("%-20.20s %-30.30s %-60.60s %-20.20s %-20.20s %-20.20s %-20.20s %-40.40s " +
                                                "%-30.30s %-20.20s %-20.20s %-15.15s %-20.20s %-30.30s %-20.20s\n",
                                        task.getId(), task.getName(), task.getDescription(), TimeFormatter.dateFormat(task.getTargetDate()),
                                        TimeFormatter.timerFormat(task.getTimeElapsed()), TimeFormatter.timerFormat(task.getTimeWorked()),
                                        TimeFormatter.dateFormat(task.getCompletionDate()), TimeFormatter.timerFormat(task.getTimePassedTargetDate()), task.getCurrentStep(), task.getSize(),
                                        task.getPriority(), task.getStatus(), task.getTimePending(), task.getCategory(), TimeFormatter.dateFormat(task.getCreateTime()));
                            }
                            System.out.println("");
                        }
                        break;

                    default:
                        System.out.println("Invalid Input!");
                        break;
                }
            }
            catch(Exception e){
                e.printStackTrace(System.out);
            }
        }
    }
}

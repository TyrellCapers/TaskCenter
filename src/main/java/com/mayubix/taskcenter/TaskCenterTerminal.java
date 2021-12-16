package com.mayubix.taskcenter;

import com.mayubix.taskcenter.api.TaskList;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class TaskCenterTerminal {
    public static void main(String[] args){
        boolean run = true;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        TaskList taskList = new TaskList();

        while(run){
            try{
                String input = reader.readLine();
                String[] inputArgs = input.split(" ");

                String command = inputArgs[0];

                switch (command){
                    case "quit" :
                        run = false;
                        break;

                    case "say_hello":
                        System.out.println("Hello World!");
                        break;

                    case "display_tasks":
                        System.out.printf("%-30.30s %-60.60s %-20.20s %-20.20s %-20.20s %-20.20s %-20.20s " +
                                "%-30.30s %-20.20s %-20.20s %-15.15s %-20.20s %-30.30s %-20.20s",
                                "Name", "Description", "Target Date", "Time Elapsed", "Time Worked",
                                "Completion Date", "Time Passed Target Date", "Current Step", "Size",
                                "Priority", "Status", "Time Pending", "Category", "Create Time");
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

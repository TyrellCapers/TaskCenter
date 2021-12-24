package com.mayubix.taskcenter.scene;

import com.mayubix.taskcenter.api.Task;
import com.mayubix.taskcenter.ui.TaskUI;
import javafx.beans.property.Property;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Paint;

public class TaskListDashboard extends Scene {
    private GridPane layout;

    private ListView<String> actionListView;

    public TaskListDashboard(Parent root){
        super(root);
        layout = new GridPane();
        buildLayout();

        this.setRoot(layout);

    }

    public TaskListDashboard(Parent root, double width, double height){
        super(root, width, height);
        layout = new GridPane();
        buildLayout();

        this.setRoot(layout);
    }

    public TaskListDashboard(Parent root, double width, double height, boolean depthBuffer) {
        super(root, width, height, depthBuffer);
        layout = new GridPane();
        buildLayout();

        this.setRoot(layout);
    }

    public TaskListDashboard(Parent root, double width, double height, boolean depthBuffer, SceneAntialiasing antiAliasing){
        super(root, width, height, depthBuffer, antiAliasing);
        layout = new GridPane();
        buildLayout();

        this.setRoot(layout);
    }

    public TaskListDashboard(Parent root, double width, double height, Paint fill){
        super(root, width, height, fill);
        layout = new GridPane();
        buildLayout();

        this.setRoot(layout);
    }

    public TaskListDashboard(Parent root, Paint fill){
        super(root, fill);
        layout = new GridPane();
        buildLayout();

        this.setRoot(layout);
    }

    private void buildLayout(){
        //Action List View
        actionListView = new ListView<String>();

        actionListView.getItems().add("Sample Action");

        //Task List Table
        TableView<Task> tlTable = new TableView<>();
        layout.add(tlTable, 0, 0);

        TableColumn taskIDCol = new TableColumn("Task ID");
        taskIDCol.setCellValueFactory(new PropertyValueFactory<TaskUI, String>("taskID"));
        tlTable.getColumns().add(taskIDCol);

        TableColumn nameCol   = new TableColumn("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<TaskUI, String>("name"));
        tlTable.getColumns().add(nameCol);

        TableColumn descriptionCol = new TableColumn("Description");
        descriptionCol.setCellValueFactory(new PropertyValueFactory<TaskUI, String>("description"));
        tlTable.getColumns().add(descriptionCol);

        TableColumn targetDateCol = new TableColumn("Target Date");
        targetDateCol.setCellValueFactory(new PropertyValueFactory<TaskUI, String>("targetDate"));
        tlTable.getColumns().add(targetDateCol);

        TableColumn timeElapsedCol = new TableColumn("Time Elapsed");
        timeElapsedCol.setCellValueFactory(new PropertyValueFactory<TaskUI, String>("timeElapsed"));
        tlTable.getColumns().add(timeElapsedCol);

        TableColumn timeWorkedCol = new TableColumn("Time Worked");
        timeWorkedCol.setCellValueFactory(new PropertyValueFactory<TaskUI, String>("timeWorked"));
        tlTable.getColumns().add(timeWorkedCol);

        TableColumn completionDateCol = new TableColumn("Completion Date");
        completionDateCol.setCellValueFactory(new PropertyValueFactory<TaskUI, String>("completionDate"));
        tlTable.getColumns().add(completionDateCol);

        TableColumn timePassedTargetDateCol = new TableColumn("Time Passed Target Date");
        timePassedTargetDateCol.setCellValueFactory(new PropertyValueFactory<TaskUI, String>("timePassedTargetDate"));
        tlTable.getColumns().add(timePassedTargetDateCol);

        TableColumn currentStepCol = new TableColumn("Current Step");
        currentStepCol.setCellValueFactory(new PropertyValueFactory<TaskUI, String>("currentStep"));
        tlTable.getColumns().add(currentStepCol);

        TableColumn sizeCol = new TableColumn("Size");
        sizeCol.setCellValueFactory(new PropertyValueFactory<TaskUI, String>("size"));
        tlTable.getColumns().add(sizeCol);

        TableColumn priorityCol = new TableColumn("Priority");
        priorityCol.setCellValueFactory(new PropertyValueFactory<TaskUI, String>("priority"));
        tlTable.getColumns().add(priorityCol);

        TableColumn statusCol = new TableColumn("Status");
        statusCol.setCellValueFactory(new PropertyValueFactory<TaskUI, String>("status"));
        tlTable.getColumns().add(statusCol);

        TableColumn timePendingCol = new TableColumn("Time Pending");
        timePendingCol.setCellValueFactory(new PropertyValueFactory<TaskUI, String>("timePending"));
        tlTable.getColumns().add(timePendingCol);

        TableColumn categoryCol = new TableColumn("Category");
        categoryCol.setCellValueFactory(new PropertyValueFactory<TaskUI, String>("category"));
        tlTable.getColumns().add(categoryCol);

        TableColumn createTimeCol = new TableColumn("Create Time");
        createTimeCol.setCellValueFactory(new PropertyValueFactory<TaskUI, String>("createTime"));
        tlTable.getColumns().add(createTimeCol);


    }
}

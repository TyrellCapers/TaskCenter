package com.mayubix.taskcenter.scene;

import com.mayubix.taskcenter.api.Task;
import com.mayubix.taskcenter.api.TaskList;
import com.mayubix.taskcenter.ui.TaskUI;
import javafx.beans.property.Property;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.controlsfx.control.tableview2.TableView2;
import java.util.ArrayList;

public class TaskListDashboard extends Scene {
    private GridPane layout;
    private TableView tlTable;
    private TaskList selectedTaskList;
    private ArrayList<TaskList> taskLists = new ArrayList<>();
    private ListView<TaskList> tlListView = new ListView<>();
    private ListView<String> actionListView = new ListView<>();
    private Text selectedTaskListText;
    private GridPane footerPane;
    private Button executeActionBtn;

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
        //Selected Task List Text=======================================================================================
        selectedTaskListText = new Text();
        selectedTaskListText.setText(selectedTaskList != null ? "Selected Task List: " + selectedTaskList.getName()
                : "Selected Task List: N/A");

        //Task List List View===========================================================================================
        tlListView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
              selectedTaskList = tlListView.getSelectionModel().getSelectedItem();
              if(selectedTaskList != null){
                  selectedTaskListText.setText("Selected Task List: " + selectedTaskList.getName());
              }
              else{
                  selectedTaskListText.setText("Selected Task List: N/A");
              }
            }
        });


        //Action List View==============================================================================================
        actionListView.getItems().add("Add Task");
        actionListView.getItems().add("Remove Task");
        actionListView.getItems().add("Test Action");

        //Execute Action Button=========================================================================================
        executeActionBtn = new Button("Execute Action");
        executeActionBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String selectedAction = actionListView.getSelectionModel().getSelectedItem();
                if(selectedAction != null){
                    executeAction(selectedAction);
                }
            }
        });


        //Task List Table===============================================================================================
        tlTable = new TableView<TaskUI>();




        //Task List Table Columns=======================================================================================
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

        //Task List Table Context Menu==================================================================================
        ContextMenu tlTableContextMenu = new ContextMenu();
        tlTable.setContextMenu(tlTableContextMenu);

        MenuItem showHideColumnsItem = new MenuItem("Show/Hide Columns");
        showHideColumnsItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Stage eventStage = new Stage();
                eventStage.setTitle("Show/Hide Columns");
                eventStage.setScene(new VisibleColumnsPopup(new AnchorPane(), 300, 300, tlTable, eventStage));
                eventStage.show();
            }
        });

        tlTableContextMenu.getItems().addAll(showHideColumnsItem);

        //Menu Bar======================================================================================================
        MenuBar menuBar = new MenuBar();

        Menu fileMenu = new Menu("File");
        Menu newMenu  = new Menu("New");
        MenuItem newTaskListMenuItem = new MenuItem("Task List...");
        newMenu.getItems().addAll(newTaskListMenuItem);
        fileMenu.getItems().addAll(newMenu);

        menuBar.getMenus().add(fileMenu);

        //Menu Bar Actions==============================================================================================
        newTaskListMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
               Stage eventStage = new Stage();
               NewTaskListFormPopup formPopup = new NewTaskListFormPopup(new AnchorPane(), 300, 300, eventStage);
               eventStage.setTitle(NewTaskListFormPopup.TITLE);
               eventStage.setScene(formPopup);
               eventStage.showAndWait();

               if(formPopup.getRunTransaction()){
                   tlListView.getItems().add(formPopup.getTaskList());
               }

            }
        });

        //Footer Layout Grid Definition=================================================================================
        footerPane = new GridPane();
        footerPane.add(selectedTaskListText, 0, 0);

        //Footer Layout Column Constraints==============================================================================

        //Footer Layout Row Constraints=================================================================================



        //Main Layout Grid Definition===================================================================================
        layout.add(menuBar, 0, 0, 3, 1);

        layout.add(actionListView, 0, 1);
        layout.add(tlTable, 1, 1);
        layout.add(tlListView, 2, 1);

        layout.add(executeActionBtn, 0, 2);

        layout.add(footerPane, 0, 3);


        //Main Layout Column Constraints================================================================================

        ColumnConstraints column0 = new ColumnConstraints();
        column0.setHgrow(Priority.SOMETIMES);
        layout.getColumnConstraints().add(column0);

        ColumnConstraints column1 = new ColumnConstraints();
        column1.setHgrow(Priority.ALWAYS);
        layout.getColumnConstraints().add(column1);

        //Main Layout Row Constraints===================================================================================



    }

    private void executeAction(String actionName){
        Stage actionStage;
        switch(actionName){
            case "Add Task":
                actionStage = new Stage();
                AddTaskPopup addTaskPopup = new AddTaskPopup(new AnchorPane(), 500, 500, actionStage);
                actionStage.setTitle(AddTaskPopup.TITLE);
                actionStage.setScene(addTaskPopup);
                actionStage.showAndWait();

                if(addTaskPopup.getRunTransaction() && selectedTaskList != null){
                    selectedTaskList.getTasks().add(addTaskPopup.getNewTask());
                    refreshTaskTable();
                }

                break;
            case "Remove Task" :
                TaskUI selectedTaskUI = (TaskUI) tlTable.getSelectionModel().getSelectedItem();
                if(selectedTaskUI != null && selectedTaskList != null){
                    selectedTaskList.removeTask(selectedTaskUI.getTask());
                    selectedTaskUI.clean();
                    refreshTaskTable();
                }
                break;
            case "Test Action" :
                System.out.println("Test Action Executed...");
                break;
            default :
                break;
        }
    }

    private void refreshTaskTable(){
        if(selectedTaskList != null){
            tlTable.getItems().clear();
            for(Task t : selectedTaskList.getTasks()){
                tlTable.getItems().add(new TaskUI(t));
            }
        }
    }
}

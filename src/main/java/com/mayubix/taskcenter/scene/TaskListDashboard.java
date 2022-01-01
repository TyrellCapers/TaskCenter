package com.mayubix.taskcenter.scene;

import com.mayubix.taskcenter.api.*;
import com.mayubix.taskcenter.ui.HistoryUI;
import com.mayubix.taskcenter.ui.StepUI;
import com.mayubix.taskcenter.ui.TaskUI;
import javafx.beans.property.Property;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.controlsfx.control.tableview2.TableView2;

import java.io.File;
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

    private TabPane auxItemsTabPane;
    private Tab     stepsTab;
    private Tab     historyTab;
    private Tab     notesTab;
    private Tab     tagsTab;

    private TableView stepsTable;
    private TableView historyTable;

    private TextArea  notesTextArea;
    private ListView  notesListView;
    private Text      addNoteText;
    private TextField addNoteTextField;
    private Button    addNoteBtn;
    private GridPane  notesLayout;
    private TaskNote  currentNote;

    private ListView  tagsListView;
    private GridPane  tagsLayout;
    private Button    addTagBtn;
    private TextField addTagTextField;
    private Text      addTagText;

    private Button    slLoad;
    private Button    slSave;
    private Button    slSaveAll;
    private Button    slNew;
    private HBox      slLayout;


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
        //Aux Items TabPane=============================================================================================
        auxItemsTabPane = new TabPane();

        stepsTab = new Tab();
        stepsTab.setText("Steps");

        historyTab = new Tab();
        historyTab.setText("History");

        notesTab = new Tab();
        notesTab.setText("Notes");

        tagsTab = new Tab();
        tagsTab.setText("Tags");

        auxItemsTabPane.getTabs().addAll(stepsTab, historyTab, notesTab, tagsTab);

        auxItemsTabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

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
                  refreshTaskTable();
                  refreshStepTable();
                  refreshHistoryTable();
                  refreshNotesList();
                  refreshTagsList();
              }
              else{
                  selectedTaskListText.setText("Selected Task List: N/A");
              }
            }
        });


        //Action List View==============================================================================================
        actionListView.getItems().add("Add Task");
        actionListView.getItems().add("Add Task Step");
        actionListView.getItems().add("Remove Task");
        actionListView.getItems().add("Remove Task Step");
        actionListView.getItems().add("Set Task Not Started");
        actionListView.getItems().add("Set Task Idle");
        actionListView.getItems().add("Set Task Working");
        actionListView.getItems().add("Set Task Pending");
        actionListView.getItems().add("Set Task Completed");
        actionListView.getItems().add("Set Task Step Incomplete");
        actionListView.getItems().add("Set Task Step Complete");
        actionListView.getItems().add("Set Current Step");
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

        //TL Table On Clicked Action====================================================================================
        tlTable.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                refreshStepTable();
                refreshHistoryTable();
                refreshNotesList();
                refreshTagsList();
            }
        });

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



        //Steps Table===================================================================================================

        stepsTable = new TableView<StepUI>();

        TableColumn stepIdCol = new TableColumn("Step ID");
        stepIdCol.setCellValueFactory(new PropertyValueFactory<StepUI, String>("stepId"));
        stepsTable.getColumns().add(stepIdCol);

        TableColumn stepNameCol = new TableColumn("Step Name");
        stepNameCol.setCellValueFactory(new PropertyValueFactory<StepUI, String>("stepName"));
        stepsTable.getColumns().add(stepNameCol);

        TableColumn stepDescriptionCol = new TableColumn("Step Description");
        stepDescriptionCol.setCellValueFactory(new PropertyValueFactory<StepUI, String>("stepDescription"));
        stepsTable.getColumns().add(stepDescriptionCol);

        TableColumn stepStatusCol = new TableColumn("Step Status");
        stepStatusCol.setCellValueFactory(new PropertyValueFactory<StepUI, String>("stepStatus"));
        stepsTable.getColumns().add(stepStatusCol);

        TableColumn stepCreateTimeCol = new TableColumn("Create Time");
        stepCreateTimeCol.setCellValueFactory(new PropertyValueFactory<StepUI, String>("createTime"));
        stepsTable.getColumns().add(stepCreateTimeCol);

        //History Table=================================================================================================
        historyTable = new TableView<HistoryUI>();

        TableColumn historyIdCol = new TableColumn("ID");
        historyIdCol.setCellValueFactory(new PropertyValueFactory<HistoryUI, String>("id"));
        historyTable.getColumns().add(historyIdCol);

        TableColumn historyDescriptionCol = new TableColumn("Description");
        historyDescriptionCol.setCellValueFactory(new PropertyValueFactory<HistoryUI, String>("description"));
        historyTable.getColumns().add(historyDescriptionCol);

        TableColumn historyEventNameCol = new TableColumn("Event Name");
        historyEventNameCol.setCellValueFactory(new PropertyValueFactory<HistoryUI, String>("eventName"));
        historyTable.getColumns().add(historyEventNameCol);

        TableColumn historyEventTimeCol = new TableColumn("Event Time");
        historyEventTimeCol.setCellValueFactory(new PropertyValueFactory<HistoryUI, String>("eventTime"));
        historyTable.getColumns().add(historyEventTimeCol);

        TableColumn historyCreateTimeCol = new TableColumn("Create Time");
        historyCreateTimeCol.setCellValueFactory(new PropertyValueFactory<HistoryUI, String>("createTime"));
        historyTable.getColumns().add(historyCreateTimeCol);

        //Notes Pane====================================================================================================
        notesTextArea    = new TextArea();
        notesListView    = new ListView<TaskNote>();
        addNoteText      = new Text("Add Note: ");
        addNoteTextField = new TextField();
        addNoteBtn       = new Button("Add");
        notesLayout      = new GridPane();

        notesLayout.add(notesListView, 0, 0, 3, 3);
        notesLayout.add(notesTextArea, 0, 4, 3, 3);
        notesLayout.add(addNoteText, 4, 0);
        notesLayout.add(addNoteTextField, 4, 1);
        notesLayout.add(addNoteBtn, 5, 1);

        addNoteBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                TaskUI selectedUI   = (TaskUI) tlTable.getSelectionModel().getSelectedItem();
                if(selectedUI != null){
                    Task selectedTask = selectedUI.getTask();
                    selectedTask.createTaskNote(addNoteTextField.getText(), "");
                    refreshNotesList();
                }
            }
        });

        notesListView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
              if(currentNote != null){
                  currentNote.setContent(notesTextArea.getText());
              }

              TaskNote newNote = (TaskNote) notesListView.getSelectionModel().getSelectedItem();
              if(newNote != null){
                  notesTextArea.setText(newNote.getContent());
                  currentNote = newNote;
              }
            }
        });

        //Tags List View================================================================================================
        tagsListView    = new ListView<TaskTag>();
        tagsLayout      = new GridPane();
        addTagBtn       = new Button("Add");
        addTagText      = new Text("Add Tag:");
        addTagTextField = new TextField();

        tagsLayout.add(tagsListView, 0, 0, 3, 3);
        tagsLayout.add(addTagText, 4, 0 );
        tagsLayout.add(addTagTextField, 4, 1);
        tagsLayout.add(addTagBtn, 5, 1);

        addTagBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                TaskUI selectedUI   = (TaskUI) tlTable.getSelectionModel().getSelectedItem();
                if(selectedUI != null){
                    Task selectedTask = selectedUI.getTask();
                    selectedTask.createTaskTag(addTagTextField.getText());
                    refreshTagsList();
                }
            }
        });

        //Tab Pane Content==============================================================================================
        stepsTab.setContent(stepsTable);
        historyTab.setContent(historyTable);
        notesTab.setContent(notesLayout);
        tagsTab.setContent(tagsLayout);

        //Save Load Box=================================================================================================
        slLoad = new Button("Load");
        slSave = new Button("Save");
        slSaveAll = new Button("Save All");
        slNew = new Button("New");
        slLayout = new HBox();


        slLayout.getChildren().addAll(slLoad, slSave, slSaveAll, slNew);

        slLoad.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Load Task List");
                File selectedFile = fileChooser.showOpenDialog(null);
                if(selectedFile != null) {
                    System.out.println("Loading newTaskList");
                    TaskList newTaskList = TaskList.loadFromXML(selectedFile.getAbsolutePath());
                    System.out.println("New Task List Name: " + newTaskList.getName());
                    tlListView.getItems().add(newTaskList);
                    System.out.println("Number of items in tlListView..." + tlListView.getItems().size());
                }
            }
        });

        slSave.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(selectedTaskList != null){
                    FileChooser fileChooser = new FileChooser();
                    fileChooser.setTitle("Save Task List");
                    File selectedFile = fileChooser.showSaveDialog(null);
                    if(selectedFile != null){
                        selectedTaskList.saveToXML(selectedFile.getAbsolutePath());
                    }
                }
            }
        });

        slNew.setOnAction(new EventHandler<ActionEvent>() {
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
        layout.add(auxItemsTabPane, 1, 2);
        layout.add(slLayout, 2, 2);

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
        TaskUI selectedTaskUI;
        StepUI selectedStepUI;
        TaskStep   selectedStep;
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
                selectedTaskUI = (TaskUI) tlTable.getSelectionModel().getSelectedItem();
                if(selectedTaskUI != null && selectedTaskList != null){
                    selectedTaskList.removeTask(selectedTaskUI.getTask());
                    selectedTaskUI.clean();
                    refreshTaskTable();
                }
                break;

            case "Remove Task Step" :
                selectedStepUI = (StepUI) stepsTable.getSelectionModel().getSelectedItem();
                if(selectedStepUI != null){
                    selectedStep = selectedStepUI.getStep();
                    selectedStep.getTask().getSteps().remove(selectedStep);
                    selectedStep.clean();
                    refreshStepTable();
                }
                break;

            case "Set Task Not Started" :
                selectedTaskUI = (TaskUI) tlTable.getSelectionModel().getSelectedItem();
                if(selectedTaskUI != null && selectedTaskList != null){
                    selectedTaskUI.getTask().getStatus().setStatus(TaskStatusValue.NOT_STARTED);
                }
                break;
            case "Set Task Idle":
                selectedTaskUI = (TaskUI) tlTable.getSelectionModel().getSelectedItem();
                if(selectedTaskUI != null && selectedTaskList != null){
                    selectedTaskUI.getTask().getStatus().setStatus(TaskStatusValue.IDLE);
                }
                break;
            case "Set Task Working" :
                selectedTaskUI = (TaskUI) tlTable.getSelectionModel().getSelectedItem();
                if(selectedTaskUI != null && selectedTaskList != null){
                    selectedTaskUI.getTask().getStatus().setStatus(TaskStatusValue.WORKING);
                }
                break;
            case "Set Task Pending" :
                selectedTaskUI = (TaskUI) tlTable.getSelectionModel().getSelectedItem();
                if(selectedTaskUI != null && selectedTaskList != null){
                    selectedTaskUI.getTask().getStatus().setStatus(TaskStatusValue.PENDING);
                }
                break;
            case "Set Task Completed" :
                selectedTaskUI = (TaskUI) tlTable.getSelectionModel().getSelectedItem();
                if(selectedTaskUI != null && selectedTaskList != null){
                    selectedTaskUI.getTask().getStatus().setStatus(TaskStatusValue.COMPLETED);
                }
                break;
            case "Add Task Step" :
                actionStage = new Stage();
                AddStepPopup addStepPopup = new AddStepPopup(new AnchorPane(), 500, 500, actionStage, selectedTaskList);
                actionStage.setTitle(AddStepPopup.TITLE);
                actionStage.setScene(addStepPopup);
                actionStage.showAndWait();
                refreshStepTable();
                break;
            case "Set Task Step Incomplete" :

                selectedStepUI = (StepUI) stepsTable.getSelectionModel().getSelectedItem();
                if(selectedStepUI != null){
                    selectedStep = selectedStepUI.getStep();
                    selectedStep.setStatusValue(TaskStepStatusValue.INCOMPLETE);
                    refreshStepTable();
                }
                break;
            case "Set Task Step Complete" :

                selectedStepUI = (StepUI) stepsTable.getSelectionModel().getSelectedItem();
                if(selectedStepUI != null){
                    selectedStep = selectedStepUI.getStep();
                    selectedStep.setStatusValue(TaskStepStatusValue.COMPLETE);
                    refreshStepTable();
                }
                break;

            case "Set Current Step" :
                selectedStepUI = (StepUI) stepsTable.getSelectionModel().getSelectedItem();
                selectedTaskUI = (TaskUI) tlTable.getSelectionModel().getSelectedItem();
                if(selectedStepUI != null && selectedTaskUI != null && selectedTaskList != null){
                    selectedStep = selectedStepUI.getStep();
                    selectedTaskList.setTaskCurrentStep(selectedTaskUI.getTask(), selectedStep);
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
            for(Object obj : tlTable.getItems()){
                TaskUI ui = (TaskUI) obj;
                ui.clean();
            }
            tlTable.getItems().clear();
            for(Task t : selectedTaskList.getTasks()){
                tlTable.getItems().add(new TaskUI(t));
            }
        }
    }

    private void refreshStepTable(){
        TaskUI l_selectedTaskUI = ((TaskUI) tlTable.getSelectionModel().getSelectedItem());
        if(l_selectedTaskUI != null){
            stepsTable.getItems().clear();
            for(TaskStep s :  l_selectedTaskUI.getTask().getSteps()){
                stepsTable.getItems().add(new StepUI(s));
            }
        }

    }

    private void refreshHistoryTable(){
        TaskUI l_selectedTaskUI = ((TaskUI) tlTable.getSelectionModel().getSelectedItem());
        if(l_selectedTaskUI != null){
            historyTable.getItems().clear();
            for(TaskHistoryItem h :  l_selectedTaskUI.getTask().getTaskHistoryItems()){
                historyTable.getItems().add(new HistoryUI(h));
            }
        }
    }

    private void refreshNotesList(){
        if(currentNote != null) {
            currentNote.setContent(notesTextArea.getText());
        }

        notesListView.getItems().clear();
        notesTextArea.setText("");
        addNoteTextField.setText("");
        currentNote = null;

        TaskUI l_selectedTaskUI = (TaskUI) tlTable.getSelectionModel().getSelectedItem();
        if(l_selectedTaskUI != null){
            Task selectedTask = l_selectedTaskUI.getTask();
            for(TaskNote note : selectedTask.getNotes()){
                notesListView.getItems().add(note);
            }
        }
    }

    private void refreshTagsList(){
        tagsListView.getItems().clear();
        addTagTextField.setText("");

        TaskUI l_selectedTaskUI = (TaskUI) tlTable.getSelectionModel().getSelectedItem();
        if(l_selectedTaskUI != null){
            Task selectedTask = l_selectedTaskUI.getTask();
            for(TaskTag tag : selectedTask.getTags()){
                tagsListView.getItems().add(tag);
            }
        }

    }
}

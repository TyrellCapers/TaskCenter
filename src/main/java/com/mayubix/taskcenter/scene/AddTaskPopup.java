package com.mayubix.taskcenter.scene;

import com.dlsc.formsfx.model.structure.Field;
import com.dlsc.formsfx.model.structure.Form;
import com.dlsc.formsfx.model.structure.Group;
import com.dlsc.formsfx.model.structure.SingleSelectionField;
import com.dlsc.formsfx.view.renderer.FormRenderer;
import com.mayubix.taskcenter.api.Task;
import com.mayubix.taskcenter.api.TaskStatus;
import com.mayubix.taskcenter.formmodels.AddTaskFormModel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;

public class AddTaskPopup extends Scene {
    public static final String TITLE = "Add Task...";

    private GridPane layout;
    private Stage stage;

    private Form form;
    private Button okBtn;
    private Button cancelBtn;
    private AddTaskFormModel model;
    private Boolean runTransaction;

    private Task newTask;

    public AddTaskPopup(Parent root, Stage stage){
        super(root);
        layout = new GridPane();
        this.setRoot(layout);
        this.stage = stage;
        initialize();
        buildLayout();

    }

    public AddTaskPopup(Parent root, double width, double height, Stage stage){
        super(root, width, height);
        layout = new GridPane();
        this.setRoot(layout);
        this.stage = stage;
        initialize();
        buildLayout();
    }

    public AddTaskPopup(Parent root, double width, double height, boolean depthBuffer, Stage stage) {
        super(root, width, height, depthBuffer);
        layout = new GridPane();
        this.setRoot(layout);
        this.stage = stage;
        initialize();
        buildLayout();

    }

    public AddTaskPopup(Parent root, double width, double height, boolean depthBuffer, SceneAntialiasing antiAliasing, Stage stage){
        super(root, width, height, depthBuffer, antiAliasing);
        layout = new GridPane();
        this.setRoot(layout);
        this.stage = stage;
        initialize();
        buildLayout();

    }

    public AddTaskPopup(Parent root, double width, double height, Paint fill, Stage stage){
        super(root, width, height, fill);
        layout = new GridPane();
        this.setRoot(layout);
        this.stage = stage;
        initialize();
        buildLayout();

    }

    public AddTaskPopup(Parent root, Paint fill, Stage stage){
        super(root, fill);
        layout = new GridPane();
        this.setRoot(layout);
        this.stage = stage;
        initialize();
        buildLayout();

    }

    private void initialize(){
        //Run Transaction===============================================================================================
        runTransaction = false;

        //Form==========================================================================================================
        model = new AddTaskFormModel();

        form = Form.of(
                Group.of(
                        Field.ofStringType(model.nameProperty())
                                .label("Name: "),
                        Field.ofStringType(model.descriptionProperty())
                                .label("Description: "),
                        Field.ofDate(model.targetDate())
                                .label("Target Date: "),
                        Field.ofIntegerType(model.sizeProperty())
                                .label("Size: "),
                        Field.ofIntegerType(model.priorityProperty())
                                .label("Priority: "),
                        Field.ofSingleSelectionType(model.statusList())
                                .label("Status: "),
                        Field.ofStringType(model.categoryProperty())
                                .label("Category: ")
                )
        );

        //OK Button=====================================================================================================
        okBtn = new Button("OK");
        okBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                newTask = new Task();
                SingleSelectionField<String> statusField = null;

                for(Field f : form.getFields()){
                    f.persist();
                    if(f.getLabel().equals("Status: ")){
                        statusField = (SingleSelectionField<String>) f;
                    }
                }

                newTask.setName(model.nameProperty().get());
                newTask.setDescription(model.descriptionProperty().get());
                newTask.setTargetDate(model.targetDate());
                System.out.println("Target Date Value: " + newTask.getTargetDate());
                newTask.setSize(model.sizeProperty().getValue().shortValue());
                newTask.setPriority(model.priorityProperty().getValue().shortValue());
                newTask.setStatus(new TaskStatus(newTask));
                newTask.getStatus().parseTaskStatusValue(statusField.getSelection());
                newTask.getCategory().setName(model.categoryProperty().get());

                runTransaction = true;
                stage.close();
            }
        });

        //Cancel Button=================================================================================================
        cancelBtn = new Button("Cancel");
        cancelBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                runTransaction = false;
                stage.close();
            }
        });
    }

    private void buildLayout(){
        //Grid Setup====================================================================================================
        layout.add(new FormRenderer(form), 0, 0);
        layout.add(cancelBtn,0, 1);
        layout.add(okBtn, 1, 1);

        //Column Constraints============================================================================================


    }

    public Task getNewTask(){
        return this.newTask;
    }

    public Boolean getRunTransaction(){
        return this.runTransaction;
    }
}

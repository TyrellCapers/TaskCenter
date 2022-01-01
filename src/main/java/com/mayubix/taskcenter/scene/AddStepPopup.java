package com.mayubix.taskcenter.scene;

import com.dlsc.formsfx.model.structure.Field;
import com.dlsc.formsfx.model.structure.Form;
import com.dlsc.formsfx.model.structure.Group;
import com.dlsc.formsfx.model.structure.SingleSelectionField;
import com.dlsc.formsfx.view.renderer.FormRenderer;
import com.mayubix.taskcenter.api.Task;
import com.mayubix.taskcenter.api.TaskList;
import com.mayubix.taskcenter.api.TaskStep;
import com.mayubix.taskcenter.api.TaskStepStatusValue;
import com.mayubix.taskcenter.formmodels.AddStepFormModel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

public class AddStepPopup extends Scene {
    public static final String TITLE = "Add Step...";

    private GridPane layout;
    private Stage stage;

    private Form form;
    private Button okBtn;
    private Button cancelBtn;
    private AddStepFormModel model;
    private Boolean runTransaction;

    private TaskList taskList;
    private TaskStep newStep;

    public AddStepPopup(Parent root, Stage stage, TaskList taskList){
        super(root);
        this.taskList = taskList;
        layout = new GridPane();
        this.setRoot(layout);
        this.stage = stage;
        initialize();
        buildLayout();

    }

    public AddStepPopup(Parent root, double width, double height, Stage stage, TaskList taskList){
        super(root, width, height);
        this.taskList = taskList;
        layout = new GridPane();
        this.setRoot(layout);
        this.stage = stage;
        initialize();
        buildLayout();
    }

    public AddStepPopup(Parent root, double width, double height, boolean depthBuffer, Stage stage, TaskList taskList) {
        super(root, width, height, depthBuffer);
        this.taskList = taskList;
        layout = new GridPane();
        this.setRoot(layout);
        this.stage = stage;
        initialize();
        buildLayout();

    }

    public AddStepPopup(Parent root, double width, double height, boolean depthBuffer, SceneAntialiasing antiAliasing, Stage stage, TaskList taskList){
        super(root, width, height, depthBuffer, antiAliasing);
        this.taskList = taskList;
        layout = new GridPane();
        this.setRoot(layout);
        this.stage = stage;
        initialize();
        buildLayout();

    }

    public AddStepPopup(Parent root, double width, double height, Paint fill, Stage stage, TaskList taskList){
        super(root, width, height, fill);
        this.taskList = taskList;
        layout = new GridPane();
        this.setRoot(layout);
        this.stage = stage;
        initialize();
        buildLayout();

    }

    public AddStepPopup(Parent root, Paint fill, Stage stage, TaskList taskList){
        super(root, fill);
        this.taskList = taskList;
        layout = new GridPane();
        this.setRoot(layout);
        this.stage = stage;
        initialize();
        buildLayout();

    }

    private void initialize(){
        //Run Transaction===============================================================================================
        this.runTransaction = false;

        //Form==========================================================================================================
        model = new AddStepFormModel(this.taskList);

        form = Form.of(
                Group.of(
                        Field.ofStringType(model.nameProperty())
                            .label("Name"),
                        Field.ofStringType(model.descriptionProperty())
                            .label("Description"),
                        Field.ofSingleSelectionType(model.taskList())
                            .label("Task")
                )
        );

        //OK Btn========================================================================================================
        okBtn = new Button("OK");
        okBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Task selectedTask = null;
                String selectedStatus = null;

                for(Field f : form.getFields()){
                    f.persist();
                    if(f.getLabel().equals("Task")){
                        selectedTask = ((SingleSelectionField<Task>) f).getSelection();
                    }

                }


                if(selectedTask != null) {
                    selectedTask.createTaskStep(model.nameProperty().get(), model.descriptionProperty().get());
                  //  newStep = new TaskStep(selectedTask);
                  //  newStep.setName(model.nameProperty().get());
                  //  newStep.setDescription(model.descriptionProperty().get());
                  //  newStep.setStatusValue(TaskStepStatusValue.valueOf(selectedStatus.toUpperCase()));

                    runTransaction = true;
                }

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

    public TaskStep getNewStep(){
        return this.newStep;
    }

    public Boolean getRunTransaction(){
        return this.runTransaction;
    }

}

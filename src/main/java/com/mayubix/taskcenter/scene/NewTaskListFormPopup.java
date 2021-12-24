package com.mayubix.taskcenter.scene;

import com.dlsc.formsfx.model.structure.Field;
import com.dlsc.formsfx.model.structure.Form;
import com.dlsc.formsfx.model.structure.Group;
import com.mayubix.taskcenter.api.TaskList;
import com.mayubix.taskcenter.formmodels.NewTaskListFormModel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import org.w3c.dom.events.Event;

import java.util.ArrayList;

public class NewTaskListFormPopup extends Scene {

    public final String TITLE = "New Task List...";

    private GridPane layout = new GridPane();
    private Form form;
    private Button okBtn;
    private Button cancelBtn;

    private ArrayList<Field> fields = new ArrayList<>();
    private NewTaskListFormModel model = new NewTaskListFormModel();
    private TaskList taskList;
    private Stage stage;

    public NewTaskListFormPopup(Parent root, Stage stage){
        super(root);
        this.setRoot(layout);
        this.stage = stage;
        initialize();
        buildLayout();

    }

    public NewTaskListFormPopup(Parent root, double width, double height, Stage stage){
        super(root, width, height);
        this.setRoot(layout);
        this.stage = stage;
        initialize();
        buildLayout();
    }

    public NewTaskListFormPopup(Parent root, double width, double height, boolean depthBuffer, Stage stage) {
        super(root, width, height, depthBuffer);
        this.setRoot(layout);
        this.stage = stage;
        initialize();
        buildLayout();

    }

    public NewTaskListFormPopup(Parent root, double width, double height, boolean depthBuffer, SceneAntialiasing antiAliasing, Stage stage){
        super(root, width, height, depthBuffer, antiAliasing);
        this.setRoot(layout);
        this.stage = stage;
        initialize();
        buildLayout();

    }

    public NewTaskListFormPopup(Parent root, double width, double height, Paint fill, Stage stage){
        super(root, width, height, fill);
        this.setRoot(layout);
        this.stage = stage;
        initialize();
        buildLayout();

    }

    public NewTaskListFormPopup(Parent root, Paint fill, Stage stage){
        super(root, fill);
        this.setRoot(layout);
        this.stage = stage;
        initialize();
        buildLayout();

    }

    private void initialize(){
        //Form==========================================================================================================
        form = Form.of(
                Group.of(
                        Field.ofStringType(model.nameProperty())
                                .label("Name:")
                )
        );

        //OK Button=====================================================================================================
        okBtn = new Button("OK");
        okBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                taskList = new TaskList();
                taskList.setName(model.nameProperty().get());
            }
        });

        //Cancel Button=================================================================================================
        cancelBtn = new Button("Cancel");
        cancelBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                stage.close();
            }
        });

    }

    private void buildLayout(){

    }

    public TaskList getTaskList(){
        return this.taskList;
    }
}

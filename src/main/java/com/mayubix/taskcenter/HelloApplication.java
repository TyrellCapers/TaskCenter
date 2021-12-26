package com.mayubix.taskcenter;

import com.dlsc.formsfx.model.structure.Field;
import com.dlsc.formsfx.model.structure.Form;
import com.dlsc.formsfx.model.structure.Group;
import com.dlsc.formsfx.model.validators.StringLengthValidator;
import com.dlsc.formsfx.view.renderer.FormRenderer;
import com.mayubix.taskcenter.formmodels.LoginFormModel;
import com.mayubix.taskcenter.scene.TaskListDashboard;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Timer;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException, InterruptedException {
       // FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
       // FXMLLoader sample1Loader = new FXMLLoader(HelloApplication.class.getResource("sample1.fxml"));
       // Scene sample1Scene = new Scene(sample1Loader.load(), 320, 240);
       // Scene scene = new Scene(fxmlLoader.load(), 320, 240);
       // stage.setTitle("Hello!");
      //  stage.setScene(sample1Scene);
        //Timer timer = new Timer();
        //timer.wait(5000);
        //stage.setScene(scene);
      //  stage.show();

        LoginFormModel model = new LoginFormModel();


        Form loginForm = Form.of(
                Group.of(
                        Field.ofStringType(model.usernameProperty())
                                .label("Username")
                                .validate(StringLengthValidator.upTo(20, "Only 20 characters allowed"))
                                ,
                        Field.ofStringType(model.passwordProperty())
                                .label("Password")
                                .required("This field can't be empty")
                                ,
                        Field.ofDate(LocalDate.now())
                                .label("Date Field")
                )
        ).title("Login");

        GridPane root = new GridPane();
        root.add(new FormRenderer(loginForm), 0, 0);
        ColumnConstraints column0 = new ColumnConstraints();
        column0.setPercentWidth(30.0);
        root.getColumnConstraints().add(column0);
        Scene formScene = new Scene(root, 500, 500);

        TaskListDashboard scene = new TaskListDashboard(new AnchorPane(), 500, 500);
        stage.setTitle("Task List Dashboard");
        stage.setScene(scene);
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
              System.exit(0);
            }
        });
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
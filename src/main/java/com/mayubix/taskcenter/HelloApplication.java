package com.mayubix.taskcenter;

import com.mayubix.taskcenter.scene.TaskListDashboard;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
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

        TaskListDashboard scene = new TaskListDashboard(new AnchorPane(), 500, 500);
        stage.setTitle("Task List Dashboard");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
package com.mayubix.taskcenter.scene;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;

public class VisibleColumnsPopup extends Scene {
    private GridPane layout;
    private TableView tableView;
    private ArrayList<CheckBox> checkBoxes;
    private HashMap<String, TableColumn> columnMap;
    private Stage stage;

    public VisibleColumnsPopup(Parent root, TableView tableView, Stage stage){
        super(root);
        layout = new GridPane();

        this.setRoot(layout);

        this.tableView = tableView;
        this.checkBoxes = new ArrayList<>();
        this.columnMap = new HashMap<>();
        this.stage = stage;

        for(Object obj : tableView.getColumns()){
            TableColumn col = (TableColumn) obj;
            columnMap.put(col.getText(), col);
        }

        buildLayout();

    }

    public VisibleColumnsPopup(Parent root, double width, double height, TableView tableView, Stage stage){
        super(root, width, height);
        layout = new GridPane();

        this.setRoot(layout);

        this.tableView = tableView;
        this.checkBoxes = new ArrayList<>();
        columnMap = new HashMap<>();
        this.stage = stage;

        for(Object obj : tableView.getColumns()){
            TableColumn col = (TableColumn) obj;
            columnMap.put(col.getText(), col);
        }

        buildLayout();
    }

    public VisibleColumnsPopup(Parent root, double width, double height, boolean depthBuffer, TableView tableView, Stage stage) {
        super(root, width, height, depthBuffer);
        layout = new GridPane();

        this.setRoot(layout);

        this.tableView = tableView;
        this.checkBoxes = new ArrayList<>();
        this.columnMap = new HashMap<>();
        this.stage = stage;

        for(Object obj : tableView.getColumns()){
            TableColumn col = (TableColumn) obj;
            columnMap.put(col.getText(), col);
        }

        buildLayout();
    }

    public VisibleColumnsPopup(Parent root, double width, double height, boolean depthBuffer, SceneAntialiasing antiAliasing, TableView tableView){
        super(root, width, height, depthBuffer, antiAliasing);
        layout = new GridPane();

        this.setRoot(layout);

        this.tableView = tableView;
        this.checkBoxes = new ArrayList<>();
        this.columnMap = new HashMap<>();
        stage = (Stage) this.getWindow();

        for(Object obj : tableView.getColumns()){
            TableColumn col = (TableColumn) obj;
            columnMap.put(col.getText(), col);
        }

        buildLayout();
    }

    public VisibleColumnsPopup(Parent root, double width, double height, Paint fill, TableView tableView, Stage stage){
        super(root, width, height, fill);
        layout = new GridPane();

        this.setRoot(layout);

        this.tableView = tableView;
        this.checkBoxes = new ArrayList<>();
        this.columnMap = new HashMap<>();
        this.stage = stage;

        for(Object obj : tableView.getColumns()){
            TableColumn col = (TableColumn) obj;
            columnMap.put(col.getText(), col);
        }

        buildLayout();
    }

    public VisibleColumnsPopup(Parent root, Paint fill, TableView tableView, Stage stage){
        super(root, fill);
        layout = new GridPane();

        this.setRoot(layout);

        this.tableView = tableView;
        this.checkBoxes = new ArrayList<>();
        this.columnMap = new HashMap<>();
        this.stage = stage;

        for(Object obj : tableView.getColumns()){
            TableColumn col = (TableColumn) obj;
            columnMap.put(col.getText(), col);
        }

        buildLayout();
    }

    private void buildLayout(){
        int count = 0;
        for(Object obj : tableView.getColumns()){
            TableColumn col = (TableColumn) obj;
            CheckBox checkBox = new CheckBox(col.getText());
            checkBoxes.add(checkBox);
            layout.add(checkBox, 0, count);
            count++;
        }

        layout.setPadding(new Insets(10));

        //Cancel Button
        Button cancelBtn = new Button("Cancel");
        cancelBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                stage.close();
            }
        });
        layout.add(cancelBtn, 0, count);

        //Ok Button
        Button okButton = new Button("OK");
        okButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent){
                for(CheckBox eventBox : checkBoxes){
                    TableColumn eventCol = columnMap.get(eventBox.getText());
                    eventCol.setVisible(eventBox.isSelected());
                    stage.close();
                }
            }
        });
        layout.add(okButton, 3, count);
    }
}

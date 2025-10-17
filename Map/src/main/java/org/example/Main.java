package org.example;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class Main extends Application {
    public static Logic logic;

    @Override
    public void start(Stage primaryStage) {
        Pane root = new Pane();

        for(Node node : logic.getNodes().values()) {
            Circle dot = new Circle(logic.scaleX(node.longitude()), logic.scaleY(node.latitude()), Settings.dotSize);
            root.getChildren().add(dot);
        }



        Scene scene = new Scene(root, Settings.screenWidth, Settings.screenHeight);

        primaryStage.setTitle("Map");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        logic = new Logic();
        launch(args);
    }
}

package org.example;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class Main extends Application {
    public static Logic logic;
    private final int screenWidth = 800;
    private final int screenHeight = 600;

    @Override
    public void start(Stage primaryStage) {
        Pane root = new Pane();

        for(Node node : logic.getNodes()) {
            Circle dot = new Circle(node.getLongitude(), node.getLatitude(), 2);
            root.getChildren().add(dot);
        }



        Scene scene = new Scene(root, screenWidth, screenHeight);

        primaryStage.setTitle("Map");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        logic = new Logic();
        launch(args);
    }
}

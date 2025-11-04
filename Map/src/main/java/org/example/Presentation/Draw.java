package org.example.Presentation;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import org.example.Logic.Logic;
import org.example.Node;
import org.example.Path;
import org.example.Logic.Settings;

public class Draw {
    private final Pane root;
    private final Logic logic;

    public Draw() {
        this.root = new Pane();
        this.logic = new Logic();

        drawMap();

        drawAllConnections(66973468L);

        drawDot(logic.getNode(66973468L), Color.GREEN);
        drawDot(logic.getNode(693323283L), Color.YELLOW);

    }

    private void drawAllConnections(long id){
        for(Path path : logic.colorAllPaths(logic.getNode(id))) {
            drawLine(path, Color.RED);
        }
    }

    private void drawMap(){
        for(Node node : logic.getNodes().values()) {
            drawDot(node);
        }
        for(Path path : logic.getPaths()) {
            drawLine(path, Color.BLACK);
        }
    }

    private void drawLine(Path path, Paint color) {
        double startX = logic.scaleX(path.getStart().getLongitude());
        double startY = logic.scaleY(path.getStart().getLatitude());
        double endX = logic.scaleX(path.getEnd().getLongitude());
        double endY = logic.scaleY(path.getEnd().getLatitude());

        Line line = new Line(startX, startY, endX, endY);

        line.setStrokeWidth(Settings.dotSize);
        line.setStroke(color);
        root.getChildren().add(line);
    }

    private void drawDot(Node node) {
        Circle dot = new Circle(logic.scaleX(node.getLongitude()), logic.scaleY(node.getLatitude()), Settings.dotSize);
        root.getChildren().add(dot);
    }

    private void drawDot(Node node, Paint color) {
        Circle dot = new Circle(logic.scaleX(node.getLongitude()), logic.scaleY(node.getLatitude()), Settings.dotSize+2);
        dot.setFill(color);
        root.getChildren().add(dot);
    }

    public Pane getRoot() {
        return root;
    }
}

package org.example;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

public class Draw {
    private final Pane root;

    public Draw(Pane root) {
        this.root = root;
    }

    public void drawLine(Node one, Node two) {
        Line line = new Line(one.getBoardX(), one.getBoardY(), two.getBoardX(), two.getBoardY());
        line.setStrokeWidth(Settings.dotSize);
        root.getChildren().add(line);
    }

    public void drawDot(Node node) {
        Circle dot = new Circle(node.getBoardX(), node.getBoardY(), Settings.dotSize);
        root.getChildren().add(dot);
    }
}

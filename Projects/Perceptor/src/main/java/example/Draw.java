package example;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Draw {
    private final Pane root;
    private final Logic logic;

    public Draw() {
        this.root = new Pane();
        this.logic = new Logic();

        drawNodes();
    }

    private void drawNodes() {
        logic.getNodes().forEach(node -> {
            Circle dot = new Circle(node.getX(),node.getY(),3);
            if(node.getLabel() == 0){
                dot.setFill(Color.RED);
            }else{
                dot.setFill(Color.BLUE);
            }
            root.getChildren().add(dot);
        });
    }

    public Pane getRoot() {
        return root;
    }
}

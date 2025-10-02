import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import java.io.FileNotFoundException;


public class MainFrame extends Application{
    private final GameLogic logic;

    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {
        int size = logic.loadIntList();
        logic.loadBoard();

        GridPane mainGrid = new GridPane();
        mainGrid.setHgap(Settings.margin);
        mainGrid.setVgap(Settings.margin);
        mainGrid.setAlignment(Pos.CENTER);
        // i = y, j = x
        for(int i = 0; i< size; i++){
            for(int j = 0; j< size; j++){
                int x = j;
                int y = i;
                Label label = new Label(logic.getCell(x, y));
                label.setTextFill(Color.RED);

                Rectangle rect = new Rectangle(Settings.pixelSize, Settings.pixelSize, Color.WHITE);
                rect.setStroke(Color.BLACK);
                rect.setStrokeWidth(1);

                StackPane slot = new StackPane();
                slot.getChildren().addAll(rect, label);

                if(label.getText().isEmpty()){
                    slot.setOnMouseClicked(_ -> {
                        label.setTextFill(Color.BLACK);
                        if(label.getText().isEmpty()){
                            logic.setValue(x, y, 0);
                            label.setText("0");
                        } else if (label.getText().equals("0")) {
                            logic.setValue(x, y, 1);
                            label.setText("1");
                        }else{
                            logic.setValue(x, y, -1);
                            label.setText("");
                        }
                    });
                }

                mainGrid.add(slot, x, y);
            }
        }


        Button checkBtn = new Button("Check");
        checkBtn.setOnMouseClicked(_ -> System.out.println(logic.checkWin()));

        VBox root = new VBox(10);
        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(mainGrid, checkBtn);

        Scene scene = new Scene(root, Settings.boardSize, Settings.boardSize);

        primaryStage.setTitle("Takuza");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public MainFrame() {
        this.logic = new GameLogic();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

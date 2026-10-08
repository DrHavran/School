import javax.swing.*;
import java.awt.*;

public class Draw {
    private final int WIDTH = 3;
    private final int HEIGHT = 3;
    private final int SCREEN_WIDTH = 600;
    private final int SCREEN_HEIGHT = 600;
    private final int WIN_COUNT = 5;

    private final JButton[][] positions;
    private final Logic logic;
    private JFrame frame;

    public Draw() {
        this.logic = new Logic(WIDTH, HEIGHT, WIN_COUNT);
        this.positions = new JButton[HEIGHT][WIDTH];

        logic.addPlayer(Player.PlayerType.Human, 'x');
        logic.addPlayer(Player.PlayerType.Human, 'o');

        drawBoard();
        runGameLoop();
    }

    private void runGameLoop() {
        while (!logic.isGameFinished() && logic.getSlotsLeft() > 0) {

            if (logic.isNextPlayerARobot()) {
                logic.next();
                updateBoard();
            }

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }

        endGame();
    }

    private void drawBoard() {
        this.frame = new JFrame();
        frame.setTitle("Currently playing - " + logic.getNextPlayer().getSymbol() + " ( " + logic.getNextPlayer().getType() + " )");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(HEIGHT, WIDTH));

        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                JButton button = new JButton();
                positions[y][x] = button;

                int finalX = x;
                int finalY = y;

                button.addActionListener(_ -> {
                    if (!logic.isGameFinished() && logic.getSlotsLeft() > 0) {
                        logic.move(finalX, finalY);
                        updateBoard();
                    }
                });

                panel.add(button);
            }
        }

        frame.add(panel);
        frame.setResizable(false);
        frame.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        frame.setVisible(true);
    }

    private void updateBoard(){
        frame.setTitle("Currently playing - " + logic.getNextPlayer().getSymbol() + " ( " + logic.getNextPlayer().getType() + " )");

        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                JButton button = positions[y][x];
                char symbol = logic.symbolAt(x, y);

                if(symbol != 'B'){
                    button.setText(String.valueOf(symbol));
                }
            }
        }
    }
    private void endGame() {
        if (logic.getSlotsLeft() == 0) {
            frame.setTitle("Tie xD");
        } else {
            frame.setTitle(
                    "Game finished! The player with symbol - "
                            + logic.getWinningPlayer().getSymbol()
                            + " ( "
                            + logic.getWinningPlayer().getType()
                            + " ) won!"
            );
        }
    }
}
package Presentation;

import Logic.Logic;
import Players.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class Draw {
    private final int WIDTH = 5;
    private final int HEIGHT = 5;
    private final int SCREEN_WIDTH = 600;
    private final int SCREEN_HEIGHT = 600;
    private final int WIN_COUNT = 3;

    private final JButton[][] positions;
    private final Logic logic;
    private JFrame frame;

    public Draw() {
        this.logic = new Logic(WIDTH, HEIGHT, WIN_COUNT);
        this.positions = new JButton[HEIGHT][WIDTH];

        logic.addPlayer(Player.PlayerType.MiniMax, 'o');
        logic.addPlayer(Player.PlayerType.Human, 'x');

        drawBoard();
        playRobotTurns();
    }

    private void drawBoard() {
        this.frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(HEIGHT, WIDTH));

        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                JButton button = new JButton();
                positions[y][x] = button;

                int finalX = x;
                int finalY = y;

                button.addActionListener(_ -> {
                    if (logic.isGameFinished() || logic.getSlotsLeft() <= 0) return;
                    if (logic.isNextPlayerARobot()) return;

                    logic.move(finalX, finalY);
                    updateBoard();
                    playRobotTurns();
                });

                panel.add(button);
            }
        }

        frame.add(panel);
        frame.setResizable(false);
        frame.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);

        onKey(panel, KeyEvent.VK_R);
        onKey(panel, KeyEvent.VK_ENTER);

        frame.setVisible(true);
        updateBoard();
    }

    private void playRobotTurns() {
        while (!logic.isGameOver() && logic.isNextPlayerARobot()) {
            logic.nextRobotTurn();
            updateBoard();
        }

        if (logic.isGameOver()) endGame();
    }

    private void onKey(JComponent c, int keyCode) {
        String name = "action-" + keyCode;
        c.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
                .put(KeyStroke.getKeyStroke(keyCode, 0), name);
        c.getActionMap().put(name, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logic.newGame();
                updateBoard();
                playRobotTurns();
            }
        });
    }

    private void updateBoard() {
        frame.setTitle("Currently playing - "
                + logic.getNextPlayer().getSymbol()
                + " ( " + logic.getNextPlayer().getType() + " )");

        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                char symbol = logic.symbolAt(x, y);
                if (symbol != 'B') {
                    positions[y][x].setText(String.valueOf(symbol));
                }else{
                    positions[y][x].setText("");
                }
            }
        }
    }

    private void endGame() {
        if (logic.isGameFinished()) {
            frame.setTitle(
                    "Game finished! The player with symbol - "
                            + logic.getWinningPlayer().getSymbol()
                            + " ( "
                            + logic.getWinningPlayer().getType()
                            + " ) won!"
            );
        } else {
            frame.setTitle("Tie xD");
        }
    }
}
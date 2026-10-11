package Logic;

import Players.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class Logic {
    private final int WIN_COUNT;
    private final int WIDHT, HEIGHT;

    private final char[][] board;
    private int slotsLeft;
    private final ArrayList<Player> playerOrder;
    private boolean gameFinished;

    public Logic(int width, int height, int WIN) {
        this.WIDHT = width;
        this.HEIGHT = height;
        this.gameFinished = false;
        this.slotsLeft = WIDHT*HEIGHT;
        this.WIN_COUNT = WIN;
        this.playerOrder = new ArrayList<>();
        this.board = new char[height][width];

        for (char[] row : board) {
            Arrays.fill(row, 'B');
        }
    }

    public void nextRobotTurn() {
        Player p = playerOrder.getFirst();
        if (!(p instanceof Robot robot)) {
            throw new IllegalStateException("next() called on non-robot player");
        }
        int[] pos = robot.calculateMove(board);
        if (pos == null) return;
        move(pos[0], pos[1]);
    }

    public void move(int x, int y){
        if(board[y][x] == 'B'){
            Player currentPlayer = playerOrder.removeFirst();
            slotsLeft--;
            board[y][x] = currentPlayer.getSymbol();

            playerOrder.add(currentPlayer);
            checkWin(board, currentPlayer.getSymbol(), x, y, true);
        }
    }

    public boolean checkWin(char[][] board, char symbol, int x, int y, boolean realGame){
        for(int[] direction : directions){
            int count = 1
                    + countDirection(board, symbol, x, y, direction, 1)
                    + countDirection(board, symbol, x, y, direction, -1);

            if (count >= WIN_COUNT) {
                // Check if this function was called on real move
                if (realGame) gameFinished = true;
                return true;
            }
        }
        return false;
    }
    private int countDirection(char[][] board, char symbol, int x, int y, int[] direction, int sign){
        int count = 0;
        for (int i = 1; i < WIN_COUNT; i++) {
            int nX = x + direction[0] * i * sign;
            int nY = y + direction[1] * i * sign;

            if (nX < 0 || nX >= WIDHT || nY < 0 || nY >= HEIGHT) break;
            if (board[nY][nX] != symbol) break;

            count++;
        }
        return count;
    }

    public void newGame(){
        playerOrder.sort(Comparator.comparingInt(Player::getIndex));
        for (char[] row : board) {
            Arrays.fill(row, 'B');
        }
        gameFinished = false;
        this.slotsLeft = WIDHT*HEIGHT;
    }

    private final int[][] directions = new int[][]{
            {0, 1},
            {1, 1},
            {1, 0},
            {1, -1}
    };

    public int getWIN_COUNT() {
        return WIN_COUNT;
    }
    public void addPlayer(Player.PlayerType type, char symbol){
        Player player = switch (type){
            case Human -> new Human(symbol, type, playerOrder.size());
            case Random -> new RandomInputs(symbol, type, playerOrder.size());
            case MiniMax -> new MiniMax(symbol, type, playerOrder, this);
        };


        playerOrder.add(player);
    }
    public int getSlotsLeft() {
        return slotsLeft;
    }
    public char symbolAt(int x, int y){
        return board[y][x];
    }
    public boolean isGameFinished() {
        return gameFinished;
    }
    public boolean isGameOver(){
        return isGameFinished() || getSlotsLeft() <= 0;
    }
    public boolean isNextPlayerARobot(){
        return playerOrder.getFirst().getType() != Player.PlayerType.Human;
    }
    public Player getNextPlayer(){
        return playerOrder.getFirst();
    }
    public Player getWinningPlayer(){return playerOrder.getLast();}
}

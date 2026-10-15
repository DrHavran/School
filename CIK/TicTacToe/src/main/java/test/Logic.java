package test;

import io.github.classgraph.ClassGraph;
import io.github.classgraph.ScanResult;
import test.players.Human;
import test.players.Player;
import test.players.Robot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class Logic {
    private final int WIN_COUNT;
    private final int WIDTH, HEIGHT;

    private final char[][] board;
    private int slotsLeft;
    private final ArrayList<Player> playerOrder;
    private boolean gameFinished;
    private ArrayList<Class<?>> availableClasses;

    public Logic(int width, int height, int WIN) {
        this.WIDTH = width;
        this.HEIGHT = height;
        this.gameFinished = false;
        this.slotsLeft = WIDTH *HEIGHT;
        this.WIN_COUNT = WIN;
        this.playerOrder = new ArrayList<>();
        this.board = new char[height][width];

        for (char[] row : board) {
            Arrays.fill(row, 'B');
        }

        loadAvailablePlayers();
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

            if (nX < 0 || nX >= WIDTH || nY < 0 || nY >= HEIGHT) break;
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
        this.slotsLeft = WIDTH *HEIGHT;
    }

    private void loadAvailablePlayers(){
        try (
                ScanResult scan = new ClassGraph()
                    .acceptPackages("test.players")
                    .enableClassInfo()
                    .scan())
        {
            ArrayList<Class<?>> classes = new ArrayList<>(
                    scan.getClassesImplementing(Robot.class.getName())
                            .loadClasses(Robot.class)
            );

            classes.add(Human.class);

            this.availableClasses = classes;
        }
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
    public void addPlayer(Class<? extends Player> type, char symbol) {
        try {
            Player player = type
                    .getDeclaredConstructor(char.class, Logic.class)
                    .newInstance(symbol, this);

            playerOrder.add(player);
        } catch (Exception e){
            System.out.println(e.getLocalizedMessage());
        }
    }

    public ArrayList<Class<?>> getAvailableClasses() {
        return availableClasses;
    }
    public ArrayList<Player> getPlayerOrder() {
        return playerOrder;
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
        return playerOrder.getFirst().getClass() != Human.class;
    }
    public Player getNextPlayer(){
        return playerOrder.getFirst();
    }
    public Player getWinningPlayer(){return playerOrder.getLast();}
}

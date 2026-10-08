import java.util.ArrayList;
import java.util.Arrays;

public class Logic {
    private final int WIN_COUNT;

    private int slotsLeft;
    private final char[][] board;
    private final ArrayList<Player> playerOrder;
    private boolean gameFinished;

    public Logic(int width, int height, int WIN) {
        this.gameFinished = false;
        this.slotsLeft = width*height;
        this.WIN_COUNT = WIN;
        this.playerOrder = new ArrayList<>();
        this.board = new char[height][width];

        for (char[] row : board) {
            Arrays.fill(row, 'B');
        }
    }

    public void next(){
        int[] position = playerOrder.getFirst().calculateMove();
        move(position[0], position[1]);
    }

    public void move(int x, int y){
        if(board[y][x] == 'B'){
            Player currentPlayer = playerOrder.removeFirst();
            slotsLeft--;
            board[y][x] = currentPlayer.getSymbol();

            playerOrder.add(currentPlayer);
            checkWin(currentPlayer.getSymbol(), x, y);
        }
    }

    public void checkWin(char symbol, int x, int y){
        for(int[] direction : directions){
            int count = 1;
            for(int i = 1; i < WIN_COUNT; i++){
                int nX = x + direction[0]*i;
                int nY = y + direction[1]*i;

                if (nX < 0 || nX >= board[0].length || nY < 0 || nY >= board.length) {
                    break;
                }

                if(board[nY][nX] == symbol){
                    count++;
                    if (count == WIN_COUNT){
                        gameFinished = true;
                        return;
                    }
                }else{
                    break;
                }
            }
            for(int i = -1; i > -WIN_COUNT; i--){
                int nX = x + direction[0]*i;
                int nY = y + direction[1]*i;

                if (nX < 0 || nX >= board[0].length || nY < 0 || nY >= board.length) {
                    break;
                }

                if(board[nY][nX] == symbol){
                    count++;
                    if (count == WIN_COUNT){
                        gameFinished = true;
                        return;
                    }
                }else{
                    break;
                }
            }
        }
    }

    private final int[][] directions = new int[][]{
            {0, 1},
            {1, 1},
            {1, 0},
            {1, -1}
    };

    public int getSlotsLeft() {
        return slotsLeft;
    }
    public char symbolAt(int x, int y){
        return board[y][x];
    }
    public boolean isGameFinished() {
        return gameFinished;
    }
    public char[][] getBoard() {
        return board;
    }
    public void addPlayer(Player.PlayerType type, char symbol){
        playerOrder.add(
                new Player(
                        symbol, type
                )
        );
    }
    public boolean isNextPlayerARobot(){
        return playerOrder.getFirst().getType() != Player.PlayerType.Human;
    }
    public Player getNextPlayer(){
        return playerOrder.getFirst();
    }
    public Player getWinningPlayer(){return playerOrder.getLast();}
}

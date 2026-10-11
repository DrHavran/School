package Players;

import java.util.ArrayList;

public class Player {
    private final int index;
    private final char symbol;
    private final PlayerType type;

    public Player(char symbol, PlayerType type, int index) {
        this.index = index;
        this.symbol = symbol;
        this.type = type;
    }

    public enum PlayerType {
        Human,
        Random,
        MiniMax
    }

    protected ArrayList<int[]> getEmptyPositons(char[][] board){
        ArrayList<int[]> empty = new ArrayList<>();
        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board[y].length; x++) {
                if (board[y][x] == 'B') empty.add(new int[]{x, y});
            }
        }
        if (empty.isEmpty()) return null;
        return empty;
    }

    public int getIndex() {
        return index;
    }
    public char getSymbol() {
        return symbol;
    }
    public PlayerType getType() {
        return type;
    }
}

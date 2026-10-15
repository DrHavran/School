package test.players;

import test.Logic;

import java.util.ArrayList;

public class Player {
    private final Logic logic;
    private final int index;
    private final char symbol;

    public Player(char symbol, Logic logic) {
        this.logic = logic;
        this.index = logic.getPlayerOrder().size();
        this.symbol = symbol;
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
}

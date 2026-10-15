package test.players;

import test.Logic;

import java.util.ArrayList;

public class RandomInputs extends Player implements Robot {

    private final java.util.Random rng = new java.util.Random();

    public RandomInputs(char symbol, Logic logic) {
        super(symbol, logic);
    }

    @Override
    public int[] calculateMove(char[][] board) {
        ArrayList<int[]> empty = getEmptyPositons(board);
        if (empty.isEmpty()) return null;
        return empty.get(rng.nextInt(empty.size()));
    }
}
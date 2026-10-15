package test.players;

import test.Logic;
import java.util.ArrayList;
import java.util.Arrays;

public class MiniMax extends Player implements Robot{

    /*
        This version of Minimax implements Alpha-beta pruning
        As well as ideal placement position with move order sorting for the fastest time
     */

    private final int DEPTH = 5;
    private final ArrayList<Player> playerQue;
    private final Logic logic;

    public MiniMax(char symbol, Logic logic) {
        super(symbol, logic);
        this.playerQue = logic.getPlayerOrder();
        this.logic = logic;
    }

    @Override
    public int[] calculateMove(char[][] board) {
        int bestSum = Integer.MIN_VALUE;
        int[] bestPlay = {board[0].length/2, board.length/2};

        ArrayList<Player> nextOrder = new ArrayList<>(playerQue);
        Player nextPlayer = nextOrder.removeFirst();
        nextOrder.add(nextPlayer);

        for(int[] emptyPositon : getCandidatePositons(board)){
            char[][] newBoard = Arrays.stream(board).map(char[]::clone).toArray(char[][]::new);
            newBoard[emptyPositon[1]][emptyPositon[0]] = getSymbol();

            // Check if you can win immediately
            if(logic.checkWin(
                    newBoard,
                    getSymbol(),
                    emptyPositon[0],
                    emptyPositon[1],
                    false
            )){
                return emptyPositon;
            }

            int pathSum = searchBoard(
                    newBoard,
                    nextOrder,
                    1,
                    Integer.MIN_VALUE,
                    Integer.MAX_VALUE
            );

            if(pathSum > bestSum){
                bestSum = pathSum;
                bestPlay = emptyPositon;
            }
        }

        return bestPlay;
    }

    /*
        This is just a short note for me, so when I come to this I can understand it quicker

        Alpha stores the best value it has already found somewhere else
        Beta stores the "worst" value that can be forced in a certain position

        Meaning if we found a branch, where one path leads to a worst option value then the best already is,
        the other nodes in that path are useless and can be skipped since they will never be better than alpha,
        because they are hard capped by beta.
     */
    private int searchBoard(char[][] board, ArrayList<Player> order, int depth, int alpha, int beta){
        ArrayList<Player> newOrder = new ArrayList<>(order);
        Player currentPlayer = newOrder.removeFirst();
        newOrder.add(currentPlayer);

        // Check if the game has been tied
        ArrayList<int[]> emptyPositons = getCandidatePositons(board);
        if(emptyPositons == null) return 0;

        // Check if the maximum depth has been reached
        if(depth == DEPTH) return 0;

        char me = currentPlayer.getSymbol();
        emptyPositons.sort((a, b) -> Integer.compare(
                getImportanceValue(board, b, me),
                getImportanceValue(board, a, me)
        ));

        // Check if I am the one playing
        boolean amIPlaying = currentPlayer.getSymbol() == getSymbol();
        int best = amIPlaying ? Integer.MIN_VALUE : Integer.MAX_VALUE;

        for(int[] emptyPositon : emptyPositons){
            char[][] newBoard = Arrays.stream(board).map(char[]::clone).toArray(char[][]::new);
            newBoard[emptyPositon[1]][emptyPositon[0]] = currentPlayer.getSymbol();

            int score;
            if(logic.checkWin(
                    newBoard,
                    currentPlayer.getSymbol(),
                    emptyPositon[0],
                    emptyPositon[1],
                    false
            )){
                score = amIPlaying ? 1 : -1;
            }else{
                score = searchBoard(newBoard, newOrder, depth + 1, alpha, beta);
            }

            if(amIPlaying){
                best = Math.max(best, score);
                alpha = Math.max(alpha, best);
            }else{
                best = Math.min(best, score);
                beta = Math.min(beta, best);
            }

            // Pruning
            if(beta <= alpha) break;
        }

        return best;
    }

    /*
        This returns a list of nearby positions of already placed symbols
         -> game tries to prioritize places around its own symbols
     */
    private ArrayList<int[]> getCandidatePositons(char[][] board){
        ArrayList<int[]> empty = getEmptyPositons(board);
        if(empty == null) return null;

        int radius = logic.getWIN_COUNT() - 1;
        ArrayList<int[]> candidates = new ArrayList<>();
        for(int[] pos : empty){
            if(hasNeighbor(board, pos[0], pos[1], radius)) candidates.add(pos);
        }
        if(candidates.isEmpty()) return empty;
        return candidates;
    }
    private boolean hasNeighbor(char[][] board, int x, int y, int radius){
        for(int dy = -radius; dy <= radius; dy++){
            for(int dx = -radius; dx <= radius; dx++){
                if(dx == 0 && dy == 0) continue;
                int nx = x + dx, ny = y + dy;

                // Make sure radius doesn't check outside the board
                if(nx < 0 || nx >= board[0].length) continue;
                if(ny < 0 || ny >= board.length) continue;

                if(board[ny][nx] != 'B') return true;
            }
        }
        return false;
    }

    /*
        This sorts the empty positions by importance, prioritizing better moves first so Alpha-beta pruning is more effective
     */
    private int getImportanceValue(char[][] board, int[] position, char symbol){
        int x = position[0];
        int y = position[1];
        int score = 0;

        for(int dy = -1; dy <= 1; dy++){
            for(int dx = -1; dx <= 1; dx++){
                if(dx == 0 && dy == 0) continue;

                int nx = x + dx;
                int ny = y + dy;

                if(nx < 0 || nx >= board[0].length) continue;
                if(ny < 0 || ny >= board.length) continue;

                char c = board[ny][nx];
                if(c == symbol)      score += 2;   // extend my line
                else if(c != 'B')    score += 1;   // block theirs
            }
        }
        return score;
    }
}
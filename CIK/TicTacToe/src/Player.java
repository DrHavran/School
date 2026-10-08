public class Player {
    private final char symbol;
    private final PlayerType type;

    public Player(char symbol, PlayerType type) {
        this.symbol = symbol;
        this.type = type;
    }

    public enum PlayerType {
        Human,
        Random,
        MinMax
    }

    public int[] calculateMove(){return null;}
    public char getSymbol() {
        return symbol;
    }
    public PlayerType getType() {
        return type;
    }
}

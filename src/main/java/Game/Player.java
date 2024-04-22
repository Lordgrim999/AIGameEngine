package Game;

public class Player {
    private final String symbol;

    public Player(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return this.symbol;
    }
    public Player flip() {
        return new Player(symbol.equals("X")?"O":"X");
    }
}
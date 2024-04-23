package Game;

public class Player {
    private final String symbol;
    private User user;
    private int timeUsed;
    public Player(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return this.symbol;
    }
    public Player flip() {
        return new Player(symbol.equals("X")?"O":"X");
    }

    public void setTimeTaken(int timeStamp) {
        this.timeUsed+=timeStamp;
    }
    public int getTimeUsed()
    {
        return this.timeUsed;
    }
}

package Game;

public class GameState {

    boolean isOver;
    String winner;
    public GameState(boolean isCompleted, String winner)
    {
        this.isOver=isCompleted;
        this.winner=winner;
    }

    public boolean isOver() {
        return isOver;
    }

    public String getWinner() {
        return winner;
    }
}

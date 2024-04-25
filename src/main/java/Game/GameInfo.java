package Game;

import Boards.Cell;

public class GameInfo {
    private boolean isOver;
    private String winner;
    private boolean hasFork;
    private Player player;
    private Cell forkCell;

    public Cell getForkCell() {
        return forkCell;
    }

    public GameInfo(boolean isOver, String winner, boolean hasFork, Player player,Cell forkCell)
    {
        this.isOver=isOver;
        this.winner=winner;
        this.hasFork=hasFork;
        this.player=player;
        this.forkCell=forkCell;
    }

    public boolean isOver() {
        return isOver;
    }

    public String getWinner() {
        return winner;
    }

    public boolean isHasFork() {
        return hasFork;
    }

    public Player getPlayer() {
        return player;
    }

}

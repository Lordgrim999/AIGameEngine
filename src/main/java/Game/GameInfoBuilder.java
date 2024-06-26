package Game;

import Boards.Cell;

public class GameInfoBuilder
{
    private boolean isOver;
    private String winner;
    private boolean hasFork;
    private Player player;
    private Cell forkCell;

    public GameInfoBuilder isOver(boolean isOver)
    {
        this.isOver=isOver;
        return this;
    }

    public GameInfoBuilder forkCell(Cell forkCell)
    {
        this.forkCell=forkCell;
        return this;
    }

    public GameInfoBuilder winner(String winner)
    {
        this.winner=winner;
        return this;
    }
    public GameInfoBuilder hasFork(boolean hasFork)
    {
        this.hasFork=hasFork;
        return this;
    }
    public GameInfoBuilder player(Player player)
    {
        this.player=player;
        return this;
    }

    public GameInfo build()
    {
        return new GameInfo(isOver,winner,hasFork,player,forkCell);
    }

}

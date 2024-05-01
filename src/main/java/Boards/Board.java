package Boards;


import Game.Move;

public interface Board {

    public abstract Board move(Move move);
    public abstract Board copy();

}

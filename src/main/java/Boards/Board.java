package Boards;


import Game.Move;

public interface Board {

    public abstract void move(Move move);
    public abstract Board copy();

}

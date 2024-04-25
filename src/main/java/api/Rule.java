package api;

import Boards.Board;
import Game.GameState;

import java.util.function.Function;

public class Rule<T extends Board>
{
    Function<T, GameState> condition;
    public  Rule(Function<T,GameState> condition)
    {
        this.condition=condition;
    }
}

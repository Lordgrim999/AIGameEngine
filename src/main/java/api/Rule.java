package api;

import Boards.Board;
import Boards.CellBoard;
import Game.GameState;

import java.util.function.Function;

public class Rule
{
    Function<CellBoard, GameState> condition;
    public  Rule(Function<CellBoard,GameState> condition)
    {
        this.condition=condition;
    }
}

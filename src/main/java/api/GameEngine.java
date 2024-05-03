package api;


import Boards.Board;
import Boards.TicTacToeBoard;
import Game.Move;

public class GameEngine {


    public Board start(String type)
    {
        if(type.equalsIgnoreCase("TicTacToe"))
            return new TicTacToeBoard();
        throw new IllegalArgumentException();
    }

    public Board move(Board board, Move move)
    {
        if(board instanceof TicTacToeBoard)
        {
           TicTacToeBoard board1=(TicTacToeBoard)board;
                    return board1.move(move);
        }
        else {
            throw new IllegalArgumentException();
        }
    }





}
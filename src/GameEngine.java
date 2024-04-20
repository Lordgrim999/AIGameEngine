import Boards.TicTacToeBoard;
import Game.Board;
import Game.Move;

public class GameEngine {


    public Board start(String type)
    {
        if(type.equalsIgnoreCase("TicTacToe"))
            return new TicTacToeBoard();
        throw new IllegalArgumentException();
    }

    public void move(Board board, Move move)
    {
        if(board instanceof TicTacToeBoard)
        {
           TicTacToeBoard board1=(TicTacToeBoard)board;
                     board1.move(move);
        }
        else {
            throw new IllegalArgumentException();
        }
    }





}
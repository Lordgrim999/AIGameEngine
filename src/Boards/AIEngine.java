package Boards;

import Game.Board;
import Game.Move;
import Game.Player;

public class AIEngine {
    public Move suggestMove(Board board, Player computer) {
        if(board instanceof TicTacToeBoard)
        {
            for(int i=0;i<3;i++)
            {

                for(int j=0;j<3;j++)
                {
                    if(((TicTacToeBoard) board).getCell(i,j)==null)
                        return new Move(new Cell(i,j),computer);
                }
            }
        }
        else{
            throw new IllegalArgumentException();
        }
        throw new IllegalStateException();
    }
}

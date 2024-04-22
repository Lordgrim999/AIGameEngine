package api;


import Boards.Board;
import Boards.Cell;
import Boards.TicTacToeBoard;
import Game.Move;
import Game.Player;

public class AIEngine {
    public Move suggestMove(Board board, Player computer) {
        if(board instanceof TicTacToeBoard)
        {
            int threshold=3;
            Move move;
            if(countMoves((TicTacToeBoard)board)<threshold)
            {
                move = getBasicMove((TicTacToeBoard) board, computer);
            }
            else{
            move = getSmartMove((TicTacToeBoard) board, computer);
            }


            if (move != null) return move;
        }
        else{
            throw new IllegalArgumentException();
        }
        throw new IllegalStateException();
    }

    private Move getSmartMove(TicTacToeBoard board, Player computer) {
        RuleEngine ruleEngine=new RuleEngine();
        TicTacToeBoard copyBoard=board.copy();

        // returning the victorious move
        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
            {
                if(board.getSymbol(i,j)==null)
                {
                    Move move=new Move(new Cell(i,j),computer);
                    copyBoard.move(move);
                    if(ruleEngine.getState(copyBoard).isOver())
                            return move;
                }
            }
        }

        // returning the defensive move to prevent computer player to win
        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
            {
                if(board.getSymbol(i,j)==null)
                {
                    Move move=new Move(new Cell(i,j),computer.flip());
                    copyBoard.move(move);
                    if(ruleEngine.getState(copyBoard).isOver())
                        return new Move(new Cell(i,j),computer);
                }
            }
        }

        //
        return getBasicMove(board,computer);
    }

    private int countMoves(TicTacToeBoard board) {
        int count=0;
        for(int i=0;i<3;i++)
        {

            for(int j=0;j<3;j++)
            {
                if(board.getSymbol(i,j)!=null)
                    count++;
            }
        }
        return count;
    }

    private static Move getBasicMove(TicTacToeBoard board, Player computer) {
        for(int i=0;i<3;i++)
        {

            for(int j=0;j<3;j++)
            {
                if(board.getSymbol(i,j)==null)
                    return new Move(new Cell(i, j), computer);
            }
        }
        return null;
    }
}

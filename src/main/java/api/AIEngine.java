package api;


import Boards.Board;
import Boards.Cell;
import Boards.TicTacToeBoard;
import Game.GameInfo;
import Game.Move;
import Game.Player;

public class AIEngine {
    RuleEngine ruleEngine=new RuleEngine();
    public Move suggestMove(Board board, Player computerPlayer) {
        if(board instanceof TicTacToeBoard)
        {
            int threshold=3;
            Cell cellToPlay;
            if(countMoves((TicTacToeBoard)board)<threshold)
            {
                cellToPlay = getBasicMove((TicTacToeBoard) board);
            }
            else if(countMoves((TicTacToeBoard)board)<threshold+1)
            {
                cellToPlay = getCellToPlay((TicTacToeBoard) board, computerPlayer);
            }
            else{
                cellToPlay=getOptimizedCellToPlay((TicTacToeBoard) board,computerPlayer);
            }


            if (cellToPlay != null) return new Move(cellToPlay,computerPlayer);
        }
        else{
            throw new IllegalArgumentException();
        }
        throw new IllegalStateException();
    }

    private Cell getOptimizedCellToPlay(TicTacToeBoard board,Player computerPlayer) {
        //1. first try to  make a move which can be win
        Cell cellToPlay=offense(board, computerPlayer);
        if(cellToPlay!=null)return cellToPlay;

        //2. try to block the move if there is a chance of winning for opp player
        cellToPlay = defense(board, computerPlayer);
        if ( cellToPlay!= null) return cellToPlay;

        return null;
    }

    private Cell getCellToPlay(TicTacToeBoard board, Player computerPlayer) {

        // returning the victorious move
        Cell cellToPlay=offense(board, computerPlayer);
        if(cellToPlay!=null)return cellToPlay;

        // returning the defensive move to prevent computerPlayer player to win
         cellToPlay = defense(board, computerPlayer);
        if ( cellToPlay!= null) return cellToPlay;

        //
        GameInfo gameInfo=ruleEngine.getInfo(board);
        if(gameInfo.isHasFork())
        {
            cellToPlay=gameInfo.getForkCell();
            return cellToPlay;
        }
        return getBasicMove(board);
    }

    private  Cell defense(TicTacToeBoard board, Player computerPlayer) {

        TicTacToeBoard copyBoard=board.copy();
        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
            {
                if(board.getSymbol(i,j)==null)
                {
                    Move move=new Move(new Cell(i,j), computerPlayer.flip());
                    copyBoard.move(move);
                    if(ruleEngine.getState(copyBoard).isOver())
                        return new Cell(i, j);
                }
            }
        }
        return null;
    }
    private  Cell offense(TicTacToeBoard board, Player computerPlayer) {

        TicTacToeBoard copyBoard=board.copy();
        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
            {
                if(board.getSymbol(i,j)==null)
                {
                    Move move=new Move(new Cell(i,j),computerPlayer);
                    copyBoard.move(move);
                    if(ruleEngine.getState(copyBoard).isOver())
                        return move.getCell();
                }
            }
        }

        return null;
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

    private static Cell getBasicMove(TicTacToeBoard board) {
        for(int i=0;i<3;i++)
        {

            for(int j=0;j<3;j++)
            {
                if(board.getSymbol(i,j)==null)
                    return new Cell(i, j);
            }
        }
        return null;
    }
}

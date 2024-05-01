package api;


import Boards.Board;
import Boards.Cell;
import Boards.TicTacToeBoard;
import Game.GameInfo;
import Game.Move;
import Game.Player;
import placements.OffensivePlacement;
import placements.Placement;

import java.util.Optional;

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
        Placement placement= OffensivePlacement.getInstance();
        while(placement.next()!=null)
        {
            Optional<Cell> cellToPlay=placement.place(board,computerPlayer);
            if(cellToPlay.isPresent())
                return cellToPlay.get();
            placement=placement.next();
        }
        return null;

    }

    private Cell getCellToPlay(TicTacToeBoard board, Player computerPlayer) {

        // returning the victorious move
        Cell cellToPlay=offense(board, computerPlayer);
        if(cellToPlay!=null)return cellToPlay;

        // returning the defensive move to prevent computerPlayer player to win
         cellToPlay = defense(board, computerPlayer);
        if ( cellToPlay!= null) return cellToPlay;

        return getBasicMove(board);
    }

    private  Cell defense(TicTacToeBoard board, Player computerPlayer) {

        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
            {
                if(board.getSymbol(i,j)==null)
                {
                    Move move=new Move(new Cell(i,j), computerPlayer.flip());
                    TicTacToeBoard copyBoard= board.move(move);
                    if(ruleEngine.getState(copyBoard).isOver())
                        return new Cell(i, j);
                }
            }
        }
        return null;
    }
    private  Cell offense(TicTacToeBoard board, Player computerPlayer) {


        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
            {
                if(board.getSymbol(i,j)==null)
                {
                    Move move=new Move(new Cell(i,j),computerPlayer);
                    TicTacToeBoard copyBoard=board.move(move);
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

package Utils;

import Boards.Board;
import Boards.Cell;
import Boards.TicTacToeBoard;
import Game.Move;
import Game.Player;
import api.RuleEngine;
import placements.OffensivePlacement;
import placements.Placement;
import Utils.Strategy;
import java.util.Optional;

public class StrategyFactory {
    RuleEngine ruleEngine=new RuleEngine();
    private Strategy getOptimizedCellToPlay(TicTacToeBoard board, Player computerPlayer) {
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

    private Strategy getCellToPlay() {
        return new Strategy(){
            @Override
            public Cell getOptimalMove(Player computerPlayer, TicTacToeBoard ticTacToeBoard){
                // returning the victorious move
                Cell cellToPlay=offense(ticTacToeBoard, computerPlayer);
                if(cellToPlay!=null)return cellToPlay;

                // returning the defensive move to prevent computerPlayer player to win
                cellToPlay = defense(ticTacToeBoard, computerPlayer);
                if ( cellToPlay!= null) return cellToPlay;

                return getBasicStrategy(ticTacToeBoard);

            }
        };

    }

    private  Cell defense(TicTacToeBoard board, Player computerPlayer) {

        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
            {
                if(board.getSymbol(i,j)==null)
                {
                    Move move=new Move(Cell.getCell(i,j), computerPlayer.flip());
                    TicTacToeBoard copyBoard= board.move(move);
                    if(ruleEngine.getState(copyBoard).isOver())
                        return Cell.getCell(i,j);
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
                    Move move=new Move(Cell.getCell(i,j),computerPlayer);
                    TicTacToeBoard copyBoard=board.move(move);
                    if(ruleEngine.getState(copyBoard).isOver())
                        return move.getCell();
                }
            }
        }

        return null;
    }
    private static Strategy getBasicStrategy(TicTacToeBoard board) {
        return new Strategy() {
            @Override
            public Cell getOptimalMove(Player computerPlayer, TicTacToeBoard ticTacToeBoard) {
                for (int i = 0; i < 3; i++) {

                    for (int j = 0; j < 3; j++) {
                        if (board.getSymbol(i, j) == null)
                            return Cell.getCell(i, j);
                    }
                }
                return null;
            }
        };
    }

    public Strategy getStrategy(Player computerPlayer, TicTacToeBoard board) {
        Strategy strategy=null;
        int threshold=3;
        if(countMoves((TicTacToeBoard)board)<threshold)
        {
            strategy = getBasicStrategy((TicTacToeBoard) board);
        }
        else if(countMoves((TicTacToeBoard)board)<threshold+1)
        {
            strategy = getCellToPlay((TicTacToeBoard) board, computerPlayer);
        }
        else if(computerPlayer.getTimeUsed()>100000)
        {
            strategy = getBasicStrategy((TicTacToeBoard) board);
        }
        else{
            strategy=getOptimizedCellToPlay((TicTacToeBoard) board,computerPlayer);
        }
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


}

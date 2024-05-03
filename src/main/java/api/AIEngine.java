package api;


import Boards.Board;
import Boards.Cell;
import Boards.TicTacToeBoard;
import Game.GameInfo;
import Game.Move;
import Game.Player;
import Utils.Strategy;
import Utils.StrategyFactory;
import placements.OffensivePlacement;
import placements.Placement;

import java.util.Optional;

public class AIEngine {
StrategyFactory strategyFactory=new StrategyFactory();
    public Move suggestMove(Board board, Player computerPlayer) {
        if(board instanceof TicTacToeBoard)
        {
            int threshold=3;
            TicTacToeBoard ticTacToeBoard=(TicTacToeBoard)board;
            Strategy strategy=  strategyFactory.getStrategy(computerPlayer,ticTacToeBoard);
            Cell cellToPlay=strategy.getOptimalMove(computerPlayer,ticTacToeBoard);



            if (cellToPlay != null) return new Move(cellToPlay,computerPlayer);
        }
        else{
            throw new IllegalArgumentException();
        }
        throw new IllegalStateException();
    }





}

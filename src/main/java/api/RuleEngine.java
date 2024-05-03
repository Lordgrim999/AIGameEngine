package api;


import Boards.Board;
import Boards.CellBoard;
import Boards.TicTacToeBoard;
import Game.*;
import Boards.Cell;
import Boards.TicTacToeBoard.Symbol;
import placements.DefensivePlacement;
import placements.OffensivePlacement;

import java.util.HashMap;

import java.util.Map;
import java.util.Optional;


public class RuleEngine {
    Map<String, RuleSet> ruleMap=new HashMap<>();

    public RuleEngine()
    {
        ruleMap.put(TicTacToeBoard.class.getName(),TicTacToeBoard.getRules());
    }

    public GameState getState(Board board)
    {
        if(board instanceof TicTacToeBoard)
        {
            GameState gameState;
            TicTacToeBoard board1=(TicTacToeBoard) board;

           RuleSet rules=ruleMap.get(TicTacToeBoard.class.getName());
           for(Rule rule:rules)
           {
                gameState=rule.condition.apply(board1);
                if(gameState.isOver())
                {
                    return gameState;
                }
           }
           return new GameState(false,"-");



        }
        else{
            throw new IllegalArgumentException();
        }

    }

    public GameInfo getInfo(CellBoard board) {
        if (board instanceof TicTacToeBoard) {
            TicTacToeBoard ticTacToeBoard = (TicTacToeBoard) board;
            GameState gameState = getState(ticTacToeBoard);

            for( Symbol playerSymbol:Symbol.values()){
                Player player=new Player(playerSymbol.getMarker());
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if(ticTacToeBoard.getSymbol(i,j)!=null)
                            continue;


                    TicTacToeBoard boardCopy=ticTacToeBoard.move(new Move( Cell.getCell(i, j),player));
                    boolean canStillWin=false;
                    // force the opponent to make a defensive move
                    // then check you are able to win
                    DefensivePlacement defensivePlacement=DefensivePlacement.getInstance();
                    Optional<Cell> defensiveCell = defensivePlacement.place(boardCopy, player.flip());
                    if(defensiveCell.isPresent())
                    {
                        boardCopy= boardCopy.move(new Move(defensiveCell.get(),player.flip()));
                        OffensivePlacement offensivePlacement=OffensivePlacement.getInstance();
                        if(offensivePlacement.place(boardCopy,player).isPresent())
                        {
                            return new GameInfoBuilder()
                                    .isOver(gameState.isOver())
                                    .winner(gameState.getWinner()).forkCell(Cell.getCell(i,j))
                                    .hasFork(true)
                                    .player(player)
                                    .build();
                        }
                    }

                }
            }
            }
            return new GameInfoBuilder()
                    .hasFork(false)
                    .winner(gameState.getWinner())
                    .isOver(gameState.isOver())
                    .build();
        } else {
            throw new IllegalArgumentException();
        }
    }


}


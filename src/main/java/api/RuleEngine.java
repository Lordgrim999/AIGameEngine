package api;


import Boards.Board;
import Boards.TicTacToeBoard;
import Game.*;
import Boards.Cell;

import java.util.HashMap;

import java.util.Map;


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

           RuleSet<TicTacToeBoard> rules=ruleMap.get(TicTacToeBoard.class.getName());
           for(Rule<TicTacToeBoard> rule:rules)
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

    public GameInfo getInfo(Board board) {
        if (board instanceof TicTacToeBoard) {
            TicTacToeBoard ticTacToeBoard = (TicTacToeBoard) board;
            GameState gameState = getState(ticTacToeBoard);
            Cell forkCell=null;
            for(String playerSymbol:new String[]{"X","O"}){
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    Board boardCopy=board.copy();
                    Player player=new Player(playerSymbol);
                    boardCopy.move(new Move(new Cell(i, j),player ));
                    boolean canStillWin=false;
                    for(int k=0;k<3;k++)
                    {
                        for(int l=0;l<3;l++)
                        {
                            Board boardCopy2=boardCopy.copy();
                            forkCell=new Cell(k, l);
                            boardCopy2.move(new Move(forkCell, player.flip()));
                            if(getState(boardCopy2).getWinner().equals(player.flip().getSymbol()))
                            {
                                canStillWin=true;
                                break;
                            }

                        }
                        if( canStillWin)
                        {
                            break;
                        }
                    }
                    if(canStillWin)
                    {
                        return new GameInfoBuilder()
                            .isOver(gameState.isOver())
                            .winner(gameState.getWinner()).forkCell(forkCell)
                            .hasFork(true)
                            .player(player)
                            .build();

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


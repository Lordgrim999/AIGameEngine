package api;


import Boards.TicTacToeBoard;
import Boards.Board;
import Game.GameState;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

public class RuleEngine {
    Map<String, List<Rule<TicTacToeBoard>>> ruleMap=new HashMap<>();

    public RuleEngine()
    {
        ruleMap.put(TicTacToeBoard.class.getName(),new ArrayList<>());
        ruleMap.get(TicTacToeBoard.class.getName()).add(new Rule<>(board->findStreak((i,j)->board.getSymbol(i,j))));
        ruleMap.get(TicTacToeBoard.class.getName()).add(new Rule<>(board->findStreak((i,j)->board.getSymbol(j,i))));
        ruleMap.get(TicTacToeBoard.class.getName()).add(new Rule<>(board->findDiagStreak((i)->board.getSymbol(i,i))));
        ruleMap.get(TicTacToeBoard.class.getName()).add(new Rule<>(board->findDiagStreak((i)->board.getSymbol(i,2-i))));
        ruleMap.get(TicTacToeBoard.class.getName()).add(new Rule<>(board->{
            int countFilledCells=0;
            for(int i=0;i<3;i++)
            {
                for(int j=0;j<3;j++)
                {
                    if(((TicTacToeBoard) board).getSymbol(i,j)!=null)
                        countFilledCells++;
                }
            }
            if(countFilledCells==9)
                return new GameState(true,"-");
            return new GameState(false,null);
        }));
    }

    public GameState getState(Board board)
    {
        if(board instanceof TicTacToeBoard)
        {
            GameState gameState;
            TicTacToeBoard board1=(TicTacToeBoard) board;

           List<Rule<TicTacToeBoard>> rules=ruleMap.get(TicTacToeBoard.class.getName());
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

    private  GameState findStreak(BiFunction<Integer, Integer, String> next) {
        for (int i = 0; i < 3; i++) {
            boolean streakComplete = true;
                for (int j = 0; j < 3; j++) {
                    if (next.apply(i,j)==null || !next.apply(i,0).equals(next.apply(i, j))) {
                        streakComplete = false;
                        break;
                    }
                }
            if (streakComplete) {
                return new GameState(true, next.apply(i,0));
            }

        }
        return new GameState(false, "-");
    }

    private  GameState findDiagStreak(Function<Integer, String> next) {

            boolean streakComplete = true;
            for (int i = 0; i < 3; i++) {
                if (next.apply(0)==null || !next.apply(0).equals(next.apply(i))) {
                    streakComplete = false;
                    break;
                }
            }
            if (streakComplete) {
                return new GameState(true, next.apply(0));
            }


        return new GameState(false,"-");
    }
}

class Rule<T extends Board>
{
    Function<T,GameState> condition;
    public  Rule(Function<T,GameState> condition)
    {
        this.condition=condition;
    }
}

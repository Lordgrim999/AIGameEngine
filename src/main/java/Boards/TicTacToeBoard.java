package Boards;


import Game.GameState;
import Game.Move;
import api.Rule;
import api.RuleSet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

public class TicTacToeBoard implements CellBoard {



    String[][] cells =new String[3][3];
    History history=new History();
    public static RuleSet getRules() {
        RuleSet ruleSet=new RuleSet();

        ruleSet.add(new Rule(board->findStreak((i, j)->board.getSymbol(i,j))));
        ruleSet.add(new Rule(board->findStreak((i,j)->board.getSymbol(j,i))));
        ruleSet.add(new Rule(board->findDiagStreak((i)->board.getSymbol(i,i))));
        ruleSet.add(new Rule(board->findDiagStreak((i)->board.getSymbol(i,2-i))));
        ruleSet.add(new Rule(board->{
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
        return ruleSet;
    }


    public String getSymbol(int i, int j) {
        return cells[i][j];
    }


    public void setCell(String symbol, Cell cell) {
            if(cells[cell.row][cell.col]==null)
                cells[cell.row][cell.col] = symbol;
            else {
                System.out.println(this);
                throw new IllegalArgumentException("cell already occupied "+cell.row+" "+cell.col);
            }


    }

    @Override
    public String toString()
    {
        StringBuilder result=new StringBuilder();
        for(String[]cell:cells)
        {
            for(String cellVal:cell)
            {
                result.append(cellVal!=null?cellVal:"-");
                result.append(" ");
            }
            result.append("\n");
        }
        return result.toString();
    }

    @Override
    public TicTacToeBoard move(Move move) {
        history.add(this);
        TicTacToeBoard copyBoard=copy();
        copyBoard.setCell(move.getPlayer().getSymbol(),move.getCell());
        return copyBoard;

    }

    @Override
    public TicTacToeBoard copy() {
        TicTacToeBoard board=new TicTacToeBoard();
        for(int i=0;i<3;i++)
        {
            System.arraycopy(cells[i],0,board.cells[i],0,3);
        }
        return board;
    }
    private static  GameState findStreak(BiFunction<Integer, Integer, String> next) {
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

    private static  GameState findDiagStreak(Function<Integer, String> next) {

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

class History{
    List<Board> boards=new ArrayList<>();

    public Board getBoardAtMove(int moveIndex){
        for(int i=0;i<boards.size()-(moveIndex+1);i++)
        {
            boards.remove(boards.size()-1);
        }
        return boards.get(moveIndex);
    }

    public Board undo()
    {
        if(boards.isEmpty())
            throw new IllegalStateException("there are no previous moves left");
        boards.remove(boards.size()-1);
        return boards.get(boards.size()-1);
    }

    public void add(Board board)
    {
        boards.add(board);
    }

}


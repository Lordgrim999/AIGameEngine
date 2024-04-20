package Boards;

import Game.Board;
import Game.Move;

public class TicTacToeBoard extends Board {



    String[][] cells =new String[3][3];


    public String getCell(int i, int j) {
        return cells[i][j];
    }


    public void setCell(String symbol, Cell cell) {

            cells[cell.row][cell.col] = symbol;


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
    public void move(Move move) {
        setCell(move.getPlayer().getSymbol(),move.getCell());
    }
}

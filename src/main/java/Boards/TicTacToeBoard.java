package Boards;


import Game.Move;

public class TicTacToeBoard implements Board {



    String[][] cells =new String[3][3];


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
    public void move(Move move) {
        setCell(move.getPlayer().getSymbol(),move.getCell());
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
}

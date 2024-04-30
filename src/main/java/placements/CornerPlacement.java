package placements;

import Boards.Board;
import Boards.Cell;
import Boards.TicTacToeBoard;
import Game.Player;
import Utils.Utils;

import java.util.Optional;

public class CornerPlacement implements Placement{
    private static CornerPlacement cornerPlacement;

    private CornerPlacement()
    {

    }
    public static synchronized CornerPlacement getInstance()
    {
        cornerPlacement= (CornerPlacement) Utils.getIfNull(cornerPlacement, CornerPlacement::new);
        return cornerPlacement;
    }
    @Override
    public Optional<Cell> place(TicTacToeBoard board, Player player) {
        return Optional.ofNullable(placeCorner(board));
    }

    @Override
    public Placement next() {
        return null;
    }

    private Cell placeCorner(TicTacToeBoard board)
    {

        final int[][] corners=new int[][]{{0,0},{0,2},{2,0},{2,2}};
        for(int[] corner:corners)
        {
            if(board.getSymbol(corner[0],corner[1])!=null) {
                return new Cell(corner[0], corner[1]);

            }
        }
        return null;

    }
}

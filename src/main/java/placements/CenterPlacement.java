package placements;

import Boards.Board;
import Boards.Cell;
import Boards.TicTacToeBoard;
import Game.Player;
import Utils.Utils;

import java.util.Optional;

public class CenterPlacement implements Placement{
    private static CenterPlacement centerPlacement;

    private CenterPlacement()
    {

    }
    public static synchronized CenterPlacement getInstance()
    {
        centerPlacement= (CenterPlacement) Utils.getIfNull(centerPlacement, CenterPlacement::new);
        return centerPlacement;
    }
    @Override
    public Optional<Cell> place(TicTacToeBoard board, Player player) {
        Cell cellToPlay=null;
        if(board.getSymbol(1,1)==null)
        {
            cellToPlay= Cell.getCell(1,1);
        }
        return Optional.ofNullable(cellToPlay);
    }

    @Override
    public Placement next() {
        return CornerPlacement.getInstance();
    }
}

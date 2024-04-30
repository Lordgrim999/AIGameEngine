package placements;

import Boards.Board;
import Boards.Cell;
import Boards.TicTacToeBoard;
import Game.GameInfo;
import Game.Player;
import Utils.Utils;

import java.util.Optional;

public class ForkPlacement implements Placement{
    private static ForkPlacement forkPlacement;

    private ForkPlacement()
    {

    }

    public static synchronized ForkPlacement getInstance(){
        forkPlacement= (ForkPlacement) Utils.getIfNull(forkPlacement, ForkPlacement::new);
        return forkPlacement;
    }
    @Override
    public Optional<Cell> place(TicTacToeBoard board, Player player) {
        GameInfo gameInfo=ruleEngine.getInfo(board);
        Cell cellToPlay=null;
        if(gameInfo.isHasFork())
        {
            cellToPlay=gameInfo.getForkCell();

        }
        return Optional.ofNullable(cellToPlay);
    }

    @Override
    public Placement next() {
        return null;
    }
}

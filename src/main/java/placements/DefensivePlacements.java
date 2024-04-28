package placements;

import Boards.Board;
import Boards.Cell;

import java.util.Optional;

public class DefensivePlacements implements Placement{
    @Override
    public Optional<Cell> place(Board board) {
        return Optional.empty();
    }

    @Override
    public Placement next() {
        return null;
    }
}

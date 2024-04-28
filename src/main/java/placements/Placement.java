package placements;

import Boards.Board;
import Boards.Cell;

import java.util.Optional;

public interface Placement {
    Optional<Cell> place(Board board);
    Placement next();
}

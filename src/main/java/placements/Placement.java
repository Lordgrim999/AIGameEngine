package placements;

import Boards.Board;
import Boards.Cell;
import Boards.TicTacToeBoard;
import Game.Player;
import api.RuleEngine;

import java.util.Optional;

public interface Placement {
    RuleEngine ruleEngine=new RuleEngine();
    Optional<Cell> place(TicTacToeBoard board, Player player);
    Placement next();
}

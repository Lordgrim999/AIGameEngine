package placements;

import Boards.Board;
import Boards.Cell;
import Boards.TicTacToeBoard;
import Game.Move;
import Game.Player;
import Utils.Utils;

import javax.swing.text.html.Option;
import java.util.Optional;

public class OffensivePlacement implements Placement{
    private static OffensivePlacement offensivePlacement;

    private OffensivePlacement()
    {

    }
    public static synchronized OffensivePlacement getInstance()
    {
        offensivePlacement= (OffensivePlacement) Utils.getIfNull(offensivePlacement, OffensivePlacement::new);
        return offensivePlacement;
    }
    @Override
    public Optional<Cell> place(TicTacToeBoard board,Player player) {
        return Optional.ofNullable(offense(board, player));
    }

    @Override
    public Placement next() {
        return DefensivePlacement.getInstance();
    }
    private  Cell offense(TicTacToeBoard board, Player computerPlayer) {

        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
            {
                if(board.getSymbol(i,j)==null)
                {
                    Move move=new Move(Cell.getCell(i,j),computerPlayer);
                    TicTacToeBoard copyBoard= board.move(move);
                    if(ruleEngine.getState(copyBoard).isOver())
                        return move.getCell();
                }
            }
        }

        return null;
    }
}

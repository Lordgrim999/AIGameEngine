package placements;

import Boards.Board;
import Boards.Cell;
import Boards.TicTacToeBoard;
import Game.Move;
import Game.Player;
import Utils.Utils;

import java.util.Optional;

public class DefensivePlacement implements Placement{
    private static DefensivePlacement defensivePlacement;

    private DefensivePlacement()
    {

    }
    public static synchronized DefensivePlacement getInstance()
    {
        defensivePlacement= (DefensivePlacement) Utils.getIfNull(defensivePlacement, DefensivePlacement::new);
        return defensivePlacement;
    }
    @Override
    public Optional<Cell> place(TicTacToeBoard board, Player player) {

        return Optional.ofNullable(defense(board, player));
    }
    private  Cell defense(TicTacToeBoard board, Player computerPlayer) {


        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
            {
                if(board.getSymbol(i,j)==null)
                {
                    Move move=new Move(new Cell(i,j), computerPlayer.flip());
                    TicTacToeBoard copyBoard= board.move(move);
                    if(ruleEngine.getState(copyBoard).isOver())
                        return new Cell(i, j);
                }
            }
        }
        return null;
    }

    @Override
    public Placement next() {
        return ForkPlacement.getInstance();
    }
}

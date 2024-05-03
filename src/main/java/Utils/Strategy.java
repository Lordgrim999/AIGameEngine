package Utils;

import Boards.Cell;
import Boards.TicTacToeBoard;
import Game.Player;

public abstract class Strategy {

    public abstract Cell getOptimalMove(Player computerPlayer, TicTacToeBoard ticTacToeBoard) ;
}

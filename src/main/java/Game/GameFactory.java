package Game;

import Boards.TicTacToeBoard;

public class GameFactory {

    public static Game createGame(Integer maxTimePerMove,Integer maxTimePerPlayer){
        return new Game(
                new GameConfig(maxTimePerPlayer!=null,maxTimePerPlayer),
                new TicTacToeBoard(),
                null,
                0,
                maxTimePerPlayer,
                maxTimePerMove
        );
    }

    public static Game createGame(Integer maxTimePerPlayer){
        return new Game(
                new GameConfig(true,null),
                new TicTacToeBoard(),
                null,
                0,
                maxTimePerPlayer,
                null
        );
    }



    public static Game createGame(){
        return new Game(
                new GameConfig(false,null),
                new TicTacToeBoard(),
                null,
                0,
                null,
                null
        );
    }
}

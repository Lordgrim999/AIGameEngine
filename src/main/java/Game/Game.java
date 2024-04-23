package Game;

import Boards.Board;

public class Game
{
    private GameConfig gameConfig;
    private Board board;
    private Player winner;
    private int lastMoveTime;
    private int maxTimePerPlayer;
    private int maxTimePerMove;
    public void move(Move move, int timeStamp)
    {
        if(gameConfig.timed)
        {
            moveForTimedGame(move, timeStamp);

        }
        else{
            board.move(move);
        }
    }

    private void moveForTimedGame(Move move, int timeStamp) {
        int timeTakenSinceLast= timeStamp -lastMoveTime;
        move.getPlayer().setTimeTaken(timeTakenSinceLast);
        if(gameConfig.timePerMove!=null)
        {

            if(moveMadeInTime(timeTakenSinceLast))
            {
                board.move(move);
            }
            else {
                winner= move.getPlayer().flip();
            }

        }
        else{
            if(moveMadeInTime(move.getPlayer()))
            {
                board.move(move);
            }
            else {
                winner= move.getPlayer().flip();
            }

        }
    }

    private boolean moveMadeInTime(int timeTakenSinceLast) {
        return timeTakenSinceLast<maxTimePerMove;
    }

    private boolean moveMadeInTime(Player player) {
        return player.getTimeUsed()<maxTimePerPlayer;
    }
}

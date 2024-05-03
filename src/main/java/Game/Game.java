package Game;


import Boards.Board;
import api.RuleEngine;

public class Game
{
    private GameConfig gameConfig;
    private Board board;
    private Player winner;
    private Integer lastMoveTime;
    private Integer maxTimePerPlayer;
    private Integer maxTimePerMove;
    private RuleEngine ruleEngine=new RuleEngine();

    public Game(GameConfig gameConfig, Board board, Player winner, Integer lastMoveTime, Integer maxTimePerPlayer, Integer maxTimePerMove) {
        this.gameConfig = gameConfig;
        this.board = board;
        this.winner = winner;
        this.lastMoveTime = lastMoveTime;
        this.maxTimePerPlayer = maxTimePerPlayer;
        this.maxTimePerMove = maxTimePerMove;
    }

    public void move(Move move, Integer timeStamp)
    {
        if(winner!=null)
                return;
        if(gameConfig.timed)
        {
            moveForTimedGame(move, timeStamp);

        }
        else{
           board=board.move(move);
        }
        if(winner==null && ruleEngine.getState(board).isOver())
                winner=move.getPlayer();
    }

    private void moveForTimedGame(Move move, Integer timeStamp) {
        int timeTakenSinceLast= timeStamp -lastMoveTime;
        if(move.getPlayer().getTimeUsed()<maxTimePerPlayer &&
                (gameConfig.timePerMove==null || timeTakenSinceLast<maxTimePerMove))
                board=board.move(move);
        else
            winner=move.getPlayer().flip();
    }



    public Player getWinner() {
        return winner;
    }
}

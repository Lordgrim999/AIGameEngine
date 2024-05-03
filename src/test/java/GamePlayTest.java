import Boards.Board;
import Boards.Cell;
import Game.Move;
import Game.Player;
import api.GameEngine;
import api.RuleEngine;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class GamePlayTest {

    GameEngine gameEngine;
    RuleEngine ruleEngine;
    @Before
    public void setup()
    {
         gameEngine=new GameEngine();
         ruleEngine=new RuleEngine();
    }

    @Test
    public void checkForRowWin()
    {
        Board board=gameEngine.start("TicTacToe");
        int[][] firstPlayerMoves =new int[][]{{1,0},{1,1},{1,2}};
        int[][] secondPlayerMoves =new int[][]{{0,0},{0,1},{0,2}};
        board=playGame(board, firstPlayerMoves,secondPlayerMoves);

        Assert.assertTrue(ruleEngine.getState(board).isOver());
        // where "X" is human player
        Assert.assertEquals(ruleEngine.getState(board).getWinner(),"X");
    }

    @Test
    public void checkForColWin()
    {

        Board board=gameEngine.start("TicTacToe");
        int[][] firstPlayerMoves =new int[][]{{1,0},{1,1},{1,2}};
        int[][] secondPlayerMoves =new int[][]{{0,0},{0,1},{0,2}};
        board= playGame(board, firstPlayerMoves,secondPlayerMoves);
        Assert.assertTrue(ruleEngine.getState(board).isOver());
        // where "X" is human player
        Assert.assertEquals(ruleEngine.getState(board).getWinner(),"X");
    }

    @Test
    public void checkForLeftDiagonalWin()
    {

        Board board=gameEngine.start("TicTacToe");
        int[][] firstPlayerMoves =new int[][]{{1,0},{1,1},{1,2}};
        int[][] secondPlayerMoves =new int[][]{{0,0},{0,1},{0,2}};
        board=playGame(board, firstPlayerMoves,secondPlayerMoves);
        Assert.assertTrue(ruleEngine.getState(board).isOver());
        // where "X" is human player
        Assert.assertEquals(ruleEngine.getState(board).getWinner(),"X");
    }

    @Test
    public void checkForRightDiagonalWin()
    {

        Board board=gameEngine.start("TicTacToe");
        int[][] firstPlayerMoves =new int[][]{{1,0},{1,1},{1,2}};
        int[][] secondPlayerMoves =new int[][]{{0,0},{0,1},{0,2}};
        board= playGame(board, firstPlayerMoves,secondPlayerMoves);
        Assert.assertTrue(ruleEngine.getState(board).isOver());
        // where "X" is human player
        Assert.assertEquals(ruleEngine.getState(board).getWinner(),"X");
    }

    @Test
    public void checkForComputerWin()
    {

        Board board=gameEngine.start("TicTacToe");
        int[][] firstPlayerMoves =new int[][]{{1,0},{2,2},{1,2}};
        int[][] secondPlayerMoves =new int[][]{{0,0},{0,1},{0,2}};
        board= playGame(board, firstPlayerMoves,secondPlayerMoves);
        Assert.assertTrue(ruleEngine.getState(board).isOver());
        // where "X" is human player
        Assert.assertEquals(ruleEngine.getState(board).getWinner(),"O");
    }

    private Board playGame(Board board, int[][] firstPlayerMoves, int[][] secondPlayerMoves) {
        int row,col;
        int next=0;
        Player humanPlayer=new Player("X");
        Player computerPlayer=new Player("O");

        while(!ruleEngine.getState(board).isOver())
        {
            row= firstPlayerMoves[next][0];
            col= firstPlayerMoves[next][1];
            Move humanPlayerMove=new Move(Cell.getCell(row,col),humanPlayer);
            board= gameEngine.move(board,humanPlayerMove);
            if(!ruleEngine.getState(board).isOver())
            {
                int sRow=secondPlayerMoves[next][0];
                int sCol=secondPlayerMoves[next][1];
                Move computerPlayerMove= new Move( Cell.getCell(sRow,sCol),computerPlayer);
                board=gameEngine.move(board,computerPlayerMove);


            }
            next++;


        }
        return board;
    }

}

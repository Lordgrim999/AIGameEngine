import Boards.Cell;
import Game.Game;
import Game.Move;
import Game.GameFactory;
import Game.Player;
import org.junit.Assert;
import org.junit.Test;

public class GameTest {

    @Test
    public void timeOutTestForOneMove()
    {

        Game game= GameFactory.createGame(3000,11000);
        Player x= new Player("X");
        Player o= new Player("O");
        Cell c00=Cell.getCell(0,0);
        int ts=5000;
        game.move(new Move(c00,x),ts);
        Assert.assertEquals(game.getWinner().getSymbol(),o.getSymbol());

    }

    @Test
    public void timeOutTestForGame()
    {

        Game game= GameFactory.createGame(6000,11000 );
        Player x= new Player("X");
        Player o= new Player("O");
        Cell c00=Cell.getCell(0,0);
        Cell c01=Cell.getCell(0,1);
        Cell c02=Cell.getCell(0,2);
        Cell c10=Cell.getCell(1,0);
        int ts=5000;
        game.move(new Move(c00,x),ts);
        game.move(new Move(c01,o),ts);
        game.move(new Move(c02,x),ts);
        game.move(new Move(c10,o),7000);
        Assert.assertEquals(game.getWinner().getSymbol(),x.getSymbol());

    }

    @Test
    public void timeOutTestPerPlayer()
    {
        Game game= GameFactory.createGame(120);
    }
}

import Boards.AIEngine;
import Boards.Cell;
import Boards.RuleEngine;
import Game.Board;
import Game.Move;
import Game.Player;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        GameEngine gameEngine=new GameEngine();
        AIEngine aiEngine =new AIEngine();
        RuleEngine ruleEngine=new RuleEngine();
        Board board=gameEngine.start("TicTacToe");
        Player humanPlayer=new Player("X");
        Player computerPlayer=new Player("O");

        while(!ruleEngine.getState(board).isOver())
        {
            System.out.println("Make your move !!");
            Scanner sc=new Scanner(System.in);
            int row=sc.nextInt();
            int col=sc.nextInt();
            Move humanPlayerMove=new Move(new Cell(row,col),humanPlayer);
            gameEngine.move(board,humanPlayerMove);
            System.out.println(board.toString());
            if(!ruleEngine.getState(board).isOver())
            {
                System.out.println("Computer's making move");
                Move computerPlayerMove= aiEngine.suggestMove(board,computerPlayer);
                gameEngine.move(board,computerPlayerMove);
                System.out.println(board.toString());

            }


        }
        System.out.println("Game Result is : "+ruleEngine.getState(board).getWinner());

    }
}

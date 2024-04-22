package api;


import Boards.TicTacToeBoard;
import Boards.Board;
import Game.GameState;

public class RuleEngine {
    public GameState getState(Board board)
    {
        GameState gameState;
        if(board instanceof TicTacToeBoard)
        {
            TicTacToeBoard board1=(TicTacToeBoard) board;
            String firstCharacter="-";
            boolean isComplete=true;
            for(int i=0;i<3;i++)
            {

                firstCharacter=board1.getSymbol(i,0);
                isComplete= firstCharacter != null;
                if(firstCharacter!=null) {
                    for (int j = 1; j < 3; j++) {
                        if (!firstCharacter.equals(board1.getSymbol(i, j))) {
                            isComplete = false;
                            break;
                        }
                    }
                }
                if(isComplete)
                {
                    return new GameState(true,firstCharacter);
                }

            }
            for(int i=0;i<3;i++)
            {

                firstCharacter=board1.getSymbol(0,i);
                isComplete= firstCharacter != null;
                if(firstCharacter!=null) {
                    for (int j = 1; j < 3; j++) {
                        if (!firstCharacter.equals(board1.getSymbol(j,i))) {
                            isComplete = false;
                            break;
                        }
                    }
                }
                if(isComplete)
                {
                    return new GameState(true,firstCharacter);
                }

            }

            firstCharacter=board1.getSymbol(0,0);
            isComplete= firstCharacter != null;
            if(firstCharacter!=null) {
                for (int i = 1; i < 3; i++) {

                    if (!firstCharacter.equals(board1.getSymbol(i, i))) {
                        isComplete = false;
                        break;
                    }
                }
            }
            if(isComplete)
            {
                return new GameState(true,firstCharacter);
            }


            firstCharacter=board1.getSymbol(0,2);
            isComplete= firstCharacter != null;
            if(firstCharacter!=null) {
                for (int i = 1; i < 3; i++) {

                    if (!firstCharacter.equals(board1.getSymbol(i, 2 - i))) {
                        isComplete = false;
                        break;
                    }
                }
            }
            if(isComplete)
            {
                return new GameState(true,firstCharacter);
            }


        }
        return new GameState(false,null);
    }
}

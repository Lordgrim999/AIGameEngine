public class Main {
    public static void main(String[] args) {

    }

    public Board start()
    {
        return new Board();
    }

    public void move(Board board,Player player,Move move)
    {

    }

    public GameResult isCompleted(Board board)
    {
        GameResult gameResult;
        if(board instanceof TicTacToeBoard)
        {
            TicTacToeBoard board1=(TicTacToeBoard) board;
            String firstCharacter="-";
            boolean isComplete=true;
            for(int i=0;i<3;i++)
            {
                isComplete=true;
                firstCharacter=board1.cells[i][0];
                for(int j=1;j<3;j++)
                {
                    if(!board1.cells[i][j].equals(firstCharacter)) {
                        isComplete = false;
                        break;
                    }
                }
                if(isComplete)
                {
                    return new GameResult(true,firstCharacter);
                }

            }
            for(int i=0;i<3;i++)
            {
                isComplete=true;
                firstCharacter=board1.cells[0][i];
                for(int j=1;j<3;j++)
                {
                    if(!board1.cells[j][i].equals(firstCharacter)) {
                        isComplete = false;
                        break;
                    }
                }
                if(isComplete)
                {
                    return new GameResult(true,firstCharacter);
                }

            }
            isComplete=true;
            firstCharacter=board1.cells[0][0];
            for(int i=1;i<3;i++)
            {

                if(!board1.cells[i][i].equals(firstCharacter))
                {
                    isComplete=false;
                    break;
                }
            }
            if(isComplete)
            {
                return new GameResult(true,firstCharacter);
            }

            isComplete=true;
            firstCharacter=board1.cells[0][2];
            for(int i=1;i<3;i++)
            {

                if(!board1.cells[i][2-i].equals(firstCharacter))
                {
                    isComplete=false;
                    break;
                }
            }
            if(isComplete)
            {
                return new GameResult(true,firstCharacter);
            }


        }
        return new GameResult(false,null);
    }
}
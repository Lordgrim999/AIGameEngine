package Boards;

public class BoardProxy {
    String representation;
    public BoardProxy(Board board)
    {
        representation=board.toString();
    }
}

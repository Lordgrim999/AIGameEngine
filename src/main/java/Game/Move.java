package Game;


import Boards.Cell;

public class Move {
    private final Cell cell;
    private final Player player;

    public Move(Cell cell,Player player) {
        this.cell = cell;
        this.player=player;
    }

    public Cell getCell() {
        return this.cell;
    }
    public Player getPlayer() {
        return this.player;
    }
}

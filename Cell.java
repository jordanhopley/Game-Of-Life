import java.awt.*;

/**
 * This class represents a single cell on the board.
 * Each cell is a Button object and can be in one of two states: dead or alive.
 * If a cell has a white background, it is dead.
 * If a cell has a black background, it is alive.
 * @author Jordan Hopley
 */
public class Cell extends Button {

    private int x;
    private int y;
    private Board board;

    private int[] neighbourPositions = {-1, 0, 1};
    private int neighbourCount = 0;

    /**
     * Creates a new cell.
     *
     * @param x the x-coordinate of this cell.
     * @param y the y-coordinate of this cell.
     * @param board the Board that this cell belongs to.
     */
    public Cell(int x, int y, Board board) {
        this.x = x;
        this.y = y;
        this.board = board;
    }

    /**
     * When a cell is clicked, its state changes.
     * An alive cell becomes dead.
     * A dead cell becomes alive.
     */
    public void clicked() {
        if (getBackgroundColor() == Color.WHITE) {
            setBackgroundColor(Color.BLACK);
        } else if (getBackgroundColor() == Color.BLACK) {
            setBackgroundColor(Color.WHITE);
        }
    }

    /**
     * Sets this cells neighbourCount to a value corresponding to the amount
     * of neighbours it has.
     */
    public void countNumberOfNeighbours() {
        neighbourCount = 0;

        for (int i = 0; i < neighbourPositions.length; i++) {
            for (int j = 0; j < neighbourPositions.length; j++) {
                Cell cell = board.getCellAt(x + neighbourPositions[i], y + neighbourPositions[j]);
                // If the neighbour cell being checked isn't itself, is inside the board and is alive
                if ((cell != this) && (cell != null) && (cell.getBackgroundColor().equals(Color.BLACK))) {
                    neighbourCount++;
                }
            }
        }
    }

    /**
     * Provides a cells neighbour count.
     *
     * @return the number of neighbours this cell has.
     */
    public int getNeighbourCount() {
        return neighbourCount;
    }

}

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class provides the graphical representation of a board in which the cells
 * will live. It will create a rectangle of buttons (Cells) that are interactive
 * @author Jordan Hopley
 */
public class Board extends JPanel implements ActionListener, Runnable {

    private int width;
    private int height;
    private Cell[][] cells;
    private JLabel iterationLabel;

    private final int initialDelay = 200;

    private int iterations = 0;
    private int delay = initialDelay;
    private boolean isPaused = true;


    /**
     * Creates a grid of cells with the number of cells determined
     * by its parameters - this grid is visualised on the screen.
     * When the board has been created, a thread controlling the Game of Life's
     * sequence is invoked.
     *
     * @param width the width of the board, in cells
     * @param height the height of the board, in cells
     */
    public Board(int width, int height) {
        this.width = width;
        this.height = height;

        setLayout(new GridLayout(width, height));

        cells = new Cell[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                cells[i][j] = new Cell(i, j, this);
                cells[i][j].addActionListener(this);
                add(cells[i][j]);
            }
        }

        setVisible(true);
        Thread thread = new Thread(this);
        thread.start();
    }

    /**
     * This method is invoked whenever the user clicks on a cell.
     * It invokes a clicked() method belonging the cell that is clicked.
     *
     * @param e the ActionEvent allowing the user to interact with the cell.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        Cell cell = (Cell) e.getSource();
        cell.clicked();
    }

    /**
     * This controls the iteration count of the application and
     * it ensures that each cell is correctly configured for its upcoming iteration.
     */
    @Override
    public void run() {
        while (true) {
            // Make sure each cell has the correct neighbour count
            setNeighbourCounts();
            if (!isPaused) {
                iterations++;
                iterationLabel.setText("Iterations: " + iterations);
                tick();
                try {
                    Thread.sleep(delay);
                } catch (InterruptedException e) {
                    System.out.println("Thread controlling game logic has been interrupted, exiting...");
                    System.exit(-1);
                }
            }
        }
    }

    /**
     * A tick() is called after every iteration.
     * It scans the cells on the board and checks whether it is dead or alive.
     * It then checks whether the cell should stay in its current state, become alive, or die.
     */
    public void tick() {
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells.length; j++) {
                Cell cell = cells[i][j];

                // If cell is dead and has 3 neighbours, then cell becomes alive
                if (cell.getBackgroundColor().equals(Color.WHITE) && cell.getNeighbourCount() == 3) {
                    cell.setBackgroundColor(Color.BLACK);
                    cell.repaint();
                }
                // If cell is alive but has more than 3 neighbours or less than 2 neighbours, it dies
                if (cell.getBackgroundColor().equals(Color.BLACK) && (cell.getNeighbourCount() > 3 || cell.getNeighbourCount() < 2)) {
                    cell.setBackgroundColor(Color.WHITE);
                    cell.repaint();
                }
            }
        }
    }

    /**
     * Scans through all of the cells on the board and ensures that each cell
     * has the correct neighbourCount.
     */
    public void setNeighbourCounts() {
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells.length; j++) {
                Cell cell = cells[i][j];
                cell.countNumberOfNeighbours();
            }
        }
    }

    /**
     * Resets the Game of Life back to its initial state.
     */
    public void restart() {
        delay = initialDelay;
        iterations = 0;
        iterationLabel.setText("Iterations: " + 0);

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Cell cell = cells[i][j];
                if (cell.getBackgroundColor().equals(Color.BLACK)) {
                    cell.setBackgroundColor(Color.WHITE);
                    cell.repaint();
                }
            }
        }
    }

    /**
     * Returns the instance of a cell at the location on the board given by the parameters.
     * Returns null if the cell is not a part of the board.
     *
     * @param x the x-coordinate of the cell.
     * @param y the y-coordinate of the cell.
     * @return the Cell at the location corresponding to the requested x and y coordinates.
     */
    public Cell getCellAt(int x, int y) {
        if (x < 0 || x >= width || y < 0 || y >= height) {
            return null;
        }
        return cells[x][y];
    }

    /**
     * Gets the delay value.
     *
     * @return the delay.
     */
    public int getDelay() {
        return delay;
    }

    /**
     * Changes the delay between each iteration.
     *
     * @param delay the desired delay.
     */
    public void setDelay(int delay) {
        this.delay = delay;
    }

    /**
     * Sets the label showing the number of iterations.
     *
     * @param iterationLabel the JLabel object created by the ControlPanel.
     */
    public void setIterationLabel(JLabel iterationLabel) {
        this.iterationLabel = iterationLabel;
    }

    /**
     * Controls the state of the application.
     *
     * @param paused the boolean value determining whether the program is paused or not.
     */
    public void setPaused(boolean paused) {
        isPaused = paused;
    }

}

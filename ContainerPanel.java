import javax.swing.*;
import java.awt.*;

/**
 * This class contains a panel that is located inside the window.
 * This panel contains two smaller panels, one for the Game of Life graphic visualisation
 * and one for the ControlPanel.
 * @author Jordan Hopley
 */
public class ContainerPanel extends JPanel {

    private Board board = new Board(50, 50);
    private ControlPanel controlPanel = new ControlPanel(board);

    /**
     * Creates a new ContainerPanel with a BorderLayout.
     * This adds the created Board and ControlPanel objects to the ContainerPanel.
     */
    public ContainerPanel() {
        setLayout(new BorderLayout());
        add(board, BorderLayout.CENTER);
        add(controlPanel, BorderLayout.SOUTH);
    }

}

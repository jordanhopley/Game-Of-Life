import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class provides the graphical interface for the control panel.
 * The control panel allows the user to start, restart and quit the application; as well
 * as changing the speed at which it runs.
 * @author Jordan Hopley
 */
public class ControlPanel extends JPanel implements ActionListener, ChangeListener {

    private Board board;

    private Button start = new Button("Start");
    private Button restart = new Button("Restart");
    private Button quit = new Button("Quit");


    /**
     * Creates and initialise a new ControlPanel with all of the necessary control buttons.
     *
     * @param board the Board which this ControlPanel will control.
     */
    public ControlPanel(Board board) {
        this.board = board;

        JSlider delaySlider = new JSlider(board.getDelay()/5, board.getDelay()*5, board.getDelay());
        JLabel iterationLabel = new JLabel("Iterations: " + 0);

        board.setIterationLabel(iterationLabel);
        delaySlider.setInverted(true);

        start.addActionListener(this);
        restart.addActionListener(this);
        quit.addActionListener(this);
        delaySlider.addChangeListener(this);

        add(iterationLabel);
        add(start);
        add(restart);
        add(quit);
        add(delaySlider);
    }

    /**
     * When a button in the ControlPanel is clicked, an action is performed based
     * on that button and the state of the board.
     *
     * @param e the ActionEvent allowing control of the button that is clicked.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        Button button = (Button) e.getSource();
        if (button == start) {
            if (button.getText().equalsIgnoreCase("Start")) {
                button.setText("Pause");
                board.setPaused(false);

            } else if (button.getText().equalsIgnoreCase("Pause")) {
                button.setText("Resume");
                board.setPaused(true);

            } else if (button.getText().equalsIgnoreCase("Resume")) {
                button.setText("Pause");
                board.setPaused(false);
            }
        }

        if (button == restart) {
            start.setText("Start");
            board.setPaused(true);
            board.restart();
        }

        if (button == quit) {
            System.exit(0);
        }
    }

    /**
     * Changes the delay value in Board to match the position of the JSlider.
     *
     * @param e the ChangeEvent that is invoked when the JSlider is acted upon.
     */
    @Override
    public void stateChanged(ChangeEvent e) {
        JSlider jSlider = (JSlider) e.getSource();
        board.setDelay(jSlider.getValue());
    }

}

import javax.swing.*;
import java.awt.*;

/**
 * This class provides a slightly graphically modified JButton.
 * The program is able to change the state of a cell by modifying
 * the background colour of a Button.
 * @author Jordan Hopley
 */
public class Button extends JButton {

    private Color backgroundColor = Color.WHITE;

    /**
     * Creates a new Button.
     */
    public Button() {
        setFocusPainted(false);
        setContentAreaFilled(false);
    }

    public Button(String text) {
        setText(text);
        setFocusPainted(false);
        setContentAreaFilled(false);
    }

    /**
     * Paints the button according to the preferred background colour.
     *
     * @param g the buttons paint component.
     */
    @Override
    public void paintComponent(Graphics g) {
        g.setColor(backgroundColor);
        g.fillRect(0, 0, getWidth(), getHeight());

        super.paintComponent(g);
    }

    /**
     * Changes the background colour of a button.
     *
     * @param backgroundColor the desired background colour.
     */
    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    /**
     * Gets the colour of a button.
     *
     * @return the Color of a button.
     */
    public Color getBackgroundColor() {
        return backgroundColor;
    }

}

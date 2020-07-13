import javax.swing.*;
import java.awt.*;

/**
 * This class provides the window for the application.
 * @author Jordan Hopley
 */
public class Window extends JFrame {

    /**
     * Creates a new Window of given width and height.
     *
     * @param width the width of the window, in pixels.
     * @param height the height of the window, in pixels.
     * @param title the title of the window.
     */
    public Window(int width, int height, String title) {
        setTitle(title);
        setMinimumSize(new Dimension(200, 200));
        setSize(width, height);
        setResizable(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        setContentPane(new ContainerPanel());

        // Must revalidate or frame will need resizing to update the container panel
        revalidate();
    }

}
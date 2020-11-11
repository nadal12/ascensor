package elevator.view.components;


import javax.swing.*;
import java.awt.*;

public class Floor extends JComponent {

    private static int MARGIN = 5;
    private static int WALL_WIDTH = 100;
    private int liftWidth, floorHeight;

    public Floor(int floorHeight, int liftWidth) {
        this.floorHeight = floorHeight;
        this.liftWidth = liftWidth;
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        graphics.fillRect(0, 0, WALL_WIDTH, floorHeight);
        graphics.fillRect(liftWidth + MARGIN, 0, WALL_WIDTH, floorHeight);
    }
}

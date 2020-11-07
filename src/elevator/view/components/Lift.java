package elevator.view.components;

import javax.swing.*;
import java.awt.*;

public class Lift extends JComponent {

    private final int WIDTH = 180;
    private final int HEIGHT = 200;
    private int positionX, positionY;

    public Lift(int positionX, int positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        graphics.drawRect(positionX, positionY, WIDTH, HEIGHT);

    }
}

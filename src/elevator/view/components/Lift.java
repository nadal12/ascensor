package elevator.view.components;

import javax.swing.*;
import java.awt.*;

public class Lift extends JComponent {

    private int positionX, positionY, width, height;

    public Lift(int positionX, int positionY, int width, int height) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.height = height;
        this.width = width;
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        graphics.drawRect(positionX, 5, width, height);

    }
}

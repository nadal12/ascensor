package elevator.view.components;

import javax.swing.*;
import java.awt.*;

public class Floor extends JComponent {

    private int positionX;
    private int positionY;

    public Floor(int positionX, int positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        graphics.drawRect(positionX,positionY, 100, 200);
    }
}

package elevator.view.components;


import javax.swing.*;
import java.awt.*;

public class Floor extends JPanel {

    private int positionX;
    private int positionY;
    private int floorHeight;

    public Floor(int positionX, int positionY, int floorHeight) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.floorHeight = floorHeight;
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        graphics.fillRect(positionX, positionY, 100, floorHeight);
        graphics.fillRect(positionX + 300, positionY, 100, floorHeight);
    }
}

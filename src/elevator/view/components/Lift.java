package elevator.view.components;

import javax.swing.*;
import java.awt.*;

public class Lift extends JPanel {

    private int positionX, width, height;
    private int positionY;

    public Lift(int positionX, int positionY, int width, int height) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.height = height;
        this.width = width;
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        graphics.drawRect(positionX, positionY, width, height);
    }

    public void goUp() {
        if (positionY >= 5) {
            positionY = positionY - height;
        }
    }

    public void goDown() {
        // TODO comprovacio d'errors.
        positionY = positionY + height;
        repaint();
    }

    public void setFloor() {
        //Pendent de fer
    }
}

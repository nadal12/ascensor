package elevator.view.components;

import javax.swing.*;
import java.awt.*;

public class Lift extends JPanel {

    private int numberOfFloors, floorHeight;
    private int positionY;
    private int previousY;
    private int actualFloor = 0;

    public Lift(int numberOfFloors, int floorHeight) {
        this.numberOfFloors = numberOfFloors;
        this.floorHeight = floorHeight;
        this.setBackground(new Color(13, 101, 111));
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        graphics.setColor(Color.pink);

        if (positionY == 0) {
            positionY = super.getHeight() - 12 - floorHeight;
        }
        positionY = super.getHeight() - 5 - ((actualFloor + 1) * (floorHeight + 5));
        graphics.fillRect(210, positionY, 180, floorHeight);
    }

    public void goUp() {
        if (!(actualFloor == numberOfFloors - 1)) {
            //previousY = positionY;
            actualFloor++;
            repaint();
            //animate();
        }
    }

    public void goDown() {
        if (actualFloor > 0) {
           // previousY = positionY;
            actualFloor--;
            repaint();
           // animate();
        }
    }

    public void setFloor(int floor) {
        if (floor >= 0 && floor < numberOfFloors) {
           // previousY = positionY;
            actualFloor = floor;
            repaint();
           // animate();
        }
    }

    public int getActualFloor() {
        return actualFloor;
    }

    public void animate() {
        int target = super.getHeight() - 5 - ((actualFloor + 1) * (floorHeight + 5));
        // TODO Animació.
        while (previousY > target) {
            positionY = previousY;
            previousY--;
            repaint();
            /*super.revalidate();
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
        }
    }
}

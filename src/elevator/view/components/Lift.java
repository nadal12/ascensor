package elevator.view.components;

import javax.swing.*;
import java.awt.*;

public class Lift extends JPanel {

    private final int numberOfFloors;
    private static final int PADDING = 5;
    private static final int SMALL_PADDING = 2;
    private int actualFloor = 0;
    private boolean doorOpen = true;

    public Lift(int numberOfFloors) {
        this.numberOfFloors = numberOfFloors;
        this.setBackground(new Color(13, 101, 111));
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        graphics.setColor(Color.pink);
        int floorHeight = getHeight() / numberOfFloors;
        int floorWidth = getWidth() - (PADDING * 2);
        int YPosition = getHeight() - (floorHeight * (actualFloor + 1));

        graphics.fillRect(PADDING, YPosition, floorWidth, floorHeight);

        if (doorOpen) {
            graphics.setColor(Color.DARK_GRAY);
            graphics.fillRect(PADDING * 2, YPosition + PADDING, floorWidth / 10, floorHeight - PADDING * 2);
            graphics.fillRect(floorWidth - PADDING * 4, YPosition + PADDING, floorWidth / 10, floorHeight - PADDING * 2);
        } else {
            graphics.setColor(Color.DARK_GRAY);
            graphics.fillRect(PADDING * 2, YPosition + PADDING, (floorWidth / 2) - SMALL_PADDING, floorHeight - PADDING * 2);
            graphics.fillRect((floorWidth / 2) + PADDING * 2, YPosition + PADDING, (floorWidth / 2) - PADDING - SMALL_PADDING * 2, floorHeight - PADDING * 2);
        }
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

    public void closeDoor() {
        doorOpen = false;
        repaint();
    }

    public void openDoor() {
        doorOpen = true;
        repaint();
    }

    public int getActualFloor() {
        return actualFloor;
    }

    public boolean isDoorOpen() {
        return doorOpen;
    }

  /*  public void animate() {
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
            }
        }
    }*/
}

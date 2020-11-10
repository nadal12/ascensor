package elevator.view.components;

import javax.swing.*;
import java.awt.*;

public class Scene extends JComponent {

    private int numberOfFloors;
    private final static int MARGIN_BETWEEN_FLOORS = 5;

    public Scene(int numberOfFloors) {
        this.numberOfFloors = numberOfFloors;
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        // Cálculos.
        int floorHeight = (getHeight() - (MARGIN_BETWEEN_FLOORS * numberOfFloors)) / numberOfFloors;
        int drawPointer = MARGIN_BETWEEN_FLOORS;

        for (int i = 0; i < numberOfFloors; i++) {

            Floor floor = new Floor(70, drawPointer, floorHeight);
            floor.paintComponent(graphics);
            drawPointer = drawPointer + floorHeight + MARGIN_BETWEEN_FLOORS;

            Lift lift = new Lift(180, 120, 180, floorHeight);
            lift.paintComponent(graphics);
        }
    }

    public int getNumberOfFloors() {
        return numberOfFloors;
    }
}

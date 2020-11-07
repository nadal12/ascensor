package elevator.view.components;

import javax.swing.*;
import java.awt.*;

public class Scene extends JComponent {

    private int numberOfFloors;

    public Scene(int numberOfFloors) {
        this.numberOfFloors = numberOfFloors;
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        for (int i = 0; i < numberOfFloors; i++) {
            Floor floor = new Floor(100, 10);

            floor.paintComponent(graphics);
        }
    }

    public int getNumberOfFloors() {
        return numberOfFloors;
    }
}

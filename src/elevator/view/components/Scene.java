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

            //Dibujar escena aqui.
            Floor floor = new Floor(70, 10 + (i * 210));
            floor.paintComponent(graphics);

            Lift lift = new Lift(180, 120);
            lift.paintComponent(graphics);
        }
    }

    public int getNumberOfFloors() {
        return numberOfFloors;
    }
}

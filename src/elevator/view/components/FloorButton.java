package elevator.view.components;

import javax.swing.*;

public class FloorButton {

    private final int floor;
    private final JButton button;

    public FloorButton(int floor, JButton button) {
        this.floor = floor;
        this.button = button;
    }

    public int getFloor() {
        return floor;
    }

    public JButton getButton() {
        return button;
    }
}

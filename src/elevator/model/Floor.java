package elevator.model;

public class Floor {

    private final int floorNumber;
    private int direction;
    private boolean selected;

    public Floor(int floorNumber, int direction, boolean selected) {
        this.floorNumber = floorNumber;
        this.direction = direction;
        this.selected = selected;
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public int getDirection() {
        return direction;
    }

    public boolean isSelected() {
        return selected;
    }

    @Override
    public String toString() {
        return "Floor{" +
                "floor=" + floorNumber +
                ", direction=" + direction +
                ", selected=" + selected +
                "}\n";
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }
}

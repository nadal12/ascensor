package elevator.model;

public class PendingFloor {

    private final int floor;
    private int direction;

    public PendingFloor(int floor, int direction) {
        this.floor = floor;
        this.direction = direction;
    }

    public int getFloor() {
        return floor;
    }

    public int getDirection() {
        return direction;
    }

    @Override
    public String toString() {
        return "PendingFloor{" +
                "floor=" + floor +
                ", direction=" + direction +
                '}';
    }
}

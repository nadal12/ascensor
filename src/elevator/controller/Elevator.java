package elevator.controller;

import elevator.MVCEvents;
import elevator.model.Floor;

import java.util.ArrayList;

public class Elevator extends Thread {

    public static final int DIRECTION_NONE = 0;
    public static final int DIRECTION_UP = 1;
    public static final int DIRECTION_DOWN = -1;
    private static final int TIME_BETWEEN_FLOORS = 1000;
    private static final int DOOR_TIME = 1200;

    private final ArrayList<Floor> floors;
    private Floor actualFloor;
    private final int numberOfFoors;

    private final MVCEvents mvcEvents;

    public Elevator(int numberOfFloors, MVCEvents mvcEvents) {
        this.mvcEvents = mvcEvents;
        floors = new ArrayList<>();
        this.numberOfFoors = numberOfFloors;
        createFloors(numberOfFloors);

        //Planta 0 com a planta actual.
        this.actualFloor = floors.get(0);
    }

    public void run() {

        while (true) {

            // Si nigú crida a l'ascensor, es queda amb standby.
            Floor firstCalledFloor;
            while ((firstCalledFloor = selectedFloors()) == null) {
                Thread.yield();
            }

            //Comprovar que no sigui la mateixa a la que ja esta.
            if (!(firstCalledFloor == actualFloor)) {

                switch (getDirection(firstCalledFloor)) {
                    case DIRECTION_UP:

                        for (int i = actualFloor.getFloorNumber(); i < floors.size() && selectedFloorsAbove(); i++) {
                            setFloor(floors.get(i));
                            if ((actualFloor.isSelected()) && (actualFloor.getDirection() == DIRECTION_NONE || actualFloor.getDirection() == DIRECTION_UP)) {
                                visitFloor();
                            }
                        }

                        break;
                    case DIRECTION_DOWN:

                        for (int i = actualFloor.getFloorNumber(); i >= 0 && selectedFloorsBelow(); i--) {
                            setFloor(floors.get(i));
                            if ((actualFloor.isSelected()) && (actualFloor.getDirection() == DIRECTION_NONE || actualFloor.getDirection() == DIRECTION_DOWN)) {
                                visitFloor();
                            }
                        }
                }
            } else {
                visitFloor();
            }
        }
    }

    private void visitFloor() {
        unselectFloor();
        openDoor();
        closeDoor();
    }

    private void unselectFloor() {
        turnOffFloorButton(actualFloor.getFloorNumber());
        floors.get(actualFloor.getFloorNumber()).setSelected(false);
    }

    private boolean selectedFloorsAbove() {
        for (int i = actualFloor.getFloorNumber(); i < floors.size(); i++) {
            if (floors.get(i).isSelected()) {
                return true;
            }
        }
        return false;
    }

    private boolean selectedFloorsBelow() {
        for (int i = actualFloor.getFloorNumber(); i >= 0; i--) {
            if (floors.get(i).isSelected()) {
                return true;
            }
        }
        return false;
    }

    private void turnOffFloorButton(int floorNumber) {
        mvcEvents.getView().notify("resetButtonColor, " + floorNumber);
    }

    private void setFloor(Floor floor) {
        espera(TIME_BETWEEN_FLOORS);
        mvcEvents.getView().notify("setFloor, " + floor.getFloorNumber());
        actualFloor = floor;
    }

    private void openDoor() {
        mvcEvents.getView().notify("display, Obrint portes...");
        espera(DOOR_TIME);
        mvcEvents.getView().notify("openDoor");
        mvcEvents.getView().notify("display, Portes obertes");
        espera(DOOR_TIME);
    }

    private void closeDoor() {
        mvcEvents.getView().notify("display, Tancant portes...");
        espera(DOOR_TIME);
        mvcEvents.getView().notify("closeDoor");
        mvcEvents.getView().notify("display, Portes tancades");
        espera(DOOR_TIME);
        mvcEvents.getView().notify("display, Planta " + actualFloor.getFloorNumber());
    }

    private int getDirection(Floor firstCalledFloor) {
        if (actualFloor.getFloorNumber() == 0) {
            return DIRECTION_UP;
        } else if (actualFloor.getFloorNumber() == numberOfFoors - 1) {
            return DIRECTION_DOWN;
        } else {

            if (selectedFloorsAbove()) {
                return DIRECTION_UP;
            }

            if (selectedFloorsBelow()) {
                return DIRECTION_DOWN;
            }
            return DIRECTION_DOWN;
        }
    }

    private Floor selectedFloors() {
        for (Floor floor : floors) {
            if (floor.isSelected()) {
                return floor;
            }
        }
        return null;
    }

    private void createFloors(int numberOfFloors) {
        for (int i = 0; i < numberOfFloors; i++) {
            floors.add(new Floor(i, DIRECTION_NONE, false));
        }
    }

    public void selectFloor(int floorNumber, int direction) {
        floors.get(floorNumber).setSelected(true);
        floors.get(floorNumber).setDirection(direction);
    }

    private void espera(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
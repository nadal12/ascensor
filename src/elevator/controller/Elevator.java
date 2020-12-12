package elevator.controller;
import elevator.MVCEvents;
import elevator.model.Floor;

import java.util.ArrayList;

public class Elevator extends Thread {

    public static final int DIRECTION_UP = 1;
    public static final int DIRECTION_DOWN = -1;
    public static final int DIRECTION_NONE = 0;
    public static final int WAITING = 1500;

    private MVCEvents mvcEvents;
    private ArrayList<Floor> floors = new ArrayList<>();
    private int actualFloor = 0;

    public Elevator(MVCEvents mvcEvents) {
        this.mvcEvents = mvcEvents;
    }

    public void run() {

        while (true) {

            //Esperar a que els usuaris pitjin botons.
            while (floors.isEmpty()) {
                Thread.yield();
            }

            //Tancar portes.
            mvcEvents.getView().notify("closeDoor");

            for (int i = 0; i < floors.size(); i++) {
                for (int j = actualFloor; j <= floors.get(i).getFloor(); j++) {
                    actualFloor = j;
                    mvcEvents.getView().notify("setFloor, " + j);
                    espera(1000);
                }
                floors.remove(floors.get(i));
                mvcEvents.getView().notify("openDoor");
            }
        }
    }

    private Floor getNextFloor(int directionUp) {
        if (directionUp == DIRECTION_UP) {
            for (int i = 0; i < floors.size(); i++) {
                if (floors.get(i).getDirection() >= 0) {
                    return floors.get(i);
                }
            }
        } else {
            for (int i = 0; i < floors.size(); i++) {
                if (floors.get(i).getDirection() < 0) {
                    return floors.get(i);
                }
            }
        }
        return null;
    }

    private void espera(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void addPendingFloor(int floor, int direction) {
        floors.add(new Floor(floor, direction));
        System.out.println(floors);
    }
}
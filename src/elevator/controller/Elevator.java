package elevator.controller;
import elevator.MVCEvents;
import elevator.model.PendingFloor;

import java.util.ArrayList;

public class Elevator extends Thread {

    public static final int DIRECTION_UP = 1;
    public static final int DIRECTION_DOWN = -1;
    public static final int DIRECTION_NONE = 0;
    public static final int WAITING = 1500;

    private MVCEvents mvcEvents;
    private ArrayList<PendingFloor> pendingFloors = new ArrayList<>();
    private int actualFloor = 0;

    public Elevator(MVCEvents mvcEvents) {
        this.mvcEvents = mvcEvents;
    }

    public void run() {

        while (true) {

            //Esperar a que els usuaris pitjin botons.
            while (pendingFloors.isEmpty()) {
                Thread.yield();
            }

            for (int i = 0; i < pendingFloors.size(); i++) {

                //Calcular direcció de l'ascensor.
                int direction = (pendingFloors.get(i).getFloor() - actualFloor < 0) ? DIRECTION_DOWN:DIRECTION_UP;

                if (direction == DIRECTION_UP) {
                    for (int j = 0; j < pendingFloors.size(); j++) {
                        PendingFloor pendingFloor = getNextFloor(DIRECTION_UP);

                        for (int k = actualFloor; k <= pendingFloor.getFloor(); k++) {
                            espera(WAITING);
                            actualFloor = k;
                            mvcEvents.getView().notify("setFloor, " + actualFloor);
                        }

                        mvcEvents.getView().notify("openDoor");
                        pendingFloors.remove(pendingFloor);
                        espera(WAITING);
                    }
                } else {

                }
            }

            //Tancar portes.
            mvcEvents.getView().notify("closeDoor");


        }
    }

    private PendingFloor getNextFloor(int directionUp) {
        if (directionUp == DIRECTION_UP) {
            for (int i = 0; i < pendingFloors.size(); i++) {
                if (pendingFloors.get(i).getDirection() >= 0) {
                    return pendingFloors.get(i);
                }
            }
        } else {
            for (int i = 0; i < pendingFloors.size(); i++) {
                if (pendingFloors.get(i).getDirection() < 0) {
                    return pendingFloors.get(i);
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
        pendingFloors.add(new PendingFloor(floor, direction));
        System.out.println(pendingFloors);
    }
}
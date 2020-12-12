package elevator.controller;

import elevator.EventsListener;
import elevator.MVCEvents;

/**
 * @author nadalLlabres
 */
public class Controller implements EventsListener {

    private final MVCEvents mvcEvents;
    private Elevator elevator;

    public Controller(MVCEvents mvcEvents) {
        this.mvcEvents = mvcEvents;
    }

    @Override
    public void notify(String message) {
        System.out.println("Mensaje en Control: " + message);

        if (message.startsWith("Start")) {
            elevator = new Elevator(Integer.parseInt(message.split(", ")[1]), mvcEvents);
            elevator.start();
        } else if (message.startsWith("keypad")) {
            elevator.selectFloor(Integer.parseInt(message.split(", ")[1]), Elevator.DIRECTION_NONE);
        } else if (message.startsWith("floorButton")) {
            elevator.selectFloor(Integer.parseInt(message.split(", ")[1]), Integer.parseInt(message.split(", ")[2]));
        }
    }
}

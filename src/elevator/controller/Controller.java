package elevator.controller;

import elevator.EventsListener;
import elevator.MVCEvents;

/**
 * @author nadalLlabres
 */
public class Controller implements EventsListener {

    private MVCEvents mvcEvents;
    private Elevator elevator;

    public Controller(MVCEvents mvcEvents) {
        this.mvcEvents = mvcEvents;
    }

    @Override
    public void notify(String message) {
        System.out.println("Mensaje en Control: " + message);

        if (message.startsWith("Start")) {
            elevator = new Elevator(mvcEvents);
            elevator.start();
        } else if (message.startsWith("keypad")) {
            elevator.addPendingFloor(Integer.parseInt(message.split(", ")[1]));
        }
    }

}

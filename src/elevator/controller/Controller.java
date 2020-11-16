package elevator.controller;

import elevator.EventsListener;
import elevator.MVCEvents;

import java.io.IOException;

/**
 * @author nadalLlabres
 */
public class Controller implements EventsListener {

    private MVCEvents mvcEvents;
    private Elevator elevator;

    public Controller(MVCEvents mvcEvents) {
        this.mvcEvents = mvcEvents;
        try {
            elevator = new Elevator();
            elevator.simulateElevator(mvcEvents);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Elevator getElevator() {
        return elevator;
    }

    @Override
    public void notify(String message) {
        System.out.println("Mensaje en Control: " + message);
    }

}

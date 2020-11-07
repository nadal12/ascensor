package elevator.controller;

import elevator.EventsListener;
import elevator.MVCEvents;

import java.io.IOException;

/**
 * @author nadalLlabres
 */
public class Controller implements EventsListener {

    private MVCEvents mvcEvents;

    public Controller(MVCEvents mvcEvents) {
        this.mvcEvents = mvcEvents;
        try {
            new Elevator().simulateElevator();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void notify(String message) {
        System.out.println("Mensaje en Control: " + message);
    }

}

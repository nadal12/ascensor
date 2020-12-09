package elevator.model;

import elevator.EventsListener;
import elevator.MVCEvents;

/**
 * @author nadalLlabres
 */
public class Model implements EventsListener {

    public Model(MVCEvents mvcEvents) {
    }

    @Override
    public void notify(String message) {
        System.out.println("Mensaje en Modelo: " + message);
    }
}

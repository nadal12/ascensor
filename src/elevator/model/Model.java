package elevator.model;

import elevator.EventsListener;
import elevator.MVCEvents;

/**
 * @author nadalLlabres
 */
public class Model implements EventsListener {

    private MVCEvents mvcEvents;

    public Model(MVCEvents mvcEvents) {
        this.mvcEvents = mvcEvents;
    }

    @Override
    public void notify(String message) {
        System.out.println("Mensaje en Modelo: " + message);
    }
}

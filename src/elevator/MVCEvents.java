package elevator;

import elevator.controller.Controller;
import elevator.model.Model;
import elevator.view.View;

public class MVCEvents {

    private Model model;        // Punter al Model del patró
    private View view;          // Punter a la Vista del patró
    private Controller controller;    // punter al Controlador del patró

    /**
     * Construcció de l'esquema MVC
     */
    private void init() {
        view = new View("Elevator - Sistemes Intel·ligents", this);
        view.start();
        controller = new Controller(this);
        model = new Model(this);
    }

    public static void main(String[] args) {
        (new MVCEvents()).init();
    }

    public Model getModel() {
        return model;
    }

    public View getView() {
        return view;
    }

    public Controller getController() {
        return controller;
    }
}

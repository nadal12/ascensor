package elevator.view;

import elevator.ErrorLog;
import elevator.EventsListener;
import elevator.MVCEvents;
import elevator.view.components.Scene;

import javax.swing.*;
import java.awt.*;

/**
 * @author nadalLlabres
 */
public class View extends JFrame implements EventsListener {

    /**
     * Constants
     */
    private static final int DEFAULT_WIDTH = 600;
    private static final int DEFAULT_HEIGHT = 700;

    private int numberOfFloors;
    private Scene scene;

    private MVCEvents mvcEvents;

    /**
     * Constructor de la vista.
     * <p>
     * Configura la interfaz de Swing.
     *
     * @param title     titulo de la ventana
     * @param mvcEvents clase principal que gestiona el patrón MVC
     */
    public View(String title, MVCEvents mvcEvents) {
        super(title);

        try {
            //Estilo de los elementos gráficos propios del sistema operativo.
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

            //Dark GUI - Darcula by Konstantin Bulenkov -  https://github.com/bulenkov/Darcula
            // UIManager.setLookAndFeel(new DarculaLaf());
        } catch (Exception e) {
            ErrorLog.logException(e);
        }

        this.mvcEvents = mvcEvents;
        getContentPane().setLayout(new BorderLayout());
        configureUI();
    }

    /**
     * Configurar la interfaz gráfica de usuario con Java Swing
     */
    private void configureUI() {
        setNumberOfFloors();
        configureScene();
    }

    private void setNumberOfFloors() {
        String[] options = {"2", "3", "4", "5", "6", "7", "8", "9", "10"};

        // Cuadro de diálogo inicial.
        String numberOfFloors = (String) JOptionPane.showInputDialog(null,
                "¿Cuantas plantas tiene el edificio?", "Selecciona el número de plantas",
                JOptionPane.QUESTION_MESSAGE,
                null, options, options[2]);

        // Si se aprieta el botón cancelar.
        if (numberOfFloors == null) {
            System.exit(0);
        }

        this.numberOfFloors = Integer.parseInt(numberOfFloors);
    }

    public void configureScene() {
        scene = new Scene(numberOfFloors);
        add(scene);
        this.getContentPane().setBackground(new Color(13, 101, 111));
    }

    /**
     * Mostrar la interfaz y configurar características de la ventana.
     */
    public void start() {
        setSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
        setMinimumSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        // TODO intentar adaptar el resize.
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        revalidate();
        repaint();
    }

    @Override
    public void notify(String message) {
        System.out.println("Mensaje en Vista: " + message);

        if (message.startsWith("Baixa")) {
            scene.getLift().goDown();
            scene.repaint();

        }
    }
}

package elevator.view;

import elevator.ErrorLog;
import elevator.EventsListener;
import elevator.MVCEvents;
import elevator.view.components.Floor;
import elevator.view.components.Lift;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class View extends JFrame implements EventsListener {

    /**
     * Constants
     */
    private static final int DEFAULT_WIDTH = 1200;
    private static final int DEFAULT_HEIGHT = 700;

    private int numberOfFloors = 5;
    private JPanel scene;
    private JPanel keypad;

    // Scene attributes.
    private final static int MARGIN_BETWEEN_FLOORS = 5;
    private Lift lift;
    private ArrayList<Integer> selectedFloors = new ArrayList<>();

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
        setLayout(new GridLayout(1,2));
        setNumberOfFloors();
        configureScene();
        configureKeyPad();
        start();
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

    private void configureKeyPad() {
        keypad = new JPanel();

        // Se pone numberOfFloors + 1 para que haya sitio para el display.
        keypad.setLayout(new GridLayout(numberOfFloors + 1, 1));

        JLabel display = new JLabel("PLANTA 0");
        display.setFont(new Font(display.getName(), Font.PLAIN, 50));
        display.setVerticalAlignment(SwingConstants.CENTER);
        display.setHorizontalAlignment(SwingConstants.CENTER);

        keypad.add(display);

        for (int i = 0; i < numberOfFloors; i++) {
            JButton button = new JButton(String.valueOf(i));
            button.setFont(new Font(display.getName(), Font.BOLD, 50));
            keypad.add(button);
        }

        add(keypad);
    }

    public void configureScene() {
        scene = new JPanel();
        scene.setLayout(new GridLayout(1, 3));

        createLeftButtons();
        createLift();
        createRightButtons();

        add(scene);
    }

    private void createRightButtons() {
        JPanel leftButtons = new JPanel();
        leftButtons.setLayout(new GridLayout(numberOfFloors, 1));

        for (int i = 0; i < numberOfFloors; i++) {
            leftButtons.add(new JButton(new ImageIcon(new ImageIcon("src\\elevator\\model\\images\\down.png").getImage().getScaledInstance(85, 85, Image.SCALE_DEFAULT))));
        }

        scene.add(leftButtons);
    }

    private void createLift() {
        lift = new Lift(numberOfFloors);
        scene.add(lift);
    }

    private void createLeftButtons() {
        JPanel leftButtons = new JPanel();
        leftButtons.setLayout(new GridLayout(numberOfFloors, 1));

        for (int i = 0; i < numberOfFloors; i++) {
            leftButtons.add(new JButton(new ImageIcon(new ImageIcon("src\\elevator\\model\\images\\up.png").getImage().getScaledInstance(85, 85, Image.SCALE_DEFAULT))));
        }

        scene.add(leftButtons);
    }

    /**
     * Mostrar la interfaz y configurar características de la ventana.
     */
    public void start() {
        setSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
        setMinimumSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setResizable(true);
        setLocationRelativeTo(null);
        setVisible(true);
        revalidate();
        repaint();
        mvcEvents.getController().notify("Start");
    }

    @Override
    public void notify(String message) {

    }
}

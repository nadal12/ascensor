package elevator.view;

import elevator.ErrorLog;
import elevator.EventsListener;
import elevator.MVCEvents;
import elevator.controller.Elevator;
import elevator.view.components.FloorButton;
import elevator.view.components.KeyPad;
import elevator.view.components.Lift;

import javax.swing.*;
import java.awt.*;

public class View extends JFrame implements EventsListener {

    /**
     * Constants
     */
    private static final int DEFAULT_WIDTH = 1200;
    private static final int DEFAULT_HEIGHT = 700;

    private int numberOfFloors;
    private JPanel scene;
    private KeyPad keypad;
    private final FloorButton[] upFloorButtons = new FloorButton[10];
    private final FloorButton[] downFloorButtons = new FloorButton[10];

    // Scene attributes.
    private Lift lift;

    private final MVCEvents mvcEvents;

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
            //UIManager.setLookAndFeel(new DarculaLaf());
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
        setLayout(new GridLayout(1, 2));
        setNumberOfFloors();
        configureScene();
        configureKeyPad();
    }

    private void setNumberOfFloors() {
        String[] options = {"2", "3", "4", "5", "6", "7", "8", "9", "10"};

        // Cuadro de diálogo inicial.
        String answer = (String) JOptionPane.showInputDialog(null,
                "¿Cuantas plantas tiene el edificio?", "Selecciona el número de plantas",
                JOptionPane.QUESTION_MESSAGE,
                null, options, options[2]);

        // Si se aprieta el botón cancelar.
        if (answer == null) {
            System.exit(0);
        }

        numberOfFloors = Integer.parseInt(answer);
    }

    private void configureKeyPad() {
        keypad = new KeyPad(numberOfFloors, mvcEvents);
        add(keypad);
    }

    private void changeButtonColor(JButton button, Color color) {
        button.setBackground(color);
        button.setContentAreaFilled(false);
        button.setOpaque(true);
    }

    public void configureScene() {
        scene = new JPanel();
        scene.setLayout(new GridLayout(1, 3));

        createUpButtons();
        createLift();
        createDownButtons();

        add(scene);
    }

    private void createDownButtons() {
        JPanel leftButtons = new JPanel();
        leftButtons.setLayout(new GridLayout(numberOfFloors, 1));

        for (int i = 0; i < numberOfFloors; i++) {
            FloorButton floorButton = new FloorButton(Math.abs(i - numberOfFloors + 1), new JButton(new ImageIcon(new ImageIcon("src\\elevator\\model\\images\\down.png").getImage().getScaledInstance(85, 85, Image.SCALE_DEFAULT))));
            downFloorButtons[Math.abs(i - numberOfFloors + 1)] = floorButton;
            floorButton.getButton().addActionListener(e -> {
                changeButtonColor(floorButton.getButton(), new Color(255, 127, 127));
                mvcEvents.getController().notify("floorButton, " + floorButton.getFloor() + ", " + Elevator.DIRECTION_DOWN);
            });
            leftButtons.add(floorButton.getButton());
        }

        scene.add(leftButtons);
    }

    private void createLift() {
        lift = new Lift(numberOfFloors);
        scene.add(lift);
    }

    private void createUpButtons() {
        JPanel leftButtons = new JPanel();
        leftButtons.setLayout(new GridLayout(numberOfFloors, 1));

        for (int i = 0; i < numberOfFloors; i++) {
            FloorButton floorButton = new FloorButton(Math.abs(i - numberOfFloors + 1), new JButton(new ImageIcon(new ImageIcon("src\\elevator\\model\\images\\up.png").getImage().getScaledInstance(85, 85, Image.SCALE_DEFAULT))));
            upFloorButtons[Math.abs(i - numberOfFloors + 1)] = floorButton;
            floorButton.getButton().addActionListener(e -> {
                changeButtonColor(floorButton.getButton(), new Color(255, 127, 127));
                mvcEvents.getController().notify("floorButton, " + floorButton.getFloor() + ", " + Elevator.DIRECTION_UP);
            });
            leftButtons.add(floorButton.getButton());
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
        mvcEvents.getController().notify("Start, " + numberOfFloors);
        setDisplayText("Planta 0");
    }

    public void setDisplayText(String text) {
        keypad.setText(text);
    }

    @Override
    public void notify(String message) {

        if (message.startsWith("openDoor")) {
            lift.openDoor();
            System.out.println("Door status: " + lift.isDoorOpen());
        } else if (message.startsWith("closeDoor")) {
            lift.closeDoor();
        } else if (message.startsWith("setFloor")) {
            lift.setFloor(Integer.parseInt(message.split(", ")[1]));
            keypad.setText("Planta " + message.split(", ")[1]);
        } else if (message.startsWith("resetButtonColor")) {
            keypad.changeButtonColor(keypad.getKeyPadButtons()[Integer.parseInt(message.split(", ")[1])], null);
            changeButtonColor(upFloorButtons[Integer.parseInt(message.split(", ")[1])].getButton(), null);
            changeButtonColor(downFloorButtons[Integer.parseInt(message.split(", ")[1])].getButton(), null);
        } else if (message.startsWith("display")) {
            keypad.setText(message.split(", ")[1]);
        }
    }
}

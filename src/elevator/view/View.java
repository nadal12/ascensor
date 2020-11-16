package elevator.view;

import elevator.ErrorLog;
import elevator.EventsListener;
import elevator.MVCEvents;
import elevator.view.components.Floor;
import elevator.view.components.Lift;

import javax.naming.BinaryRefAddr;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

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
        // Cálculos.
        int floorHeight = (DEFAULT_HEIGHT - 50 - (MARGIN_BETWEEN_FLOORS * numberOfFloors)) / numberOfFloors;

        int drawPointer = MARGIN_BETWEEN_FLOORS;

        for (int i = 0; i < numberOfFloors; i++) {
            Floor floor = new Floor(floorHeight, 300, Math.abs(i - numberOfFloors + 1));

            add(floor).setBounds(100, drawPointer, DEFAULT_WIDTH, floorHeight);

            drawPointer = drawPointer + floorHeight + MARGIN_BETWEEN_FLOORS;
        }

        lift = new Lift(numberOfFloors, floorHeight);
        add(lift);
    }

    /**
     * Mostrar la interfaz y configurar características de la ventana.
     */
    public void start() {
        setSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
        setMinimumSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        revalidate();
        repaint();
    }

    public void selectDestinationFloor() {
        JFrame floorSelector = new JFrame();
        floorSelector.setUndecorated(true);
        floorSelector.setVisible(true);
        setEnabled(false); //Desactivar frame principal
        floorSelector.setResizable(false);
        floorSelector.pack();

        //Establecer tamaño del frame
        floorSelector.setLocationRelativeTo(null);
        floorSelector.setSize(DEFAULT_WIDTH/2, DEFAULT_HEIGHT/3);
        floorSelector.setAlwaysOnTop(true);

        JLabel text = new JLabel("Selecciona las plantas a las que deseas ir y pulsa confirmar");
        floorSelector.add(text, BorderLayout.NORTH);


        JPanel buttonsGrid = new JPanel();
        buttonsGrid.setLayout(new GridLayout(4, 3));
        for (int i = 0; i < numberOfFloors; i++) {
            JButton button = new JButton(String.valueOf(i + 1));
            button.addActionListener(e -> {

                //Cambiar color para que se vea que esta seleccionado.
                button.setBackground(Color.RED);
                button.setContentAreaFilled(false);
                button.setOpaque(true);

                //Añadir al array de seleccionados.
                if (!selectedFloors.contains(Integer.valueOf(button.getText()))) {
                    selectedFloors.add(Integer.valueOf(button.getText()));
                    System.out.println(selectedFloors);
                }

            });
            buttonsGrid.add(button);
        }

        floorSelector.add(buttonsGrid, BorderLayout.CENTER);

        //Botón de aceptar.
        JButton confirm = new JButton("Confirmar");
        floorSelector.add(confirm, BorderLayout.SOUTH);
        confirm.addActionListener(e -> {
            floorSelector.dispose();
            setEnabled(true); //Activar frame principal

            setAlwaysOnTop(true); //Para que no se quede minimizado.
            setAlwaysOnTop(false);
            mvcEvents.getController().getElevator().notify("floorsSelected");
        });
    }

    public ArrayList<Integer> getSelectedFloors() {
        return selectedFloors;
    }

    public int getNumberOfFloors() {
        return numberOfFloors;
    }

    public int getActualFloor() {
        return lift.getActualFloor();
    }

    public void setFloor(int floor) {
        lift.setFloor(floor);
        repaint();
    }

    @Override
    public void notify(String message) {
        System.out.println("Mensaje en Vista: " + message);

        if (message.startsWith("Baixa")) {
            lift.goDown();
            repaint();
        }

        if (message.startsWith("Puja")) {
            lift.goUp();
            repaint();
        }

        if (message.startsWith("selectFloors")) {
            selectDestinationFloor();
        }

        if (message.startsWith("closeDoor")) {
            lift.closeDoor();
        }

        if (message.startsWith("openDoor")) {
            lift.openDoor();
        }
    }
}

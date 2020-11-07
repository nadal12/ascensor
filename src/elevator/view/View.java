package elevator.view;

import com.sun.source.tree.ForLoopTree;
import elevator.ErrorLog;
import elevator.EventsListener;
import elevator.MVCEvents;
import elevator.view.components.Floor;
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
    private static final int DEFAULT_WIDTH = 800;
    private static final int DEFAULT_HEIGHT = 600;

    private static final int STATUS_READY = 1;
    private static final int STATUS_RUNNING = 2;
    private static final int STATUS_FINISH = 3;

    private MVCEvents mvcEvents;

    private int status = STATUS_READY;

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
        configureScene();
    }

    public void configureScene() {
        Scene scene = new Scene(3);
        add(scene);
        this.getContentPane().setBackground(new Color(13, 101,111));
    }

    /**
     * Mostrar la interfaz y configurar características de la ventana.
     */
    public void start() {
        setSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
        setMinimumSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        revalidate();
        repaint();
    }

    @Override
    public void notify(String message) {
        System.out.println("Mensaje en Vista: " + message);
    }
}

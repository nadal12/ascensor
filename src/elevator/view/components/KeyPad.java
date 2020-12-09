package elevator.view.components;

import elevator.MVCEvents;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class KeyPad extends JPanel {

    private JLabel display;
    private JButton[] keyPadButtons = new JButton[10];
    private MVCEvents mvcEvents;

    public KeyPad(int numberOfFloors, MVCEvents mvcEvents) {
        this.mvcEvents = mvcEvents;

        // Se pone numberOfFloors + 1 para que haya sitio para el display.
        setLayout(new GridLayout(numberOfFloors + 1, 1));

        display = new JLabel();
        display.setForeground(Color.RED);
        display.setFont(new Font(display.getName(), Font.PLAIN, 50));
        display.setVerticalAlignment(SwingConstants.CENTER);
        display.setHorizontalAlignment(SwingConstants.CENTER);

        display.setOpaque(true);
        display.setBackground(new Color(252, 255, 157));
        add(display);

        for (int i = 0; i < numberOfFloors; i++) {
            JButton button = new JButton(String.valueOf(i));
            button.setFont(new Font(display.getName(), Font.BOLD, 50));

            //TODO revisar
            button.setBackground(null);

            keyPadButtons[i] = button;

            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    changeButtonColor(button, new Color(255, 127, 127));
                    mvcEvents.getController().notify("keypad, " + button.getText());
                }
            });

            add(button);
        }
    }

    public void changeButtonColor(JButton button, Color color) {
        button.setBackground(color);
        button.setContentAreaFilled(false);
        button.setOpaque(true);
    }

    public JButton[] getKeyPadButtons() {
        return keyPadButtons;
    }

    public void setText(String text) {
        display.setText(text);
    }
}

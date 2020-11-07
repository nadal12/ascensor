package elevator.view.components;


import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Floor extends JPanel {

    private int positionX;
    private int positionY;

    public Floor(int positionX, int positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        //Dibujar planta aqui.
        graphics.fillRect(positionX, positionY, 100, 200);
        graphics.fillRect(positionX + 300, positionY, 100, 200);
    }
}

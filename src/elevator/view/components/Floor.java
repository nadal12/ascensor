package elevator.view.components;


import javax.swing.*;
import java.awt.*;

public class Floor extends JComponent {

    private static int MARGIN = 5;
    private static int WALL_WIDTH = 100;
    private int liftWidth, floorHeight, floorNumber;

    public Floor(int floorHeight, int liftWidth, int floorNumber) {
        this.floorHeight = floorHeight;
        this.liftWidth = liftWidth;
        this.floorNumber = floorNumber;

        ImageIcon adalt = new ImageIcon(new ImageIcon("src\\elevator\\model\\images\\up.png").getImage().getScaledInstance(85, floorHeight / 2, Image.SCALE_DEFAULT));
        ImageIcon abaix = new ImageIcon(new ImageIcon("src\\elevator\\model\\images\\down.png").getImage().getScaledInstance(85, floorHeight / 2, Image.SCALE_DEFAULT));

        JLabel floorNumberLabel = new JLabel("Floor: " + floorNumber);
        add(floorNumberLabel).setBounds(5, 0, 180 / 2, floorHeight / 2);

        JButton up = new JButton(adalt);

        add(up).setBounds(310, 45, 180 / 2, floorHeight / 4);

        JButton down = new JButton(abaix);
        add(down).setBounds(5, 45, 180 / 2, floorHeight / 4);
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        graphics.setColor(Color.ORANGE);
        graphics.fillRect(0, 0, WALL_WIDTH, floorHeight);
        graphics.fillRect(liftWidth + MARGIN, 0, WALL_WIDTH, floorHeight);
    }
}

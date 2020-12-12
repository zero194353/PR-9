import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.Random;


class ShapeRandomazer {
    static Random r = new Random();

    static Shape getRandomShape() {
        int shapeNumber = r.nextInt(3);
        if (shapeNumber == 1) {
            return new Rectangle(r.nextInt(1000), r.nextInt(600), r.nextInt(70), r.nextInt(100));
        } else if (shapeNumber == 2) {
            int radius = r.nextInt(1000);
            return new Ellipse2D.Double(r.nextInt(1000), r.nextInt(600), radius, radius);
        } else {
            int side = r.nextInt(700);
            return new Rectangle(r.nextInt(1000), r.nextInt(600), side, side);
        }
    }
}

class ShapeCreate extends JComponent {
    ArrayList<Shape> shapes = new ArrayList<>();

    ShapeCreate(Graphics g) {
        for (int i = 0; i < 20; i++) {
            shapes.add(ShapeRandomazer.getRandomShape());
        }
        this.paintComponent(g);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Random rand = new Random();
        int c = rand.nextInt(4);
        Graphics2D g2d = (Graphics2D) g;

        for (Shape s : shapes) {
            float red = rand.nextFloat();
            float green = rand.nextFloat();
            float blue = rand.nextFloat();
            Color randomColor = new Color(red, green, blue);
            g2d.setColor(randomColor);
            g2d.fill(s);
        }
    }
}


public class Main extends JPanel{
    public static void main(String[] args) {
        JFrame jFrame = getDefaultFrame();
        JPanel jPanel = new JPanel();
        jPanel.setBackground(Color.WHITE);
        JButton jButton = new JButton("Random generate");

        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jPanel.add(new ShapeCreate(jPanel.getGraphics()));
            }
        });

        jPanel.add(jButton);
        jFrame.add(jPanel);
        jFrame.setVisible(true);

    }

    static JFrame getDefaultFrame() {
        JFrame jFrame = new JFrame() {
        };
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setTitle("Shapes");
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        jFrame.setBounds(toolkit.getScreenSize().width / 4, toolkit.getScreenSize().height / 4, 1200, 800);
        return jFrame;
    }
}

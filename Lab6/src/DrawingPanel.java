import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.sql.Struct;
import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;
import java.util.List;

public class DrawingPanel extends JPanel {
    final MainFrame frame;
    final static int W = 800, H = 600;
    BufferedImage image; //the offscreen image
    Graphics2D graphics; //the "tools" needed to draw in the image
    List<Shape> shapes = new ArrayList<>();

    public DrawingPanel(MainFrame frame) {
        this.frame = frame; createOffscreenImage(); init();
    }
    private void createOffscreenImage() {
        image = new BufferedImage(W, H, BufferedImage.TYPE_INT_ARGB);
        graphics = image.createGraphics();
        graphics.setColor(Color.WHITE); //fill the image with white
        graphics.fillRect(0, 0, W, H);
    }

    private void init() {
        setPreferredSize(new Dimension(W, H)); //don’t use setSize. Why?
        setBorder(BorderFactory.createEtchedBorder()); //for fun
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                drawShape(e.getX(), e.getY()); repaint();
            } //Can’t use lambdas, JavaFX does a better job in these cases
        });
    }
    private void drawShape(int x, int y) {
        Random rng = new Random();
        int radius = (int)frame.configPanel.sizeField.getValue();//rng.nextInt(500);// ... TODO; //generate a random number
        int sides = 4;
        // if shapeField is Any, than the figure drawn will have
        // the nr of sides equal to the value in sidesField
        if(frame.configPanel.shapeField.getValue().equals("Any"))
            sides = (int)frame.configPanel.sidesField.getValue();//...TODO; //get the value from UI (in ConfigPanel)
        // otherwise, the figure drawn will be the one in shapeField
        else{
            switch((String)frame.configPanel.shapeField.getValue()){
                case "Square":
                    sides = 4;
                    frame.configPanel.sidesField.setValue(4);
                    break;
                case "Triangle":
                    sides = 3;
                    frame.configPanel.sidesField.setValue(3);
                    break;
                case "Pentagon":
                    sides = 5;
                    frame.configPanel.sidesField.setValue(5);
                    break;
                case "Hexagon":
                    sides = 6;
                    frame.configPanel.sidesField.setValue(6);
                    break;
            }
        }
        float r = rng.nextFloat();
        float g = rng.nextFloat();
        float b = rng.nextFloat();
        Color color = new Color(r,g,b, .5f);//...TODO; //create a transparent random Color.
        //graphics.setColor(color);
        //graphics.fill(new RegularPolygon(x, y, radius, sides));
        //graphics.fill(new NodeShape(x, y, radius));
        Shape s = new Shape(sides, radius, color, x, y);
        shapes.add(s);
        paintComponent(graphics);
    }
    @Override
    public void update(Graphics g) { } //Why did I do that?

    @Override
    protected void paintComponent(Graphics g) {
        createOffscreenImage();
        for( Shape i : shapes ) {
            //g.drawImage(image, 0, 0, this);
            graphics.setColor(i.getColor());
            graphics.fill(new RegularPolygon(i.getX(), i.getY(), i.getSize(), i.getShape()));
        }
        g.drawImage(image, 0, 0, this);
    }
}

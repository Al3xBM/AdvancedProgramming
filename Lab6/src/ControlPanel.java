import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ControlPanel extends JPanel {
    final MainFrame frame;
    JButton saveBtn = new JButton("Save");
    JButton loadBtn = new JButton("Load");
    JButton exitBtn = new JButton("Exit");
    //create all buttons (Load, Reset, Exit)
 //...TODO
    public ControlPanel(MainFrame frame) {
        this.frame = frame;
        init();
    }
    private void init() {
        //change the default layout manager (just for fun)
        setLayout(new FlowLayout());
        //add all buttons ...TODO
        add(saveBtn);
        add(loadBtn);
        add(exitBtn);
        //configure listeners for all buttons
        saveBtn.addActionListener(this::save);
        loadBtn.addActionListener(this::load);
        exitBtn.addActionListener(this::exit);
// ...TODO
    }

    private void exit(java.awt.event.ActionEvent actionEvent) {
        System.exit(0);
        return;
    }

    // works, must click the image first....
    private void load(java.awt.event.ActionEvent actionEvent) {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("./test.png"));
            frame.canvas.image = img;
            frame.canvas.graphics = frame.canvas.image.createGraphics();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void save(ActionEvent actionEvent) {
        try {
            ImageIO.write(frame.canvas.image, "PNG", new File("./test.png"));
        } catch (IOException ex) { System.err.println(ex); }
    }
// ...TODO
}

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ControlPanel extends JPanel {
    final MainFrame frame;
    JButton undoBtn = new JButton("Undo");
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
        add(undoBtn);
        add(saveBtn);
        add(loadBtn);
        add(exitBtn);
        //configure listeners for all buttons
        undoBtn.addActionListener(this::undo);
        saveBtn.addActionListener(this::save);
        loadBtn.addActionListener(this::load);
        exitBtn.addActionListener(this::exit);
// ...TODO
    }

    private void undo(ActionEvent actionEvent){
        if( frame.canvas.shapes.size() > 0 )
            frame.canvas.shapes.remove((frame.canvas.shapes.size() - 1) );
        frame.canvas.repaint();
    }

    private void exit(ActionEvent actionEvent) {
        System.exit(0);
        return;
    }

    // works, must click the image first....
    // button in file chooser says save, actually loads
    private void load(ActionEvent actionEvent) {
        JFileChooser chooseFile = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        int approved = chooseFile.showSaveDialog(null);
        if( approved == JFileChooser.APPROVE_OPTION ){
            try {
                BufferedImage img;
                img = ImageIO.read(new File(chooseFile.getSelectedFile().getAbsolutePath()));
                frame.canvas.image = img;
                frame.canvas.graphics = frame.canvas.image.createGraphics();
                frame.canvas.repaint();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private void save(ActionEvent actionEvent) {
        /*try {
            ImageIO.write(frame.canvas.image, "PNG", new File("./test.png"));
        } catch (IOException ex) { System.err.println(ex); }*/
        JFileChooser chooseFile = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        int approved = chooseFile.showSaveDialog(null);
        if( approved == JFileChooser.APPROVE_OPTION ){
            try {
                BufferedImage img = frame.canvas.image;
                ImageIO.write(img, "png", new File(chooseFile.getSelectedFile().getAbsolutePath()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
// ...TODO
}

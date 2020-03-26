import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    ConfigPanel configPanel;
    ControlPanel controlPanel;
    DrawingPanel canvas;

    public MainFrame() {
        super("My Drawing Application");
        init();
    }

    private void init() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        //create the components
        canvas = new DrawingPanel(this);
        configPanel = new ConfigPanel(this);
        controlPanel = new ControlPanel(this);
//...TODO

        //arrange the components in the container (frame)
        this.setLayout(new BorderLayout());
        //JFrame uses a BorderLayout by default
        add(canvas, BorderLayout.CENTER); //this is BorderLayout.CENTER
        add(configPanel, BorderLayout.NORTH);
        add(controlPanel, BorderLayout.SOUTH);
//...TODO

        //invoke the layout manager
        pack();
    }
}

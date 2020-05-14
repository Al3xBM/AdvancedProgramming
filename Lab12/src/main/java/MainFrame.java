import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    ControlPanel controlPanel;
    DesignPanel designPanel;

    public MainFrame(){
        super("My App");
        init();
    }

    private void init(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        controlPanel = new ControlPanel(this);
        designPanel = new DesignPanel(this);

        this.setLayout(new BorderLayout() );

        add(controlPanel, BorderLayout.NORTH);
        add(designPanel, BorderLayout.CENTER);

    }



}


import javax.swing.*;
import java.awt.*;

public class DesignPanel extends JPanel {
    final MainFrame frame;

    public DesignPanel(MainFrame frame){
        this.frame = frame;
        init();
    }

    private void init(){
        this.setLayout(new FlowLayout());
    }

    // primeste un JComponent de la ControlPanel atunci
    // cand compButton este apasat
    // seteaza dimensiunile, textul, vizibilitatea
    // apoi il adauga la FlowLayout si redeseneaza frame-ul
    public void addComponent(JComponent c){

        c.setBounds(
                300,
                300,
                (int)c.getPreferredSize().getWidth(),
                (int)c.getPreferredSize().getHeight()
        );
        c.setToolTipText("comp text");
        c.setVisible(true);
        add(c, BorderLayout.AFTER_LAST_LINE);
        frame.repaint();
    }
}

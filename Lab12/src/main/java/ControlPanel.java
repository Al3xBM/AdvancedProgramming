import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.lang.reflect.InvocationTargetException;

// controlpanel are un camp de text, compField,
// in care este introdus numele componentei care urmeaza
// sa fie adaugata
public class ControlPanel extends JPanel {
    final MainFrame frame;
    JLabel compLabel;
    JTextField compField;
    JButton compButton;
    public ControlPanel(MainFrame frame){
        this.frame = frame;
        init();
    }

    private void init(){
        setLayout(new FlowLayout());

        compLabel = new JLabel("Swing Componenet");
        compField = new JTextField();
        compField.setText("javax.swing.JButton");
        compButton = new JButton("Add component");
        add(compLabel);
        add(compField);
        add(compButton);
        compButton.addActionListener(this::loadComp);
    }

    // action listener pe compButton
    // cand compButton este apasat, acesta ia textul din compField
    // creaza un obiect de tipul compName si il trimite mai departe
    // pt a fi adaugat la DesignPanel
    private void loadComp(ActionEvent a){
        String compName = compField.getText();
        Class clazz = null;
        try {
            clazz = Class.forName(compName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        if( clazz != null ){
            try {
                JComponent c = (JComponent) clazz.getConstructor().newInstance();
                frame.designPanel.addComponent(c);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
    }
}

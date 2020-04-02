import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class ConfigPanel extends JPanel {
    final MainFrame frame;
    JLabel sidesLabel;
    JSpinner sidesField; // number of sides
    JComboBox colorCombo; // the color of the shape
    JLabel sizeLabel;
    JSpinner sizeField;
    JLabel shapeLabel;
    JSpinner shapeField;
    public ConfigPanel(MainFrame frame) {
        this.frame = frame;
        init();
    }
    private void init() {
        //create the label and the spinner
        sidesLabel = new JLabel("Number of sides:");
        sidesField = new JSpinner(new SpinnerNumberModel(0, 0, 100, 3));
        sidesField.setValue(6); //default number of sides
        sizeLabel = new JLabel("Shape size:");
        sizeField = new JSpinner(new SpinnerNumberModel(0, 0, 500, 10));
        sizeField.setValue(45); //default number of sides
        shapeLabel = new JLabel("Shape:");
        String[] shapeNames = {"Any", "Triangle", "Square", "Pentagon", "Hexagon"};
        shapeField = new JSpinner( new SpinnerListModel(shapeNames));
        shapeField.setPreferredSize(new Dimension(100, 25));
        //create the colorCombo, containing the values: Random and Black
        Random rng = new Random();
        float r = rng.nextFloat();
        float g = rng.nextFloat();
        float b = rng.nextFloat();
        Color rngColor = new Color(r, g, b);

        Color colors[] = { Color.WHITE, rngColor };

        colorCombo = new JComboBox(new String[]{"Random", "Black"});


 //...TODO
        add(shapeLabel);
        add(shapeField);
        add(sizeLabel);
        add(sizeField);
        add(sidesLabel); //JPanel uses FlowLayout by default
        add(sidesField);
        add(colorCombo);
    }
}

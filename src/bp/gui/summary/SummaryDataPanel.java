package bp.gui.summary;

import javax.swing.*;
import java.awt.*;

/**
 * Created by agnieszka on 21.06.2017.
 */
public class SummaryDataPanel extends JPanel {

    private JLabel datelabel, monthlabel, yearlabel;
    private JComboBox datetext1, datetext2;

    public SummaryDataPanel() {
        datelabel = new JLabel("Date:");
        monthlabel = new JLabel("Month:");
        yearlabel = new JLabel("Year:");
        datetext1 = new JComboBox();
        datetext2 = new JComboBox();

        setLayout(new GridLayout(1, 8));

        add(new JLabel());
        add(new JLabel());
        add(datelabel);
        add(monthlabel);
        add(datetext1);
        add(yearlabel);
        add(datetext2);
        add(new JLabel());
        add(new JLabel());

        datelabel.setHorizontalAlignment(JLabel.RIGHT);
        monthlabel.setHorizontalAlignment(JLabel.RIGHT);
        yearlabel.setHorizontalAlignment(JLabel.RIGHT);


    }


}

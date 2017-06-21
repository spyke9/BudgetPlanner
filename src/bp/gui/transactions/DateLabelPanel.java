package bp.gui.transactions;

import javax.swing.*;
import java.awt.*;

/**
 * Created by agnieszka on 21.06.2017.
 */
public class DateLabelPanel extends JPanel {
    private JLabel name, date1, date2;

    public DateLabelPanel() {

        name = new JLabel("Date:");
        date1 = new JLabel("From:");
        date2 = new JLabel("To:");

        setLayout(new GridLayout(3, 1));


        add(name);
        add(date1);
        add(date2);

        name.setHorizontalAlignment(JLabel.RIGHT);
        date1.setHorizontalAlignment(JLabel.RIGHT);
        date2.setHorizontalAlignment(JLabel.RIGHT);



    }


}

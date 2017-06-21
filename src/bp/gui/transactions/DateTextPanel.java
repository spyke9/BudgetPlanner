package bp.gui.transactions;

import javax.swing.*;
import java.awt.*;

/**
 * Created by agnieszka on 21.06.2017.
 */
public class DateTextPanel extends JPanel{

    private JTextField date1text,date2text;


    public DateTextPanel()
    {
        date1text= new JTextField();
        date2text = new JTextField();



        setLayout(new GridLayout(3,3));

        add(new JLabel());

        add(date1text);
        add(date2text);

    }


}

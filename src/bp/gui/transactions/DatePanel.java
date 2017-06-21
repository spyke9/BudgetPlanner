package bp.gui.transactions;


import javax.swing.*;
import java.awt.*;

public class DatePanel extends JPanel
{

    private DateLabelPanel dateLabelPanel;
    private DateTextPanel dateTextPanel;
    private JPanel panel1;


    public DatePanel()
    {
        dateLabelPanel = new DateLabelPanel();
        dateTextPanel = new DateTextPanel();



        setLayout(new GridLayout(1,3));

        add(new JPanel());
        add(dateLabelPanel);
        add(dateTextPanel);
        add(new JPanel());
        add(new JPanel());


    }
}

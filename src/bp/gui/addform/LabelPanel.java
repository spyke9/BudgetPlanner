package bp.gui.addform;

import javax.swing.*;
import java.awt.*;




/**
 * Created by agnieszka on 17.06.2017.
 */
public class LabelPanel extends JPanel
{
    private JLabel dateLabel,categoryLabel,typeLabel,amountLabel;




    public  LabelPanel()
    {
        dateLabel=new JLabel("Date:");
        categoryLabel=new JLabel("Category:");
        typeLabel=new JLabel("Type:");
        amountLabel=new JLabel("Amount:" );

        setLayout(new GridLayout(5,1));

        add(dateLabel);
        add(categoryLabel);
        add(typeLabel);
        add(amountLabel);

    }


}

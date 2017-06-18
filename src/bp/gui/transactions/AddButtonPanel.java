package bp.gui.transactions;

import javax.swing.*;
import java.awt.*;

/**
 * Created by agnieszka on 17.06.2017.
 */
public class AddButtonPanel extends JPanel
{
    private JButton addbutton, cancelbutton;

    public AddButtonPanel()
    {
        addbutton=new JButton("Add Transaction");
        cancelbutton=new JButton("Cancel");

        setLayout(new GridLayout(1,2));
        add(addbutton);
        add(cancelbutton);

    }



}

package bp.gui.transactions;

import javax.swing.*;
import java.awt.*;

/**
 * Created by agnieszka on 17.06.2017.
 */
public class AddPanel extends JPanel
{
    private DatePanel datePanel;


    public AddPanel()
    {
        datePanel=new DatePanel();

        setLayout(new BorderLayout());

        add(datePanel,BorderLayout.NORTH);



    }


}

package bp.gui.transactions;

import javax.swing.*;
import java.awt.*;

/**
 * Created by agnieszka on 17.06.2017.
 */
public class SavePanel extends JPanel
{

    private JButton saveButton;

    public SavePanel()
    {

        saveButton=new JButton("Save");

        setLayout(new BorderLayout());

        this.add(saveButton,BorderLayout.SOUTH);



    }

}

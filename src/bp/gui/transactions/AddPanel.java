package bp.gui.transactions;

import javax.swing.*;
import java.awt.*;

/**
 * Created by agnieszka on 17.06.2017.
 */
public class AddPanel extends JPanel
{

      private LabelPanel labelPanel;
      private ComponentPanel componentPanel;
      private AddButtonPanel addButtonPanel;


    public AddPanel()
    {
        componentPanel=new ComponentPanel();
          labelPanel = new LabelPanel();
          addButtonPanel = new AddButtonPanel();

        setLayout(new BorderLayout());

       // add(datePanel,BorderLayout.NORTH);
       add(componentPanel,BorderLayout.EAST);
        add(labelPanel,BorderLayout.WEST);
        add(addButtonPanel,BorderLayout.SOUTH);



    }


}

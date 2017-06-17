package bp.gui.transactions;

import javax.swing.*;
import java.awt.*;


/**
 * Created by agnieszka on 17.06.2017.
 */
public class DatePanel extends JPanel
{
        private JLabel datelabel;
        private JTextField datetextfield;

        public  DatePanel()
        {
            super();
            datelabel=new JLabel("Data:");
            datetextfield=new JTextField();

            setLayout(new GridLayout(1,2));
            this.add(datelabel);
            this.add(datetextfield);




        }


}

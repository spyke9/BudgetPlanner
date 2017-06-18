package bp.gui.transactions;



import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.*;
import javax.swing.text.DefaultFormatter;
import java.util.Properties;

/**
 * Created by agnieszka on 17.06.2017.
 */
public class AddForm extends JFrame
{


    public AddForm()
    {
        AddPanel addPanel=new AddPanel();

        this.setTitle("Add Transaction");
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(350,250);
        this.setResizable(false);


        this.add(addPanel);




        this.setVisible(true);



    }






}

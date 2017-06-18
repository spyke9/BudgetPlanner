package bp.gui.transactions;
import bp.model.AbstractTransaction;
import bp.model.CategoryType;
import javax.swing.*;
import java.awt.*;

/**
 * Created by agnieszka on 17.06.2017.
 */

public class ComponentPanel extends JPanel
{


    private JTextField dateTextField;
    private JComboBox categorycombobox;
    private JComboBox typecombobox;
    private JTextField amountfield;



    public ComponentPanel()
    {

        dateTextField=new JTextField();
        categorycombobox=new JComboBox(CategoryType.values());
        typecombobox=new JComboBox(AbstractTransaction.TransactionType.values());
        amountfield=new JTextField();




        setLayout(new GridLayout(5,1));
        add(dateTextField);
        add(categorycombobox);
        add(typecombobox);
        add(amountfield);

    }

}

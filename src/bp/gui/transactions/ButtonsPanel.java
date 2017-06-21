package bp.gui.transactions;

import javax.swing.*;
import java.awt.*;

/**
 * Created by agnieszka on 16.06.2017.
 */
public class ButtonsPanel extends JPanel {

    //private TransactionRepository repository;
    private JButton addbutton, deletebutton, modifybutton;


    public ButtonsPanel() {

        addbutton = new JButton("Add Transaction");
        deletebutton = new JButton("Delete Transaction");
        modifybutton = new JButton("Modify Transaction");

        this.setLayout(new GridLayout(1, 3));
        this.add(addbutton);
        this.add(modifybutton);
        this.add(deletebutton);


    }
}

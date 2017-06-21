package bp.gui.transactions;

import bp.gui.MainWindow;
import bp.gui.addform.AddForm;
import bp.model.Transaction;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by agnieszka on 16.06.2017.
 */
public class ButtonsPanel extends JPanel implements ActionListener {

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

        addbutton.addActionListener(this);
        deletebutton.addActionListener(this);
        modifybutton.addActionListener(this);


    }

    @Override
    public void actionPerformed(ActionEvent e) {

        Object source = e.getSource();

        if (source == addbutton) {
            JFrame parent = new JFrame();

            JDialog window = new JDialog(new AddForm());

        } else if (source == deletebutton) {

        } else if (source == modifybutton) {

        }


    }


}

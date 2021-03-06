package bp.gui.transactions;

import bp.gui.addform.AddForm;
import bp.services.SummaryService;
import bp.services.TransactionService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by agnieszka on 16.06.2017.
 */
public class ButtonsPanel extends JPanel implements ActionListener {


    private JButton addbutton, deletebutton, modifybutton;
    TransactionService transactionService;
    SummaryService summaryService;
    AbstractTableModel tableModel;


    public ButtonsPanel(TransactionService transactionService, SummaryService summaryService, AbstractTableModel abstractTableModel) {


        this.transactionService = transactionService;
        this.summaryService = summaryService;
        this.tableModel = abstractTableModel;

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
            JDialog window = new JDialog(new AddForm(transactionService, summaryService, tableModel));


        } else if (source == deletebutton) {

        } else if (source == modifybutton) {

        }


    }


}

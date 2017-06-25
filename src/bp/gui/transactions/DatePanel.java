package bp.gui.transactions;


import bp.services.TransactionService;

import javax.swing.*;
import java.awt.*;

public class DatePanel extends JPanel {

    private DateLabelPanel dateLabelPanel;
    private DateTextPanel dateTextPanel;
    private JPanel panel1;
    TransactionService transactionService;
    AbstractTableModel tableModel;


    public DatePanel(TransactionService transactionService, AbstractTableModel tableModel) {
        this.transactionService = transactionService;
        this.tableModel = tableModel;
        dateLabelPanel = new DateLabelPanel();
        dateTextPanel = new DateTextPanel(transactionService, tableModel);


        setLayout(new GridLayout(1, 3));

        add(new JPanel());
        add(dateLabelPanel);
        add(dateTextPanel);
        add(new JPanel());
        add(new JPanel());


    }
}

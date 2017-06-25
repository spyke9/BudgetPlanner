package bp.gui.transactions;

import bp.services.TransactionService;

import javax.swing.*;

/**
 * Created by agnieszka on 16.06.2017.
 */
public class TransactionTable extends JPanel {


    TransactionService transactionService;

    public TransactionTable(TransactionService transactionService, AbstractTableModel tableModel) {
        JTable table = new JTable(tableModel);
        table.setAutoCreateRowSorter(true);
        add(new JScrollPane(table));
    }


}

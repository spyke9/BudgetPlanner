package bp.gui.transactions;

import bp.model.Transaction;
import bp.services.TransactionService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * Created by agnieszka on 16.06.2017.
 */
public class TransactionTable extends JPanel {


    TransactionService transactionService;

    public TransactionTable(TransactionService transactionService) {
        JTable table = new JTable(new AbstractTableModel(transactionService));
        add(new JScrollPane(table));
    }


}

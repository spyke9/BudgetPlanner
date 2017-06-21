package bp.gui.transactions;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * Created by agnieszka on 16.06.2017.
 */
public class TransactionTable extends JPanel {
    private JTable table;
    private String column_names[] = {"Date", "Category", "Type", "Amount"};


    public TransactionTable() {

        DefaultTableModel model = new DefaultTableModel(column_names, column_names.length);
        table = new JTable(model);

        add(new JScrollPane(table));


    }


}

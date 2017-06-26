package bp.gui.transactions;

import bp.model.ITransaction;
import bp.services.TransactionService;

import java.time.LocalDate;
import java.util.Collection;

/**
 * Created by agnieszka on 22.06.2017.
 */
public class AbstractTableModel extends javax.swing.table.AbstractTableModel {


    TransactionService transactionService;
    private String[] columnNames;
    private Object[][] data;
    Collection<ITransaction> collection;


    public AbstractTableModel(TransactionService transactionService) {
        this.transactionService = transactionService;
        collection = transactionService.getAllTransactions();
        columnNames = new String[]{"Date", "Category", "Type", "Amount", "jdsjsn"};
        update();


    }


    public int getColumnCount() {
        return 4;
    }

    public int getRowCount() {
        return data.length;
    }

    public String getColumnName(int col) {
        return columnNames[col];
    }

    public Object getValueAt(int row, int col) {
        return data[row][col];
    }

    public void update() {
        collection = transactionService.getAllTransactions();
        data = new Object[collection.size()][4];
        transformData();

        this.fireTableDataChanged();
    }

    public void update(LocalDate date1, LocalDate date2) {
        collection = transactionService.getTransactionsFromPeriod(date1, date2);
        data = new Object[collection.size()][4];
        transformData();

        this.fireTableDataChanged();
    }

    public void transformData() {
        int i = 0;

        for (ITransaction iTransaction : collection) {
            data[i][0] = iTransaction.getDate();
            data[i][1] = iTransaction.getCategory().getName();
            data[i][2] = iTransaction.getType().getName();
            data[i][3] = iTransaction.getAmount();
            i++;

        }
    }


}

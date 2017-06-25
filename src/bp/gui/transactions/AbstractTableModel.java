package bp.gui.transactions;

import bp.model.ITransaction;
import bp.services.TransactionService;

import javax.swing.table.DefaultTableModel;
import java.util.Collection;
import java.util.Iterator;

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
//        data = new Object[][]{
//                {"Mary", "Campione", "Snowboarding", new Integer(5),
//                        new Boolean(false)},
//                {"Alison", "Huml", "Rowing", new Integer(3), new Boolean(true)},
//                {"Kathy", "Walrath", "Knitting", new Integer(2),
//                        new Boolean(false)},
//                {"Sharon", "Zakhour", "Speed reading", new Integer(20),
//                        new Boolean(true)},
//                {"Philip", "Milne", "Pool", new Integer(10),
//                        new Boolean(false)}};

        data = new Object[collection.size()][4];
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
        int i = 0;

        for (ITransaction iTransaction : collection) {
            data[i][0] = iTransaction.getDate();
            data[i][1] = iTransaction.getCategory().getName();
            data[i][2] = iTransaction.getType().getName();
            data[i][3] = iTransaction.getAmount();
            i++;

        }

        this.fireTableDataChanged();
    }


}

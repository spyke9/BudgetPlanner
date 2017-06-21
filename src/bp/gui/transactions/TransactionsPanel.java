package bp.gui.transactions;

import javax.swing.*;
import java.awt.*;


public class TransactionsPanel extends JPanel
{

    private ButtonsPanel panel = new ButtonsPanel();
    private TransactionTable table = new TransactionTable();
    private DatePanel datePanel = new DatePanel();



    public TransactionsPanel() {
        super();

        setLayout(new BorderLayout());

        add(panel,BorderLayout.NORTH);
        add(table,BorderLayout.CENTER);
        add(datePanel,BorderLayout.SOUTH);

    }
}

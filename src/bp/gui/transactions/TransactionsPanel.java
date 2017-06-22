package bp.gui.transactions;

import bp.services.TransactionService;

import javax.swing.*;
import java.awt.*;


public class TransactionsPanel extends JPanel {


    TransactionService transactionService;


    public TransactionsPanel(TransactionService transactionService) {

        super();
        this.transactionService=transactionService;
        ButtonsPanel panel = new ButtonsPanel(transactionService);
        TransactionTable table = new TransactionTable();
        DatePanel datePanel = new DatePanel();




        setLayout(new BorderLayout());

        add(panel, BorderLayout.NORTH);
        add(table, BorderLayout.CENTER);
        add(datePanel, BorderLayout.SOUTH);

    }
}

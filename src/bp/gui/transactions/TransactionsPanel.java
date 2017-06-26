package bp.gui.transactions;

import bp.services.SummaryService;
import bp.services.TransactionService;

import javax.swing.*;
import java.awt.*;


public class TransactionsPanel extends JPanel {

//    private TransactionService transactionService;
//    private SummaryService summaryService;


    public TransactionsPanel(TransactionService transactionService, SummaryService summaryService) {

        super();
//        this.transactionService = transactionService;
//        this.summaryService = summaryService;

        AbstractTableModel tableModel = new AbstractTableModel(transactionService);
        ButtonsPanel panel = new ButtonsPanel(transactionService, summaryService, tableModel);
        TransactionTable table = new TransactionTable(transactionService, tableModel);
        DatePanel datePanel = new DatePanel(transactionService, tableModel,summaryService);

        setLayout(new BorderLayout());
        add(panel, BorderLayout.NORTH);
        add(table, BorderLayout.CENTER);
        add(datePanel, BorderLayout.SOUTH);

    }
}

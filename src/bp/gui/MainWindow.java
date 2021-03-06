package bp.gui;

import bp.config.Configuration;
import bp.gui.planner.PlannerPanel;
import bp.gui.summary.SummaryPanel;
import bp.gui.transactions.TransactionsPanel;
import bp.repository.SummaryRepository;
import bp.repository.TransactionRepository;
import bp.services.*;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Random;

/**
 * Created by agnieszka on 16.06.2017.
 */
public class MainWindow extends JFrame {


    public MainWindow(TransactionService transactionService,
                      SummaryService summaryService,
                      GraphService graphService,
                      BudgetPlanner budgetPlanner) {

        TransactionsPanel transactionsPanel = new TransactionsPanel(transactionService, summaryService);
        PlannerPanel plannerPanel = new PlannerPanel(budgetPlanner);
        JTabbedPane table = new JTabbedPane();
        SummaryPanel summaryPanel = new SummaryPanel(summaryService, graphService);


        table.add("Transactions", transactionsPanel);
        table.add("Planner", plannerPanel);
        table.add("Summary", summaryPanel);

        this.setTitle("BudgetPlanner");
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 600);
        this.setResizable(false);

        add(table);

        addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
                Serializer.serialize(summaryService.getTransactionRepository(), Configuration.TRANSACTION_REPOSITORY_FILE);
                Serializer.serialize(summaryService.getSummaryRepository(), Configuration.SUMMARY_REPOSITORY_FILE);
            }

            @Override
            public void windowClosed(WindowEvent e) {
            }

            @Override
            public void windowIconified(WindowEvent e) {
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
            }

            @Override
            public void windowActivated(WindowEvent e) {
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
            }
        });

        this.setVisible(true);
    }


    public static void main(String[] args) {

        TransactionRepository transactionRepository = null;
        SummaryRepository summaryRepository = null;
        try {
            transactionRepository = (TransactionRepository) Serializer.deserialize(Configuration.TRANSACTION_REPOSITORY_FILE);
            summaryRepository = (SummaryRepository) Serializer.deserialize(Configuration.SUMMARY_REPOSITORY_FILE);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (transactionRepository == null) {
            transactionRepository = new TransactionRepository();
        }
        if (summaryRepository == null) {
            summaryRepository = new SummaryRepository();
        }

        TransactionService transactionService = new TransactionService(transactionRepository);
        SummaryService summaryService = new SummaryService(summaryRepository, transactionRepository);
        GraphService graphService = new GraphService(summaryRepository);
        BudgetPlanner budgetPlanner = new BudgetPlanner(summaryRepository);


        /**
         * Dodaje przykladowe transakcje
         */
//        Random random = new Random();
//        for (int y = 2010; y <= 2017; y++) {
//            for (int m = 1; m <= 12; m++) {
//                for (int d = 1; d < 28; d++) {
//                    LocalDate date = LocalDate.of(y, m, d);
//                    CategoryType[] categoryTypeList = CategoryType.values();
//                    int rCat = random.nextInt(categoryTypeList.length);
//
//                    Transaction transaction = new Transaction();
//                    transaction.setDate(date);
//                    transaction.setCategory(categoryTypeList[rCat]);
//                    transaction.setType(Transaction.TransactionType.EXPENSE);
//                    transaction.setAmount(random.nextInt(100) + random.nextDouble());
//
//                    transactionService.addTransaction(transaction);
//
//                }
//                LocalDate date = LocalDate.of(y, m, 1);
//                CategoryType[] categoryTypeList = CategoryType.values();
//                int rCat = random.nextInt(categoryTypeList.length);
//
//                Transaction transaction = new Transaction();
//                transaction.setDate(date);
//                transaction.setCategory(categoryTypeList[rCat]);
//                transaction.setType(Transaction.TransactionType.INCOME);
//                transaction.setAmount(2000.0);
//
//                transactionService.addTransaction(transaction);
//
//                summaryService.updateSummaryRepository(LocalDate.of(y, m, 1));
//            }
//        }

        MainWindow window = new MainWindow(transactionService, summaryService, graphService, budgetPlanner);

    }

}

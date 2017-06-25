package bp.gui;

import bp.config.Configuration;
import bp.gui.planner.PlannerPanel;
import bp.gui.summary.SummaryPanel;
import bp.gui.transactions.AbstractTableModel;
import bp.gui.transactions.TransactionsPanel;
import bp.model.CategoryExpensesType;
import bp.model.CategoryType;
import bp.model.MonthlyExpensesAndIncomeType;
import bp.model.Summary;
import bp.repository.SummaryRepository;
import bp.repository.TransactionRepository;
import bp.services.*;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.time.LocalDate;
import java.util.Random;

/**
 * Created by agnieszka on 16.06.2017.
 */
public class MainWindow extends JFrame {


    public MainWindow(TransactionService transactionService,
                      SummaryService summaryService,
                      GraphService graphService,
                      BudgetPlanner budgetPlanner) {

        TransactionsPanel transactionsPanel = new TransactionsPanel(transactionService);
        PlannerPanel plannerPanel = new PlannerPanel();
        JTabbedPane table = new JTabbedPane();
       // SummaryPanel summaryPanel = new SummaryPanel(summaryService, graphService);


        table.add("Transactions", transactionsPanel);
        table.add("Planner", plannerPanel);
        //table.add("Summary", summaryPanel);

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

        Random random = new Random();
        for (int i = 2010; i < 2016; i++) {
            LocalDate date1 = LocalDate.of(i, 2, 1);
            LocalDate date2 = LocalDate.of(i, 3, 1);
            Summary exampleSummary1 = new Summary(date1);
            Summary exampleSummary2 = new Summary(date2);

            for (CategoryType categoryType : CategoryType.values()) {
                exampleSummary1.addExpense(new CategoryExpensesType(date1, categoryType, random.nextInt(100)));
                exampleSummary2.addExpense(new CategoryExpensesType(date2, categoryType, random.nextInt(100)));
            }
            exampleSummary1.setExpensesAndIncome(
                    new MonthlyExpensesAndIncomeType(date1, random.nextInt(1000), random.nextInt(1000)));
            exampleSummary2.setExpensesAndIncome(
                    new MonthlyExpensesAndIncomeType(date2, random.nextInt(1000), random.nextInt(1000)));

            summaryService.addSummary(exampleSummary1);
            summaryService.addSummary(exampleSummary2);
        }














        MainWindow window = new MainWindow(transactionService, summaryService, graphService, budgetPlanner);

    }

}

package bp.gui;

import bp.config.Configuration;
import bp.gui.planner.PlannerPanel;
import bp.gui.summary.SummaryPanel;
import bp.gui.transactions.TransactionsPanel;
import bp.repository.SummaryRepository;
import bp.repository.TransactionRepository;
import bp.services.*;

import javax.swing.*;

/**
 * Created by agnieszka on 16.06.2017.
 */
public class MainWindow extends JFrame {

    public MainWindow(TransactionService transactionService,
                      SummaryService summaryService,
                      GraphService graphService,
                      BudgetPlanner budgetPlanner) {

        TransactionsPanel transactionpanel = new TransactionsPanel();
        PlannerPanel plannerPanel = new PlannerPanel();
        JTabbedPane table = new JTabbedPane();
        SummaryPanel summaryPanel = new SummaryPanel();


        table.add("Transactions", transactionpanel);
        table.add("Planner", plannerPanel);
        table.add("Summary", summaryPanel);


        this.setTitle("BudgetPlanner");
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 600);
        this.setResizable(false);

        add(table);
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
        SummaryService summaryService = new SummaryService(summaryRepository);
        GraphService graphService = new GraphService(summaryRepository);
        BudgetPlanner budgetPlanner = new BudgetPlanner(summaryRepository);

//        repository.addItem(LocalDate.now(), new Summary());
//        Serializer.serialize(repository, Configuration.SUMMARY_REPOSITORY_FILE);


        MainWindow window = new MainWindow(transactionService, summaryService, graphService, budgetPlanner);

        Serializer.serialize(transactionRepository, Configuration.TRANSACTION_REPOSITORY_FILE);
        Serializer.serialize(summaryRepository, Configuration.SUMMARY_REPOSITORY_FILE);

    }


}

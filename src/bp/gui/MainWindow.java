package bp.gui;

import bp.gui.addform.AddForm;
import bp.gui.planner.PlannerPanel;
import bp.gui.summary.SummaryPanel;
import bp.gui.transactions.*;

import javax.swing.*;

/**
 * Created by agnieszka on 16.06.2017.
 */
public class MainWindow extends JFrame {

    public MainWindow() {

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
//      setLayout(new BorderLayout());

//        add(panel,BorderLayout.NORTH);
        add(table);
        this.setVisible(true);
    }


    public static void main(String[] args) {

        MainWindow window = new MainWindow();

      //  AddForm addForm = new AddForm();

//        PieChart demo = new PieChart("Comparison", "Which operating system are you using?");
//        demo.pack();
//        demo.setVisible(true);

    }


}

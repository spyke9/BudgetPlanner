package bp.gui.planner;

import bp.model.CategoryExpensesType;
import bp.model.CategoryType;
import bp.model.ITransaction;
import bp.model.Summary;
import bp.services.BudgetPlanner;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Map;

/**
 * Created by agnieszka on 17.06.2017.
 */
public class PlannerPanel extends JPanel {
    private Categorytable categorytable;
    private SavePanel savePanel;
    private BudgetPlanner budgetPlanner;

    public PlannerPanel(BudgetPlanner budgetPlanner) {
        super();
        this.budgetPlanner = budgetPlanner;

        categorytable = new Categorytable(budgetPlanner);
        savePanel = new SavePanel();
        setLayout(new BorderLayout());
        add(categorytable, BorderLayout.NORTH);
        add(savePanel, BorderLayout.CENTER);
    }

    /**
     * Created by agnieszka on 17.06.2017.
     */
    public static class Categorytable extends JPanel {
        private JTable category;
        private JTable plan;
        private JTable spend;
        private String name1[] = {"Category"};
        private String name2[] = {"Plan"};
        private String name3[] = {"Estimated:"};
        private DefaultTableModel model1;
        private BudgetPlanner budgetPlanner;


        public Categorytable(BudgetPlanner budgetPlanner) {

            this.budgetPlanner = budgetPlanner;
            model1 = new DefaultTableModel(name1, 9);
            category = new JTable(model1);
            DefaultTableModel model2 = new DefaultTableModel(name2, 20);
            plan = new JTable(model2);
            DefaultTableModel model3 = new DefaultTableModel(name3, 20);
            spend = new JTable(model3);


            this.setLayout(new GridLayout(1, 3));
            add(new JScrollPane(category));
            add(new JScrollPane(plan));
            add(new JScrollPane(spend));
            transformCategoryData();
            transformSpendData();
        }


        public void transformCategoryData() {

            int i = 0;

            for (CategoryType categoryType : CategoryType.values()) {
                category.getModel().setValueAt(categoryType.getName(), i, 0);
                i++;
            }
        }

        public void transformSpendData() {
            int i = 0;

            //budgetPlanner.estimateMeanBudget();
            Summary summary = budgetPlanner.getEstimatedBudget();
            if(summary!=null) {
                Map<CategoryType, CategoryExpensesType> map = summary.getCategoryExpensesMap();


                for (CategoryType categoryType : CategoryType.values()) {

                    CategoryExpensesType categoryExpensesType = map.get(categoryType);

                    if (categoryExpensesType != null)
                        spend.getModel().setValueAt(map.get(categoryType).getExpenses(), i, 0);

                    else
                        spend.getModel().setValueAt(0, i, 0);

                    i++;
                }
            }

            else
                System.out.println("Nie ma");
        }


    }
}

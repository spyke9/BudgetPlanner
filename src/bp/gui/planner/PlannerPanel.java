package bp.gui.planner;

import bp.model.*;
import bp.services.BudgetPlanner;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.HashMap;
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
        savePanel = new SavePanel(budgetPlanner);
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
        private DecimalFormat format = new DecimalFormat("##########.00");


        public Categorytable(BudgetPlanner budgetPlanner) {
            this.budgetPlanner = budgetPlanner;
            model1 = new DefaultTableModel(name1, 10);
            category = new JTable(model1);
            DefaultTableModel model2 = new DefaultTableModel(name2, 10);
            plan = new JTable(model2);
            DefaultTableModel model3 = new DefaultTableModel(name3, 10);
            spend = new JTable(model3);
            transformCategoryData();
            transformSpendData();

            this.setLayout(new GridLayout(1, 3));
            add(new JScrollPane(category));
            add(new JScrollPane(plan));
            add(new JScrollPane(spend));


            transformCategoryData();
            //transformSpendData();

        }


        public void transformCategoryData() {

            int i = 1;
            category.getModel().setValueAt("Income", 0, 0);

            for (CategoryType categoryType : CategoryType.values()) {
                category.getModel().setValueAt(categoryType.getName(), i, 0);
                i++;
            }
        }

        public void transformSpendData() {

            Summary summary = budgetPlanner.getEstimatedBudget();
            Map<CategoryType, CategoryExpensesType> map = summary.getCategoryExpensesMap();
            int i = 1;

            spend.getModel().setValueAt(summary.getExpensesAndIncome().getIncome(), i, 0);

            if (summary != null) {


                for (CategoryType categoryType : CategoryType.values()) {
                    CategoryExpensesType categoryExpensesType = map.get(categoryType);
                    if (categoryExpensesType != null) {
                        spend.getModel().setValueAt(map.get(categoryType).getExpenses(), i, 0);
                        //spend.getModel().setValueAt( Double.parseDouble(format.format(map.get(categoryType).getExpenses())), i, 0);
                    } else {
                        spend.getModel().setValueAt(0.0, i, 0);
                    }

                    i++;
                }

            } else
                System.out.println("Nie ma");


        }

        public Double objectToDouble(Object o) {

            double amount = Double.parseDouble(o.toString());
            return amount;

        }


        public Object[] getValues() {
            Object[] doubles = new Object[10];

            for (int i = 1; i < 10; i++) {
                doubles[i - 1] = plan.getValueAt(i, 0);

            }

            return doubles;
        }

        public CategoryType[] categoryTypes() {

            CategoryType[] categoryArray = new CategoryType[10];
            categoryArray[0] = CategoryType.fromName("Food");
            categoryArray[1] = CategoryType.fromName("Housing");
            categoryArray[2] = CategoryType.fromName("Utilities");
            categoryArray[3] = CategoryType.fromName("Health care");
            categoryArray[4] = CategoryType.fromName("Personal care");
            categoryArray[5] = CategoryType.fromName("Entertainment");
            categoryArray[6] = CategoryType.fromName("Education");
            categoryArray[7] = CategoryType.fromName("Debt");
            categoryArray[8] = CategoryType.fromName("Other");

            return categoryArray;

        }

        public CategoryExpensesType[] categoryExpensesTypesArray() {

            LocalDate datenow = LocalDate.now();
            CategoryExpensesType categoryExpensesArray[] = new CategoryExpensesType[9];
            CategoryType[] categoryTypesArray = categoryTypes();
            Object[] doublesArray = getValues();


            for (int i = 0; i < 10; i++) {
                categoryExpensesArray[i] = new CategoryExpensesType(datenow, categoryTypesArray[i],
                        objectToDouble(doublesArray[i]));
            }

            return categoryExpensesArray;

        }

        public Map<CategoryType, CategoryExpensesType> getMap() {
            Map<CategoryType, CategoryExpensesType> result = new HashMap<>();
            CategoryType[] categoryTypesArray = categoryTypes();
            CategoryExpensesType[] categoryExpensesTypeArray = categoryExpensesTypesArray();
            for (int i = 0; i < 10; i++) {
                result.put(categoryTypesArray[i], categoryExpensesTypeArray[i]);

            }

            return result;
        }


        public double sum() {
            double result = 0;
            double d;

            for (CategoryExpensesType categoryExpensesType : getMap().values()) {
                d = categoryExpensesType.getExpenses();
                result += d;
            }

            return result;
        }

        public MonthlyExpensesAndIncomeType monthlyExpensesAndIncomeType() {


            return new MonthlyExpensesAndIncomeType(LocalDate.now(), sum(), objectToDouble(plan.getValueAt(0, 0)));
        }


    }
    public class SavePanel extends JPanel implements ActionListener {

        private JButton saveButton;
        private BudgetPlanner budgetPlanner;

        public SavePanel(BudgetPlanner budgetPlanner) {
            this.budgetPlanner=budgetPlanner;
            saveButton = new JButton("Save");
            saveButton.addActionListener(this);

            setLayout(new BorderLayout());
            this.add(saveButton, BorderLayout.SOUTH);
        }

        public void actionPerformed(ActionEvent e) {

            Object source = e.getSource();

            if (source == saveButton) {
                Summary summary = new Summary(
                        LocalDate.now(), categorytable.getMap(), categorytable.monthlyExpensesAndIncomeType());
                budgetPlanner.setPlannedBudget(summary);
                System.out.println("Zapisano");

            }

        }


    }


}




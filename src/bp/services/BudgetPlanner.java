package bp.services;

import bp.model.CategoryExpensesType;
import bp.model.CategoryType;
import bp.model.MonthlyExpensesAndIncomeType;
import bp.model.Summary;
import bp.repository.SummaryRepository;

import java.time.LocalDate;

/**
 * Created by szkutek on 12.06.17.
 */
public class BudgetPlanner {
    private SummaryRepository summaryRepository;
    private Summary estimatedBudget;
    private Summary plannedBudget;

    public BudgetPlanner(SummaryRepository summaryRepository) {
        this.summaryRepository = summaryRepository;
    }

    /**
     * Prognoza liczona za pomoca metody usredniania zwrotow dla kazdej kategorii i przychodu
     */
    public void estimateBudget() {
        this.estimatedBudget = calculatePrognosis(LocalDate.now());
    }

    /**
     * Prognoza to srednie wydatki dla kategorii i przychodu
     */
    public void estimateMeanBudget() {
        this.estimatedBudget = calculateMeanSummary(LocalDate.now());
    }


    public Summary getEstimatedBudget() {
        if (estimatedBudget == null) {
            estimateMeanBudget();
        }
        return estimatedBudget;
    }

    public Summary getPlannedBudget() {
        return plannedBudget;
    }

    public void setPlannedBudget(Summary plannedBudget) {
        this.plannedBudget = plannedBudget;
    }


    private double calculatePrognosisPerCategory(CategoryType category, LocalDate date) {
        double n = summaryRepository.getAll().size();
        if (n < 2.0) {
            return 0.0;
        }
        double expense1 = 0.0, expenseN = 0.0;
        Summary summary1 = summaryRepository.getById(summaryRepository.getMinDate());
        Summary summaryN = summaryRepository.getById(date.minusMonths(1));

        if (summary1 != null) {
            expense1 = summary1.getCategoryExpensesMap().get(category).getExpenses();
        }
        if (summaryN != null) {
            expenseN = summaryN.getCategoryExpensesMap().get(category).getExpenses();
        }
        return (n * expenseN - expense1) / (n - 1.0);
        //metoda usredniania zwrotow x_{n+1} = x_n + S, S = (x_n - x_1) / (n - 1);
    }

    private MonthlyExpensesAndIncomeType calculatePrognosisOfIncomeAndExpense(LocalDate date) {
        double n = summaryRepository.getAll().size();
        if (n < 2.0) {
            return new MonthlyExpensesAndIncomeType(date, 0.0, 0.0);
        }

        double income1 = 0.0, incomeN = 0.0;
        double expense1 = 0.0, expenseN = 0.0;
        Summary summary1 = summaryRepository.getById(summaryRepository.getMinDate());
        Summary summaryN = summaryRepository.getById(date.minusMonths(1));

        if (summary1 != null) {
            expense1 = summary1.getExpensesAndIncome().getExpenses();
            income1 = summary1.getExpensesAndIncome().getIncome();
        }
        if (summaryN != null) {
            expenseN = summaryN.getExpensesAndIncome().getExpenses();
            incomeN = summaryN.getExpensesAndIncome().getIncome();
        }

        return new MonthlyExpensesAndIncomeType(date,
                (n * expenseN - expense1) / (n - 1.0),
                (n * incomeN - income1) / (n - 1.0));
        //metoda usredniania zwrotow x_{n+1} = x_n + S, S = (x_n - x_1) / (n - 1);
    }


    private double calculateMeanExpensePerCategory(CategoryType category, LocalDate date) {
        double sum = 0.0;
        double n = 0.0;

        for (LocalDate tmp = summaryRepository.getMinDate(); tmp.isBefore(date); tmp = tmp.plusMonths(1)) {
            Summary summary = summaryRepository.getById(tmp);
            if (summary != null) {
                sum += summary.getCategoryExpensesMap().get(category).getExpenses();
                n++;
            }
        }
        return sum / n;
    }

    private MonthlyExpensesAndIncomeType calculateMeanExpensesAndIncome(LocalDate date) {
        double expense = 0.0;
        double income = 0.0;
        double n = 0.0;

        for (LocalDate tmp = summaryRepository.getMinDate(); tmp.isBefore(date); tmp = tmp.plusMonths(1)) {
            Summary summary = summaryRepository.getById(tmp);
            if (summary != null) {
                expense += summary.getExpensesAndIncome().getExpenses();
                income += summary.getExpensesAndIncome().getIncome();
                n++;
            }
        }
        return new MonthlyExpensesAndIncomeType(date, expense / n, income / n);
    }


    private Summary calculatePrognosis(LocalDate date) {
        Summary summary = new Summary(date);
        for (CategoryType category : CategoryType.values()) {
            summary.addExpense(
                    new CategoryExpensesType(date, category, calculatePrognosisPerCategory(category, date)));
        }
        summary.setExpensesAndIncome(calculatePrognosisOfIncomeAndExpense(date));
        return summary;
    }

    private Summary calculateMeanSummary(LocalDate date) {
        Summary summary = new Summary(date);
        for (CategoryType category : CategoryType.values()) {
            summary.addExpense(
                    new CategoryExpensesType(date, category, calculateMeanExpensePerCategory(category, date)));
        }
        summary.setExpensesAndIncome(calculateMeanExpensesAndIncome(date));
        return summary;
    }

    public double checkPlannedBudget() {
        double plannedExpense = 0.0, estimatedExpense = 0.0;
        if (estimatedBudget != null && plannedBudget != null) {
            for (CategoryType category : CategoryType.values()) {
                plannedExpense += plannedBudget.getCategoryExpensesMap().get(category).getExpenses();
                estimatedExpense += estimatedBudget.getCategoryExpensesMap().get(category).getExpenses();
            }
        }
        return plannedExpense - estimatedExpense;
    }

}

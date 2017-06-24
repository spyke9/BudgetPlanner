package bp.gui.summary;

import bp.model.MonthlyExpensesAndIncomeType;
import bp.model.Transaction;
import bp.services.GraphService;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.title.LegendTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.RectangleEdge;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;

/**
 * Created by agnieszka on 18.06.2017.
 */
public class BarChart extends JPanel {
    GraphService graphService;

    public BarChart(String chartTitle, GraphService graphService) {
        this.graphService = graphService;
        add(new ChartPanel(createChart(createDataset(), chartTitle)));
    }


    private CategoryDataset createDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        List<MonthlyExpensesAndIncomeType> barGraphData = graphService.barGraph(LocalDate.now().getYear());
//        dataset.sort(Comparator.comparing(MonthlyExpensesType::getDate));
        for (MonthlyExpensesAndIncomeType item : barGraphData) {
//            String x = item.getDate().format(DateTimeFormatter.ofPattern("yyyy-MM"));
            String x = item.getDate().getMonth().getDisplayName(TextStyle.FULL, Locale.getDefault());
            dataset.addValue(item.getExpenses(), Transaction.TransactionType.EXPENSE.getName(), x);
            dataset.addValue(item.getIncome(), Transaction.TransactionType.INCOME.getName(), x);
        }

        return dataset;
    }

    private JFreeChart createChart(CategoryDataset dataset, String title) {
        JFreeChart chart = ChartFactory.createBarChart(
                title, "Month", "Amount", dataset,
                PlotOrientation.VERTICAL, true, true, false);

        LegendTitle legend = chart.getLegend();
        legend.setPosition(RectangleEdge.RIGHT);

        chart.setBackgroundPaint(Color.white);

        CategoryPlot plot = chart.getCategoryPlot();
        plot.setBackgroundPaint(Color.lightGray);
        plot.setDomainGridlinePaint(Color.white);
        plot.setRangeGridlinePaint(Color.white);


        return chart;


    }


}

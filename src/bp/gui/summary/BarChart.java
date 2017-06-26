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
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;

/**
 * Created by agnieszka on 18.06.2017.
 */
public class BarChart extends JPanel {
    private GraphService graphService;
    private ChartPanel chartPanel;

    public BarChart(int year, GraphService graphService) {
        this.graphService = graphService;

        chartPanel = new ChartPanel(updateChart(year));
        add(chartPanel);
    }

    public JFreeChart updateChart(int year) {
        return createChart(createDataset(year), "Monthly expenses and income");
    }

    private CategoryDataset createDataset(int year) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        List<MonthlyExpensesAndIncomeType> barGraphData = graphService.barGraph(year);
        if (barGraphData != null) {
            for (MonthlyExpensesAndIncomeType item : barGraphData) {
                String x = item.getDate().getMonth().getDisplayName(TextStyle.SHORT, Locale.getDefault());
                dataset.addValue(item.getExpenses(), Transaction.TransactionType.EXPENSE.getName(), x);
                dataset.addValue(item.getIncome(), Transaction.TransactionType.INCOME.getName(), x);
            }
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

    public ChartPanel getChartPanel() {
        return chartPanel;
    }

}

package bp.gui.summary;

import bp.model.CategoryType;
import bp.model.MonthlyExpensesAndIncomeType;
import bp.model.MonthlyExpensesType;
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
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.time.LocalDate;

/**
 * Created by agnieszka on 18.06.2017.
 */
public class BarChart extends JPanel {
    GraphService graphService;

    public BarChart(String chartTitle, GraphService graphService) {
        this.graphService = graphService;
        CategoryDataset dataset = createdataset();
        JFreeChart chart = createChart(dataset, chartTitle);
        ChartPanel chartPanel = new ChartPanel(chart);
        add(chartPanel);
    }


    private CategoryDataset createdataset() {
        DefaultCategoryDataset result = new DefaultCategoryDataset();

//        result.addValue(1.0, "S1", "Category 1");
        List<MonthlyExpensesAndIncomeType> dataset = graphService.barGraph(LocalDate.now().getYear());
//        dataset.sort(Comparator.comparing(MonthlyExpensesType::getDate));
        for (MonthlyExpensesAndIncomeType item : dataset) {
            result.addValue(item.getExpenses(), Transaction.TransactionType.EXPENSE.getName(), item.getDate().getMonth());
            result.addValue(item.getIncome(), Transaction.TransactionType.INCOME.getName(), item.getDate().getMonth());
        }

        return result;
    }

    private JFreeChart createChart(CategoryDataset dataset, String title) {
        JFreeChart chart = ChartFactory.createBarChart(
                title,
                "Year",
                "Amount",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        LegendTitle legend = chart.getLegend();
        legend.setPosition(RectangleEdge.RIGHT);

        chart.setBackgroundPaint(Color.white);

        // get a reference to the plot for further customisation...
        CategoryPlot plot = chart.getCategoryPlot();
        plot.setBackgroundPaint(Color.lightGray);
        plot.setDomainGridlinePaint(Color.white);
        plot.setRangeGridlinePaint(Color.white);


        return chart;


    }


}

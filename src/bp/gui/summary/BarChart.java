package bp.gui.summary;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.chart.title.LegendTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.XYBarDataset;
import org.jfree.ui.RectangleEdge;
import org.jfree.util.Rotation;

import javax.swing.*;
import java.awt.*;

/**
 * Created by agnieszka on 18.06.2017.
 */
public class BarChart extends JPanel {

    public BarChart(String chartTitle) {

        CategoryDataset dataset = createdataset();
        JFreeChart chart = createChart(dataset,chartTitle);
        ChartPanel chartPanel = new ChartPanel(chart);
        add(chartPanel);


    }


    private CategoryDataset createdataset() {
        DefaultCategoryDataset result = new DefaultCategoryDataset();

        result.addValue(1.0, "S1", "Category 1");
        result.addValue(4.0, "S1", "Category 2");
        result.addValue(3.0, "S1", "Category 3");
        result.addValue(5.0, "S1", "Category 4");
        result.addValue(5.0, "S1", "Category 5");
        result.addValue(7.0, "S1", "Category 6");
        result.addValue(7.0, "S1", "Category 7");
        result.addValue(8.0, "S1", "Category 8");

        result.addValue(5.0, "S2", "Category 1");
        result.addValue(7.0, "S2", "Category 2");
        result.addValue(6.0, "S2", "Category 3");
        result.addValue(8.0, "S2", "Category 4");
        result.addValue(4.0, "S2", "Category 5");
        result.addValue(4.0, "S2", "Category 6");
        result.addValue(2.0, "S2", "Category 7");
        result.addValue(1.0, "S2", "Category 8");

        return result;

    }


    private JFreeChart createChart(CategoryDataset dataset, String title) {
        JFreeChart chart = ChartFactory.createBarChart(
                title,
                "Month",
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

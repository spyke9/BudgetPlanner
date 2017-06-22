package bp.gui.summary;

import bp.model.CategoryType;
import bp.services.GraphService;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.title.LegendTitle;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.RectangleEdge;
import org.jfree.util.Rotation;

import javax.swing.*;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by agnieszka on 18.06.2017.
 */
public class SummaryPieChart extends JPanel {
    private GraphService graphService;

    public SummaryPieChart(String chartTitle, GraphService graphService) {
        this.graphService = graphService;
        PieDataset dataset = createDataset(graphService);
        JFreeChart chart = createChart(dataset, chartTitle);
        ChartPanel chartPanel = new ChartPanel(chart);
        add(chartPanel);
    }

    private PieDataset createDataset(GraphService graphService) {
        DefaultPieDataset result = new DefaultPieDataset();

        Map<CategoryType, Double> dataset = graphService.pieChart(LocalDate.now());
        for (CategoryType category : CategoryType.values()) {
            result.setValue(category.name(), dataset.get(category));
        }

        return result;
    }

    private JFreeChart createChart(PieDataset dataset, String title) {
        JFreeChart chart = ChartFactory.createPieChart(title, dataset, true, true, false);

        LegendTitle legend = chart.getLegend();
        legend.setPosition(RectangleEdge.RIGHT);
        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setStartAngle(290);
        plot.setDirection(Rotation.CLOCKWISE);
        plot.setForegroundAlpha(0.5f);
        return chart;
    }


}

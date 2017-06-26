package bp.gui.summary;

import bp.services.GraphService;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.title.LegendTitle;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.RectangleEdge;
import org.jfree.util.Rotation;

import javax.swing.*;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.Map;

/**
 * Created by agnieszka on 18.06.2017.
 */
public class PieChart extends JPanel {
    private GraphService graphService;
    private ChartPanel chartPanel;
    private LocalDate date;

    public PieChart(LocalDate date, GraphService graphService) {
        this.graphService = graphService;
        this.date = date;

        chartPanel = new ChartPanel(updateChart(date));
        add(chartPanel);
    }

    public JFreeChart updateChart(LocalDate date) {
        this.date = date;
        return createChart(createDataset(date), "Expenses by category");
    }

    private PieDataset createDataset(LocalDate date) {
        DefaultPieDataset result = new DefaultPieDataset();

        Map<String, Double> pieChartData = graphService.pieChart(date);
        for (String s : pieChartData.keySet()) {
            result.setValue(s, pieChartData.get(s));
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

        plot.setLabelGenerator(new StandardPieSectionLabelGenerator(
                "{1}PLN, {2}", NumberFormat.getNumberInstance(), NumberFormat.getPercentInstance()));
        return chart;
    }

    public ChartPanel getChartPanel() {
        return chartPanel;
    }

    public LocalDate getDate() {
        return date;
    }
}

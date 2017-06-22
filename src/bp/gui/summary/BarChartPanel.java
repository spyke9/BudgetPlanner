package bp.gui.summary;

import bp.services.GraphService;

import javax.swing.*;

/**
 * Created by agnieszka on 18.06.2017.
 */
public class BarChartPanel extends JPanel {
    private BarChart barChart;

    public BarChartPanel(GraphService graphService) {
        barChart = new BarChart("Monthly expenses and income", graphService);

        add(barChart);

        barChart.setVisible(true);
    }

}

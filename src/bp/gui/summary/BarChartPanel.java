package bp.gui.summary;

import javax.swing.*;

/**
 * Created by agnieszka on 18.06.2017.
 */
public class BarChartPanel extends JPanel {
    private BarChart barChart;


    public BarChartPanel() {
        barChart = new BarChart("Monthly expenses and incomes");

        add(barChart);

        barChart.setVisible(true);
    }

}

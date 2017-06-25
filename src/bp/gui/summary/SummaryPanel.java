package bp.gui.summary;


import bp.services.GraphService;
import bp.services.SummaryService;

import javax.swing.*;
import java.awt.*;

/**
 * Created by agnieszka on 17.06.2017.
 */
public class SummaryPanel extends JPanel {

    private PieChartPanel pieChart;
    private BarChartPanel barChart;
    private JTabbedPane tabbedPane;

    public SummaryPanel(SummaryService summaryService, GraphService graphService) {
        barChart = new BarChartPanel(summaryService, graphService);
        pieChart = new PieChartPanel(summaryService, graphService);

        tabbedPane = new JTabbedPane();
        tabbedPane.add("Monthly expenses", barChart);
        tabbedPane.add("Expenses by category", pieChart);

        setLayout(new BorderLayout());
        add(tabbedPane, BorderLayout.CENTER);

    }

}

package bp.gui.summary;


import bp.services.GraphService;
import bp.services.SummaryService;

import javax.swing.*;
import java.awt.*;

/**
 * Created by agnieszka on 17.06.2017.
 */
public class SummaryPanel extends JPanel {

    private PieChartPanel pieChartPanel;
    private BarChartPanel barChart;
    private JTabbedPane tabbedPane;
    private SummaryDataPanel summaryDataPanel;


    public SummaryPanel(SummaryService summaryService, GraphService graphService) {

        pieChartPanel = new PieChartPanel(graphService);
        tabbedPane = new JTabbedPane();
        barChart = new BarChartPanel(graphService);
        summaryDataPanel = new SummaryDataPanel(summaryService);

        tabbedPane.add("Expenses by category", pieChartPanel);
        tabbedPane.add("Monthly expenses", barChart);

        setLayout(new BorderLayout());

        add(summaryDataPanel, BorderLayout.NORTH);
        add(tabbedPane, BorderLayout.CENTER);

    }

}

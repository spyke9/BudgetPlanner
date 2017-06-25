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
//    private SummaryDataPanel summaryDataPanel;


    public SummaryPanel(SummaryService summaryService, GraphService graphService) {

//        summaryDataPanel = new SummaryDataPanel(summaryService);

        barChart = new BarChartPanel(summaryService, graphService);
        pieChart = new PieChartPanel(summaryService, graphService);

        tabbedPane = new JTabbedPane();
        tabbedPane.add("Monthly expenses", barChart);
        tabbedPane.add("Expenses by category", pieChart);

        setLayout(new BorderLayout());

//        add(summaryDataPanel, BorderLayout.NORTH);
        add(tabbedPane, BorderLayout.CENTER);

    }

}

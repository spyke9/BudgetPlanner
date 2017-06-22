package bp.gui.summary;

import bp.services.GraphService;
import org.omg.CORBA.PUBLIC_MEMBER;

import javax.swing.*;

/**
 * Created by agnieszka on 18.06.2017.
 */
public class PieChartPanel extends JPanel {

    private SummaryPieChart summaryPieChart;


    public PieChartPanel(GraphService graphService) {
        summaryPieChart = new SummaryPieChart("Expenses by category", graphService);
        summaryPieChart.setVisible(true);

        add(summaryPieChart);


    }

}

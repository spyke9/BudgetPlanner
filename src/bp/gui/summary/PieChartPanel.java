package bp.gui.summary;

import org.omg.CORBA.PUBLIC_MEMBER;

import javax.swing.*;

/**
 * Created by agnieszka on 18.06.2017.
 */
public class PieChartPanel extends JPanel {

    private SummaryPieChart summaryPieChart;


    public PieChartPanel() {
        summaryPieChart = new SummaryPieChart("Expenses by category");
        summaryPieChart.setVisible(true);

        add(summaryPieChart);


    }

}

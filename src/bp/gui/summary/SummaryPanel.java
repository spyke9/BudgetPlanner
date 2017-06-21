package bp.gui.summary;


import javax.swing.*;
import java.awt.*;

/**
 * Created by agnieszka on 17.06.2017.
 */
public class SummaryPanel extends JPanel {

    private PieChartPanel pieChartPanel = new PieChartPanel();
    private BarChartPanel barChart = new BarChartPanel();
    private JTabbedPane tabbedPane = new JTabbedPane();
    private SummaryDataPanel summaryDataPanel = new SummaryDataPanel();


    public SummaryPanel() {

        tabbedPane.add("Expenses by category", pieChartPanel);
        tabbedPane.add("Monthly expenses", barChart);


        setLayout(new BorderLayout());

        add(summaryDataPanel,BorderLayout.NORTH);
        add(tabbedPane, BorderLayout.CENTER);


    }


}

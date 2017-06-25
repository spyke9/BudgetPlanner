package bp.gui.summary;

import bp.services.GraphService;
import bp.services.SummaryService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by agnieszka on 18.06.2017.
 */
public class BarChartPanel extends JPanel {
    private SummaryService summaryService;
    private GraphService graphService;
    private BarChart barChart;

    private JPanel yearPanel;
    private JLabel yearLabel;
    private JComboBox yearComboBox;

    public BarChartPanel(SummaryService summaryService, GraphService graphService) {
        this.summaryService = summaryService;
        this.graphService = graphService;

        yearLabel = new JLabel("Year: ");
        yearLabel.setHorizontalAlignment(JLabel.CENTER);
        yearLabel.setSize(1, 1);
        yearComboBox = createYearComboBox();
        yearComboBox.setSize(1, 1);


        yearPanel = new JPanel();
        yearPanel.setLayout(new GridLayout(1, 2));
        yearPanel.setSize(5, 0);
        yearPanel.add(yearLabel);
        yearPanel.add(yearComboBox);


        barChart = new BarChart(LocalDate.now().getYear(), graphService);

        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        add(yearPanel, BorderLayout.BEFORE_FIRST_LINE);
        add(barChart, BorderLayout.CENTER);

        barChart.setVisible(true);
    }

    private JComboBox createYearComboBox() {
        List<String> labels = new ArrayList<>();
        LocalDate minYear = summaryService.getMinDate();
        if (minYear != null) {
            for (int year = LocalDate.now().getYear(); year >= minYear.getYear(); year--) {
                labels.add(String.valueOf(year));
            }
        }
        JComboBox jComboBox = new JComboBox(labels.toArray(new String[1]));
        jComboBox.addActionListener(e -> {
            String chosenYearStr = (String) jComboBox.getSelectedItem();
            System.out.println(chosenYearStr);
            int year = Integer.parseInt(chosenYearStr);
            barChart.getChartPanel().setChart(barChart.updateChart(year));

            validate();
            repaint();
        });

        return jComboBox;
    }

}

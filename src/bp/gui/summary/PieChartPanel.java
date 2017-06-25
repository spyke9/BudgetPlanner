package bp.gui.summary;

import bp.services.GraphService;
import bp.services.SummaryService;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by agnieszka on 18.06.2017.
 */
public class PieChartPanel extends JPanel {
    private SummaryService summaryService;
    private GraphService graphService;
    private PieChart pieChart;

    private JPanel dateChoicePanel;
    private JLabel monthLabel;
    private JLabel yearLabel;

    private JComboBox monthComboBox;
    private JComboBox yearComboBox;

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM");


    public PieChartPanel(SummaryService summaryService, GraphService graphService) {
        this.summaryService = summaryService;
        this.graphService = graphService;

        monthLabel = new JLabel("Month: ");
        monthLabel.setHorizontalAlignment(JLabel.CENTER);
        monthLabel.setSize(1, 1);
        monthComboBox = createMonthComboBox();
//        monthComboBox = new JComboBox();
        monthComboBox.setSize(1, 1);

        yearLabel = new JLabel("Year: ");
        yearLabel.setHorizontalAlignment(JLabel.CENTER);
        yearLabel.setSize(1, 1);
        yearComboBox = createYearComboBox();
        yearComboBox.setSize(1, 1);

        dateChoicePanel = new JPanel();
        dateChoicePanel.setLayout(new GridLayout(1, 4));
//        dateChoicePanel.setSize(4, 0);
        dateChoicePanel.add(monthLabel);
        dateChoicePanel.add(monthComboBox);
        dateChoicePanel.add(yearLabel);
        dateChoicePanel.add(yearComboBox);


        pieChart = new PieChart(LocalDate.now(), this.graphService);

        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        add(dateChoicePanel, BorderLayout.BEFORE_FIRST_LINE);
        add(pieChart, BorderLayout.CENTER);

        pieChart.setVisible(true);
    }

    private JComboBox createYearComboBox() {
        List<String> labels = new ArrayList<>();
        int now = LocalDate.now().getYear();
        for (int year = summaryService.getMinDate().getYear(); year <= now; year++) {
            labels.add(String.valueOf(year));
        }
        JComboBox jComboBox = new JComboBox(labels.toArray(new String[1]));
        jComboBox.addActionListener(e -> {
            String chosenYearStr = (String) jComboBox.getSelectedItem();
            System.out.println(chosenYearStr);

            int year = Integer.parseInt(chosenYearStr);
            LocalDate newDate = LocalDate.of(year, pieChart.getDate().getMonth(), 1);

            pieChart.getChartPanel().setChart(pieChart.updateChart(newDate));

            validate();
            repaint();
        });

        return jComboBox;
    }

    private JComboBox createMonthComboBox() {
        List<String> labels = new ArrayList<>();
        LocalDate date1 = LocalDate.of(2000, 1, 1);
        LocalDate date2 = LocalDate.of(2001, 1, 1);
//        for (int i = 0; i < 12; i++) {

        for (; date1.isBefore(date2); date1 = date1.plusMonths(1)) {
            labels.add(date1.getMonth().getDisplayName(TextStyle.FULL, Locale.getDefault()));
//            System.out.println(date1.getMonth().getDisplayName(TextStyle.FULL, Locale.getDefault()));
        }
        JComboBox jComboBox = new JComboBox(labels.toArray(new String[1]));
//        JComboBox jComboBox = new JComboBox(java.time.Month.values());
//        jComboBox.addActionListener(e -> {
//            String chosenMonthStr = (String) jComboBox.getSelectedItem();
//            System.out.println(chosenMonthStr);
////TODO naprawić
//            int month = Month.parseMonth(chosenMonthStr).getMonth();
//
//            LocalDate date = LocalDate.of(pieChart.getDate().getYear(), month, 1);
//            pieChart.getChartPanel().setChart(pieChart.updateChart(date));
//
//            validate();
//            repaint();
//        });

        return jComboBox;
    }

}

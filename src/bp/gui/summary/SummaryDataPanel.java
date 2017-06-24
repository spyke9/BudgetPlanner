package bp.gui.summary;

import bp.services.SummaryService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by agnieszka on 21.06.2017.
 */
public class SummaryDataPanel extends JPanel {
    private SummaryService summaryService;
    private JLabel datelabel, monthlabel, yearlabel;
    private JComboBox chooseMonth, chooseYear;

    public SummaryDataPanel(SummaryService summaryService) {
        this.summaryService = summaryService;

        datelabel = new JLabel("Date: ");
        monthlabel = new JLabel("Month: ");
        yearlabel = new JLabel("Year: ");
        chooseMonth = createMonthComboBox();
        chooseYear = createYearComboBox();
//        chooseYear = new JComboBox();

        setLayout(new GridLayout(1, 6));

        add(new JLabel());
//        add(new JLabel());
//        add(datelabel);
        add(monthlabel);
        add(chooseMonth);
        add(yearlabel);
        add(chooseYear);
        add(new JLabel());
//        add(new JLabel());

//        datelabel.setHorizontalAlignment(JLabel.RIGHT);
        monthlabel.setHorizontalAlignment(JLabel.CENTER);
        yearlabel.setHorizontalAlignment(JLabel.CENTER);


    }

    private JComboBox createMonthComboBox() {
        summaryService.getMinDate();


        JComboBox comboBox = new JComboBox();

        return comboBox;
    }

    private JComboBox createYearComboBox() {
        List<String> labels = new ArrayList<>();
        int now = LocalDate.now().getYear();
        for (int year = summaryService.getMinDate().getYear(); year < now; year++) {
            labels.add(String.valueOf(year));
        }
        JComboBox jComboBox = new JComboBox(labels.toArray(new String[1]));
        jComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String item = (String) jComboBox.getSelectedItem();
                System.out.println(item);
            }
        });


        return jComboBox;
    }

    public void actionPerformed(ActionEvent e) {
        JComboBox cb = (JComboBox) e.getSource();
        String petName = (String) cb.getSelectedItem();
//        updateLabel(petName);
    }

}

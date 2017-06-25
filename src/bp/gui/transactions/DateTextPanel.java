package bp.gui.transactions;

import bp.model.ITransaction;
import bp.services.TransactionService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collection;

/**
 * Created by agnieszka on 21.06.2017.
 */
public class DateTextPanel extends JPanel implements ActionListener {

    TransactionService transactionService;
    private JTextField date1text, date2text;
    private JButton findButton;
    private AbstractTableModel tableModel;


    public DateTextPanel(TransactionService transactionService, AbstractTableModel tableModel) {
        this.transactionService = transactionService;
        this.tableModel = tableModel;
        date1text = new JTextField("yyyy-mm-dd");
        date2text = new JTextField("yyyy-mm-dd");
        findButton = new JButton("Find");
        findButton.addActionListener(this);

        setLayout(new GridLayout(4, 1));
        add(new JLabel());
        add(date1text);
        add(date2text);
        add(findButton);
    }


    public void actionPerformed(ActionEvent e) {

        Object source = e.getSource();

        if (source == findButton) {
            String getdate1 = date1text.getText();
            String getdate2 = date2text.getText();
            if (!getdate1.isEmpty() && !getdate2.isEmpty()) {

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                try {
                    LocalDate localDate1 = LocalDate.parse(getdate1, formatter);
                    LocalDate localDate2 = LocalDate.parse(getdate2, formatter);
                    tableModel.update(localDate1, localDate2);
                } catch (Exception ex) {

                }
            } else {
                tableModel.update();
            }


        }


    }


}

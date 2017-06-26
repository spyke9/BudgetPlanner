package bp.gui.addform;


import bp.gui.transactions.AbstractTableModel;
import bp.model.CategoryType;
import bp.model.Transaction;
import bp.services.SummaryService;
import bp.services.TransactionService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Created by agnieszka on 17.06.2017.
 */
public class AddForm extends JFrame implements ActionListener {

    private LocalDate datenow = LocalDate.now();
    private JPanel buttonpanel = new JPanel();
    private JPanel componentPanel = new JPanel();
    private JPanel mainpanel = new JPanel();
    private JButton cancelbutton = new JButton("Cancel");
    private JButton addbutton = new JButton("Add Transaction");
    private JTextField dateTextField = new JTextField(datenow.toString());
    private JComboBox categorycombobox = new JComboBox();
    private JComboBox typecombobox = new JComboBox();
    private LabelPanel labelPanel = new LabelPanel();
    private JTextField amountfield = new JTextField("0.0");
    private TransactionService transactionService;
    private SummaryService summaryService;
    private AbstractTableModel tableModel;


    public AddForm(TransactionService transactionService, SummaryService summaryService, AbstractTableModel abstractTableModel) {

        this.tableModel = abstractTableModel;
        this.transactionService = transactionService;
        this.summaryService = summaryService;

        buttonpanel.setLayout(new GridLayout(1, 2));
        buttonpanel.add(addbutton);
        buttonpanel.add(cancelbutton);
        addbutton.addActionListener(this);
        cancelbutton.addActionListener(this);

        componentPanel.setLayout(new GridLayout(5, 1));
        componentPanel.add(dateTextField);
        componentPanel.add(categorycombobox);
        componentPanel.add(typecombobox);
        componentPanel.add(amountfield);

        mainpanel.setLayout(new GridLayout(1, 2));
        mainpanel.add(labelPanel);
        mainpanel.add(componentPanel);

        categorycombobox.setModel(new DefaultComboBoxModel(CategoryType.getNames().toArray()));
        typecombobox.setModel(new DefaultComboBoxModel(Transaction.TransactionType.getNames().toArray()));

        setLayout(new BorderLayout());

        this.setTitle("Add Transaction");
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(350, 250);
        this.setResizable(false);
        this.add(mainpanel, BorderLayout.CENTER);
        this.add(buttonpanel, BorderLayout.SOUTH);
        this.setVisible(true);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source == cancelbutton) {

            this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
        } else if (source == addbutton) {

            Transaction transaction = new Transaction();
            String getdate = dateTextField.getText();
            String getcategory = categorycombobox.getSelectedItem().toString();
            String gettype = typecombobox.getSelectedItem().toString();
            String getamount = amountfield.getText();

            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate localDate = LocalDate.parse(getdate, formatter);

                double amount = Double.parseDouble(getamount);
                transaction.setAmount(amount);
                transaction.setDate(localDate);
                transaction.setCategory(CategoryType.fromName(getcategory));
                transaction.setType(Transaction.TransactionType.fromName(gettype));

                transactionService.addTransaction(transaction);
                summaryService.updateSummaryRepository(localDate);

                tableModel.update();

            } catch (Exception ex) {
                System.out.println("Niepoprawny format danych");

            }

            this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
        }


    }


}

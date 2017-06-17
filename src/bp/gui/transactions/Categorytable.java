package bp.gui.transactions;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

/**
 * Created by agnieszka on 17.06.2017.
 */
public class Categorytable extends JPanel
{
    private JTable category;
    private JTable forecast;
    private JTable spend;
    private String name1[]={"Category"};
    private String name2[]={"Forecast"};
    private String name3[]={"Last month:"};

    public Categorytable()
    {


        DefaultTableModel model1 = new DefaultTableModel(name1, 20);
        category = new JTable(model1);
        DefaultTableModel model2 = new DefaultTableModel(name2, 20);
        forecast = new JTable(model2);
        DefaultTableModel model3 = new DefaultTableModel(name3, 20);
        spend = new JTable(model3);

        this.setLayout(new GridLayout(1,3));
        add(new JScrollPane(category));
        add(new JScrollPane(forecast));
        add(new JScrollPane(spend));



    }

}

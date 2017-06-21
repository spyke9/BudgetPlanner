package bp.gui.addform;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

/**
 * Created by agnieszka on 17.06.2017.
 */
public class AddForm extends JFrame implements ActionListener {


    JPanel panel = new JPanel();
    JButton cancelbutton = new JButton("Cancel");
    JButton addbutton = new JButton("Add Transaction");

    public AddForm() {
        AddPanel addPanel = new AddPanel();

        panel.setLayout(new GridLayout(1, 2));

        panel.add(addbutton);
        panel.add(cancelbutton);
        addbutton.addActionListener(this);
        cancelbutton.addActionListener(this);

        setLayout(new BorderLayout());

        this.setTitle("Add Transaction");
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(350, 250);
        this.setResizable(false);
        this.add(addPanel, BorderLayout.CENTER);
        this.add(panel, BorderLayout.SOUTH);
        this.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source == cancelbutton) {

            this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));

        }


    }


}

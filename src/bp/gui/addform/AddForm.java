package bp.gui.addform;


import javax.swing.*;

/**
 * Created by agnieszka on 17.06.2017.
 */
public class AddForm extends JFrame {


    public AddForm() {
        AddPanel addPanel = new AddPanel();

        this.setTitle("Add Transaction");
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(350, 250);
        this.setResizable(false);


        this.add(addPanel);


        this.setVisible(true);


    }


}

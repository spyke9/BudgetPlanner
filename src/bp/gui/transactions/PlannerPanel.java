package bp.gui.transactions;

import javax.swing.*;
import java.awt.*;

/**
 * Created by agnieszka on 17.06.2017.
 */
public class PlannerPanel extends JPanel
{
    private Categorytable categorytable = new Categorytable();
    private SavePanel savePanel = new SavePanel();


    public PlannerPanel()
    {
        super();

        setLayout(new BorderLayout());

        add(categorytable, BorderLayout.NORTH);
        add(savePanel,BorderLayout.CENTER);
    }

}

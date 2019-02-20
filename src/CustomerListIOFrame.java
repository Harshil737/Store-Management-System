import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

class CustomerListIOFrame extends JInternalFrame {
    private JMenuBar menuBar;
    private JMenu menu;
    private JMenuItem menuItemExit;
    private CustomerDB customerDB;

    CustomerListIOFrame(JPanel customerIOGUI) {
        customerDB = new CustomerDB();
        this.add(customerIOGUI);
        this.setSize(400, 200);
        this.setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
        this.setClosable(true);
        this.setResizable(true);

        menuBar = new JMenuBar();
        menu = new JMenu("File");
        menuItemExit = new JMenuItem("Exit");

        menu.setMnemonic(KeyEvent.VK_F);

        menuItemExit.setAccelerator(KeyStroke.getKeyStroke('X', InputEvent.CTRL_DOWN_MASK));

        menu.add(menuItemExit);

        menuBar.add(menu);

        menuItemExit.addActionListener(new EventHandler());

        this.setJMenuBar(menuBar);
        this.setSize(700, 300);
        this.setTitle("Project");
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }

    class EventHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == menuItemExit) {
                dispose();
            }
        }
    }
}

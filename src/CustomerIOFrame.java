import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class CustomerIOFrame extends JInternalFrame implements CustomerActionListener {
    private JPanel customerIOGUI;
    private JMenuBar menuBar;
    private JMenu menu;
    private JMenuItem menuItemLoad;
    private JMenuItem menuItemSave;
    private JMenuItem menuItemExit;
    private CustomerDB customerDB;

    public CustomerIOFrame(JPanel customerIOGUI) {
        customerDB = new CustomerDB();
        this.customerIOGUI = customerIOGUI;
        ((CustomerGUI) customerIOGUI).setActionListener(this);
        this.add(customerIOGUI);
        this.setSize(400, 200);
        this.setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
        this.setClosable(true);
        this.setResizable(true);

        menuBar = new JMenuBar();
        menu = new JMenu("File");
        menuItemSave = new JMenuItem("Save");
        menuItemLoad = new JMenuItem("Load");
        menuItemExit = new JMenuItem("Exit");

        menu.setMnemonic(KeyEvent.VK_F);

        menuItemSave.setAccelerator(KeyStroke.getKeyStroke('S', InputEvent.CTRL_DOWN_MASK));
        menuItemLoad.setAccelerator(KeyStroke.getKeyStroke('L', InputEvent.CTRL_DOWN_MASK));
        menuItemExit.setAccelerator(KeyStroke.getKeyStroke('E', InputEvent.CTRL_DOWN_MASK));

        menu.add(menuItemSave);
        menu.add(menuItemLoad);
        menu.add(menuItemExit);

        menuBar.add(menu);

        menuItemSave.addActionListener(new EventHandler());
        menuItemLoad.addActionListener(new EventHandler());
        menuItemExit.addActionListener(new EventHandler());

        this.setJMenuBar(menuBar);
        this.setSize(700, 300);
        this.setTitle("Project");
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }

    class EventHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == menuItemSave) {
                try {
                    customerDB.writeToFile();
                    JOptionPane.showMessageDialog(null, "Data written to File.", "Success", JOptionPane.INFORMATION_MESSAGE);
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                }

            } else if (e.getSource() == menuItemLoad) {
                try {
                    ArrayList<Customer> customers = customerDB.fetchToArrayList();
                    customerDB.setList(customers);
                    JOptionPane.showMessageDialog(null, "Data loaded to ArrayList.", "Success", JOptionPane.INFORMATION_MESSAGE);
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                }
            } else if (e.getSource() == menuItemExit) {
//                System.exit(0);
                dispose();
            }
        }
    }

    @Override
    public void onCustomerAddClick(Customer c) {
        customerDB.addCustomer(c);
    }

    @Override
    public boolean checkCustomerIDAvailable(int id) {
        return customerDB.checkCustomerIdAvailable(id);
    }

    @Override
    public Customer onCustomerFindClick(int id) throws FileNotFoundException {
        return customerDB.findCustomer(id);
    }

    @Override
    public void onCustomerUpdateClick(Customer c) throws FileNotFoundException {
        customerDB.updateCustomerAtIndex(c);
    }
}

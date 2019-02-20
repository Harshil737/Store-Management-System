import javax.swing.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;

class StoreFrame extends JFrame {
    private JMenu menu;
    private JMenuBar menuBar;
    private JMenuItem menuItemProducts, menuItemCustomers, menuItemExit, menuItemCustomersList;
    private JDesktopPane desktopPane;

    StoreFrame() {
        this.initializeComponents();
        this.setSize(750, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Store");
        this.menuItemProducts.addActionListener(e -> showProductsGUI());
        this.menuItemCustomers.addActionListener(e -> showCustomersGUI());
        this.menuItemCustomersList.addActionListener(e -> {
            showCustomersList();
        });
        this.menuItemExit.addActionListener(e -> exit());
    }

    private void exit() {
        System.exit(0);
    }

    private void showCustomersGUI() {
        CustomerGUI customer = new CustomerGUI();
        JInternalFrame frame = new CustomerIOFrame(customer);
        frame.setTitle("Customer");
        this.desktopPane.add(frame);
    }

    private void showProductsGUI() {
        ProductGUI product = new ProductGUI();
        JInternalFrame frame = new ProductIOFrame(product);
        frame.setTitle("Product");
        this.desktopPane.add(frame);
    }

    private void showCustomersList() {
        CustomersList customersList = new CustomersList();
        JInternalFrame jIF = new CustomerListIOFrame(customersList);
        jIF.setTitle("Customers List");
        this.desktopPane.add(jIF);
    }

    private void initializeComponents() {
        menuBar = new JMenuBar();
        menu = new JMenu("Operations");
        menuItemCustomers = new JMenuItem("Customers");
        menuItemProducts = new JMenuItem("Products");
        menuItemCustomersList = new JMenuItem("Customers List");
        menuItemExit = new JMenuItem("Exit");

        menu.add(menuItemProducts);
        menu.add(menuItemCustomers);
        menu.add(menuItemCustomersList);
        menu.add(menuItemExit);
        menuBar.add(menu);

        menu.setMnemonic(KeyEvent.VK_O);
        menuItemProducts.setAccelerator(KeyStroke.getKeyStroke('P', InputEvent.CTRL_DOWN_MASK));
        menuItemCustomers.setAccelerator(KeyStroke.getKeyStroke('C', InputEvent.CTRL_DOWN_MASK));
        menuItemCustomersList.setAccelerator(KeyStroke.getKeyStroke('L', InputEvent.CTRL_DOWN_MASK));
        menuItemExit.setAccelerator(KeyStroke.getKeyStroke('X', InputEvent.CTRL_DOWN_MASK));

        this.setJMenuBar(menuBar);
        desktopPane = new JDesktopPane();
        this.add(desktopPane);
    }
}

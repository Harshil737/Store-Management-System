import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.awt.event.InputEvent.CTRL_DOWN_MASK;

public class ProductIOFrame extends JInternalFrame implements ProductActionClickListner {

    private JPanel productIOGUI;
    private JMenuBar menuBar;
    private JMenu menuFile;
    private JMenuItem menuItemLoad;
    private JMenuItem menuItemSave;
    private JMenuItem menuItemExit;
    private ProductDB productDB;

    public ProductIOFrame(ProductGUI product) {
        this.productDB = new ProductDB();
        productIOGUI = product;
        ((ProductGUI) productIOGUI).setActionClickListner(this);
        this.add(productIOGUI);
        this.setSize(400, 200);
        this.setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
        this.setClosable(true);
        this.setResizable(true);

        menuBar = new JMenuBar();
        menuFile = new JMenu("File");
        menuItemLoad = new JMenuItem("Load");
        menuItemSave = new JMenuItem("Save");
        menuItemExit = new JMenuItem("Exit");

        menuFile.add(menuItemLoad);
        menuFile.add(menuItemSave);
        menuFile.add(menuItemExit);

        menuFile.setMnemonic(KeyEvent.VK_F);

        menuItemSave.setAccelerator(KeyStroke.getKeyStroke('S', CTRL_DOWN_MASK));
        menuItemLoad.setAccelerator(KeyStroke.getKeyStroke('L', CTRL_DOWN_MASK));
        menuItemExit.setAccelerator(KeyStroke.getKeyStroke('E', CTRL_DOWN_MASK));

        menuBar.add(menuFile);

        menuItemSave.addActionListener(new EventHandler());
        menuItemLoad.addActionListener(new EventHandler());
        menuItemExit.addActionListener(new EventHandler());

        this.setJMenuBar(menuBar);

        this.setSize(700, 300);
        this.setTitle("Project");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    class EventHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == menuItemSave) {
                try {
                    productDB.writeToFile();
                    JOptionPane.showMessageDialog(null, "Data written to File.", "Success", JOptionPane.INFORMATION_MESSAGE);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(ProductIOFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (e.getSource() == menuItemLoad) {
                try {
                    ArrayList<Product> tempList = productDB.fetchToArrayList();
                    productDB.setProductList(tempList);
                    JOptionPane.showMessageDialog(null, "Data loaded to ArrayList.", "Success", JOptionPane.INFORMATION_MESSAGE);
                } catch (FileNotFoundException | RangeException ex) {
                    Logger.getLogger(ProductIOFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (e.getSource() == menuItemExit) {
                dispose();
            }
        }
    }

    @Override
    public void onProductAddClick(Product p) {
        productDB.addProduct(p);
    }

    @Override
    public Product onProductFind(int id) {
        Product p = null;
        try {
            p = productDB.findProduct(id);
        } catch (FileNotFoundException | RangeException ex) {
            Logger.getLogger(ProductIOFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        return p;
    }

    @Override
    public void onProductUpdate(Product p) {
        try {
            this.productDB.updateProductAtIndex(p);
        } catch (FileNotFoundException | RangeException ex) {
            Logger.getLogger(ProductIOFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public boolean checkProductIdAvailable(int id) {
        return productDB.checkProductIdAvailable(id);
    }
}

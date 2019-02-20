import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
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
        productIOGUI = product;
        this.productDB = new ProductDB();
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

        menuFile.setMnemonic(KeyEvent.VK_F);

        menuItemSave.setAccelerator(KeyStroke.getKeyStroke('S', CTRL_DOWN_MASK));
        menuItemLoad.setAccelerator(KeyStroke.getKeyStroke('L', CTRL_DOWN_MASK));
        menuItemExit.setAccelerator(KeyStroke.getKeyStroke('X', CTRL_DOWN_MASK));

        menuFile.add(menuItemLoad);
        menuFile.add(menuItemSave);
        menuFile.add(menuItemExit);

        menuBar.add(menuFile);

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
                    productDB.writeToFile();
                    JOptionPane.showMessageDialog(null, "Saved.", "Success", JOptionPane.INFORMATION_MESSAGE);
                } catch (IOException ex) {
                    Logger.getLogger(ProductIOFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (e.getSource() == menuItemLoad) {
                ArrayList<Product> tempList = productDB.fetchToArrayList();
                productDB.setProductList(tempList);
                JOptionPane.showMessageDialog(null, "Fetched.", "Success", JOptionPane.INFORMATION_MESSAGE);
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
        return productDB.findProduct(id);
    }

    @Override
    public void onProductUpdate(Product p) throws IOException {
        productDB.updateProductAtIndex(p);
    }

    @Override
    public boolean checkProductIdAvailable(int id) {
        return productDB.checkProductIdAvailable(id);
    }
}

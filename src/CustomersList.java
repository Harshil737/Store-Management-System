import javax.swing.*;
import java.awt.*;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

class CustomersList extends JPanel {
    private JLabel lblFullName;
    private JComboBox cmbNames;
    private JTextArea txtOutput;
    private ArrayList<Customer> customerList = new ArrayList();

    CustomersList() {
        lblFullName = new JLabel("Name: ");
        cmbNames = new JComboBox();
        txtOutput = new JTextArea();
        txtOutput.setEditable(false);
        this.setLayout(new BorderLayout());
        JPanel northPanel = new JPanel();
        northPanel.add(lblFullName);
        northPanel.add(cmbNames);
        this.add(northPanel, BorderLayout.NORTH);
        this.add(txtOutput, BorderLayout.CENTER);
        this.loadNames();
        cmbNames.addItemListener(e -> txtOutput.setText(customerList.get(CustomersList.this.cmbNames.getSelectedIndex()).toString()));
    }

    private void loadNames() {
        File file = new File("Customers.txt");
        String search;
        String[] searchSplit;
        Customer c = new Customer();

        if (file.exists()) {
            try {
                DataInputStream inputStream = new DataInputStream(new FileInputStream(file));
                while (inputStream.available() > 0) {
                    search = inputStream.readUTF();
                    searchSplit = search.split(":");
                    c = new Customer(Integer.parseInt(searchSplit[0]), searchSplit[1], searchSplit[2], searchSplit[3], searchSplit[4]);
                    customerList.add(c);
                    cmbNames.addItem(c.getName());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
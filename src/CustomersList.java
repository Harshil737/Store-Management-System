import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class CustomersList extends JPanel {
    private JLabel lblFullName;
    private JComboBox cmbNames;
    private JTextArea txtOutput;
    private String fileName = "customers.txt";
    private ArrayList<Customer> customerList = new ArrayList();

    public CustomersList() throws FileNotFoundException {
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
        cmbNames.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                txtOutput.setText(customerList.get(CustomersList.this.cmbNames.getSelectedIndex()).toString());
            }
        });
    }

    private void loadNames() throws FileNotFoundException {
        File file = new File("customers.txt");
        Scanner s = new Scanner(file);
        String search = "";
        String[] searchSplit = new String[4];
        Customer c = new Customer();
        while (s.hasNextLine()) {
            search = s.nextLine();
            if (!search.isEmpty()) {
                searchSplit = search.split(":");
                c = new Customer(Integer.parseInt(searchSplit[0]), searchSplit[1], searchSplit[2], searchSplit[3], searchSplit[4]);
                customerList.add(c);
                cmbNames.addItem(c.getName());
            }
        }

    }
}

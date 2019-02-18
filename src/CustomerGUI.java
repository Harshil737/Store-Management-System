import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

public class CustomerGUI extends JPanel {
    private JLabel lblCustomerID, lblName, lblPhone, lblEmail, lblPostalCode;
    private JTextField txtCustomerID, txtName, txtPhone, txtEmail, txtPostalCode;
    private JButton btnAdd, btnUpdate, btnFind, btnReset;
    private CustomerActionListener actionListener = null;
    private JList jList;

    public CustomerGUI() {
        lblCustomerID = new JLabel("Customer ID");
        lblName = new JLabel("Name");
        lblPhone = new JLabel("Phone");
        lblEmail = new JLabel("Email");
        lblPostalCode = new JLabel("Postal Code");

        txtCustomerID = new JTextField(5);
        txtName = new JTextField(25);
        txtPhone = new JTextField(10);
        txtEmail = new JTextField(30);
        txtPostalCode = new JTextField(6);

        btnAdd = new JButton("Add");
        btnFind = new JButton("Find");
        btnUpdate = new JButton("Update");
        btnReset = new JButton("Reset");

        btnAdd.addActionListener(new EventHandler());
        btnFind.addActionListener(new EventHandler());
        btnUpdate.addActionListener(new EventHandler());
        btnReset.addActionListener(new EventHandler());

        this.setLayout(new GridLayout(7, 2));
        this.add(lblCustomerID);
        this.add(txtCustomerID);
        this.add(lblName);
        this.add(txtName);
        this.add(lblPhone);
        this.add(txtPhone);
        this.add(lblEmail);
        this.add(txtEmail);
        this.add(lblPostalCode);
        this.add(txtPostalCode);
        this.add(btnAdd);
        this.add(btnFind);
        this.add(btnUpdate);
        this.add(btnReset);
    }

    public void setActionListener(CustomerActionListener actionListener) {
        this.actionListener = actionListener;
    }

    public class EventHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == btnAdd) {
                Customer c = new Customer();
                try {
                    int customerID = Integer.parseInt(txtCustomerID.getText());
                    if (actionListener.checkCustomerIDAvailable(customerID)) {
                        c.setCustomerId(customerID);
                    } else {
                        JOptionPane.showMessageDialog(null, "Customer ID is taken.");
                        return;
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid Customer ID");
                    return;
                }

                c.setName(txtName.getText());
                c.setPhone(txtPhone.getText());
                c.setEmail(txtEmail.getText());
                c.setPostalCode(txtPostalCode.getText());

                reset();

                actionListener.onCustomerAddClick(c);
            } else if (e.getSource() == btnFind) {
                if (!txtCustomerID.getText().isEmpty()) {
                    Customer c;
                    try {
                        c = actionListener.onCustomerFindClick(Integer.parseInt(txtCustomerID.getText()));
                        find(c);
                    } catch (FileNotFoundException | NumberFormatException e1) {
                        JOptionPane.showMessageDialog(null, e1);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter product ID.");
                }
            } else if (e.getSource() == btnUpdate) {
                Customer c = new Customer();
                try {
                    c.setCustomerId(Integer.parseInt(txtCustomerID.getText()));
                } catch (NumberFormatException e1) {
                    JOptionPane.showMessageDialog(null, "Invalid Customer ID.");
                }
                c.setName(txtName.getText());

                try {
                    c.setPhone(txtPhone.getText());
                } catch (NumberFormatException e1) {
                    JOptionPane.showMessageDialog(null, "Invalid Phone.");
                }

                c.setEmail(txtEmail.getText());
                c.setPostalCode(txtPostalCode.getText());
                try {
                    actionListener.onCustomerUpdateClick(c);
                    JOptionPane.showMessageDialog(null, "Customer updated.");
                    reset();
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                }
            } else if (e.getSource() == btnReset) {
                reset();
            }
        }
    }

    private void reset() {
        txtCustomerID.setText("");
        txtName.setText("");
        txtPhone.setText("");
        txtEmail.setText("");
        txtPostalCode.setText("");

        btnAdd.setEnabled(true);
        btnFind.setEnabled(true);
        btnUpdate.setEnabled(true);
        btnReset.setEnabled(true);
        txtCustomerID.setEnabled(true);
    }

    private void find(Customer c) {
        if (c != null) {
            txtCustomerID.setText(String.valueOf(c.getCustomerId()));
            txtName.setText(c.getName());
            txtPhone.setText(c.getPhone());
            txtEmail.setText(c.getEmail());
            txtPostalCode.setText(c.getPostalCode());

            txtCustomerID.setEnabled(false);
            btnAdd.setEnabled(false);
            btnFind.setEnabled(false);
            btnReset.setEnabled(true);
            btnUpdate.setEnabled(true);
        } else {
            JOptionPane.showMessageDialog(null, "Customer not found.");
            txtCustomerID.setText("");
        }
    }
}

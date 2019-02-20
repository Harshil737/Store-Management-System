/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author Harshil
 */
class CustomerGUI extends JPanel {
    private JLabel lblCustomerID, lblName, lblPhone, lblEmail, lblPostalCode;
    private JTextField txtCustomerID, txtName, txtPhone, txtEmail, txtPostalCode;
    private JButton btnAdd, btnUpdate, btnFind, btnReset;
    private CustomerActionListener actionListener = null;

    CustomerGUI() {
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
        btnUpdate.setEnabled(false);
        btnReset.addActionListener(new EventHandler());
        btnReset.setEnabled(false);

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

    void setActionListener(CustomerActionListener actionListener) {
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
                    JOptionPane.showMessageDialog(null, "Please enter customer ID.");
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
                } catch (IOException e1) {
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
        btnUpdate.setEnabled(false);
        btnReset.setEnabled(false);
        txtCustomerID.setEnabled(true);
    }

    private void find(Customer c) {
        if (c != null) {
            txtCustomerID.setText(c.getCustomerId() + "");
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
            txtName.setText("");
            txtEmail.setText("");
            txtPhone.setText("");
            txtPostalCode.setText("");
        }
    }
}

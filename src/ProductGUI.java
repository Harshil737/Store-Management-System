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
class ProductGUI extends JPanel {
    private JLabel lblProductID, lblProductName, lblProductPrice, lblQty;
    private JTextField txtProductID, txtProductName, txtProductPrice, txtQty;
    private JButton btnAdd, btnUpdate, btnFind, btnReset;
    private ProductActionClickListner actionClickListner = null;

    ProductGUI() {
        lblProductID = new JLabel("Product ID");
        lblProductName = new JLabel("Product Name");
        lblProductPrice = new JLabel("Product Price");
        lblQty = new JLabel("Quantity");

        txtProductID = new JTextField(5);
        txtProductName = new JTextField(20);
        txtProductPrice = new JTextField(7);
        txtQty = new JTextField(5);

        btnAdd = new JButton("Add");
        btnUpdate = new JButton("Update");
        btnFind = new JButton("Find");
        btnReset = new JButton("Reset");

        btnAdd.addActionListener(new EventHandler());
        btnUpdate.addActionListener(new EventHandler());
        btnUpdate.setEnabled(false);
        btnFind.addActionListener(new EventHandler());
        btnReset.addActionListener(new EventHandler());
        btnReset.setEnabled(false);

        this.setLayout(new GridLayout(6, 1));
        this.add(lblProductID);
        this.add(txtProductID);
        this.add(lblProductName);
        this.add(txtProductName);
        this.add(lblProductPrice);
        this.add(txtProductPrice);
        this.add(lblQty);
        this.add(txtQty);
        this.add(btnAdd);
        this.add(btnUpdate);
        this.add(btnFind);
        this.add(btnReset);
    }

    void setActionClickListner(ProductActionClickListner actionClickListner) {
        this.actionClickListner = actionClickListner;
    }

    public class EventHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == btnAdd) {
                Product p = new Product();
                try {
                    int productID = Integer.parseInt(txtProductID.getText());
                    if (actionClickListner.checkProductIdAvailable(productID)) {
                        p.setProductId(productID);
                    } else {
                        JOptionPane.showMessageDialog(null, "Product ID is taken");
                        return;
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid Product ID");
                    return;
                }

                p.setProductName(txtProductName.getText());

                try {
                    p.setProductPrice(Double.parseDouble(txtProductPrice.getText()));
                } catch (RangeException e1) {
                    JOptionPane.showMessageDialog(null, "Invalid Product Price");
                    return;
                }

                try {
                    p.setProductQty(Integer.parseInt(txtQty.getText()));
                } catch (RangeException e1) {
                    JOptionPane.showMessageDialog(null, "Invalid Product QTY");
                    return;
                }

                reset();

                actionClickListner.onProductAddClick(p);
            } else if (e.getSource() == btnUpdate) {
                Product p = new Product();
                try {
                    p.setProductId(Integer.parseInt(txtProductID.getText()));
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid Product ID");
                    return;
                }

                p.setProductName(txtProductName.getText());

                try {
                    p.setProductPrice(Double.parseDouble(txtProductPrice.getText()));
                } catch (NumberFormatException | RangeException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid Product Price");
                    return;
                }

                try {
                    p.setProductQty(Integer.parseInt(txtQty.getText()));
                } catch (NumberFormatException | RangeException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid Product QTY");
                    return;
                }
                try {
                    actionClickListner.onProductUpdate(p);
                    JOptionPane.showMessageDialog(null, "Product updated.");
                    reset();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            } else if (e.getSource() == btnFind) {
                if (!txtProductID.getText().isEmpty()) {
                    Product p;
                    try {
                        p = actionClickListner.onProductFind(Integer.parseInt(txtProductID.getText()));
                        find(p);
                    } catch (FileNotFoundException | NumberFormatException e1) {
                        JOptionPane.showMessageDialog(null, e1);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter product id.");
                }
            } else if (e.getSource() == btnReset) {
                reset();
            }
        }

    }

    private void find(Product p) {
        if (p != null) {
            txtProductID.setText(p.getProductId() + "");
            txtProductName.setText(p.getProductName());
            txtProductPrice.setText(p.getProductPrice() + "");
            txtQty.setText(p.getProductQty() + "");

            btnAdd.setEnabled(false);
            btnUpdate.setEnabled(true);
            btnReset.setEnabled(true);
            btnFind.setEnabled(false);
            txtProductID.setEnabled(false);
        } else {
            JOptionPane.showMessageDialog(null, "Product not found.");
            txtProductID.setText("");
            txtProductName.setText("");
            txtProductPrice.setText("");
            txtQty.setText("");
        }

    }

    private void reset() {
        txtProductID.setText("");
        txtProductName.setText("");
        txtProductPrice.setText("");
        txtQty.setText("");

        btnUpdate.setEnabled(false);
        btnReset.setEnabled(false);
        btnAdd.setEnabled(true);
        btnFind.setEnabled(true);
        txtProductID.setEnabled(true);
    }
}

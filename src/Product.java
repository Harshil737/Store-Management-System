/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * @author Harshil
 */
public class Product {

    private int productQty, productId;
    private double productPrice;
    private String productName;

    Product() {
        productId = 0;
        productName = "";
        productPrice = 0.0;
        productQty = 0;
    }

    Product(int id, String name, double price, int quantity) {
        this.productQty = quantity;
        this.productId = id;
        this.productPrice = price;
        this.productName = name;
    }

    int getProductQty() {
        return productQty;
    }

    void setProductQty(int productQty) throws RangeException {
        if (this.productQty < 0) {
            throw new RangeException("Please enter positive value for quantity.");
        }
        this.productQty = productQty;
    }

    int getProductId() {
        return productId;
    }

    void setProductId(int productId) {
        this.productId = productId;
    }

    double getProductPrice() {
        return productPrice;
    }

    void setProductPrice(double productPrice) throws RangeException {
        if (this.productPrice < 0) {
            throw new RangeException("Please enter positive value for price.");
        }
        this.productPrice = productPrice;
    }

    String getProductName() {
        return productName;
    }

    void setProductName(String productName) {
        this.productName = productName;
    }

    @Override
    public String toString() {
        return "ID :" + productId + ", NAME : " + productName + ", PRICE : " + productPrice + ", QTY " + productQty;
    }

    @Override
    public boolean equals(Object obj) {
        Product p = (Product) obj;
        return (this.productId == p.productId);
    }

}

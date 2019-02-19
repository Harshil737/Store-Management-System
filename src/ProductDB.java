/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Harshil
 */
public class ProductDB {

    File file = null;
    ArrayList<Product> productList;

    public ProductDB() {
        file = new File("Products.txt");
        productList = new ArrayList<>();
    }

    public ProductDB(File file, ArrayList<Product> products) {
        this.file = file;
        this.productList = products;
    }

    public ArrayList<Product> getProductList() {
        return productList;
    }

    public void setProductList(ArrayList<Product> productList) {
        this.productList = productList;
    }

    public void addProduct(Product p) {
        this.productList.add(p);
    }

    public Product findProduct(int id) throws FileNotFoundException, RangeException {
        Product p = null;
        Scanner input = new Scanner(file);
        String search = "";
        String[] searchSplit = new String[4];
        while (input.hasNextLine()) {
            search = input.nextLine();
            if (!search.isEmpty()) {
                searchSplit = search.split(":");
                if (Integer.parseInt(searchSplit[0]) == id) {
                    p = new Product();
                    p.setProductId(Integer.parseInt(searchSplit[0]));
                    p.setProductName(searchSplit[1]);
                    p.setProductPrice(Double.parseDouble(searchSplit[2]));
                    p.setProductQty(Integer.parseInt(searchSplit[3]));
                }
            }
        }
        return p;
    }

    public void writeToFile() throws FileNotFoundException {
        PrintWriter out = new PrintWriter(new FileOutputStream(file, false));
        for (Product product : productList) {
            out.println(product.getProductId() + ":" + product.getProductName() + ":" + product.getProductPrice() + ":" + product.getProductQty());
        }
        out.close();
    }

    ArrayList<Product> fetchToArrayList() throws FileNotFoundException, RangeException {
        Scanner input = new Scanner(file);
        String search = "";
        String[] searchSplit = new String[4];
        ArrayList<Product> products = new ArrayList<>();
        while (input.hasNextLine()) {
            search = input.nextLine();
            if (!search.isEmpty()) {
                searchSplit = search.split(":");
                products.add(new Product(Integer.parseInt(searchSplit[0]), searchSplit[1], Double.parseDouble(searchSplit[2]), Integer.parseInt(searchSplit[3])));
            }
        }
        return products;
    }

    public boolean checkProductIdAvailable(int id) {
        for (int i = 0; i < this.productList.size(); i++) {
            Product p = this.productList.get(i);
            if (p.getProductId() == id) {
                return false;
            }
        }
        return true;
    }

    public void updateProductAtIndex(Product p) throws FileNotFoundException, RangeException {
        this.productList = fetchToArrayList();
        this.productList.set(productList.indexOf(p), p);
        writeToFile();
    }

}

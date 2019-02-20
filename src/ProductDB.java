import java.io.*;
import java.util.ArrayList;

/**
 * @author Harshil
 */
public class ProductDB {

    File file;
    private ArrayList<Product> productList;

    ProductDB() {
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

    void setProductList(ArrayList<Product> productList) {
        this.productList = productList;
    }

    void addProduct(Product p) {
        this.productList.add(p);
    }

    boolean checkProductIdAvailable(int id) {
        for (Product p : this.productList) {
            if (p.getProductId() == id) {
                return false;
            }
        }
        return true;
    }

    void writeToFile() throws IOException {
        DataOutputStream stream;
        stream = new DataOutputStream(new FileOutputStream(file));
        for (Product product : productList) {
            stream.writeUTF(product.getProductId() + ":" + product.getProductName() + ":" + product.getProductPrice() + ":" + product.getProductQty());
        }
        stream.close();
    }

    Product findProduct(int id) {
        Product p = null;
        String search;
        String[] searchSplit;

        if (file.exists()) {
            try {
                DataInputStream inputStream = new DataInputStream(new FileInputStream(file));
                while (inputStream.available() > 0) {
                    search = inputStream.readUTF();
                    searchSplit = search.split(":");
                    if (Integer.parseInt(searchSplit[0]) == id) {
                        p = new Product(Integer.parseInt(searchSplit[0]), searchSplit[1], Double.parseDouble(searchSplit[2]), Integer.parseInt(searchSplit[3]));
                        break;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return p;
    }

    ArrayList<Product> fetchToArrayList() {
        String search;
        String[] searchSplit;
        ArrayList<Product> list = new ArrayList<>();

        if (file.exists()) {
            try {
                DataInputStream inputStream = new DataInputStream(new FileInputStream(file));
                while (inputStream.available() > 0) {
                    search = inputStream.readUTF();
                    searchSplit = search.split(":");
                    list.add(new Product(Integer.parseInt(searchSplit[0]), searchSplit[1], Double.parseDouble(searchSplit[2]), Integer.parseInt(searchSplit[3])));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    void updateProductAtIndex(Product p) throws IOException {
        this.productList = fetchToArrayList();
        this.productList.set(productList.indexOf(p), p);
        writeToFile();
    }

}

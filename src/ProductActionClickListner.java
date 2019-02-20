import java.io.FileNotFoundException;
import java.io.IOException;

public interface ProductActionClickListner {
    void onProductAddClick(Product p);

    Product onProductFind(int id) throws FileNotFoundException;

    void onProductUpdate(Product p) throws IOException;

    boolean checkProductIdAvailable(int id);
}

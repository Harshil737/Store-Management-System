import java.io.FileNotFoundException;
import java.io.IOException;

public interface CustomerActionListener {
    void onCustomerAddClick(Customer c);

    boolean checkCustomerIDAvailable(int id);

    Customer onCustomerFindClick(int id) throws FileNotFoundException;

    void onCustomerUpdateClick(Customer c) throws IOException;
}

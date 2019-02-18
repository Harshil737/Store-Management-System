import java.io.FileNotFoundException;

public interface CustomerActionListener {
    void onCustomerAddClick(Customer c);

    boolean checkCustomerIDAvailable(int id);

    Customer onCustomerFindClick(int id) throws FileNotFoundException;

    void onCustomerUpdateClick(Customer c) throws FileNotFoundException;
}

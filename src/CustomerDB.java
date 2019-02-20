import java.io.*;
import java.util.ArrayList;

/**
 * @author Harshil
 */
public class CustomerDB {

    File file;
    private ArrayList<Customer> list;

    CustomerDB() {
        file = new File("Customers.txt");
        list = new ArrayList<>();
    }

    public CustomerDB(File file, ArrayList<Customer> list) {
        this.file = file;
        this.list = list;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public ArrayList<Customer> getList() {
        return list;
    }

    void setList(ArrayList<Customer> list) {
        this.list = list;
    }

    void addCustomer(Customer c) {
        this.list.add(c);
    }

    boolean checkCustomerIdAvailable(int id) {
        for (Customer customer : this.list) {
            if (customer.getCustomerId() == id) {
                return false;
            }
        }
        return true;
    }

    void writeToFile() throws IOException {
        DataOutputStream stream;
        stream = new DataOutputStream(new FileOutputStream(file));
        for (Customer customer : list) {
            stream.writeUTF(customer.getCustomerId() + ":" + customer.getName() + ":" + customer.getPhone() + ":" + customer.getEmail() + ":" + customer.getPostalCode());
        }
        stream.close();
    }

    Customer findCustomer(int id) {
        Customer c = null;
        String search;
        String[] searchSplit;

        if (file.exists()) {
            try {
                DataInputStream inputStream = new DataInputStream(new FileInputStream(file));
                while (inputStream.available() > 0) {
                    search = inputStream.readUTF();
                    searchSplit = search.split(":");
                    if (Integer.parseInt(searchSplit[0]) == id) {
                        c = new Customer(Integer.parseInt(searchSplit[0]), searchSplit[1], searchSplit[2], searchSplit[3], searchSplit[4]);
                        break;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return c;
    }

    ArrayList<Customer> fetchToArrayList() {
        String search;
        String[] searchSplit;
        ArrayList<Customer> list = new ArrayList<>();

        if (file.exists()) {
            try {
                DataInputStream inputStream = new DataInputStream(new FileInputStream(file));
                while (inputStream.available() > 0) {
                    search = inputStream.readUTF();
                    searchSplit = search.split(":");
                    list.add(new Customer(Integer.parseInt(searchSplit[0]), searchSplit[1], searchSplit[2], searchSplit[3], searchSplit[4]));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    void updateCustomerAtIndex(Customer c) throws IOException {
        this.list = fetchToArrayList();
        this.list.set(list.indexOf(c), c);
        writeToFile();
    }
}

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class CustomerDB {

    File file = null;
    ArrayList<Customer> list;

    public CustomerDB() {
        file = new File("customers.txt");
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

    public void setList(ArrayList<Customer> list) {
        this.list = list;
    }

    public void addCustomer(Customer c) {
        this.list.add(c);
    }

    public boolean checkCustomerIdAvailable(int id) {
        for (Customer customer : this.list) {
            if (customer.getCustomerId() == id) {
                return false;
            }
        }
        return true;
    }

    public void writeToFile() throws FileNotFoundException {
        PrintWriter writer = new PrintWriter(new FileOutputStream(file, false));
        for (Customer customer : list) {
            writer.println(customer.getCustomerId() + ":" + customer.getName() + ":" + customer.getPhone() + ":" + customer.getEmail() + ":" + customer.getPostalCode());
        }
        writer.close();
    }

    public Customer findCustomer(int id) throws FileNotFoundException {
        Customer c = null;
        Scanner s = new Scanner(file);
        String search = "";
        String[] searchSplit = new String[5];
        while (s.hasNextLine()) {
            search = s.nextLine();
            if (!search.isEmpty()) {
                searchSplit = search.split(":");
                if (Integer.parseInt(searchSplit[0]) == id) {
                    c = new Customer(Integer.parseInt(searchSplit[0]), searchSplit[1], searchSplit[2], searchSplit[3], searchSplit[4]);
                }
            }
        }
        return c;
    }

    ArrayList<Customer> fetchToArrayList() throws FileNotFoundException {
        Scanner s = new Scanner(file);
        String search = "";
        String[] searchSplit = new String[5];
        ArrayList<Customer> list = new ArrayList<>();
        while (s.hasNextLine()) {
            search = s.nextLine();
            if (!search.isEmpty()) {
                searchSplit = search.split(":");
                list.add(new Customer(Integer.parseInt(searchSplit[0]), searchSplit[1], searchSplit[2], searchSplit[3], searchSplit[4]));
            }
        }
        return list;
    }

    public void updateCustomerAtIndex(Customer c) throws FileNotFoundException {
        this.list = fetchToArrayList();
        this.list.set(list.indexOf(c), c);
        writeToFile();
    }
}

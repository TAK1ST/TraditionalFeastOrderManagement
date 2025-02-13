package Data.CustomerDao;

import Data.Entity.Customer;
import Data.IFileManager;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class CustomerDao implements ICustomerDao<Customer> {

    //Declare an arraylist to store users Customers  
    IFileManager<Customer> fileManager;
    List<Customer> customerList = new ArrayList<>();
    public CustomerDao() {
    }
    public CustomerDao(IFileManager fileManager) throws Exception {
        this.fileManager = fileManager;
        loadDataFromFile();
    }
    @Override
    public void loadDataFromFile() throws Exception {
        String customerCode, customerName, phoneNumber, Email;
        List<String> cusData = fileManager.readDataFromFile();
        //get Data
        for (String c : cusData) {
            List<String> emp = Arrays.asList(c.split(","));
            customerCode = emp.get(0).trim();
            customerName = emp.get(1).trim();
            phoneNumber = emp.get(2).trim();
            Email = emp.get(3).trim();
            Customer newCustomer = new Customer(customerCode, customerName, Integer.parseInt(phoneNumber), Email);
            addNew(newCustomer);
        }
    }
    @Override
    public List<Customer> getList() throws Exception {
        if (customerList.isEmpty()) {
            throw new Exception("Customer list is empty.");
        }
        return customerList;
    }
    @Override
    public void addNew(Customer customer) throws Exception {
        customerList.add(customer);
    }
}

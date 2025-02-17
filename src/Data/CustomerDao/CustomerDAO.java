/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Data.CustomerDao;

import Data.Entity.Customer;
import Data.File.FileManagement;
import java.util.List;

/**
 *
 * @author asus
 */
public class CustomerDAO {

    private final String filename = "./src/Data/customers.dat";
    FileManagement<Customer> customerManager = new FileManagement<>(filename);

    public boolean addCustomer(Customer customer) {
        if (findCustomerByCode(customer.getCustomerCode()) != null) {
            return false;
        }
        customerManager.getObjects().add(customer);
        return true;
    }

    public Customer findCustomerByCode(String Code) {
        return customerManager.getObjects().stream()
                .filter(code -> code.getCustomerCode().equalsIgnoreCase(Code))
                .findFirst()
                .orElse(null);
    }

    public List<Customer> getAllCustomers() {
        return customerManager.getObjects();
    }

    public void saveCustomers() {
        customerManager.writeToFile();
    }

    public boolean updateCustomer(Customer customer) {
        for (int i = 0; i < customerManager.getObjects().size(); i++) {
            if (getAllCustomers().get(i).getCustomerCode().equals(customer.getCustomerCode())) {
                getAllCustomers().set(i, customer);
                return true;
            }
        }
        return false;
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Application.Service;

import Data.DAO.CustomerDAO;
import Data.Entity.Customer;
import Validator.CustomerValidator;

/**
 *
 * @author asus
 */
public class CustomerService {

    private final CustomerDAO customerDAO;
    private final CustomerValidator customerValidator;

    public CustomerService() {
        this.customerDAO = new CustomerDAO();
        this.customerValidator = new CustomerValidator();
    }

    public boolean addNew(Customer customer) {
        if (customerValidator.validCustomerName(customer.getCustomerName())
                && customerValidator.validPhone(customer.getPhoneNumber())
                && customerValidator.validEmail(customer.getEmail())) {
            return customerDAO.addCustomer(customer);
        }
        return false;
    }

    public Customer findCustomerByCode(String code) {
        return customerDAO.findCustomerByCode(code);
    }

    public boolean updateCustomer(Customer cus) {
        return customerDAO.updateCustomer(cus);
    }

    public void saveCustomersData() {
        customerDAO.saveCustomers();
    }
}

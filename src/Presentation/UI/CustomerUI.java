/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Presentation.UI;

import Application.Service.CustomerService;
import Data.Entity.Customer;
import Util.DataInput;
import Validator.CustomerValidator;

/**
 *
 * @author asus
 */
public class CustomerUI {

    private final CustomerService customerService;
    private final CustomerValidator customerValidator;

    public CustomerUI() {
        this.customerService = new CustomerService();
        this.customerValidator = new CustomerValidator();
    }

    public void displayListDetail() {
        System.out.println("============================================================");
        System.out.println("List of Set Menus for ordering party:");
        System.out.println("============================================================");
        System.out.println("Code     : ");
        System.out.println("Name     : ");
        System.out.println("Price    : " + String.format("%,d", Integer.valueOf("get price")) + " Vnd");
        System.out.println("Ingredients:");
        System.out.println("+ Khai vị    : ");
        System.out.println("+ Món chính  : ");
        System.out.println("+ Tráng miệng: ");
        System.out.println("------------------------------------------------------------");
    }

    public void registerCustomer() {
        String customerCode, customerName, phone, email;
        System.out.println("CREATE USER");
        customerCode = customerValidator.autoGenerateCustomerCode();
        System.out.println("Your code: " + customerCode);
        while (true) {
            customerName = DataInput.getString("Enter customer name: ");
            if (customerValidator.validCustomerName(customerName)) {
                System.out.println("Your Name: " + customerName);
                break;
            } else {
                System.out.println("\nInvalid, please try again.");
            }
        }
        while (true) {
            phone = DataInput.getString("Enter phone number: ");
            System.out.println("Your Phone: " + phone);
            if (customerValidator.validPhone(phone)) {
                break;
            } else {
                System.out.println("\nInvalid, please try again.");
            }
        }
        while (true) {
            email = DataInput.getString("Enter email: ");
            System.out.println("Your email: " + email);
            if (customerValidator.validEmail(email)) {
                break;
            } else {
                System.out.println("\nInvalid, please try again.");
            }
        }

        Customer customer = new Customer(customerCode, customerName, phone, email);
        if (customerService.addNew(customer)) {
            System.out.println("Add new customer successful");
        } else {
            System.out.println("Customer was existed.");
        }
        //test
        System.out.println("Customer Information: ");
        System.out.println("Customer Code: " + customerCode);
        System.out.println("Customer Name: " + customerName);
        System.out.println("Phone Number: " + phone);
        System.out.println("Email: " + email);
        System.out.println("");
    }

    public void updateCustomer() {
        Customer customer;
        String inputCode = DataInput.getString("Input customer code: ");
        customer = customerService.findCustomerByCode(inputCode);
        if (customer == null) {
            System.out.println("Cannot find customer");
            return;
        }
        displayCustomerInfo(customer);
        try {
            System.out.println("\nEnter new details (press Enter to keep current value):");
            //set name
            System.out.print("Customer name [" + customer.getCustomerName() + "]: ");
            String name = DataInput.getString();
            if (!name.isEmpty() || customerValidator.validCustomerName(name)) {
                customer.setCustomerName(name);
            } else {
                System.out.println("Set default.");
            }

            //set phone
            System.out.print("Customer phone [" + customer.getPhoneNumber() + "]: ");
            String phone = DataInput.getString();
            if (!phone.isEmpty() || customerValidator.validPhone(phone)) {
                customer.setPhoneNumber(phone);
            } else {
                System.out.println("Set default.");
            }

            //set email
            System.out.print("Customer email [" + customer.getEmail() + "]: ");
            String email = DataInput.getString();
            if (!email.isEmpty() || customerValidator.validEmail(email)) {
                customer.setCustomerName(email);
            } else {
                System.out.println("Set default.");
            }
        } catch (Exception e) {
            System.out.println("Error occurred: " + e.getMessage());
            e.printStackTrace();
        }

        if (customerService.updateCustomer(customer)) {
            System.out.println("Customer's information was updated.");
        }
    }

    public Customer findCustomerByCode() {
        String code = DataInput.getString();
        Customer cus = customerService.findCustomerByCode(code);
        displayCustomerInfo(cus);
        return cus;
    }

    private void displayCustomerInfo(Customer customer) {
        System.out.println("\nCustomer Information: ");
        System.out.println("Customer Code: " + customer.getCustomerCode());
        System.out.println("Customer Name: " + customer.getCustomerName());
        System.out.println("Phone Number: " + customer.getPhoneNumber());
        System.out.println("Email: " + customer.getEmail());
    }

    public void saveCustomers() {
        customerService.saveCustomersData();
    }
}

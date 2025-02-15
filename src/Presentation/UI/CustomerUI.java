/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Presentation.UI;

import Application.Service.CustomerService;
import Data.Entity.Customer;
import Util.DataInput;

/**
 *
 * @author asus
 */
public class CustomerUI {

    private final CustomerService customerService;

    public CustomerUI() {
        this.customerService = new CustomerService();
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
        String customerCode = DataInput.getInput("Enter customer code: ", );
        String customerName = DataInput.getInput("Enter customer name: ", );
        String phone = DataInput.getInput("Enter phone number: ", );
        String email = DataInput.getInput("Enter email: ", );

        Customer customer = new Customer(customerCode, customerName, phone, email);
        if (customerService.addNew(customer)) {
            System.out.println("Add new customer successful");
        } else {
            System.out.println("Fail to add data");
        }
        //test
        System.out.println("Customer Information: ");
        System.out.println("Customer Code: " + customerCode);
        System.out.println("Customer Name: " + customerName);
        System.out.println("Phone Number: " + phone);
        System.out.println("Email: " + email);
    }

    public void updateCustomer() {
        String customerCode = DataInput.getString("Update customer code: ");
        String customerName = DataInput.getString("Enter customer name: ");
        String phone = DataInput.getString("Enter phone number: ");
        String email = DataInput.getString("Enter email: ");
        customer.setCustomerCode(customerCode);
        customer.setCustomerName(customerName);
        customer.setPhoneNumber(phone);
        customer.setEmail(email);
    }

    private void displayCustomerInfo(Customer customer) {
        System.out.println("\nCustomer Information: ");
        System.out.println("Customer Code: " + customer.getCustomerCode());
        System.out.println("Customer Name: " + customer.getCustomerName());
        System.out.println("Phone Number: " + customer.getPhoneNumber());
        System.out.println("Email: " + customer.getEmail());
    }
}

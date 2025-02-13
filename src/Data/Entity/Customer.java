/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Data.Entity;

/**
 *
 * @author asus
 */
public class Customer {
    private String customerCode;
    private String customerName;
    private int phoneNumber;
    private String Email;

    public Customer() {
    }

    public Customer(String customerCode, String customerName, int phoneNumber, String Email) {
        this.customerCode = customerCode;
        this.customerName = customerName;
        this.phoneNumber = phoneNumber;
        this.Email = Email;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }
}

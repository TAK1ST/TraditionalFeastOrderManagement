/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Data.Entity;

import Util.DataInput;
import java.io.Serializable;
import java.util.Random;

/**
 *
 * @author asus
 */
public class Customer implements Serializable {
    private static final long serialVersionUID = 1L;
    private String customerCode;
    private String customerName;
    private String phoneNumber;
    private String email;

    public Customer() {
    }

    public Customer(String customerCode, String customerName, String phoneNumber, String email) {
        this.customerCode = customerCode;
        this.customerName = customerName;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public String getCustomerCode() {
        return customerCode.toUpperCase();
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

//using Override toString to show data in this, avoid print hashcode from Object.
    @Override
    public String toString() {
        return "customerCode=" + customerCode
                + ", customerName=" + customerName
                + ", phoneNumber=" + phoneNumber
                + ", email=" + email + '}';
    }
}

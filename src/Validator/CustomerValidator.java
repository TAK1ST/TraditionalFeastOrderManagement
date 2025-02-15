package Validator;

import static Constant.Regex.REGEX_CUSTOMER_CODE;
import static Constant.Regex.REGEX_CUSTOMER_NAME;
import static Constant.Regex.REGEX_EMAIL;
import static Constant.Regex.REGEX_PHONENUMBER;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author asus
 */
public class CustomerValidator  {
    //"static || default" can write body code in interface to check (simple to understand but hard to scalable)

    public boolean validCustomerCode(String customerCode) {
        return customerCode != null && !customerCode.trim().isEmpty()
                && customerCode.matches(REGEX_CUSTOMER_CODE);
    }

    public boolean validCustomerName(String customerName) {
        return customerName != null && !customerName.trim().isEmpty()
                && customerName.matches(REGEX_CUSTOMER_NAME);
    }

    public boolean validPhone(String phone) {
        return phone != null && !phone.trim().isEmpty()
                && phone.matches(REGEX_PHONENUMBER);
    }

    public boolean validEmail(String email) {
        return email != null && !email.trim().isEmpty()
                && email.matches(REGEX_EMAIL);
    }
}

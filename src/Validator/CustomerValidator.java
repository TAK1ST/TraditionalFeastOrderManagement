package Validator;

import static Constant.Regex.REGEX_CUSTOMER_NAME;
import static Constant.Regex.REGEX_EMAIL;
import static Constant.Regex.REGEX_PHONENUMBER;
import Data.CustomerDao.CustomerDAO;
import Data.Entity.Customer;
import Util.DataInput;
import java.util.Random;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author asus
 */
public class CustomerValidator {
    CustomerDAO customerDAO = new CustomerDAO();


//    public boolean validCustomerCode(String customerCode) {
//        if (checkCustomerCodeValid(customerCode)) {
//            System.out.println("Customer existed.");
//            return false;
//        }
//        return true;
//    }
//    
//    private boolean checkCustomerCodeValid(String customerCode) {
//        return customerDAO.findCustomerByCode(customerCode).getCustomerCode().equalsIgnoreCase(customerCode);
//    }

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

    private char getFirstCharCustomerCode() {
        do {
            System.out.println("choice of Code:");
            System.out.println("1. C");
            System.out.println("2. K");
            System.out.println("3. G");
            try {
                int choice = DataInput.getIntegerNumber("Choose option:");
                char input;
                switch (choice) {
                    case 1:
                        input = 'C';
                        return input;
                    case 2:
                        input = 'K';
                        return input;
                    case 3:
                        input = 'G';
                        return input;
                    default:
                        System.out.println("Invalid number please choice 1 | 2 | 3");
                }
            } catch (Exception e) {
                System.out.println("Invalid input, just number, please try again");
            }
        } while (true);
    }

    public String autoGenerateCustomerCode() {
        String generateCode;
        Random random = new Random();
        return generateCode = getFirstCharCustomerCode() + String.format("%03d", random.nextInt(1000));
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Constant;

/**
 *
 * @author asus
 */
public class Regex {

    //regex for customer
    public static final String REGEX_NUMBER = "\\d+";
    public static final String REGEX_PHONENUMBER = "^(09|03|07|08|05)\\d{8}$";
    public static final String REGEX_CUSTOMER_CODE = "^[CGK][0-9]{4}$";
    public static final String REGEX_CUSTOMER_NAME = "^.{2,25}$";
    public static final String REGEX_EMAIL = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

}

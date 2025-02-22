/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Validator;

import static Constant.Regex.REGEX_FEAST_CODE;


/**
 *
 * @author asus
 */
public class FeastMenuesValidator {
        public boolean validFeastMenuesCode(String customerCode) {
        return customerCode != null && !customerCode.trim().isEmpty()
                && customerCode.matches(REGEX_FEAST_CODE);
    }
}

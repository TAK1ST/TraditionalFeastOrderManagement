/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Validator;

import java.io.IOException;

/**
 *
 * @author asus
 */
public class HandleException {

    public static void handleException(String message, Exception e) {
        System.err.println(message + e.getMessage());
        e.printStackTrace();
    }
    public static void handleIOException(String message, IOException e) {
        System.err.println(message + e.getMessage());
        e.printStackTrace();
    }
    public static void handleClassNotFoundException(String message, ClassNotFoundException e) {
        System.err.println(message + e.getMessage());
        e.printStackTrace();
    }
}

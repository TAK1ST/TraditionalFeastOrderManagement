/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Util;

import static Constant.DateFormat.formatter;
import static Constant.Regex.REGEX_NUMBER;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author asus
 */
public class DataInput {

    private static final Scanner sc = new Scanner(System.in);

    public static int getIntegerNumber(String displayMessage) throws Exception {
        int number = 0;
        String s;
        System.out.print(displayMessage);
        try {
            s = sc.nextLine();
            number = Integer.parseInt(s);
        } catch (NumberFormatException ex) {
            System.out.println("Data invalid.");
        }
        return number;
    }

    public static int getIntegerNumber() {
        int number = 0;
        String s;
        s = sc.nextLine();
        if (!s.matches(REGEX_NUMBER)) {
            System.out.println("Invalid input number.");
        } else {
            number = Integer.parseInt(s);
        }
        return number;
    }

    public static String getString(String displayMessage) {
        String s;
        System.out.print(displayMessage);
        s = sc.nextLine();
        return s.trim();
    }

    public static String getString() {
        String s;
        s = sc.nextLine();
        return s;
    }

    public static LocalDate getDate() {
        while (true) {
            String dateString = sc.nextLine();
            try {
                return LocalDate.parse(dateString, formatter);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please try again.");
            }
        }
    }
    public static LocalDate getDateNotLoop() {
        while (true) {
            String dateString = sc.nextLine();
            try {
                return LocalDate.parse(dateString, formatter);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please try again.");
            }
        }
    }

    public static LocalDate getDate(String displayMessage) {
        System.out.print(displayMessage);
        while (true) {
            String dateString = sc.nextLine();
            try {
                return LocalDate.parse(dateString, formatter);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please try again.");
            }
        }
    }
}

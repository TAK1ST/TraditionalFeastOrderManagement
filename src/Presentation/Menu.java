/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Presentation;

import Data.CustomerDao.CustomerDAO;
import Presentation.UI.CustomerUI;


/**
 *
 * @author asus
 */
public class Menu {
    private static final CustomerUI customerUI  = new CustomerUI();

    public static void displayMenu() {
        System.out.println("A Management System for Traditional Feast Orders");
        System.out.println("1. Register customers");
        System.out.println("2. Update customer information");
        System.out.println("3. Search for customer information by name");
        System.out.println("4. Display feast menus.");
        System.out.println("5. Place a feast order");
        System.out.println("5. Place a feast order");
        System.out.println("6. Update order information");
        System.out.println("7. Save data to file");
        System.out.println("8. Display Customer or Order lists");
        System.out.println("9.Quit");
        System.out.print("Enter your choice: ");
    }

    public static boolean handleChoice(int choice) throws Exception {
        switch (choice) {
            case 1:
                customerUI.registerCustomer();
                customerUI.saveCustomers();
                break;
            case 2:
                customerUI.updateCustomer();
                break;
            case 3:
                customerUI.findCustomerByCode();
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                break;
            case 7:
                break;
            case 8:
                customerUI.saveCustomers();
                break;
            case 9:
                System.out.println("Good bye");
                return false;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
        return true;
    }
}

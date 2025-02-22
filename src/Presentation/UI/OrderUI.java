/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Presentation.UI;

import Application.Service.OrderService;
import static Constant.DateFormat.formatter;
import Data.DAO.CustomerDAO;
import Data.Entity.Customer;
import Util.DataInput;
import Validator.CustomerValidator;
import Validator.FeastMenuesValidator;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 *
 * @author asus
 */
public class OrderUI {

    private final OrderService orderService;
    private final CustomerDAO customer;
    private final CustomerValidator customerValidator;
    private final FeastMenuesValidator feastMenuesValidator;

    public OrderUI() {
        this.orderService = new OrderService();
        this.customer = new CustomerDAO();
        this.customerValidator = new CustomerValidator();
        this.feastMenuesValidator = new FeastMenuesValidator();
    }

    public void placeFeastOrder() {
        String customerCode, setMenuCode;
        int numTables;
        LocalDate eventDate;
        System.out.println(customer.getAllCustomers());
        try {
            // Get customer code
            while (true) {
                customerCode = DataInput.getString("Enter customer code: ");
                if (customerValidator.validCustomerCode(customerCode)) {
                    System.out.println("Your code: " + customerCode);
                    break;
                } else {
                    System.out.println("\nInvalid, please try again.");
                }
            }
            
            // Get set menu code
            do {
                System.out.print("Enter Set Menu Code: ");
                setMenuCode = DataInput.getString();
                if (feastMenuesValidator.validFeastMenuesCode(setMenuCode)) {
                    System.out.println("Your Menu Code: " + setMenuCode);break;} 
                else{System.out.println("Invalid code, please input format PW[number]");}
            } while (true);
            
            // Get number of tables
            while (true) {
                System.out.print("Enter Number of Tables: ");
                numTables = DataInput.getIntegerNumber();
                if (numTables > 0) {
                    System.out.println("Your number of tables: " + numTables);
                    break;
                } else {
                    System.out.println("Number of Tables must greater than 0");
                }
            }
// Get preferred event date
            do {
                eventDate = DataInput.getDate("Enter Preferred Event Date (dd/MM/yyyy): ");
                if (eventDate.isAfter(LocalDate.now())) {
                    System.out.println("Your Preferred Event Date: " + eventDate);
                    break;
                } else {
                    System.out.println("Event date must be in the future.Try again");
                }
            } while (true);

            // Place the order through service layer
//            System.out.println(setMenuCode);
            boolean success = orderService.placeFeastOrder(customerCode, setMenuCode, numTables, eventDate);

            if (success) {
                System.out.println("Order placed successfully!");
                orderService.displayOrderDetails();
            }

        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format. Please use dd/MM/yyyy");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Function to handle updating order information
    public void updateOrderInfo() {
        try {
            System.out.print("Enter Order ID: ");
            String orderId = DataInput.getString();

            // Check if order exists first
            if (!orderService.orderExists(orderId)) {
                System.out.println("This Order does not exist.");
                return;
            }

            // Get fields to update
            System.out.println("\nLeave blank to keep current value");

            System.out.print("Enter new Set Menu Code: ");
            String setMenuCode = DataInput.getString();

            System.out.print("Enter new Number of Tables (0 to keep current): ");
            String tablesStr = DataInput.getString();
            int numTables = tablesStr.isEmpty() ? 0 : Integer.parseInt(tablesStr);

            System.out.print("Enter new Event Date (dd/MM/yyyy, blank to keep current): ");
            LocalDate eventDate = DataInput.getDateNotLoop();

            // Update through service layer
            boolean success = orderService.updateOrder(orderId, setMenuCode, numTables, eventDate);

            if (success) {
                System.out.println("Order updated successfully!");
                orderService.displayOrderDetails();
            }

        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format. Please use dd/MM/yyyy");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    public void displayOrderDetail()
    {
        orderService.displayOrderDetails();
    }
    
    public void saveOrder()
    {
        orderService.saveData();
    }
}

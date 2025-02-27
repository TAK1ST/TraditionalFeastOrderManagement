/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Presentation;

import Application.Service.OrderService;
import Data.DAO.CustomerDAO;
import Data.DAO.FeastMenuDAO;
import Data.DAO.OrderDAO;
import Data.Entity.Customer;
import Data.Entity.FeastMenues;
import Data.Entity.Order;
import Presentation.UI.CustomerUI;
import Presentation.UI.FeastMenuUI;
import Presentation.UI.OrderUI;
import Util.DataInput;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author asus
 */
public class Menu {

    private static final CustomerUI customerUI = new CustomerUI();
    private static final CustomerDAO customerDAO = new CustomerDAO();
    private static final OrderDAO orderDAO = new OrderDAO();
    private static final FeastMenuUI feastMenuUI = new FeastMenuUI();
    private static final FeastMenuDAO feastMenuDAO = new FeastMenuDAO();
    private static final OrderUI orderUI = new OrderUI();
    private static final OrderService orderService = new OrderService();

    public static void displayMainMenu() {
        System.out.println("A Management System for Traditional Feast Orders");
        System.out.println("1. Register customers");
        System.out.println("2. Update customer information");
        System.out.println("3. Search for customer information by name");
        System.out.println("4. Display feast menus");
        System.out.println("5. show order menu");
        System.out.println("6. Update order information");
        System.out.println("7. Save data to file");
        System.out.println("8. Display Customer or Order lists");
        System.out.println("9.Quit");
        System.out.print("Enter your choice: ");
    }

    public static void showOrderMenu() throws Exception {
        while (true) {
            System.out.println("\n=== Order Management ===");
            System.out.println("1. Place a feast order");
            System.out.println("2. Display Latest Order");
            System.out.println("3. Display All Orders");
            System.out.println("4. Return to Main Menu");

            int choice = DataInput.getIntegerNumber("Enter your choice: ");

            switch (choice) {
                case 1:
                    orderUI.placeFeastOrder();
                    break;
                case 2:
                    //display customer place a feast order
                    orderUI.displayOrderDetail();
                    break;
                case 3:
                    //display all order detail
                    orderService.displayAllOrders();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public static boolean handleChoice(int choice) throws Exception {
        switch (choice) {
            case 1:
                customerUI.registerCustomer();
                customerUI.saveCustomers();
                break;
            case 2:
                customerUI.updateCustomer();
                customerUI.saveCustomers();
                break;
            case 3:
                customerUI.findCustomerByCode();
                break;
            case 4:
                feastMenuUI.displayFeastMenu();
                break;
            case 5:
                showOrderMenu();
                break;
            case 6:
                orderUI.displayOrderDetail();
                orderUI.updateOrderInfo();
                break;
            case 7:
                customerUI.saveCustomers();
                orderUI.saveOrder();
                System.out.println("Save file successful");
                break;
            case 8:
                displayMenu();
                break;
            case 9:
                System.out.println("Good bye");
                return false;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
        return true;
    }

    public static void displayMenu() {
        while (true) {
            System.out.println("\n=== Display Information ===");
            System.out.println("1. Display Customer List");
            System.out.println("2. Display Order List");
            System.out.println("3. Back to Main Menu");
            System.out.print("Choose an option: ");

            int choice = DataInput.getIntegerNumber();

            switch (choice) {
                case 1:
                    displayCustomerList();
                    break;
                case 2:
                    displayOrderList();
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    public static void displayCustomerList() {
        List<Customer> customers = customerDAO.getAllCustomers();

        if (customers.isEmpty()) {
            System.out.println("Does not have any customer information.");
            return;
        }

        // Sort customers by name
        customers = customers.stream()
                .sorted(Comparator.comparing(Customer::getCustomerName)
//                        .thenComparing(Customer::getCustomerCode)
                )
                .collect(Collectors.toList());

        System.out.println("\nCustomers information:");
        System.out.println("----------------------------------------------------------------");
        System.out.printf("%-8s | %-20s | %-12s | %-25s%n",
                "Code", "Customer Name", "Phone", "Email");
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------");

        for (Customer customer : customers) {
            System.out.printf("%-8s | %-20s | %-12s | %-25s%n",
                    customer.getCustomerCode(),
                    customer.getCustomerName(),
                    customer.getPhoneNumber(),
                    customer.getEmail());
        }
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------");
    }

    public static void displayOrderList() {
        List<Order> orders = orderDAO.getAllOrders();

        if (orders.isEmpty()) {
            System.out.println("No orders in the system.");
            return;
        }

        // Sort orders by event date
        orders = orders.stream()
                .sorted(Comparator.comparing(Order::getEventDate))
                .collect(Collectors.toList());

        System.out.println("\n--------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%-8s | %-10s | %-10s | %-8s | %-8s | %-6s | %-12s%n",
                "ID", "Event date", "Customer", "Set Menu", "Price", "Tables", "Total Cost");
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------");

        for (Order order : orders) {
            FeastMenues menu = feastMenuDAO.getFeastMenuesBySetMenuCode(order.getSetMenuCode());

            // Get price per table from FeastMenues
            long pricePerTable = (menu != null) ? menu.getPrice() : 0;
            System.out.printf("%-8s | %-10s | %-10s | %-8s | %,8d | %-6d | %,12.0f%n",
                    order.getOrderCode(),
                    order.getEventDate().format(Constant.DateFormat.formatter),
                    order.getCustomerCode(),
                    order.getSetMenuCode(),
                    pricePerTable,
                    order.getNumberOfTables(),
                    order.getTotalCost());
        }
        System.out.println("-------------------------------------------------------------------------");
    }
}

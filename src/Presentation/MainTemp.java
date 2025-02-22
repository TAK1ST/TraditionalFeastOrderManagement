/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Presentation;

/**
 *
 * @author asus
 */
import Data.Entity.Customer;
import Data.Entity.FeastMenues;
import Data.Entity.Order;
import Data.DAO.CustomerDAO;
import Data.DAO.FeastMenuDAO;
import Data.DAO.OrderDAO;
import java.time.LocalDate;

public class MainTemp {

    public static void main(String[] args) {
        // Initialize DAOs
        CustomerDAO customerDAO = new CustomerDAO();
        FeastMenuDAO feastMenuDAO = new FeastMenuDAO();
        OrderDAO orderDAO = new OrderDAO();

        // Generate sample data
        generateSampleData(customerDAO, feastMenuDAO, orderDAO);

        // Start the application
        try {
            boolean running = true;
            while (running) {
                Menu.displayMainMenu();
                int choice = Util.DataInput.getIntegerNumber("Enter your choice: ");
                running = Menu.handleChoice(choice);
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void generateSampleData(CustomerDAO customerDAO, FeastMenuDAO feastMenuDAO, OrderDAO orderDAO) {
        // Generate customers with valid codes (C, G, K)
        Customer[] customers = {
            new Customer("C0012", "An, Hoang Thi To", "0987654321", "anhtt@hotmail.com"),
            new Customer("G0171", "Binh, Ngo Quoc", "0902345678", "binhnq@yahoo.com"),
            new Customer("K0310", "Yen, Hoang Minh", "0351232321", "yenhm11@gmail.com")
        };

        for (Customer customer : customers) {
            customerDAO.addCustomer(customer);
        }

        // Generate orders with future dates
        LocalDate now = LocalDate.now();
        Order[] orders = {
            new Order("ORD001", "K0310", "PW002", 8, now.plusMonths(1)),
            new Order("ORD002", "G0171", "PW001", 20, now.plusMonths(2)),
            new Order("ORD003", "C0012", "PW003", 5, now.plusMonths(3))
        };

        for (Order order : orders) {
            orderDAO.addNew(order);
        }
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Presentation;

import Data.DAO.CustomerDAO;
import Data.DAO.FeastMenuDAO;
import Data.DAO.OrderDAO;
import static Util.StartUtils.start;

/**
 *
 * @author asus
 */

public class Program {

    public static void main(String[] args) throws Exception 
    {
        start();
        CustomerDAO customerDAO = new CustomerDAO();
        System.out.println(customerDAO.getAllCustomers());
        FeastMenuDAO feastMenuDAO = new FeastMenuDAO();
        feastMenuDAO.display();
        OrderDAO orderDAO = new OrderDAO();
        System.out.println(orderDAO.getAllOrders());
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Presentation.UI;

import Data.DAO.FeastMenuDAO;
import Data.Entity.FeastMenues;
import Util.DataInput;

/**
 *
 * @author asus
 */
public class FeastMenuUI {

    FeastMenuDAO fes = new FeastMenuDAO();

    public void displayFeastMenu() {
        fes.display();
    }
    
//    // add new Menu
//    public void inputNewMenu() {
//
//        System.out.print("Input set menu (ví dụ: PW001): ");
//        String code = DataInput.getString();
//
//        System.out.print("Input menu: ");
//        String name = DataInput.getString();
//
//        System.out.print("Input price: ");
//        int price = Integer.parseInt(DataInput.getString());
//
//        System.out.print("Input ingredients : ");
//        String ingredients = DataInput.getString();
//
//
//        FeastMenues newMenu = new FeastMenues(code, name, price, ingredients);
//
//        if (fes.addFeastMenues(newMenu)) {
//            System.out.println("add new menu successful.");
//        } else {
//            System.out.println("Menu not exited.");
//        }
//    }
}

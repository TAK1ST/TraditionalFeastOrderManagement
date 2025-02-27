/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Data.DAO;

import Data.Entity.FeastMenues;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author asus
 */
public class FeastMenuDAO {

    List<FeastMenues> menus = new ArrayList<>();

    private void readFeastMenu() {
        String csvFile = "./src/Data/feastMenu.csv";
        String line;
        String csvSplitBy = ",";

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            // Skip header line
            br.readLine();

            while ((line = br.readLine()) != null) {
                String[] data = line.split(csvSplitBy, 4);
                if (data.length == 4) {
                    String code = data[0].replaceAll("\"", "");
                    String name = data[1].replaceAll("\"", "");
                    int price = Integer.parseInt(data[2].trim().replaceAll("\"", ""));
                    String ingredients = data[3].replaceAll("\"", "");

                    FeastMenues menu = new FeastMenues(code, name, price, ingredients);
                    menus.add(menu);
                }
            }
        } catch (IOException e) {
            System.out.println("Cannot read data from \"feastMenu.csv\". Please check it.");
        }
    }
    
    public boolean addFeastMenues(FeastMenues menu) {
        readFeastMenu();

        if (menu == null) {
            return false;
        }

        // Check if menu code already exists
        boolean isDuplicate = menus.stream()
                .anyMatch(m -> m.getCode().equalsIgnoreCase(menu.getCode()));

        if (isDuplicate) {
            return false;
        }

        // Validate menu data
        if (!isValidMenu(menu)) {
            return false;
        }

        // Add the new menu
        menus.add(menu);

        // Try to write to file
        return saveFeastMenu();
    }
    
    //  method to validate menu data
    private boolean isValidMenu(FeastMenues menu) {
        // Check menu code format (should start with PW followed by 3 digits)
        if (!menu.getCode().matches("PW\\d{3}")) {
            return false;
        }

        // Check price (should be positive)
        if (menu.getPrice() <= 0) {
            return false;
        }

        // Check name (should not be empty)
        if (menu.getName() == null || menu.getName().trim().isEmpty()) {
            return false;
        }

        // Check ingredients (should not be empty)
        if (menu.getIngredients() == null || menu.getIngredients().trim().isEmpty()) {
            return false;
        }

        return true;
    }

    // method to save menus to CSV file
    private boolean saveFeastMenu() {
        String csvFile = "./src/Data/feastMenu.csv";
        try (FileWriter writer = new FileWriter(csvFile)) {
            // Write header
            writer.write("Code,Name,Price,Ingredients\n");

            // Write menu data
            for (FeastMenues menu : menus) {
                writer.write(String.format("\"%s\",\"%s\",\"%d\",\"%s\"\n",
                        menu.getCode(),
                        menu.getName(),
                        menu.getPrice(),
                        menu.getIngredients()));
            }
            return true;
        } catch (IOException e) {
            System.out.println("Error saving to feastMenu.csv: " + e.getMessage());
            return false;
        }
    }
    
    public FeastMenues getFeastMenuesBySetMenuCode(String Code) {
        readFeastMenu();
        return menus.stream()
                .filter(menu -> menu.getCode().equalsIgnoreCase(Code))
                .findFirst()
                .orElse(null);
    }

    public void display() {
        readFeastMenu();
        if (menus.isEmpty()) {
            System.out.println("No menu data available.");
        } else {
            System.out.println("List of Set Menus for ordering party:");
            System.out.println("========================================");
            for (FeastMenues menu : menus) {
                menu.display();
            }
        }
    }

    public void displayDetail(String setMenuCode) {
        boolean found = false;
        for (FeastMenues feastMenues : menus) {
            if (feastMenues.getCode().equalsIgnoreCase(setMenuCode)) {
                feastMenues.display();
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("No menu found with code: " + setMenuCode);
        }
    }
}

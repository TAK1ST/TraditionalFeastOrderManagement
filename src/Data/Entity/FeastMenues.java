/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Data.Entity;

/**
 *
 * @author asus
 */
public class FeastMenues {

    String code;
    String name;
    long price;
    String ingredients;

    public FeastMenues(String code, String name, long price, String ingredients) {
        this.code = code;
        this.name = name;
        this.price = price;
        this.ingredients = ingredients;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public void display() {
        System.out.println("Code       : " + code);
        System.out.println("Name       : " + name);
        System.out.printf("Price      : %,d Vnd%n", price);

        String[] parts = ingredients.split("#");
        System.out.println("Ingredients:");
        for (String part : parts) {
            System.out.println(part.trim());
        }
        System.out.println("========================================\n");
    }
}

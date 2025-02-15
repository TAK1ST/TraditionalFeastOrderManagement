/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Data.File;


/**
 *
 * @author asus
 * @param <T>
 */
public interface IFileManagement<T> {
    void readFromFile() throws Exception;
    void createFileIfNotExist() throws Exception;
    void writeToFile() throws Exception;
}

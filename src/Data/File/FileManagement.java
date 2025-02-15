/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Data.File;

import static Validator.HandleException.handleIOException;
import static Validator.HandleException.handleClassNotFoundException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author asus
 */
//= "./src/Data/feastMenu.csv"
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public final class FileManagement<T> implements IFileManagement<T> {

    private final String filename;
    private List<T> objects;
//Contructor

    public FileManagement(String filename) {
        this.filename = filename;
        this.objects = new ArrayList<>();
        createFileIfNotExist();
        readFromFile();
    }
//Getter

    public String getFilename() {
        return filename;
    }

    public List<T> getObjects() {
        return objects;
    }

    @Override
    public void readFromFile() {
        if (new File(filename).length() == 0) {
            // not read if file empty
            return;
        }
        try (FileInputStream fis = new FileInputStream(filename); ObjectInputStream ois = new ObjectInputStream(fis)) {
            objects = (List<T>) ois.readObject();
        } catch (IOException e) {
            handleIOException("An error occurred IOException in readFromFile:\n", e);
        } catch (ClassNotFoundException e) {
            handleClassNotFoundException("An error ClassNotFoundException occurred in readFromFile:\n", e);
        }
    }

    @Override
    public void writeToFile() {
        try (FileOutputStream fos = new FileOutputStream(filename); ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(objects);
        } catch (IOException e) {
            handleIOException("Error writing to file: ", e);
        }
    }

    @Override
    public void createFileIfNotExist() {
        File file = new File(filename);
        try {
            if (file.createNewFile()) {
                System.out.println("File created: " + filename);
            }
        } catch (IOException e) {
            handleIOException("Error creating file: ", e);
        }
    }

    //add Object into List
    public void addObject(T object) {
        objects.add(object);
//        writeToFile();
    }
}

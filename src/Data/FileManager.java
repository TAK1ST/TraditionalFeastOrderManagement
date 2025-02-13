package Data;


import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.List;

/**
 *
 * @author SwordLake
 * @param <T>
 */
public class FileManager<T> implements IFileManager<T> {
    private String fileName; 

    public FileManager() {
    }
    //--------------------------------------------------        
    public FileManager(String fileName) {
        this.fileName = fileName;
    }
    //--------------------------------------------------
    @Override
    public List<String> readDataFromFile() throws IOException {
        List<String> result;
        result = Files.readAllLines(new File(fileName).toPath(), Charset.forName("utf-8"));
        return result;
    }   
}

package Data;

import java.util.List;

public interface IUserDao<T>{
    void addNew(T obj) throws Exception;
    Object saveFile() throws Exception;
    List<T> getList() throws Exception;        
}
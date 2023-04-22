package userClasses;
import java.util.ArrayList;

public interface InsertDeleteRow {

    
    public ArrayList<User> updateEmployee(ArrayList<User> employees, int index, Employee xEmployee);
    
    
    public ArrayList<User> deleteEmployee(ArrayList<User> employees, int index);

    
    public ArrayList<User> insertEmployee(ArrayList<User> userList, User user);
    

    public ArrayList<User> giveRaise(ArrayList<User> employees, int index, double raise);

}

package userClasses;

import java.util.ArrayList;

/**
 * employer
 */
public class Employer extends User implements InsertDeleteRow{
   

    public Employer(String name, String userName, String password) {
        super(name, userName, password);
        this.type="Employer";
    }

    public Employer(String name) {
        super(name);
    }

    @Override
    public ArrayList<User> deleteEmployee(ArrayList<User> employees, int index) {
        employees.remove(index);
        return employees;

    }

    @Override
    public ArrayList<User> insertEmployee(ArrayList<User> userList, User user) {
        userList.add(user);
        return userList;
    }

    @Override
    public ArrayList<User> updateEmployee(ArrayList<User> userList, int index, Employee xEmployee) {
        return null;
    }

    @Override
    public ArrayList<User> giveRaise(ArrayList<User> userList, int index, double raise) {
        Employee employee = (Employee) userList.get(index);
        double newSalary = employee.getSalary() + raise;
        employee.setSalary(newSalary);
        userList.set(index, employee); 
        return userList;
    }
    
    

    
    

    

    

    
}
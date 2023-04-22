package userClasses;

import java.util.ArrayList;

public class Admin extends User implements InsertDeleteRow{


    public Admin(String name, String userName, String password) {
        super(name, userName, password);
        this.type="Admin";
    }

    public Admin(String name) {
        super(name);
    }

    @Override
    public ArrayList<User> deleteEmployee(ArrayList<User> employees, int index) {
        employees.remove(index);
        return employees;

    }

    

    @Override
    public ArrayList<User> giveRaise(ArrayList<User> userList, int index, double raise) {
        Employee employee = (Employee) userList.get(index);
        double newSalary = employee.getSalary() + raise;
        employee.setSalary(newSalary);
        userList.set(index, employee); 
        return userList;
    }

    @Override
    public ArrayList<User> insertEmployee(ArrayList<User> userList, User user) {
        userList.add(user);
        return userList;
    }

    @Override
    public ArrayList<User> updateEmployee(ArrayList<User> userList, int index, Employee xEmployee) {
        
        User user = userList.get(index);
        user.setName(xEmployee.getName());
        ((Employee) user).setJob(xEmployee.getJob());
        ((Employee) user).setSalary(xEmployee.getSalary());

        return userList;
    }


    
    
}

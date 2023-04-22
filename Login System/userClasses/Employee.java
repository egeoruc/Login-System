package userClasses;

/**
 * employee
 */
public class Employee extends User {
    
    private double salary;
    private String job;

    public Employee(String name, String job, double salary) {
        super(name);
        this.salary = salary;
        this.job = job;
        this.type= "Employee";
    }
  

    public Employee(String name, String userName,String password, String job, double salary,String type){
        super(name, userName, password);
        this.job=job;
        this.salary=salary;
    }

    public Employee(String name, String userName, String password) {
        super(name, userName, password);
    }

    
    public String getUserName(){
        return super.getUserName();
    }


    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }
 
}
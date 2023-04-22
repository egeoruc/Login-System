package MVC;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;


import javax.crypto.SecretKey;

import Cryptography.Cryptography;
import userClasses.*;



public class Model {
    private ArrayList<User> userList;
    private ArrayList<User> fromDataBase;
    private SecretKey secretKey = Cryptography.generateSecretKey();

    public Model() throws Exception{
        userList = new ArrayList<>();
        Employee ali = new Employee("Bo","imtall", "123", "architect", 500.5,"Employee");
        Employer ege = new Employer("Ege", "b", "b");
        Employee Fulya = new Employee("Fulya", "ogrenci", 330);
        Admin admin=new Admin("Ayberk", "a", "a");
        ArrayList<User> fromDataBase=new ArrayList<>();
        fromDataBase=turnDataback(secretKey);
        ArrayList<User> userPass=new ArrayList<>();
        userPass=userAuthenticationReader(secretKey);
      
        addUser(ali);
        addUser(ege);
        addUser(Fulya);
        addUser(admin);

    }

    void addUser(User user) {
        userList.add(user);
    }

    public void dataBase(ArrayList<User> userList, SecretKey secretKey) throws Exception {
        FileWriter writer = new FileWriter("C:/Users/egeor/OneDrive/Masaüstü/projeq/data/dataBase.txt");
        for (User user : userList) {
            String name = user.getName();
            String type = user.getType();

            String dataString;
            if (user instanceof Employee) {
                String job = ((Employee) user).getJob();
                double salary = ((Employee) user).getSalary();
                dataString = name + "," + type + "," + job + "," + salary;
            } else {
                dataString = name + "," + type;
            }

            String encryptedData = Cryptography.encrypt(dataString, secretKey);
            writer.write(encryptedData + "\n");
        }
        writer.close();
    }
    
    public ArrayList<User> turnDataback(SecretKey secretKey) throws Exception {
        ArrayList<User> users = new ArrayList<>();
    
        BufferedReader reader = new BufferedReader(new FileReader("C:/Users/egeor/OneDrive/Masaüstü/projeq/data/dataBase.txt"));
        String line = reader.readLine();
        while (line != null) {
            String userData = Cryptography.decrypt(line, secretKey);
            String[] fields = userData.split(",");
            String name = fields[0];
            String type = fields[1];
    
            User user = null;
            if (type.equals("Employee")) {
                String job = fields[2];
                double salary = Double.parseDouble(fields[3]);
                user = new Employee(name, job, salary);
            } else if (type.equals("Employer")) {
                user = new Employer(name);
            } else if (type.equals("Admin")) {
                user = new Admin(name);
            } 
            if (user != null) {
                users.add(user);
            }
    
            line = reader.readLine();
        }
        reader.close();
        return users;
    }
    

    
    public String employeeDataBaseString(ArrayList<User> users ){
        String toString="";
        for(User user: users){
            if(user instanceof Employee)
            toString += user.getName()+", "+((Employee) user).getJob()+", "+((Employee) user).getSalary()+"\n";      
        }
        return toString;
    }
    

    public void userAuthentication(ArrayList<User> userList, SecretKey secretKey) throws Exception {
        FileWriter writer = new FileWriter("C:/Users/egeor/OneDrive/Masaüstü/projeq/data/userAuthentication.txt");
        for (User user : userList) {
            if(user.getUserName()!=null){
                String type= user.getType();
                String name = user.getName();
                String userName = user.getUserName();
                String password = user.getPasswordHash();
                String dataString = type + "," + name + "," + userName + "," + password;
                String encryptedData = Cryptography.encrypt(dataString, secretKey);
                writer.write(encryptedData + "\n");
            }
        }
        writer.close();
    }

    public ArrayList<User> userAuthenticationReader(SecretKey secretKey) throws Exception{
        BufferedReader reader = new BufferedReader(new FileReader("C:/Users/egeor/OneDrive/Masaüstü/projeq/data/userAuthentication.txt"));
        String line = reader.readLine();
        ArrayList<User> userPass=new ArrayList<>();
        while (line != null) {
            String userData = Cryptography.decrypt(line, secretKey);
            String[] fields = userData.split(",");
            String type = fields[0];
            String name = fields[1];
            String userName= fields[2];
            String password= fields[3];
    
            User user=null;
            if (type.equals("employee")) {
                user = new Employee(name, userName, password);
            } else if (type.equals("employer")) {
                user = new Employer(name, userName, password);
            } else if (type.equals("admin")) {
                user = new Admin(name,userName,password);
            } 
            if (user != null) {
                userPass.add(user);
            }
    
            line = reader.readLine();
        }
        reader.close();
        return userPass;
    }
    

    
        
    public boolean checkLogin(String userName, String password) throws NoSuchAlgorithmException {
        User user = getUser(userName);
        if (user == null||user.getUserName()=="") {
            return false;
        }else{
            return user.getPasswordHash().equals(Cryptography.getHash(password));
        } 
    }
    public SecretKey getSecretKey() {
        return secretKey;
    }

  
    public User getUser(int index){
        return userList(index);
    }
    
    public User userList(int index) {
        return userList.get(index);
    }

    public User getUser(String userName) {
        for (User user : userList) {
            if (user.getUserName() != null && !user.getUserName().isEmpty() && user.getUserName().equals(userName)) {
                return user;
            }
        }
        return null;
    }
    
    public ArrayList<User> getFromDataBase() {
        return fromDataBase;
    }

    public boolean isEmployer(User user){
        if(user instanceof Employer)
            return true;
        else
            return false;    
    }

    public boolean isEmployee(User user){
        if(user instanceof Employee)
            return true;
        else
            return false;    
    }

    public boolean isAdmin(User user){
        if(user instanceof Employee)
            return true;
        else
            return false;    
    }

    public ArrayList<User> getUserList() {
        return userList;
    }
    
    public void setUserList(ArrayList<User> userList) {
        this.userList = userList;
    }
    
    


}

package userClasses;

import java.security.NoSuchAlgorithmException;

import Cryptography.Cryptography;

public abstract class User {
   

    protected String type;
    private String name;
    private String userName=null;
    private String passwordHash = null;
   

    public User(String name) {
        this.name = name;
    }
    
    public User(String name, String userName, String password) {
        this.userName = userName;
        this.name = name;
        this.type="User";
        if (password != null) {
            try {
                this.passwordHash = Cryptography.getHash(password);
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPasswordHash() {
        return passwordHash;
    }
       
    public String getUserName() {
        return userName;
    }


    public void setUserName(String userName) {
        this.userName = userName;
    }
   
}

package MVC;

import javax.swing.*;
import userClasses.*;
import java.awt.FontMetrics;
import MVC.Controller.*;
import MVC.Controller.DataBaseActionListener;





public class View extends JPanel {
    private JFrame frame;
    private JTextField usernameField;
    private JTextField passwordField;
    private JButton exitButton;
    private JLabel userJLabel;
    private JLabel password;
    private JButton signButton;
    private Model model=new Model();
    
    

    
    public JButton getExiButton() {
        return exitButton;
    }
    public JFrame getFrame() {
        return frame;
    }
    public String getUsernameField() {
        return usernameField.getText();
    } 

    public String getPasswordField() {
        return passwordField.getText();
    }
   

    

    public View(JFrame frame) throws Exception {
        Controller controller = new Controller(model,this);
        SignActionListener listener = controller.new SignActionListener(this);
        ExitActionListener listener2= controller.new ExitActionListener(this);
        this.frame = frame;
        frame.setTitle("Sign In");
        setLayout(null);
        userJLabel = new JLabel("User Name");
        userJLabel.setBounds(200, 110, 70, 20);
        usernameField = new JTextField();
        usernameField.setBounds(270, 110, 175, 20);
        password = new JLabel("Password");
        password.setBounds(200, 165, 70, 20);
        passwordField = new JPasswordField();
        passwordField.setBounds(270, 165, 175, 20);
        signButton = new JButton("Sign in");
        signButton.setBounds(225, 210, 90, 30);
        exitButton= new JButton("Exit");
        exitButton.setBounds(320, 210, 90, 30);
        signButton.addActionListener(listener);
        exitButton.addActionListener(listener2);
        
        add(userJLabel);
        add(usernameField);
        add(password);
        add(passwordField);
        add(signButton);
        add(exitButton);
        setVisible(true);    
    }

    public void showErrorMessage(String message) {
    
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public  class EmployeeView extends JPanel{
       private JFrame frame;
       private Employee employee;
       private JButton exitButton;
       private JLabel nameJLabel;
       private JLabel jobJLabel;
       private JLabel salaryJLabel;
       private JLabel namLabel;
       private JLabel jobLabel;
       private JLabel salaryLabel;
       private static EmployeeView currentView;

    public JButton getExitButton() {
        return exitButton;
    }
    public JFrame getFrame() {
        return frame;
    }
       public Employee getEmployee() {
        return employee;
    }

    public EmployeeView(JFrame frame, User user) throws Exception{
        this.frame=frame;
        this.employee=(Employee) user;  
        currentView=this;
        View view=new View(frame);
        Controller controller = new Controller(model,view);
        logedExitActionListener listener=controller.new logedExitActionListener(this);
        setLayout(null);
        frame.setTitle("Employee Page");
        String name=user.getName();
        String job=((Employee) user).getJob();
        double salary=((Employee) user).getSalary();
        nameJLabel=new JLabel("Name");
        nameJLabel.setBounds(235, 130, 50, 30);
        jobJLabel=new JLabel("Job");
        jobJLabel.setBounds(300, 130, 50, 30);
        salaryJLabel=new JLabel("Salary");
        salaryJLabel.setBounds(365, 130, 50, 30);
        namLabel=new JLabel(name);
        namLabel.setBounds(235, 165, 50, 30);
        jobLabel=new JLabel(job);
        jobLabel.setBounds(300, 165, 50, 30);
        salaryLabel=new JLabel(Double.toString(salary));
        salaryLabel.setBounds(365, 165, 50, 30);
        exitButton=new JButton("Exit");
        exitButton.setBounds(275, 200, 65, 20);
        exitButton.addActionListener(listener);
        
        frame.setVisible(true);

        add(nameJLabel);
        add(jobJLabel);
        add(salaryJLabel);
        add(namLabel);
        add(jobLabel);
        add(salaryLabel);
        add(exitButton);
        }

        public void showErrorMessage(String message) {
            JOptionPane.showMessageDialog(this , message, "Error", JOptionPane.ERROR_MESSAGE);
        }
    } 
    
    public class EmployerView extends JPanel{
        private JFrame frame;
        private JButton hireButton;
        private Employer employer;
        private JTextField nameField;
        private JTextField jobField;
        private JTextField salaryField;
        private JButton dataBaseButton;
        private JLabel name;
        private JLabel job;
        private JLabel salary;
        private JLabel index;
        private JTextField fireField;
        private JButton fireButton;
        private JLabel indexRaise;
        private JLabel salaryRaise;
        private JTextField indexRaisField;
        private JTextField salaryRaiseField;
        private JButton raiseButton;
        private JButton exitButton;


        public int getIndexRaisField() {
            try {
                return Integer.parseInt(indexRaisField.getText());
            } catch (NumberFormatException e) {
                // handle the exception here
                showErrorMessage("Please enter a valid index value ");
                return -1;
            } 
        }
        public double getSalaryRaiseField() {
            try{
                String fieldValue= salaryField.getText();
                return  Double.parseDouble(fieldValue);
            }catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Please enter a valid salary value.");
                salaryField.setText("0.0");
                return 0.0;
            }
        }

        public int getFireField() {
            try {
                return Integer.parseInt(fireField.getText());
            } catch (NumberFormatException e) {
                // handle the exception here
                showErrorMessage("Please enter a valid index value.");
                return -1;
            }
        }
        public JFrame getFrame() {
            return frame;
        }
        public Employer getEmployer() {
            return employer;
        }
        public String getNameField() {
            return nameField.getText();
        }
        public String getJobField() {
            return jobField.getText();
        }
        public double getSalaryField() {
            try{
                String fieldValue= salaryField.getText();
                return  Double.parseDouble(fieldValue);
            }catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Please enter a valid number.");
                salaryField.setText("0.0");
                return 0.0;
            }
        }

        public EmployerView(JFrame frame, User employer) throws Exception {
            this.employer=(Employer) employer;
            View dummyView=new View(frame);
            Controller controller= new Controller(model, dummyView);
            DataBaseActionListener listener=controller.new DataBaseActionListener(this,frame);
            logedExitActionListener listener2=controller.new logedExitActionListener(this);
            hireButtonActionListener listener3=controller.new hireButtonActionListener(this);
            fireButtonActionListener listener4=controller.new fireButtonActionListener(this);
            
            setLayout(null);
            frame.setTitle("Employer Page");
            dataBaseButton= new JButton("Show DataBase");
            dataBaseButton.setBounds(165, 85, 340, 20);
            name=new JLabel("Name");
            name.setBounds(170, 110, 65, 18);
            job=new JLabel("Job");
            job.setBounds(240, 110, 65, 18);
            salary=new JLabel("Salary");
            salary.setBounds(305, 110, 65, 18);
            nameField=new JTextField();
            nameField.setBounds(165,132 ,66 , 18);
            jobField=new JTextField();
            jobField.setBounds(235, 132, 66, 18);
            salaryField=new JTextField();
            salaryField.setBounds(305, 132, 66, 18);
            hireButton=new JButton("Hire Employee");
            hireButton.setBounds(380, 130, 125, 19);
            index=new JLabel("Index");
            index.setBounds(240, 150, 70, 18);
            fireField=new JTextField();
            fireField.setBounds(165, 170, 205, 18);
            fireButton=new JButton("Fire Employee");
            fireButton.setBounds(380, 170, 125, 19);
            indexRaise=new JLabel("Index");
            indexRaise.setBounds(185, 195, 60, 18);
            salaryRaise=new JLabel("Salary");
            salaryRaise.setBounds(285, 195, 60, 18);
            indexRaisField=new JTextField();
            indexRaisField.setBounds(165, 215, 107, 18);
            salaryRaiseField=new JTextField();
            salaryRaiseField.setBounds(275,215,97,18);
            raiseButton=new JButton("Raise Salary");
            raiseButton.setBounds(380, 215, 125, 19);
            exitButton=new JButton("Exit");
            exitButton.setBounds(165, 240, 340, 20);
            dataBaseButton.addActionListener(listener);
            exitButton.addActionListener(listener2);
            hireButton.addActionListener(listener3);
            fireButton.addActionListener(listener4);
            
            frame.setVisible(true);
            add(salaryField);
            add(jobField);
            add(nameField);
            add(salary);
            add(job);
            add(name);
            add(dataBaseButton);
            add(hireButton);
            add(index);
            add(fireField);
            add(fireButton);
            add(indexRaise);
            add(salaryRaise);
            add(indexRaisField);
            add(salaryRaiseField);
            add(raiseButton);
            add(exitButton);

        }
        public void showErrorMessage(String message) {
            JOptionPane.showMessageDialog(this , message, "Error", JOptionPane.ERROR_MESSAGE);
        }

       
    }

    public class DataBase extends JPanel{
        private JFrame frame;
        private JLabel title;
        private JTextArea data;
        private JButton returnButton;
        private String stringData;
        private Employer employer;
        private int height;


        public void setHeight(int height) {
            this.height = height;
        }
        public void setStringData(String stringData) {
            this.stringData = stringData;
            data.setText(stringData); 
        }
        public JTextArea getData() {
            return data;
        }
        public void setData(JTextArea data) {
            this.data = data;
        }
        public Employer getEmployer() {
            return employer;
        } 
        public JFrame getFrame() {
            return frame;
        }
        public int determineHeight(JTextArea textArea, String text) {
            FontMetrics fontMetrics = textArea.getFontMetrics(textArea.getFont());
            int height = fontMetrics.getHeight() * text.split("\n").length;
            return height + 10;
        }

        public DataBase(JFrame frame, Employer employer) throws Exception {
            this.frame = frame;
            this.employer=employer;
            setLayout(null);
            View dummyView=new View(frame);
            Controller controller=new Controller(model, dummyView);
            returnActionListener listener=controller.new returnActionListener(this);
            frame.setTitle("Employee Database");
            title=new JLabel("Name,  Job,  Salary");
            title.setBounds(300, 50, 125, 30);
            data = new JTextArea();
            height=100;
            data.setText(stringData);
            data.setBounds(300, 85, 125, height );
            returnButton=new JButton("Return");
            returnButton.setBounds(300, height+90, 125, 25);
            returnButton.addActionListener(listener);
            

            
            add(title);
            add(data);  
            add(returnButton);

            frame.setVisible(true);
        }
    }

    public class AdminView extends JPanel{
        private JFrame frame;
        private Admin admin;
        private JTextField nameField;
        private JTextField jobField;
        private JTextField salaryField;
        private JButton dataBaseButton;
        private JLabel name;
        private JLabel job;
        private JLabel salary;
        private JButton addButton;
        private JLabel index;
        private JTextField fireField;
        private JButton deleteButton;
        private JLabel indexUpdate;
        private JLabel nameLabel;
        private JLabel jobLabel;
        private JLabel salaryLabel;
        private JTextField indexUpdateField;
        private JTextField nameLabelField;
        private JTextField jobLabelField;
        private JTextField salaryLabelField;
        private JButton updateButton;
        private JButton exitButton;


        public JFrame getFrame() {
            return frame;
        }
        public Admin getAdmin() {
            return admin;
        }

        public AdminView(JFrame frame, User admin) throws Exception {
            this.frame=frame;
            this.admin=(Admin) admin;
            View dummyView=new View(frame);
            Controller controller= new Controller(model, dummyView);
            DataBaseActionListener listener=controller.new DataBaseActionListener(this,frame);
            logedExitActionListener listener2=controller.new logedExitActionListener(this);
            hireButtonActionListener listener3=controller.new hireButtonActionListener(this);
            frame.setTitle("Admin Page");
            setLayout(null);
            dataBaseButton= new JButton("Show DataBase");
            dataBaseButton.setBounds(165, 85, 340, 20);
            name=new JLabel("Name");
            name.setBounds(170, 110, 65, 18);
            job=new JLabel("Job");
            job.setBounds(240, 110, 65, 18);
            salary=new JLabel("Salary");
            salary.setBounds(305, 110, 65, 18);
            nameField=new JTextField();
            nameField.setBounds(165,132 ,66 , 18);
            jobField=new JTextField();
            jobField.setBounds(235, 132, 66, 18);
            salaryField=new JTextField();
            salaryField.setBounds(305, 132, 66, 18);
            addButton=new JButton("Add Row");
            addButton.setBounds(380, 130, 125, 19);
            index=new JLabel("Index");
            index.setBounds(240, 150, 70, 18);
            fireField=new JTextField();
            fireField.setBounds(165, 170, 205, 18);
            deleteButton=new JButton("Delete Row");
            deleteButton.setBounds(380, 170, 125, 19);
            indexUpdate=new JLabel("Index");
            indexUpdate.setBounds(170, 190, 40, 18);
            nameLabel= new JLabel("Name");
            nameLabel.setBounds(215, 190, 40, 18);
            jobLabel= new JLabel("Job");
            jobLabel.setBounds(260, 190, 40, 18);
            salaryLabel= new JLabel("Salary");
            salaryLabel.setBounds(305, 190, 40, 18);
            indexUpdateField=new JTextField();
            indexUpdateField.setBounds(170, 210, 41, 19);
            nameLabelField=new JTextField();
            nameLabelField.setBounds(218, 210, 41, 19);
            jobLabelField=new JTextField();
            jobLabelField.setBounds(263, 210, 41, 19);
            salaryLabelField=new JTextField();
            salaryLabelField.setBounds(308, 210, 41, 19);
            updateButton=new JButton("Update Row");
            updateButton.setBounds(380, 210, 125, 19);
            exitButton=new JButton("Exit");
            exitButton.setBounds(165, 230, 340, 20);
            exitButton.addActionListener(listener2);
            dataBaseButton.addActionListener(listener);
            addButton.addActionListener(listener3);

            add(exitButton);
            add(updateButton);
            add(salaryLabelField);
            add(jobLabelField);
            add(nameLabelField);
            add(indexUpdateField);
            add(indexUpdate);
            add(nameLabel);
            add(jobLabel);
            add(salaryLabel);
            add(dataBaseButton);
            add(name);
            add(job);
            add(salary);
            add(nameField);
            add(jobField);
            add(salaryField);
            add(addButton);
            add(deleteButton);
            add(index);
            add(fireField);

            frame.setVisible(true);
        }

    }
    
}    
    
        
    
         
     

    


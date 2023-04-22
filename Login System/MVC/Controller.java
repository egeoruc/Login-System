package MVC;


import java.awt.event.*;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import MVC.View.*;
import MVC.Model.*;
import userClasses.*;



    public class Controller {
        private Model model;
        private View view;

        public Model getModel() {
            return model;
        }
    
        public Controller(Model model, View view) {
            this.view = view;
            this.model=model;
          
          }
        
        public class SignActionListener implements ActionListener {
            private View view;


            public SignActionListener( View view) {
                this.view = view;
              }
              

            public void actionPerformed(ActionEvent e) {
              String userName = view.getUsernameField();
              String pasword = view.getPasswordField();
              try {
                if (model.checkLogin(userName, pasword)) {
                  // Login successful, do something here
                  JFrame frame =view.getFrame();
                  User user= model.getUser(userName);
                  if(user instanceof Employee){
                    EmployeeView newView = view.new EmployeeView(frame, user);
                    frame.setContentPane(newView);
                    frame.revalidate();
                  }else if(user instanceof Employer){
                    EmployerView newView = view.new EmployerView(frame, user);
                    frame.setContentPane(newView);
                    frame.revalidate();
                  }if(user instanceof Admin){
                    AdminView newView= view.new AdminView(frame, user);
                    frame.setContentPane(newView);
                    frame.revalidate();
                  }

                } else {
                  view.showErrorMessage("Invalid username or password");
                }
              } catch (NoSuchAlgorithmException ex) {
                view.showErrorMessage("Error");
              } catch (Exception e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            }
          }
          public class ExitActionListener implements ActionListener {

            private View view;

            public ExitActionListener(View view) {
                this.view = view;
              }
              
            public void actionPerformed(ActionEvent e) {
               JFrame frame= view.getFrame();
               frame.dispose();
            }
          }

          public class logedExitActionListener implements ActionListener{
            private EmployeeView employeeView;
            private EmployerView employerView;
            private AdminView adminView;

            public logedExitActionListener(EmployeeView employeeView){
                this.employeeView=employeeView;
            }

            public logedExitActionListener(EmployerView employerView) {
                this.employerView=employerView;
            }

            public logedExitActionListener(AdminView adminView) {
                this.adminView=adminView;
            }

            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame=view.getFrame();
                View newView;
                try {
                    newView = new View(frame);
                    frame.setContentPane(newView);
                    frame.revalidate();
                } catch (Exception e1) {
                    view.showErrorMessage("Some problem occured while trying to exit.");
                }
                
                
            }
        
            
        }
        public class returnActionListener implements ActionListener{
            private DataBase dataView;

            public returnActionListener(DataBase dataView) {
                this.dataView=dataView;

            }


            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                JFrame frame=view.getFrame();
                Employer employer=dataView.getEmployer();
                try {
                    EmployerView employerView=view.new EmployerView(frame, employer);
                    frame.setContentPane(employerView);
                    frame.revalidate();
                } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    view.showErrorMessage("Some error happened while returning.");
                }
                
                
            }

            

            
        }
        public class DataBaseActionListener implements ActionListener{
            private EmployerView employerView;
            private Employer employer;
            private JFrame frame;
            private AdminView adminView;
        
            public DataBaseActionListener(EmployerView employerView, JFrame frame) {
                this.employerView = employerView;
                this.frame=frame;
                employer=employerView.getEmployer();
                
            }
        
            public DataBaseActionListener(AdminView adminView, JFrame frame2) {
                this.adminView=adminView;
                this.frame=frame2;
            }

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ArrayList<User> myData = model.turnDataback(model.getSecretKey());
                    String dataString = model.employeeDataBaseString(myData);
                    DataBase newView= view.new DataBase(frame, employer);
                    int height = newView.determineHeight(newView.getData(), dataString);
                    newView.setStringData(dataString);
                    newView.setHeight(height);
                    JFrame frame=view.getFrame();
                    frame.setContentPane(newView);
                    frame.revalidate();
                } catch (Exception ex) {
                    view.showErrorMessage("Error!");
                }
            }
        

        }
        public class hireButtonActionListener implements ActionListener{
            private EmployerView viewEmployer;
            private Employer employer;
            private AdminView adminView;

            public hireButtonActionListener(EmployerView viewEmployer) {
                this.viewEmployer = viewEmployer;
                employer=viewEmployer.getEmployer();
            }


            public hireButtonActionListener(AdminView adminView) {
                this.adminView=adminView;
            }


            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                ArrayList<User> userList;
                try {
                    userList = model.turnDataback(model.getSecretKey());
                    String name=viewEmployer.getNameField();
                    String job=viewEmployer.getJobField();
                    double salary=viewEmployer.getSalaryField();
                    if(salary==0){
                        
                    }else{
                        Employee newEmployee=new Employee(name, job, salary);
                        ArrayList<User> updatedList=employer.insertEmployee(userList, newEmployee);
                        model.setUserList(updatedList);
                        model.dataBase(updatedList,model.getSecretKey());
                    }
                    
                } catch (Exception e2) {
                    // TODO Auto-generated catch block
                    view.showErrorMessage("oopss something went wrong");
                }

                

                
            }
            

        } 
        public class fireButtonActionListener implements ActionListener{

            private EmployerView viewEmployer;
            private Employer employer;
            private int index;
            

            public fireButtonActionListener(EmployerView viewEmployer) {
                this.viewEmployer = viewEmployer;
                employer=viewEmployer.getEmployer();
                
            }

            public fireButtonActionListener(AdminView adminView) {
            }

            @Override
            public void actionPerformed(ActionEvent e) {
                index=viewEmployer.getFireField();
                if(index!=-1){
                    ArrayList<User> userList;
                    try {
                        userList = model.turnDataback(model.getSecretKey());
                        employer.deleteEmployee(userList, index);
                        model.setUserList(userList);
                        model.dataBase(userList,model.getSecretKey());
                    } catch (Exception e1) {
                        // TODO Auto-generated catch block
                        viewEmployer.showErrorMessage(null);
                    }
                    

                }
            
            }
              
        }
        public class raiseButtonActionListener implements ActionListener{
            private EmployerView viewEmployer;
            private Employer employer;
            private int index;
            

            public raiseButtonActionListener(EmployerView viewEmployer) {
                this.viewEmployer = viewEmployer;
                employer=viewEmployer.getEmployer();
                
            }
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                index=viewEmployer.getIndexRaisField();
                if(index!=-1){
                    ArrayList<User> userList;
                    try {
                        double salary =viewEmployer.getSalaryRaiseField();
                        userList = model.turnDataback(model.getSecretKey());
                        model.setUserList(employer.giveRaise(userList, index, salary));
                        model.dataBase(userList,model.getSecretKey());
                    } catch (Exception e1) {
                        // TODO Auto-generated catch block
                        viewEmployer.showErrorMessage(null);
                    }
                    

                }
                
            }


        }       


    

}

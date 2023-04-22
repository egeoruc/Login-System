package MVC;


import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) throws Exception {
        JFrame frame=new JFrame();
        frame.add(new View(frame));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 400);
        frame.setVisible(true);               
    }    
}
    
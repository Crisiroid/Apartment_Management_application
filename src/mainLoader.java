import javax.swing.*;
import java.io.File;


public class mainLoader {
    public static void main(String[] args){
            File autCheck = new File("aut.txt");
            JFrame jf = new JFrame("Apartment Management Application Register");
            if(autCheck.exists()){
                //Creating new Jframe
                jf = new JFrame("Apartment Management Application");
                //Setting Close operation
                jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                jf.setContentPane(new main_login_page().maing_login_panel);
                //Packing and showing main Frame
                jf.pack();
                jf.setVisible(true);
            }else{
                jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                jf.setContentPane(new registerLayout().registerPanel);
                jf.pack();
                jf.setVisible(true);
            }
    }
}

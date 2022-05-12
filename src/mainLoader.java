import javax.swing.*;
import java.io.File;
import java.awt.*;
import java.awt.Toolkit;

public class mainLoader {
    public static JFrame jf;
    public static void main(String[] args){
            File autCheck = new File("aut.json");
            if(autCheck.exists()){
                openFrame(new main_login_page().maing_login_panel, "Apartment Management Application");
            }else{
                openFrame(new registerLayout().registerPanel, "Apartment Management Application Register Form");
            }
    }
    public static void openFrame(Container nj, String title){
        jf = new JFrame(title);
        jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jf.setContentPane(nj);
        jf.pack();
        jf.setLocationRelativeTo(null);
        jf.setVisible(true);
    }
    public static void changeFrame(){
        jf.dispose();
    }
}

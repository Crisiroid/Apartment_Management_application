import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Scanner;

public class mainLoader {
    public static void main(String[] args){
        File autCheck = new File("aut.txt");
        JFrame jf = new JFrame("Apartment Management Application Register");
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setContentPane(new registerLayout().registerPanel);
        jf.pack();
        jf.setVisible(true);
        if(autCheck.exists()){
            //Creating new Jframe
            jf = new JFrame("Apartment Management Application");
            //Setting Close operation
            jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            jf.setContentPane(new main_login_page().maing_login_panel);
            //Packing and showing main Frame
            jf.pack();
            jf.setVisible(true);
        }
    }
}

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class main_login_page {
    JPanel maing_login_panel;
    private JLabel login_main_top_text;
    private JButton creditsButton;
    private JButton userLoginButton;
    private JButton adminLogin;

    public main_login_page(){
        login_main_top_text.setFont(new Font("calibri", Font.BOLD, 22));
        //create an action Listener for Credits button
        creditsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Credits: " +
                        "\n Database and Search System: Amir Sajjad Hosein Pour " +
                        "\n Admin Page: Maryam Ahankoob Ekbatani " +
                        "\n User Page: Arash Danesh Nejad");
            }
        });
        //create an action Listener for User Page
        userLoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame jf = new JFrame("User Login");
                jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                jf.setContentPane(new userLogin().userLoginPanel);
                jf.pack();
                jf.setLocationRelativeTo(null);
                jf.setVisible(true);
                mainLoader.changeFrame();
            }
        });
        //create an action Listener for Admin Page
        adminLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Maryam
                //call Designed Admin Page here
            }
        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}

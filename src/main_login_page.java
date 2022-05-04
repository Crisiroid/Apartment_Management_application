import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class main_login_page {
    private JPanel maing_login_panel;
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
                //Arash
                //call Designed User Page here
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
    public static void main(String[] args){
        //Creating new Jframe
        JFrame jf = new JFrame("Apartment Management Application");
        //Setting Close operation
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setContentPane(new main_login_page().maing_login_panel);
        //Packing and showing main Frame
        jf.pack();
        jf.setVisible(true);
    }
}

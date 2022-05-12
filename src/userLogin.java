import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class userLogin {
    //sql variables
    public static Connection C = null;
    public static Statement stmt = null;
    static ResultSet res = null;
    //swing variables
    JPanel userLoginPanel;
    private JLabel loginPageTopLabel;
    private JButton loginButton;
    private static JTextField userNameField;
    private static JPasswordField passWordField;
    private JLabel userNameLabel;
    private JLabel PasswordLabel;

    public userLogin(){
        loginPageTopLabel.setFont(new Font("calibri", Font.BOLD, 22));
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            connect();
            }
        });
    }
    public static void connect() {
        try {
            Class.forName("org.sqlite.JDBC");
            C = DriverManager.getConnection("jdbc:sqlite:database.db");
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Opened database successfully");
    }
    public static void authenticate(){
        try{
            C.setAutoCommit(false);
            stmt = C.createStatement();
            res = stmt.executeQuery("SELECT * from usersDetails where house_holder_phoneNumber='"+userNameField+"' and Password='"+passWordField+"';");
        }catch (Exception e){

        }
    }
}

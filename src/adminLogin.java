import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class adminLogin {
    //sql variables
    public static Connection C = null;
    public static Statement stmt = null;
    static ResultSet res = null;

    //swing variablesloginPageTopLabel
    JPanel adminLogin_panel;
    private JTextField adminUserNameField;
    private JPasswordField adminPasswordField;
    private JLabel adminUserLbel;
    private JLabel adminPassWordLabl;
    private JButton adminLoginBtn;
    private JLabel adminLoginName;

    public adminLogin(){
        adminLoginName.setFont(new Font("calibri", Font.BOLD, 22));
        adminLoginBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                connect();
                authenticate(adminUserNameField.getText(), adminPasswordField.getText());
            }
        });
    }
    public static void connect() {
        //Opening connection to Sqlite using JDBC driver
        try {
            Class.forName("org.sqlite.JDBC");
            C = DriverManager.getConnection("jdbc:sqlite:database.db");
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Opened database successfully");
    }
    public static void authenticate(String userNameFieldi, String passWordFieldi){
        //searching in database for username and password
        try{
            C.setAutoCommit(false);
            stmt = C.createStatement();
            res = stmt.executeQuery("SELECT EXISTS(SELECT 1 FROM usersDetails where house_holder_phoneNumber='"+userNameFieldi+"' and Password='"+passWordFieldi+"');");
            System.out.println("this is working");
            if(res.next()){
                boolean found = res.getBoolean(1);
                if (found) {
                    mainLoader.changeFrame();
                    //mainLoader.openFrame(new userMainPage().userMainPagePanel, "User Page");
                } else {
                    JOptionPane.showMessageDialog(null, "We Don't have You on record. Please Call Administration");
                }

            }
        }catch (Exception e){
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
    }
}

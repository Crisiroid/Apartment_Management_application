import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class adminExtensionOfTheRentalPage {
    //sqlite variables
    public static String sql;
    public static Connection C = null;
    public static Statement stmt = null;
    static ResultSet res = null;
    //swing variables
    JPanel adminExtensionOfTheRentalPanel;
    private JButton submitBtn;
    private JTextField userPhoneField;
    private JLabel userPhoneLabel;
    static int currentTime;
    public adminExtensionOfTheRentalPage(){
        submitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                connect();
                receiveUserInfo(userPhoneField.getText());
            }
        });
    }
    public static void connect() {
        //Opening connection to Sqlite using JDBC driver
        try {
            Class.forName("org.sqlite.JDBC");
            C = DriverManager.getConnection("jdbc:sqlite:database.db");
        } catch ( Exception e ) {
            JOptionPane.showMessageDialog(null, e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Opened database successfully");
    }
    public static void receiveUserInfo(String userNameFieldi){
        try{
            C.setAutoCommit(false);
            stmt = C.createStatement();
            res = stmt.executeQuery("select * from usersDetails where house_holder_phoneNumber = '" + userNameFieldi +"';");
            res.next();
            System.out.println("this is working");
            String ct = res.getString("rent_remaining_time");
            System.out.println(currentTime);
            int nextTime = currentTime + 365;
            updateUserInfo(nextTime, userNameFieldi);
            JOptionPane.showMessageDialog(null, "Request Submitted");
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getClass().getName() + ": " + e.getMessage());
        }
    }
    public static void updateUserInfo(int newTime, String userPhoneNumber){
        try{
            C.setAutoCommit(false);
            stmt = C.createStatement();
            stmt.executeUpdate("UPDATE usersDetails set rent_remaining_time = " + newTime + " where house_holder_phoneNumber = '" + userPhoneNumber +"';");
            C.commit();
            C.close();
            JOptionPane.showMessageDialog(null, "Request Submitted");
            mainLoader.changeFrame();
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getClass().getName() + ": " + e.getMessage());
        }
    }
}

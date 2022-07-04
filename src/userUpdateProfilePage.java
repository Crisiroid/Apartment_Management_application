import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class userUpdateProfilePage {
    //sqlite variables
    public static String sql;
    public static Connection C = null;
    public static Statement stmt = null;
    static ResultSet res = null;
    //swing variables
    JPanel userUpdateProfilePanel;
    private JButton registerBtn;
    private JTextField nameAndLastNameField;
    private JLabel nameAndLastNameLabel;
    private JLabel phoneNumberLabel;
    private JLabel housePhoneNumberLabel;
    private JLabel ownerShipStatusLabel;
    private JLabel passwordLabel;
    private JTextField phoneNumberField;
    private JTextField housePhoneNumberField;
    private JTextField ownerShipStatusField;
    private JTextField passWordField;

    public userUpdateProfilePage(String phoneNumber){

        registerBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if((nameAndLastNameField.getText() == null) ||
                        (phoneNumberField.getText() == null) ||
                        (housePhoneNumberField.getText() == null) ||
                        (ownerShipStatusField.getText() == null) ||
                        (passWordField.getText() == null)){
                    JOptionPane.showMessageDialog(null, "Please Fill out the form Completely. if you don't want to change a detail, write the old value.");
                }else{
                    connect();
                    updateUserInfo(nameAndLastNameField.getText(), phoneNumberField.getText(), housePhoneNumberField.getText(), ownerShipStatusField.getText(), passWordField.getText(), phoneNumber);
                }
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
    public static void updateUserInfo(String nameAndLastName, String phoneNumberNew, String phoneNumberOld, String houseNumber, String ownerShipStatus, String password){
        try{
            C.setAutoCommit(false);
            stmt = C.createStatement();
            stmt.executeUpdate("UPDATE usersDetails set house_holder_name_lastName = '" + nameAndLastName + "', house_holder_phoneNumber = '" + phoneNumberNew + "', house_phone_number = '" + houseNumber + "', house_rental_situation = '" + ownerShipStatus + "', Password = '" + password + "' where house_holder_phoneNumber = '" + phoneNumberOld +"';");
            C.commit();
            C.close();
            JOptionPane.showMessageDialog(null, "Request Submitted");
            mainLoader.changeFrame();
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getClass().getName() + ": " + e.getMessage());
        }
    }
}

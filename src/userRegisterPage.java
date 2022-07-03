import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class userRegisterPage {
    //sqlite database connection variables
    static Connection C = null;
    static String sql = null;
    static Statement stmt = null;
    //swing related variables
    private JLabel registerLabel;
    private JLabel nameAndLastNameLabel;
    private JTextField nameAndLastNameField;
    private JLabel phoneNumberLabel;
    private JLabel unitNumberLabel;
    private JTextField floorField;
    private JTextField unitField;
    private JLabel floorNumberLabel;
    private JTextField phoneNumberField;
    private JLabel houseNumberLabel;
    private JTextField housePhoneNumberField;
    private JButton registerBtn;
    JPanel userRegisterPanel;
    private JLabel userPasswordLabel;
    private JTextField userPasswordField;

    public userRegisterPage(){
        registerLabel.setFont(new Font("calibri", Font.BOLD, 23));
        registerBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                connect();
                writeToDB(nameAndLastNameField.getText(), phoneNumberField.getText(), housePhoneNumberField.getText(), Integer.parseInt(floorField.getText()), "user", userPasswordField.getText() ,Integer.parseInt(floorField.getText()));
                try {
                    File myObj = new File(phoneNumberField.getText()+"/"+"waterBills.txt");
                    myObj = new File(phoneNumberField.getText()+"/"+"gasBills.txt");
                    myObj = new File(phoneNumberField.getText()+"/"+"electricBills.txt");
                    if (myObj.createNewFile()) {
                        System.out.println("File created: " + myObj.getName());
                    }
                } catch (IOException ea) {
                    System.out.println("An error occurred.");
                    ea.printStackTrace();
                }
            }
        });
    }
    //opening connection using JDBC driver
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
    //Writing into Database
    public static void writeToDB(String nameLastName, String userPhoneNumber, String userHousePhoneNumber, int floor, String accessType, String passWord,  int unit){
        try {
            C.setAutoCommit(false);
            stmt = C.createStatement();
            sql = "INSERT INTO usersDetails (house_holder_name_lastName, house_holder_phoneNumber, house_floor, access_type, Password, house_phone_number, house_unit) " +
                    "VALUES ("+ nameLastName + ", "+ userPhoneNumber +", "+ floor +", "+ accessType +", "+ passWord +", "+ userHousePhoneNumber +", "+ unit +" );";
            stmt.executeUpdate(sql);
            stmt.close();
            C.commit();
            C.close();
        } catch (SQLException e) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Records created successfully");
    }
}

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
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
    private JTextField ownerShipStatusField;
    private JLabel ownerShipStatusLabel;

    public userRegisterPage(){
        registerLabel.setFont(new Font("calibri", Font.BOLD, 23));
        registerBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                connect();
                writeToDB(nameAndLastNameField.getText(),
                        phoneNumberField.getText(),
                        housePhoneNumberField.getText(),
                        Integer.parseInt(floorField.getText()),
                        "user",
                        userPasswordField.getText(),
                        Integer.parseInt(floorField.getText()),
                        ownerShipStatusField.getText());
                File nf = new File(phoneNumberField.getText());
                createUserTable(phoneNumberField.getText());
            }
        });
    }
    //opening connection using JDBC driver
    public static void connect() {
        try {
            Class.forName("org.sqlite.JDBC");
            C = DriverManager.getConnection("jdbc:sqlite:database.db");
        } catch ( Exception e ) {
            JOptionPane.showMessageDialog(null, e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Opened database successfully");
    }
    //Writing into Database
    public static void writeToDB(String nameLastName, String userPhoneNumber, String userHousePhoneNumber, int floor, String accessType, String passWord,  int unit, String ownerShipStatus){
        try {
            C.setAutoCommit(false);
            stmt = C.createStatement();
            sql = "INSERT INTO usersDetails (house_holder_name_lastName, house_holder_phoneNumber, house_floor, access_type, Password, house_phone_number, house_unit, house_rental_situation) " +
                    "VALUES ('"+ nameLastName + "', '"+ userPhoneNumber +"', "+ floor +", '"+ accessType +"', '"+ passWord +"', '"+ userHousePhoneNumber +"', "+ unit +", '"+ ownerShipStatus +"');";
            stmt.executeUpdate(sql);
            C.commit();
            stmt.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Records created successfully");
    }
    public static void createUserTable(String phoneNumber){
        try{
                stmt = C.createStatement();
                String sql = "CREATE TABLE '" + phoneNumber +
                        "' (ID INTEGER PRIMARY KEY     AUTOINCREMENT NULL   UNIQUE," +
                        " waterBillDate           TEXT, " +
                        " gasBillDate            TEXT, " +
                        " electricBillDate        TEXT, " +
                        " rentDate         TEXT)";
                stmt.executeUpdate(sql);
                C.commit();
                stmt.close();
                C.close();

        }catch ( Exception e ){
            JOptionPane.showMessageDialog(null, e.getClass().getName() + ": " + e.getMessage());
        }
        JOptionPane.showMessageDialog(null, "user created successfully!");
        mainLoader.changeFrame();
    }
}

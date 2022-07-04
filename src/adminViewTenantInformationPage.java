import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class adminViewTenantInformationPage {
    //normal variables
    public static String name_s_sql;
    public static String is_s_sql;
    public static String homeStatus_s_sql;
    public static String housePhoneNumber_s_sql;
    //sql variables
    public static String sql;
    public static Connection C = null;
    public static Statement stmt = null;
    static ResultSet res = null;
    //swing variables
    JPanel adminViewTenantInformationPanel;
    private JButton Exit;
    private JLabel nameAndLastNameLabel;
    private JLabel ownerShipStatusLanbel;
    private JLabel phoneNumberLabel;
    private JLabel housePhoneNumberLabel;
    private JLabel nameAndLastNameSql;
    private JLabel ownerShipSql;
    private JLabel phoneNumberSql;
    private JLabel housePhoneNumberSql;
    public adminViewTenantInformationPage(String phoneNumber){
        //connecting to database and filling out the form
        connect();
        fillOutForm(phoneNumber);
        //changing font of labels
        nameAndLastNameSql.setText(name_s_sql);
        nameAndLastNameSql.setFont(new Font("calibri", Font.BOLD, 23));
        phoneNumberSql.setText(phoneNumber);
        phoneNumberSql.setFont(new Font("calibri", Font.BOLD, 23));
        housePhoneNumberSql.setText(housePhoneNumber_s_sql);
        housePhoneNumberSql.setFont(new Font("calibri", Font.BOLD, 23));
        ownerShipSql.setText(homeStatus_s_sql);
        ownerShipSql.setFont(new Font("calibri", Font.BOLD, 23));
        nameAndLastNameLabel.setFont(new Font("calibri", Font.BOLD, 20));
        ownerShipStatusLanbel.setFont(new Font("calibri", Font.BOLD, 20));
        phoneNumberLabel.setFont(new Font("calibri", Font.BOLD, 20));
        housePhoneNumberLabel.setFont(new Font("calibri", Font.BOLD, 20));
        Exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainLoader.changeFrame();
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
    public static void fillOutForm(String userNameFieldi){
        //searching in database for username and password
        try{
            C.setAutoCommit(false);
            stmt = C.createStatement();
            res = stmt.executeQuery("select * from usersDetails where house_holder_phoneNumber = '" + userNameFieldi +"';");
            res.next();
            System.out.println("this is working");
            name_s_sql = res.getString("house_holder_name_lastName");
            is_s_sql = String.valueOf(res.getInt("Id"));
            homeStatus_s_sql = res.getString("house_rental_situation");
            housePhoneNumber_s_sql = res.getString("house_phone_number");
            C.close();
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getClass().getName() + ": " + e.getMessage());
        }
    }
}

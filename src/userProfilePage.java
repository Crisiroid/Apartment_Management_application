import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.DAYS;

public class userProfilePage {
    //normal variables
    public static String name_s_sql;
    public static String floor_s_sql;
    public static String phone_s_sql;
    //sql variables
    public static String sql;
    public static Connection C = null;
    public static Statement stmt = null;
    static ResultSet res = null;
    //Swing variables
    private JLabel nameAndLastNameLabel;
    private JLabel nameAndLastNameSql;
    private JLabel floorDetailsLabel;
    private JLabel floorDetailsSql;
    private JLabel userPhoneNumberLabel;
    private JLabel userPhoneNumberSql;
    JPanel userProfilePagePanel;
    private JLabel daysRemainingToPay;
    private JLabel nextRentSql;
    private JLabel waterBillLabel;
    private JLabel waterBillSql;
    private JLabel electricBillLabel;
    private JLabel electricBillSql;
    private JLabel gasBillLabel;
    private JLabel gasBillSql;
    //date variables
    LocalDate today = LocalDate.now();
    LocalDate endOfMonth = today.withDayOfMonth(today.lengthOfMonth());
    long daysBetween = DAYS.between(today, endOfMonth);
    LocalDate ta = today.withDayOfMonth(today.lengthOfMonth()-2);
    long daysBetweenta = DAYS.between(today, ta);

    public userProfilePage(String phoneNumber){
        nameAndLastNameLabel.setFont(new Font("calibri", Font.BOLD, 22));
        floorDetailsLabel.setFont(new Font("calibri", Font.BOLD, 22));
        userPhoneNumberLabel.setFont(new Font("calibri", Font.BOLD, 22));
        daysRemainingToPay.setFont(new Font("calibri", Font.BOLD, 22));
        waterBillLabel.setFont(new Font("calibri", Font.BOLD, 22));
        gasBillLabel.setFont(new Font("calibri", Font.BOLD, 22));
        electricBillLabel.setFont(new Font("calibri", Font.BOLD, 22));
        connect();
        fillOutForm(phoneNumber);
        nameAndLastNameSql.setText(name_s_sql);
        nameAndLastNameSql.setFont(new Font("calibri", Font.BOLD, 22));
        floorDetailsSql.setText(floor_s_sql);
        floorDetailsSql.setFont(new Font("calibri", Font.BOLD, 22));
        userPhoneNumberSql.setText(phone_s_sql);
        userPhoneNumberSql.setFont(new Font("calibri", Font.BOLD, 22));
        nextRentSql.setText(String.valueOf(daysBetween));
        nextRentSql.setFont(new Font("calibri", Font.BOLD, 22));
        waterBillSql.setText(String.valueOf(daysBetweenta));
        waterBillSql.setFont(new Font("calibri", Font.BOLD, 22));
        electricBillSql.setText(String.valueOf(daysBetweenta + 5));
        electricBillSql.setFont(new Font("calibri", Font.BOLD, 22));
        gasBillSql.setText(String.valueOf(daysBetweenta - 6));
        gasBillSql.setFont(new Font("calibri", Font.BOLD, 22));
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
            phone_s_sql = res.getString("house_phone_number");
            floor_s_sql = res.getString("house_floor");
            C.close();
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getClass().getName() + ": " + e.getMessage());
        }
    }
}

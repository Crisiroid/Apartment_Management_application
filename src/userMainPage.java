import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class userMainPage {
    //normal variables
    public static String name_s_sql;
    public static String is_s_sql;
    public static String homeStatus_s_sql;
    //sql variables
    public static String sql;
    public static Connection C = null;
    public static Statement stmt = null;
    static ResultSet res = null;
    //swing variables
    JPanel userMainPagePanel;
    private JLabel nameLabel;
    private JLabel nameSql;
    private JLabel idSql;
    private JLabel IdLabel;
    private JLabel homeStatusLabel;
    private JLabel homeStatusSql;
    private JButton buyOrLeaveBtn;
    private JButton billHistoryBtn;
    private JButton paymentHistoryBtn;
    private JLabel warningsLabel;
    private JList warningListSql;
    private JButton profileBtn;
    private JButton quitBtn;
    private JLabel watermark;
    public String phonenumber;

    public userMainPage(String phonenum){
        nameLabel.setFont(new Font("calibri", Font.BOLD, 22));
        IdLabel.setFont(new Font("calibri", Font.BOLD, 22));
        warningsLabel.setFont(new Font("calibri", Font.BOLD, 22));
        watermark.setFont(new Font("calibri", Font.BOLD, 22));
        homeStatusLabel.setFont(new Font("calibri", Font.BOLD, 22));
        phonenumber = phonenum;
        connect();
        fillOutForm(phonenum);
        nameSql.setText(name_s_sql);
        idSql.setText(is_s_sql);
        homeStatusSql.setText(homeStatus_s_sql);

        buyOrLeaveBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                connect();
                sellRequest(phonenum);
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
            homeStatus_s_sql = res.getString("access_type");
        }catch (Exception e){
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
    }
    public static void sellRequest(String userNameFieldi){
        //searching in database for username and password
        try{
            C.setAutoCommit(false);
            stmt = C.createStatement();
            sql = "INSERT INTO request_list (Type,PhoneNum,AcOrDc) VALUES (" + "'Sell', '" + userNameFieldi + "', 'wating');";
            stmt.executeUpdate(sql);
            C.commit();
            JOptionPane.showMessageDialog(null, "Request Submitted");
        }catch (Exception e){
            System.err.println( e.getClass().getName() + ": " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Something went wrong");
        }
    }
}

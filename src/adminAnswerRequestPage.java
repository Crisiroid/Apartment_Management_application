import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class adminAnswerRequestPage {
    //sqlite variables
    public static String sql;
    public static Connection C = null;
    public static Statement stmt = null;
    static ResultSet res = null;
    //swing variables
    JPanel adminAnswerRequestPanel;
    private JTextField userPhoneNumberField;
    private JLabel userPhoneNumberLabel;
    private JButton acceptBtn;
    private JButton declineBtn;
    public adminAnswerRequestPage(){
        connect();
        acceptBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                answerToRequest("Accepted", userPhoneNumberField.getText(), "Your request have been approved!");
            }
        });
        declineBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                answerToRequest("Declined", userPhoneNumberField.getText(), "Your request have beed declined!");
            }
        });
        mainLoader.changeFrame();
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
    public static void answerToRequest(String res, String userPhoneNumber, String answerMessage){
        try{
            C.setAutoCommit(false);
            stmt = C.createStatement();
            stmt.executeUpdate("UPDATE request_list set AcOrDc = '" + res + "', Message ='" + answerMessage + "' where PhoneNum = '" + userPhoneNumber +"';");
            C.commit();
            C.close();
            JOptionPane.showMessageDialog(null, "Request Submitted");
            mainLoader.changeFrame();
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getClass().getName() + ": " + e.getMessage());
        }
    }
}

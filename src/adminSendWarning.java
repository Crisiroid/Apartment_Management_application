import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class adminSendWarning {
    //sqlite database connection variables
    static Connection C = null;
    static String sql = null;
    static Statement stmt = null;
    public static String Query;
    //swing variables
    JPanel adminSendWarningPanel;
    private JButton sendBtn;
    private JTextField phoneNumField;
    private JTextField warningMessageField;
    private JLabel phoneNumLabel;
    private JLabel warningMessageLabel;
    private JLabel topTextLabel;
    public adminSendWarning(){
        topTextLabel.setFont(new Font("calibri", Font.BOLD, 22));
        sendBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                connect();
                writeToDB("Warning", phoneNumField.getText(), "waiting", warningMessageField.getText());
            }
        });
    }
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
    public static void writeToDB(String typeOfWarning, String phoneNumber, String response, String adminMessage){
        try {
            C.setAutoCommit(false);
            stmt = C.createStatement();
            sql = "INSERT INTO request_list(Type, PhoneNum, AcOrDc, Message) VALUES ('" + typeOfWarning + "', '" + phoneNumber + "', '" + response + "' ,'" + adminMessage + "');";
            stmt.executeUpdate(sql);
            stmt.close();
            C.commit();
            C.close();
            JOptionPane.showMessageDialog(null, "Your warning have been sent!");
            mainLoader.changeFrame();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getClass().getName() + ": " + e.getMessage());
        }
        System.out.println("Records created successfully");

    }
}

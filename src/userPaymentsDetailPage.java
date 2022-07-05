import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class userPaymentsDetailPage {
    //sql variables
    public static Connection C = null;
    public static PreparedStatement stmt = null;
    static ResultSet res = null;
    public static String Query;
    //swing variables
    JPanel userPaymentsDetailPanel;
    private JButton payBtn;
    private JTable payTable;
    private JLabel topTextLabel;
    public userPaymentsDetailPage(String phoneNumber, String type){
        topTextLabel.setText(type + " Details");
        topTextLabel.setFont(new Font("calibri", Font.BOLD, 22));
        connect();
        try{
            Query = "select * from '" + phoneNumber + "';";
            stmt = C.prepareStatement(Query);
            res = stmt.executeQuery();
            payTable.setModel(DbUtils.resultSetToTableModel(res));
            C.close();
            res.close();
        }catch ( Exception e ){
            JOptionPane.showMessageDialog(null, e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        payBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainLoader.openFrame(new userPayPage(phoneNumber).userPayPanel, "Pay");
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
}

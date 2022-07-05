import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.time.LocalDate;

public class userPayPage {
    //sql variables
    public static String sql;
    public static Connection C = null;
    public static Statement stmt = null;
    static ResultSet res = null;
    //Swing variables
    JPanel userPayPanel;
    private JLabel topTextLabel;
    private JRadioButton rentPayBtn;
    private JRadioButton elecBillBtn;
    private JRadioButton gasBillBtn;
    private JRadioButton waterBillBtn;
    private JButton payBtn;
    ButtonGroup G = new ButtonGroup();

    LocalDate today = LocalDate.now();
    public userPayPage(String phoneNumber) {
        G.add(rentPayBtn);
        G.add(elecBillBtn);
        G.add(gasBillBtn);
        G.add(waterBillBtn);
        connect();
        payBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(rentPayBtn.isSelected()){
                    try {
                        C.setAutoCommit(false);
                        stmt = C.createStatement();
                        sql = "INSERT INTO '" + phoneNumber + "'(rentDate) VALUES ('" + today + "');";
                        stmt.executeUpdate(sql);
                        stmt.close();
                        C.commit();
                        C.close();
                        JOptionPane.showMessageDialog(null, "You paid your bill!");
                        mainLoader.changeFrame();
                    } catch (SQLException a) {
                        JOptionPane.showMessageDialog(null, a.getClass().getName() + ": " + a.getMessage());
                    }
                    System.out.println("Records created successfully");
                    mainLoader.changeFrame();
                }
                if(elecBillBtn.isSelected()){
                    try {
                        C.setAutoCommit(false);
                        stmt = C.createStatement();
                        sql = "INSERT INTO '" + phoneNumber + "'(electricBillDate) VALUES ('" + today + "');";
                        stmt.executeUpdate(sql);
                        stmt.close();
                        C.commit();
                        C.close();
                        JOptionPane.showMessageDialog(null, "You paid your bill!");
                        mainLoader.changeFrame();
                    } catch (SQLException a) {
                        JOptionPane.showMessageDialog(null, a.getClass().getName() + ": " + a.getMessage());
                    }
                    System.out.println("Records created successfully");
                    mainLoader.changeFrame();
                }
                if(gasBillBtn.isSelected()){
                    try {
                        C.setAutoCommit(false);
                        stmt = C.createStatement();
                        sql = "INSERT INTO '" + phoneNumber + "'(gasBillDate) VALUES ('" + today + "');";
                        stmt.executeUpdate(sql);
                        stmt.close();
                        C.commit();
                        C.close();
                        JOptionPane.showMessageDialog(null, "You paid your bill!");
                        mainLoader.changeFrame();
                    } catch (SQLException a) {
                        JOptionPane.showMessageDialog(null, a.getClass().getName() + ": " + a.getMessage());
                    }
                    System.out.println("Records created successfully");
                    mainLoader.changeFrame();
                }
                if(waterBillBtn.isSelected()){
                    try {
                        C.setAutoCommit(false);
                        stmt = C.createStatement();
                        sql = "INSERT INTO '" + phoneNumber + "'(waterBillDate) VALUES ('" + today + "');";
                        stmt.executeUpdate(sql);
                        stmt.close();
                        C.commit();
                        C.close();
                        JOptionPane.showMessageDialog(null, "You paid your bill!");
                        mainLoader.changeFrame();
                    } catch (SQLException a) {
                        JOptionPane.showMessageDialog(null, a.getClass().getName() + ": " + a.getMessage());
                    }
                    System.out.println("Records created successfully");
                    mainLoader.changeFrame();
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
}

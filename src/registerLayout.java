import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
public class registerLayout {
    //main variables
    String writeToFileStr = null;
    JFrame jf;
    //sqlite database connection variables
    static Connection C = null;
    static String sql = null;
    static Statement stmt = null;
    //swing related variables
    JPanel registerPanel;
    private JLabel registerLabel;
    private JLabel regAdminName;
    private JLabel regPass;
    private JLabel unitNum;
    private JTextField regAdminField;
    private JTextField regFloorNFrield;
    private JTextField regUnitNField;
    private JButton regSubBTN;
    private JPasswordField regPasswordField;

    //Submitting inputs into database
    public registerLayout() {
        registerLabel.setFont(new Font("calibri", Font.BOLD, 23));
        connect();
        regSubBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String adminName = regAdminField.getText();
                int floorNumber = Integer.parseInt(regFloorNFrield.getText());
                int unitesNumber = Integer.parseInt(regUnitNField.getText());
                String passwordUnits = regPasswordField.getText();
                System.out.println(adminName);
                writeToDB(adminName, floorNumber, passwordUnits, unitesNumber);
                try {
                    File myObj = new File("aut.json");
                    if (myObj.createNewFile()) {
                        System.out.println("File created: " + myObj.getName());
                    }
                } catch (IOException ea) {
                    System.out.println("An error occurred.");
                    ea.printStackTrace();
                }
                mainLoader mainloader = new mainLoader();
                String[] arguments = new String[] {"123"};
                mainloader.changeFrame();
                mainloader.main(arguments);
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
    public static void writeToDB(String aName, int Fnum, String Passw, int uni){
        try {
            C.setAutoCommit(false);
            stmt = C.createStatement();
            sql = "INSERT INTO apartmentDetails (admin_name,floor_number,password,unit_per_floor) " +
                    "VALUES ('"+ aName + "', "+ Fnum +", '"+Passw+"', "+ uni +" );";
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

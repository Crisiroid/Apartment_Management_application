import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import net.proteanit.sql.DbUtils;

public class adminViewRequests {
    //sql variables
    public static Connection C = null;
    public static PreparedStatement stmt = null;
    static ResultSet res = null;
    public static String Query;
    //swing variables
    JPanel adminViewRequestsPanel;
    private JTable requestListSql;
    private JButton answerToTheRequestBtn;
    private JButton exitBtn;

    public adminViewRequests() {
        connect();
        try{
            Query = "select * from request_list";
            stmt = C.prepareStatement(Query);
            res = stmt.executeQuery();
            requestListSql.setModel(DbUtils.resultSetToTableModel(res));
            C.close();
            res.close();
        }catch ( Exception e ){
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        exitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainLoader.changeFrame();
            }
        });
        answerToTheRequestBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            mainLoader.openFrame(new adminAnswerRequestPage().adminAnswerRequestPanel, "Answer to User's request");
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
}

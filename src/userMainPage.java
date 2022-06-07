import javax.swing.*;
import java.awt.*;

public class userMainPage {
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

    public userMainPage(){
        nameLabel.setFont(new Font("calibri", Font.BOLD, 22));
        IdLabel.setFont(new Font("calibri", Font.BOLD, 22));
        warningsLabel.setFont(new Font("calibri", Font.BOLD, 22));
        watermark.setFont(new Font("calibri", Font.BOLD, 22));
    }
}

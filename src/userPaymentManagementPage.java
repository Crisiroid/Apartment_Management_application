import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class userPaymentManagementPage {
    JPanel userPaymentManagementPanel;
    private JButton billsBtn;
    private JButton rentBtn;
    public userPaymentManagementPage(String phoneNumber){

        rentBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainLoader.openFrame(new userPaymentsDetailPage(phoneNumber, "Rent").userPaymentsDetailPanel, "Rent Payment");
            }
        });
        billsBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainLoader.openFrame(new userPaymentsDetailPage(phoneNumber, "Bills").userPaymentsDetailPanel, "Rent Payment");
            }
        });
    }
}

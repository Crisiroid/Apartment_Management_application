import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class adminMainPage {
    private JButton logOutButton;
    private JButton viewTenantInformationButton;
    private JButton updateTenantProfileButton;
    private JButton viewRequestsButton;
    private JButton warningsButton;
    private JButton createTenantBtn;
    JPanel adminMainPagePanel;

    public adminMainPage() {
        createTenantBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainLoader.openFrame(new userRegisterPage().userRegisterPanel, "Apartment Management Application Register Form");
            }
        });
    }
}

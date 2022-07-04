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
        logOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainLoader.changeFrame();
                mainLoader.openFrame(new main_login_page().maing_login_panel, "Apartment Management Application");
            }
        });
        viewTenantInformationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userPhoneNumber = JOptionPane.showInputDialog("Enter user's phone number: ");
                mainLoader.openFrame(new adminViewTenantInformationPage(userPhoneNumber).adminViewTenantInformationPanel, "View Tenant Information");
            }
        });
        updateTenantProfileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userPhoneNumber = JOptionPane.showInputDialog("Enter user's phone number: ");
                mainLoader.openFrame(new userUpdateProfilePage(userPhoneNumber).userUpdateProfilePanel, "Update User Profile");
            }
        });
        viewRequestsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainLoader.openFrame(new adminViewRequests().adminViewRequestsPanel, "View Requests");
            }
        });
    }
}

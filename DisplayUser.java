package com.mycompany.project;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;

public class DisplayUser {
    public static void displayUserDetails() {
        JFrame UserDetailsFrame = new JFrame("Registered User Details");
        UserDetailsFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        UserDetailsFrame.setSize(1370, 735);

        JPanel UserDetailsPanel = new JPanel();
        UserDetailsPanel.setLayout(null);

        JButton BackButton = new JButton("Back");
        BackButton.setBounds(180, 620, 100, 23);
        JLabel CNICLabel = new JLabel("CNIC: ");
        CNICLabel.setBounds(100, 170, 70, 100);

        JTable table = SignupPage.table;
        
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(500, 100, 800, 500);

        UserDetailsPanel.add(scrollPane); 

        UserDetailsFrame.add(UserDetailsPanel);
        UserDetailsFrame.setVisible(true);
    }
}
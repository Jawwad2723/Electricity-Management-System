package com.mycompany.project;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Image;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.image.BufferedImage;
import java.io.File;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

public class LoginPage implements Serializable {
    private static void setGhostText(JTextField textField, String ghostText) {
        textField.setText(ghostText);
        textField.setForeground(Color.GRAY);
        textField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (textField.getText().equals(ghostText)) {
                    textField.setText("");
                    textField.setForeground(Color.BLACK);
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (textField.getText().isEmpty()) {
                    textField.setText(ghostText);
                    textField.setForeground(Color.GRAY);
                }
            }
        });
    }      
    private static boolean checkCredentials(String loginName, String password) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("\\\\DESKTOP-3DH51Q4\\Users\\jawwa\\OneDrive\\Desktop\\DA\\signup.dat"))) {
        while (true) {
            try {
                UserData userData = (UserData) ois.readObject();
                if (userData.getName().equals(loginName) && userData.getPassword().equals(password)) {
                    return true;
                }
            } catch (EOFException e) {
                break; // Reached end of file exit the loop
            }
         }
            }catch (IOException | ClassNotFoundException ex) {
        return false;
        }
    return false; // Credentials not found
    }
    public static void CreateLoginPage() {
        String LoginImagePath = "\\\\DESKTOP-3DH51Q4\\Users\\jawwa\\OneDrive\\Desktop\\DA\\LoginPage.JPG";
        File f = new File(LoginImagePath);
        int ImageWidth = 1370, ImageHeight = 735;
        try {
            BufferedImage image = ImageIO.read(f);
            JLabel LoginPageImage = new JLabel(new ImageIcon(image.getScaledInstance(ImageWidth, ImageHeight, Image.SCALE_SMOOTH)));
            LoginPageImage.setBounds(0, 0, ImageWidth, ImageHeight);

            JFrame frame = new JFrame("Login Page");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JPanel loginPanel = new JPanel();
            loginPanel.setLayout(null);

            JLabel loginPageLabel = new JLabel("Login");
            loginPageLabel.setBounds(600, 10, 150, 230);

            JLabel LoginNameLabel = new JLabel("Name: ");
            LoginNameLabel.setBounds(550, 50, 150, 230);
            JTextField loginNameField = new JTextField();
            loginNameField.setBounds(610, 155, 160, 22);
            setGhostText(loginNameField, "Enter your Name...");
            
            JButton backButton = new JButton("Back");
            backButton.setBounds(630, 290, 100, 22);
            backButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    frame.dispose();
                    Project.createProject();
                }
            });
            
            JLabel loginPasswordLabel = new JLabel("Password: ");
            loginPasswordLabel.setBounds(550, 100, 150, 230);
            JPasswordField loginPasswordField = new JPasswordField();
            loginPasswordField.setBounds(615, 205, 160, 22);
            setGhostText(loginPasswordField, "Enter your password...");
            
            JButton loginButton = new JButton("Login");
            loginButton.setBounds(630, 260, 100, 22);
            loginButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String name = loginNameField.getText();
                    String password = new String(loginPasswordField.getPassword());
                    boolean x = checkCredentials(name, password);
                    if (x) {
                    UserData userData = getUserData(name, password);
                    if (userData != null) {
                        displayUserDetails(userData);
                    } 
                    } else {
                    JOptionPane.showMessageDialog(frame, "Invalid login credentials!");
                }
            }
        });
            loginPanel.add(loginPageLabel);
            loginPanel.add(LoginNameLabel);
            loginPanel.add(backButton);
            loginPanel.add(loginNameField);
            loginPanel.add(loginPasswordLabel);
            loginPanel.add(loginPasswordField);
            loginPanel.add(loginButton);
            loginPanel.add(LoginPageImage);

            frame.setSize(1370, 735);
            frame.add(loginPanel);
            frame.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	private static UserData getUserData(String loginName, String password) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("\\\\DESKTOP-3DH51Q4\\Users\\jawwa\\OneDrive\\Desktop\\DA\\signup.dat"))) {
            while (true) {
                try {
                    UserData userData = (UserData) ois.readObject();
                    if (userData.getName().equals(loginName) && userData.getPassword().equals(password)) {
                        return userData;
                    }
                } catch (EOFException e) {
                    break; // Reached end of file, exit the loop
                    }
                }
            } catch (IOException | ClassNotFoundException ex) {
        ex.printStackTrace();
            }
        return null; // User data not found
        }


    private static void displayUserDetails(UserData userData) {
        JFrame userDetailsFrame = new JFrame("User Details");
        userDetailsFrame.setSize(500, 500);
        JPanel userDetailsPanel = new JPanel();
        userDetailsPanel.setLayout(new BoxLayout(userDetailsPanel, BoxLayout.PAGE_AXIS));
        if (userData instanceof UserData) {
            userDetailsPanel.add(new JLabel("Name: " + userData.getName()));
            userDetailsPanel.add(new JLabel("CNIC: " + userData.getCnic()));
            userDetailsPanel.add(new JLabel("Phone: " + userData.getPhone()));
            userDetailsPanel.add(new JLabel("Address: " + userData.getAddress()));
            userDetailsPanel.add(new JLabel("City: " + userData.getSelectedCity()));
            userDetailsPanel.add(new JLabel("Already a consumer: " + userData.isAlreadyConsumer()));
            userDetailsPanel.add(new JLabel("Agreed to TC: " + userData.hasAgreedToTC()));

            JTextField unitsField = new JTextField();
            unitsField.setMaximumSize(new Dimension(200, 30));

            userDetailsPanel.add(new JLabel("Enter Units To Calculate Charges:"));
            userDetailsPanel.add(unitsField);
            JButton calculateButton = new JButton("Calculate");
            calculateButton.setAlignmentX(Component.LEFT_ALIGNMENT);
            calculateButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String unitsString = unitsField.getText();
                    if (!unitsString.isEmpty()) {
                        try {
                            int units = Integer.parseInt(unitsString);
                            double electricityCharges = Calculator.calculateElectricityCharges(units);
                            if (electricityCharges >= 0) {
                            // Display the calculated charges using JOptionPane
                            JOptionPane.showMessageDialog(userDetailsFrame, "Electricity charges: " + electricityCharges);
                        } else {
                            JOptionPane.showMessageDialog(userDetailsFrame, "Failed to calculate electricity charges. Please check the perUnitRate.txt file.");
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(userDetailsFrame, "Invalid input! Please enter a valid number.");
                    }
                } else {
                    JOptionPane.showMessageDialog(userDetailsFrame, "Please enter the units first.");
                }
            }
        });
        userDetailsPanel.add(calculateButton);
    } else {
        userDetailsPanel.add(new JLabel("User data is invalid."));
    }
    userDetailsFrame.add(userDetailsPanel);
    userDetailsFrame.setVisible(true);
}
}
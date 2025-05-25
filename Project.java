package com.mycompany.project;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Image;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.image.BufferedImage;
import java.io.File;

public class Project {
    public static void createProject(){
        String ProjectFrameImage="\\\\DESKTOP-3DH51Q4\\Users\\jawwa\\OneDrive\\Desktop\\DA\\Project.JPG";
        File f = new File(ProjectFrameImage);
        int ImageWidth = 1370, ImageHeight = 735;
        try {
            // Image upload
            BufferedImage image = ImageIO.read(f);
            JLabel LoginPagePicture = new JLabel(new ImageIcon(image.getScaledInstance(ImageWidth, ImageHeight, Image.SCALE_SMOOTH)));
            LoginPagePicture.setBounds(0, 0, ImageWidth, ImageHeight);

            JFrame ElectricityManagementSystemFrame = new JFrame("Electricity Management System");
            ElectricityManagementSystemFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JPanel loginsignupPannel = new JPanel();
            loginsignupPannel.setLayout(null);

            JButton loginbutton = new JButton("login");
            loginbutton.setBounds(545, 420, 100, 30);
            loginbutton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ElectricityManagementSystemFrame.setVisible(false);
                    LoginPage.CreateLoginPage();
                    ElectricityManagementSystemFrame.dispose();
                }
            });
            JButton signupButton = new JButton("Signup");
            signupButton.setBounds(655, 420, 100, 30);
            signupButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    SignupPage.createSignupPage();
                    ElectricityManagementSystemFrame.dispose();
                }
            });
            loginsignupPannel.add(loginbutton);
            loginsignupPannel.add(signupButton);
            loginsignupPannel.add(LoginPagePicture);

            ElectricityManagementSystemFrame.setSize(1370, 735);
            ElectricityManagementSystemFrame.add(loginsignupPannel);
            ElectricityManagementSystemFrame.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();                       
        }
    }
    public static void main(String[] args){
        String filePath = "\\\\DESKTOP-3DH51Q4\\Users\\jawwa\\OneDrive\\Desktop\\DA\\signup.dat";
        FileUtil.emptyFile(filePath);
        createProject();        
    }
}
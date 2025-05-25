package com.mycompany.project;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Image;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.image.BufferedImage;
import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;
import javax.swing.table.DefaultTableModel;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class SignupPage {
    public static String name;
    public static String cnic;
    public static String phone;
    public static String address;
    public static String password;
    public static String selectedCity;
    public static boolean alreadyConsumer;
    public static boolean agreedToTC;
    public static JTable table;

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
    private static void setGhostText(JTextArea textArea, String ghostText) {
        textArea.setText(ghostText);
        textArea.setForeground(Color.GRAY);

        textArea.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (textArea.getText().equals(ghostText)) {
                    textArea.setText("");
                    textArea.setForeground(Color.BLACK);
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (textArea.getText().isEmpty()) {
                    textArea.setText(ghostText);
                    textArea.setForeground(Color.GRAY);
                }
            }
        });
    }
    private static void saveUserDataToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("\\\\DESKTOP-3DH51Q4\\Users\\jawwa\\OneDrive\\Desktop\\DA\\signup.dat", true))) {
            UserData userData = new UserData();
            userData.setName(name);
            userData.setCnic(cnic);
            userData.setPhone(phone);
            userData.setAddress(address);
            userData.setPassword(password);
            userData.setSelectedCity(selectedCity);
            userData.setAlreadyConsumer(alreadyConsumer);
            userData.setAgreedToTC(agreedToTC);
            oos.writeObject(userData);
            System.out.println("Signup data saved successfully.");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void createSignupPage() {
        String SignupFileImage = "\\\\DESKTOP-3DH51Q4\\Users\\jawwa\\OneDrive\\Desktop\\DA\\SignupPage.JPG";
        File f = new File(SignupFileImage);
        int ImageWidth = 1370, ImageHeight = 735;

        try {
            BufferedImage image = ImageIO.read(f);
            JLabel SignupPageImage = new JLabel(new ImageIcon(image.getScaledInstance(ImageWidth, ImageHeight, Image.SCALE_SMOOTH)));
            SignupPageImage.setBounds(0, 0, ImageWidth, ImageHeight);

            JFrame SignupPageFrame = new JFrame("EMS Signup Page");
            SignupPageFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            SignupPageFrame.setSize(1370, 735);

            JPanel signupPanel = new JPanel();
            signupPanel.setLayout(null);

            JLabel WelcomeLabel = new JLabel("Welcome to EMS Signup Page");
            WelcomeLabel.setBounds(565, 10, 250, 30);

            JLabel NameLabel = new JLabel("Name: ");
            NameLabel.setBounds(100, 120, 70, 20);
            JTextField NameField = new JTextField(); // Full NAME in CAPITAL
            NameField.setBounds(160, 120, 135, 20);
            setGhostText(NameField, "Enter your Full Name...");

            JButton BackButton = new JButton("Back");
            BackButton.setBounds(180, 620, 100, 23);
            BackButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Project.createProject();
                }
            });
            JLabel CNICLabel = new JLabel("CNIC: ");
            CNICLabel.setBounds(100, 170, 70, 20);
            JTextField CNICField = new JTextField(); // without dashes
            CNICField.setBounds(160, 170, 135, 20);
            setGhostText(CNICField, "Enter your CNIC...");

            JLabel phoneLabel = new JLabel("Phone #: ");
            phoneLabel.setBounds(100, 220, 70, 20);
            JTextField phoneField = new JTextField(); // without dashes
            phoneField.setBounds(160, 220, 135, 20);
            setGhostText(phoneField, "Enter your Phone#...");

            JLabel AddressLabel = new JLabel("Address: ");
            AddressLabel.setBounds(100, 270, 70, 20);
            JTextArea AddressArea = new JTextArea(); // without dashes
            AddressArea.setBounds(160, 270, 150, 80);
            setGhostText(AddressArea, "Enter your Address...");
            
            JLabel PasswordLabel = new JLabel("Password: ");
            PasswordLabel.setBounds(100, 360, 70, 20);
            JPasswordField PasswordField = new JPasswordField(); 
            PasswordField.setBounds(170, 360, 135, 20);
            setGhostText(PasswordField, "Enter Password#...");

            JLabel cityLabel = new JLabel("City: ");
            cityLabel.setBounds(100, 400, 70, 100);

            String[] city = {"          Select City", "Karachi", "Lahore", "Darya Khan"};
            JComboBox<String> cityCB = new JComboBox<>(city);
            cityCB.setBounds(160, 442, 150, 20);

            JLabel RegLabel = new JLabel("Are you already a consumer of any other Electricity Company? ");
            RegLabel.setBounds(100, 492, 370, 20);

            JRadioButton RegRB1 = new JRadioButton("Yes");
            RegRB1.setBounds(200, 515, 50, 25);
            JRadioButton RegRB2 = new JRadioButton("No");
            RegRB2.setBounds(260, 515, 50, 25);

            ButtonGroup RegRB = new ButtonGroup();
            RegRB.add(RegRB1);
            RegRB.add(RegRB2);

            JLabel TCLabel = new JLabel("By clicking on Signup, you agree to the Terms of Use and Privacy Policy");
            TCLabel.setBounds(100, 542, 450, 20);
            JCheckBox TCCB = new JCheckBox();
            TCCB.setBounds(100, 560, 20, 20);

            JButton SubmitButton = new JButton("Submit");
            SubmitButton.setBounds(180, 590, 100, 23);
            SubmitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            name = NameField.getText();
            cnic = CNICField.getText();
            phone = phoneField.getText();
            address = AddressArea.getText();
            password = new String(PasswordField.getPassword());
            selectedCity = (String) cityCB.getSelectedItem();
            alreadyConsumer = RegRB1.isSelected();
            agreedToTC = TCCB.isSelected();
            if (name.isEmpty() || cnic.isEmpty() || phone.isEmpty() || address.isEmpty() || password.isEmpty()
                    || selectedCity.equals("          Select City") || (!RegRB1.isSelected() && !RegRB2.isSelected())
                    || !agreedToTC) {
                JOptionPane.showMessageDialog(SignupPageFrame, "Please fill in all the fields.");
                return;
            }

            UserData userData = new UserData();
            userData.setName(name);
            userData.setCnic(cnic);
            userData.setPhone(phone);
            userData.setAddress(address);
            userData.setPassword(password);
            userData.setSelectedCity(selectedCity);
            userData.setAlreadyConsumer(alreadyConsumer);
            userData.setAgreedToTC(agreedToTC);

            DefaultTableModel model = (DefaultTableModel) table.getModel();
            Object[] rowData = {
                userData.getName(),
                userData.getCnic(),
                userData.getPhone(),
                userData.getAddress(),
                userData.getPassword(),
                userData.getSelectedCity(),
                userData.isAlreadyConsumer(),
                userData.hasAgreedToTC()
            };
            model.addRow(rowData);

            saveUserDataToFile();
            JOptionPane.showMessageDialog(SignupPageFrame, "Signup successful!");

            // Clearing fields 
            NameField.setText("");
            CNICField.setText("");
            phoneField.setText("");
            AddressArea.setText("");
            PasswordField.setText("");
            cityCB.setSelectedIndex(0);
            RegRB.clearSelection();
            TCCB.setSelected(false);
        }
    });

    JButton RegisteredDetailsUserButton = new JButton("Registered Users Details");
    RegisteredDetailsUserButton.setBounds(1000, 520, 150, 23);

    RegisteredDetailsUserButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            DisplayUser.displayUserDetails();
        }
    });

    table = new JTable();
    DefaultTableModel model = new DefaultTableModel();
    model.addColumn("Name");
    model.addColumn("CNIC");
    model.addColumn("Phone");
    model.addColumn("Address");
    model.addColumn("Password");
    model.addColumn("City");
    model.addColumn("Already Consumer");
    model.addColumn("Agreed to TC");
    table.setModel(model);

    JScrollPane scrollPane = new JScrollPane(table);
    scrollPane.setBounds(500, 100, 800, 500);

    signupPanel.add(WelcomeLabel);
    signupPanel.add(NameLabel);
    signupPanel.add(NameField);
    signupPanel.add(BackButton);
    signupPanel.add(CNICLabel);
    signupPanel.add(CNICField);
    signupPanel.add(phoneLabel);
    signupPanel.add(phoneField);
    signupPanel.add(AddressLabel);
    signupPanel.add(AddressArea);
    signupPanel.add(PasswordLabel);
    signupPanel.add(PasswordField);
    signupPanel.add(cityLabel);
    signupPanel.add(cityCB);
    signupPanel.add(RegLabel);
    signupPanel.add(RegRB1);
    signupPanel.add(RegRB2);
    signupPanel.add(TCLabel);
    signupPanel.add(TCCB);
    signupPanel.add(SubmitButton);
    signupPanel.add(RegisteredDetailsUserButton);
    signupPanel.add(SignupPageImage);

    SignupPageFrame.add(signupPanel);
    SignupPageFrame.setVisible(true);

    } catch (Exception e) {
    e.printStackTrace();
        }
   }
}  
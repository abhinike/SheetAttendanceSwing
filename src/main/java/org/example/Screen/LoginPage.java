package org.example.Screen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;

public class LoginPage {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(LoginPage::createAndShowGUI);
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Login Page");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                Color color1 = new Color(0xC6FFDD);
                Color color2 = new Color(0xFBD786);
                Color color3 = new Color(0xf7797d);

                Point2D center = new Point2D.Float(frame.getWidth() / 2, frame.getHeight() / 2);
                float radius = Math.max(frame.getWidth(), frame.getHeight());

                RadialGradientPaint gradient = new RadialGradientPaint(center, radius,
                        new float[] { 0.0f, 0.45f, 1.0f }, new Color[] { color1, color2, color3 });
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        panel.setBackground(Color.white);
        panel.setLayout(new GridBagLayout());
        frame.add(panel);

        placeComponents(panel);

        frame.setSize(400, 300);

        // Center the frame on the screen with space from the top
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((screenSize.getWidth() - frame.getWidth()) / 2);
        int y = (int) ((screenSize.getHeight() - frame.getHeight()) / 2) - 50; // Adjust this value for the space from
                                                                               // the top
        frame.setLocation(x, y);

        frame.setVisible(true);
    }

    private static void placeComponents(JPanel panel) {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = new Insets(5, 5, 5, 5);

        JLabel userLabel = new JLabel("User:");
        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(userLabel, constraints);

        JTextField userText = new JTextField(20);
        constraints.gridx = 1;
        constraints.gridy = 0;
        panel.add(userText, constraints);

        JLabel passwordLabel = new JLabel("Password:");
        constraints.gridx = 0;
        constraints.gridy = 1;
        panel.add(passwordLabel, constraints);

        JPasswordField passwordText = new JPasswordField(20);
        constraints.gridx = 1;
        constraints.gridy = 1;
        panel.add(passwordText, constraints);

        JButton loginButton = new JButton("Login");
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        panel.add(loginButton, constraints);

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = userText.getText();
                String password = new String(passwordText.getPassword());

                if (username.equals("admin") && password.equals("password")) {
                    JOptionPane.showMessageDialog(panel, "Login Successful!");
                } else {
                    JOptionPane.showMessageDialog(panel, "Login Failed. Please check your username and password.",
                            "Access Denied", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Add an empty label for padding and centering
        JLabel emptyLabel = new JLabel();
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 2;
        constraints.weighty = 1.0;
        panel.add(emptyLabel, constraints);
    }
}

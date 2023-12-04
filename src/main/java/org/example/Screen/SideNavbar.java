package org.example.Screen;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Main application frame
public class SideNavbar extends JFrame {
    private JPanel sideNavPanel;
    private CardLayout cardLayout;
    private JPanel contentPanel;


    public SideNavbar() {

        setTitle("Side Navbar Example");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Initialize components
        initComponents();

        // Set layout
        setLayout(new BorderLayout());
        add(sideNavPanel, BorderLayout.WEST);
        add(contentPanel, BorderLayout.CENTER);

        // Set the default screen
        showScreen("Home");
    }

    private void initComponents() {
        // Create side navigation panel
        sideNavPanel = new JPanel();
        sideNavPanel.setLayout(new BoxLayout(sideNavPanel, BoxLayout.Y_AXIS));

        // Set button size and margin
        Dimension buttonSize = new Dimension(150, 40);
        EmptyBorder buttonMargin = new EmptyBorder(10, 10, 10, 10);
        // Create buttons for each screen
        String[] screenNames = {"Home        ", "Student     ", "Defaulters", "Analysis    "};
        for (String screenName : screenNames) {
            JButton button = new JButton(screenName);
            button.setPreferredSize(buttonSize); // Set a fixed size
            button.setBorder(buttonMargin); // Add margin
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    showScreen(screenName);
                }
            });
            sideNavPanel.add(button);
        }

        // Create content panel with CardLayout
        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);

        // Add screens to content panel
        addScreen(new HomeScreen(), "Home        ");
        addScreen(new StudentScreen(), "Student     ");
        addScreen(new DefaultersScreen(), "Defaulters");
        addScreen(new Screen1(), "Analysis    ");
    }

    private void addScreen(Component panel, String name) {
        contentPanel.add(panel, name);
    }

    private void showScreen(String name) {
        cardLayout.show(contentPanel, name);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new SideNavbar().setVisible(true);
        });
    }
}


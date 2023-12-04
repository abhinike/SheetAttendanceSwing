package org.example.Screen;

import org.example.Utils.AppData;

import javax.swing.*;
import java.awt.*;

// DefaultersScreen class
class DefaultersScreen extends JPanel {
    private JPanel toppanel;
    private JPanel inputspanel;
    private JPanel tablepanel;

    public DefaultersScreen() {
        setLayout(new BorderLayout()); // Set BorderLayout for the main panel

        toppanel = new JPanel();
        // You can add components to toppanel if needed

        // Add tabs for batches
        JTabbedPane tabbedPane = new JTabbedPane();

        AppData data = new AppData();
        for (String batch : data.getAllBatches()) {
            inputspanel = new JPanel(); // Create a new inputspanel for each batch
            inputspanel.setLayout(new GridLayout(4, 2));

            inputspanel.add(new JLabel("Sheet URL"));
            inputspanel.add(new TextField(20));

            // Add the inputspanel to the current tab
            tabbedPane.addTab(batch, inputspanel);
        }

        tablepanel = new JPanel();
        // You can add components to tablepanel if needed

        // Add components to the main panel
        add(toppanel, BorderLayout.NORTH);
        add(tabbedPane, BorderLayout.CENTER);
        add(tablepanel, BorderLayout.SOUTH);
    }
}

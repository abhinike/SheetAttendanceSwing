package org.example.Screen;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

// Screen2 class
class StudentScreen extends JPanel {
    private DefaultTableModel tableModel;
    private JTable studentTable = new JTable();
    public StudentScreen() {
        add(new JLabel("Screen 2"));

        // Initialize table model with data and column names
        String[] columnNames = {"Name", "ID", "Present", "Absent", "Subject"};
        tableModel = new DefaultTableModel(getSampleData(), columnNames);

        studentTable = new JTable(tableModel);
        add(studentTable);
    }

    private Object[][] getSampleData() {
        return new Object[][]{
                {"John Doe", "101", "25", "5", "Math"},
                {"Jane Smith", "102", "20", "10", "Science"},
                {"Bob Johnson", "103", "22", "8", "History"},
                {"Alice Brown", "104", "18", "12", "English"},
                {"David Lee", "105", "23", "7", "Physics"}
        };

    }
}
package org.example.Screen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.example.Model.Student;

public class SchoolManagementSystem extends JFrame {
    private JLabel profileLabel;
    private JLabel nameLabel;
    private JTextField textField;
    private JButton submitButton;
    private JTextArea studentListArea;

    private static final String API_URL = "http://localhost:8080/attendance/defaulters?spreadsheetId=1NxDH80Y9dwKWR6HuHeeRiPB8JGD1J-yFeplxG-FeioE&classId=JSLD1";


    public SchoolManagementSystem() {
        // Set up the main frame
        setTitle("DataScience Attendance Management System");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create components
        profileLabel = new JLabel(new ImageIcon("Abhishek_Sinha.jpg")); // Replace with the actual path
        profileLabel.setPreferredSize(new Dimension(100, 100)); // Adjust size as needed

        nameLabel = new JLabel("Teacher's Name");

        textField = new JTextField(20);
        submitButton = new JButton("Find Defaulters");
        studentListArea = new JTextArea(20, 40);
        studentListArea.setEditable(false);

        // Create panels
        JPanel sidePanel = new JPanel();
        sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.Y_AXIS));
        sidePanel.setBackground(Color.LIGHT_GRAY);

        // Add components to the side panel
        sidePanel.add(profileLabel);
        sidePanel.add(nameLabel);

        // Create tabs
        JTabbedPane tabbedPane = new JTabbedPane();
        JPanel calculatePanel = new JPanel();
        calculatePanel.setLayout(new FlowLayout()); // Adjust layout as needed
        calculatePanel.add(new JLabel("Enter Sheet URL: "));
        calculatePanel.add(textField);
        calculatePanel.add(submitButton);
        calculatePanel.add(new JLabel("Student List:"));
        calculatePanel.add(new JScrollPane(studentListArea));

        // Add action listener to the submit button
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Process the input and update the student list
                // String input = textField.getText();
                // updateStudentList(input);
                fetchStudentData();
            }
        });

      

        // Add panels to the main frame
        tabbedPane.addTab("Defautlers", calculatePanel);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(sidePanel, BorderLayout.WEST);
        getContentPane().add(tabbedPane, BorderLayout.CENTER);

        // Set the frame to be visible
        setVisible(true);
    }

    // private void updateStudentList(String input) {
    //     // Perform any processing needed with the input
    //     // For simplicity, let's just add the input to the student list
    //     studentListArea.append(input + "\n");
    //     // Clear the text field after submission
    //     textField.setText("");
    // }

    private void fetchStudentData() {
        // Make API request and update student list
        try {
            URL url = new URL(API_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String inputLine;

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }

                in.close();

                // Process the JSON response
                updateStudentList(response.toString());
            } else {
                // Handle error response
                JOptionPane.showMessageDialog(this, "Failed to fetch data from the API. HTTP Error Code: " + responseCode,
                        "API Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to connect to the API.", "Connection Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateStudentList(String jsonResponse) {
        // Parse the JSON response and update the student list
        try {
            List<Student> students = parseJsonResponse(jsonResponse);

            // Clear previous data
            studentListArea.setText("");

            // Update student list
            for (Student student : students) {
                studentListArea.append("Name: " + student.getName() + ", SAP: " + student.getSap() + "\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to parse JSON response.", "Parsing Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private List<Student> parseJsonResponse(String jsonResponse) {
        // Use Gson library for JSON parsing
        System.out.println(jsonResponse);
        Gson gson = new Gson();
        return gson.fromJson(jsonResponse, new TypeToken<List<Student>>() {}.getType());
    }


    // Define a simple Student class to represent the data


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new SchoolManagementSystem();
            }
        });
    }
}

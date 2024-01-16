// Import necessary libraries for GUI components, layout, and file operations
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

// Define the TextEditor class that implements ActionListener interface
public class TextEditor implements ActionListener {

    // Declare class-level variables
    JFrame frame;              // Main application window
    JMenuBar menuBar;           // Menu bar for File and Edit menus
    JMenu file, edit;           // File and Edit menus
    JTextArea textArea;         // Text area for editing text
    JMenuItem newFile, openFile, saveFile; // File menu items
    JMenuItem cut, copy, paste, selectAll, close; // Edit menu items

    // Constructor for initializing the TextEditor object
    TextEditor() {
        // Create the main frame for the text editor
        frame = new JFrame();
        menuBar = new JMenuBar();
        textArea = new JTextArea();

        // Create File and Edit menus
        file = new JMenu("File");
        edit = new JMenu("Edit");

        // Create menu items for File menu
        newFile = new JMenuItem("New Window");
        openFile = new JMenuItem("Open File");
        saveFile = new JMenuItem("Save File");

        // Add action listeners to menu items
        newFile.addActionListener(this);
        openFile.addActionListener(this);
        saveFile.addActionListener(this);

        // Add menu items to File menu
        file.add(newFile);
        file.add(openFile);
        file.add(saveFile);

        // Create menu items for Edit menu
        cut = new JMenuItem("Cut");
        copy = new JMenuItem("Copy");
        paste = new JMenuItem("Paste");
        selectAll = new JMenuItem("Select All");
        close = new JMenuItem("Close");

        // Add action listeners to menu items
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectAll.addActionListener(this);
        close.addActionListener(this);

        // Add menu items to Edit menu
        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectAll);
        edit.add(close);

        // Add menus to the menu bar
        menuBar.add(file);
        menuBar.add(edit);

        // Set up the panel with a text area and a scroll pane
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(5, 5, 5, 5));
        panel.setLayout(new BorderLayout(0, 0));
        panel.add(textArea, BorderLayout.CENTER);

        JScrollPane scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        panel.add(scrollPane);

        // Add the panel to the main frame
        frame.add(panel);

        // Set frame properties
        frame.setBounds(0, 0, 400, 400);
        frame.setVisible(true);
        frame.setTitle("Text Editor");
        frame.setLayout(null);
    }

    // Override the actionPerformed method from the ActionListener interface
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        // Handle actions based on the source of the event
        if (actionEvent.getSource() == cut) {
            // Perform cut operation on the text area
            textArea.cut();
        }
        if (actionEvent.getSource() == copy) {
            // Perform copy operation on the text area
            textArea.copy();
        }
        if (actionEvent.getSource() == paste) {
            // Perform paste operation on the text area
            textArea.paste();
        }
        if (actionEvent.getSource() == selectAll) {
            // Select all text in the text area
            textArea.selectAll();
        }
        if (actionEvent.getSource() == close) {
            // Close the application
            System.exit(0);
        }

        if (actionEvent.getSource() == openFile) {
            // Open a file dialog to choose a file for opening
            JFileChooser fileChooser = new JFileChooser("C:");
            int chooseOption = fileChooser.showOpenDialog(null);
            if (chooseOption == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();

                // Read the content of the selected file and set it in the text area
                String filePath = file.getPath();
                try {
                    FileReader fileReader = new FileReader(filePath);
                    BufferedReader bufferedReader = new BufferedReader(fileReader);
                    String intermediate, output = "";

                    // Read each line of the file and append to the output string
                    while ((intermediate = bufferedReader.readLine()) != null) {
                        output += intermediate + "\n";
                    }

                    // Set the text area content with the file content
                    textArea.setText(output);
                } catch (IOException ioException) {
                    // Handle IO exception
                    ioException.printStackTrace();
                }
            }
        }

        if (actionEvent.getSource() == saveFile) {
            // Open a file dialog to choose a location for saving the file
            JFileChooser fileChooser = new JFileChooser("C:");
            int chooseOption = fileChooser.showSaveDialog(null);

            if (chooseOption == JFileChooser.APPROVE_OPTION) {
                // Create a new file with ".txt" extension and write the text area content into it
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath() + ".txt");
                try {
                    FileWriter fileWriter = new FileWriter(file);
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

                    // Write the text area content to the file
                    textArea.write(bufferedWriter);
                    bufferedWriter.close();
                } catch (IOException ioException) {
                    // Handle IO exception
                    ioException.printStackTrace();
                }
            }
        }

        if (actionEvent.getSource() == newFile) {
            // Create a new instance of TextEditor to open a new window
            TextEditor newTextEditor = new TextEditor();
        }
    }

    // Main method to launch the text editor application
    public static void main(String[] args) {
        // Create an instance of TextEditor to start the application
        TextEditor textEditor = new TextEditor();
    }
}

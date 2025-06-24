//This is a lazy fun time project
// The code is free to use and modify ♥️
// Follow the readme for instructions or just press the [Help] button

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CharacterShapeGenerator extends JFrame {
    private JTextArea outputArea;
    private JTextField commandField;
    private JButton generateButton, clearButton, helpButton;

    public CharacterShapeGenerator() {
        setTitle("Character Shape Generator");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create components
        outputArea = new JTextArea();
        outputArea.setEditable(false);
        outputArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(outputArea);

        commandField = new JTextField();
        commandField.setFont(new Font("Monospaced", Font.PLAIN, 14));

        generateButton = new JButton("Generate");
        clearButton = new JButton("Clear");
        helpButton = new JButton("Help");  // NEW: Help button

        // Create panel for controls
        JPanel controlPanel = new JPanel(new BorderLayout());
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(generateButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(helpButton);

        controlPanel.add(new JLabel("Enter command:"), BorderLayout.NORTH);
        controlPanel.add(commandField, BorderLayout.CENTER);
        controlPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Add components to frame
        add(scrollPane, BorderLayout.CENTER);
        add(controlPanel, BorderLayout.SOUTH);

        // Event Listeners
        generateButton.addActionListener(e -> generateShape());
        clearButton.addActionListener(e -> outputArea.setText(""));
        commandField.addActionListener(e -> generateShape());

        // NEW: Help button action
        helpButton.addActionListener(e -> showHelpDialog());
    }

    // NEW: Help Dialog with Simple Instructions
    private void showHelpDialog() {
        String helpMessage =
                "📌 HOW TO USE:\n" +
                        "1. Type a command like this: [CHARACTER] [SHAPE] [SIZE]\n" +
                        "   Example: * triangle 5\n\n" +
                        "🛠️ AVAILABLE SHAPES:\n" +
                        "- square (Example: # square 4)\n" +
                        "- triangle (Example: + triangle 6)\n" +
                        "- diamond (Example: @ diamond 5)\n" +
                        "- pyramid (Example: X pyramid 3)\n" +
                        "- heart (Example: ! heart 4)\n\n" +
                        "💡 TIP: If you don't specify size, it will be 5 by default.";

        JOptionPane.showMessageDialog(
                this,
                helpMessage,
                "Help - How to Use",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    private void generateShape() {
        String command = commandField.getText().trim();
        if (command.isEmpty()) {
            return;
        }

        try {
            String shape = processCommand(command);
            outputArea.append(shape + "\n\n");
            commandField.setText("");
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(),
                    "Invalid Command", JOptionPane.ERROR_MESSAGE);
        }
    }

    private String processCommand(String command) {
        String[] parts = command.split("\\s+");
        if (parts.length < 2) {
            throw new IllegalArgumentException("Invalid command format");
        }

        char fillChar = parts[0].charAt(0);
        String shapeType = parts[1].toLowerCase();
        int size = 5; // default size
        if (parts.length > 2) {
            try {
                size = Integer.parseInt(parts[2]);
                if (size < 1 || size > 20) {
                    throw new IllegalArgumentException("Size must be between 1 and 20");
                }
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Invalid size value");
            }
        }

        switch (shapeType) {
            case "square":
                return generateSquare(fillChar, size);
            case "triangle":
                return generateTriangle(fillChar, size);
            case "diamond":
                return generateDiamond(fillChar, size);
            case "pyramid":
                return generatePyramid(fillChar, size);
            case "heart":
                return generateHeart(fillChar, size);
            default:
                throw new IllegalArgumentException(
                        "Unknown shape type. Use square, triangle, diamond, pyramid, or heart");
        }
    }

    private String generateSquare(char fillChar, int size) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                sb.append(fillChar).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    private String generateTriangle(char fillChar, int size) {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= size; i++) {
            for (int j = 0; j < i; j++) {
                sb.append(fillChar).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    private String generateDiamond(char fillChar, int size) {
        StringBuilder sb = new StringBuilder();
        // Upper half
        for (int i = 1; i <= size; i++) {
            for (int j = size - i; j > 0; j--) {
                sb.append("  ");
            }
            for (int j = 0; j < 2 * i - 1; j++) {
                sb.append(fillChar).append(" ");
            }
            sb.append("\n");
        }
        // Lower half
        for (int i = size - 1; i >= 1; i--) {
            for (int j = size - i; j > 0; j--) {
                sb.append("  ");
            }
            for (int j = 0; j < 2 * i - 1; j++) {
                sb.append(fillChar).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    private String generatePyramid(char fillChar, int size) {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= size; i++) {
            for (int j = size - i; j > 0; j--) {
                sb.append("  ");
            }
            for (int j = 0; j < 2 * i - 1; j++) {
                sb.append(fillChar).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    private String generateHeart(char fillChar, int size) {
        StringBuilder sb = new StringBuilder();
        for (int y = size / 2; y <= size; y++) {
            for (int x = 0; x <= size * 2; x++) {
                // Heart equation: (x² + y² - 1)³ - x² * y³ ≤ 0
                double dx = (x - size) / (double) size * 2;
                double dy = (y - size / 2.0) / (double) size * 2;
                if (Math.pow(dx * dx + dy * dy - 1, 3) - dx * dx * dy * dy * dy <= 0) {
                    sb.append(fillChar);
                } else {
                    sb.append(" ");
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CharacterShapeGenerator().setVisible(true);
            }
        });
    }
}
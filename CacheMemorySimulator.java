import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*; // keep this for Map, ArrayList, HashMap, etc.

public class CacheMemorySimulator extends JFrame implements ActionListener {

    // GUI Components
    private JTextField cacheSizeField, blockSizeField, addressInputField;
    private JTextArea outputArea;
    private JButton simulateButton, clearButton;

    public CacheMemorySimulator() {
        setTitle("Cache Memory Design and Optimization");
        setSize(700, 600);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(240, 248, 255));

        JLabel title = new JLabel("Cache Memory Simulator", JLabel.CENTER);
        title.setFont(new Font("Times New Roman", Font.BOLD, 24));
        title.setBounds(150, 20, 400, 30);
        add(title);

        JLabel cacheLabel = new JLabel("Enter Cache Size (lines):");
        cacheLabel.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        cacheLabel.setBounds(80, 80, 250, 25);
        add(cacheLabel);

        cacheSizeField = new JTextField();
        cacheSizeField.setBounds(320, 80, 150, 25);
        add(cacheSizeField);

        JLabel blockLabel = new JLabel("Enter Block Size:");
        blockLabel.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        blockLabel.setBounds(80, 120, 250, 25);
        add(blockLabel);

        blockSizeField = new JTextField();
        blockSizeField.setBounds(320, 120, 150, 25);
        add(blockSizeField);

        JLabel addressLabel = new JLabel("Enter Memory Addresses (comma-separated):");
        addressLabel.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        addressLabel.setBounds(80, 160, 400, 25);
        add(addressLabel);

        addressInputField = new JTextField();
        addressInputField.setBounds(80, 190, 500, 25);
        add(addressInputField);

        simulateButton = new JButton("Simulate");
        simulateButton.setBounds(150, 240, 150, 30);
        simulateButton.setFont(new Font("Times New Roman", Font.BOLD, 14));
        simulateButton.addActionListener(this);
        add(simulateButton);

        clearButton = new JButton("Clear");
        clearButton.setBounds(350, 240, 150, 30);
        clearButton.setFont(new Font("Times New Roman", Font.BOLD, 14));
        clearButton.addActionListener(this);
        add(clearButton);

        outputArea = new JTextArea();
        outputArea.setEditable(false);
        outputArea.setFont(new Font("Courier New", Font.PLAIN, 14));
        outputArea.setMargin(new Insets(10, 10, 10, 10));

        JScrollPane scrollPane = new JScrollPane(outputArea);
        scrollPane.setBounds(80, 300, 520, 220);
        add(scrollPane);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == simulateButton) {
            simulateCache();
        } else if (e.getSource() == clearButton) {
            cacheSizeField.setText("");
            blockSizeField.setText("");
            addressInputField.setText("");
            outputArea.setText("");
        }
    }

    private void simulateCache() {
        try {
            // Input parsing
            int cacheSize = Integer.parseInt(cacheSizeField.getText().trim());
            int blockSize = Integer.parseInt(blockSizeField.getText().trim());
            String[] addressStrings = addressInputField.getText().split(",");

            java.util.List<Integer> addresses = new ArrayList<>(); // fully qualified to avoid ambiguity
            for (String s : addressStrings) {
                if (!s.trim().isEmpty()) {
                    addresses.add(Integer.parseInt(s.trim()));
                }
            }

            if (addresses.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter at least one memory address.", "Input Error",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (cacheSize <= 0 || blockSize <= 0) {
                JOptionPane.showMessageDialog(this, "Cache size and block size must be positive integers.",
                        "Input Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Simulation logic
            Map<Integer, Integer> cache = new HashMap<>();
            int hits = 0, misses = 0;

            StringBuilder sb = new StringBuilder();
            sb.append("CACHE MEMORY SIMULATION (DIRECT MAPPING)\n");
            sb.append("----------------------------------------------------\n");

            for (int i = 0; i < addresses.size(); i++) {
                int address = addresses.get(i);
                int blockNumber = address / blockSize;
                int cacheLine = Math.floorMod(blockNumber, cacheSize); // safe modulo for negatives (addresses shouldn't
                                                                       // be negative)

                sb.append(String.format("Access %2d: Address %-4d -> Block %-3d ", (i + 1), address, blockNumber));

                if (cache.containsKey(cacheLine) && cache.get(cacheLine) == blockNumber) {
                    hits++;
                    sb.append("-> HIT\n");
                } else {
                    misses++;
                    sb.append("-> MISS\n");
                    cache.put(cacheLine, blockNumber);
                }
            }

            double hitRatio = (double) hits / (hits + misses) * 100.0;
            sb.append("----------------------------------------------------\n");
            sb.append(String.format("Total Accesses: %d\n", (hits + misses)));
            sb.append(String.format("Cache Hits: %d\n", hits));
            sb.append(String.format("Cache Misses: %d\n", misses));
            sb.append(String.format("Hit Ratio: %.2f%%\n", hitRatio));

            outputArea.setText(sb.toString());

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid input! Please enter numeric values only.", "Error",
                    JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "An unexpected error occurred: " + ex.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CacheMemorySimulator app = new CacheMemorySimulator();
            app.setVisible(true);
        });
    }
}

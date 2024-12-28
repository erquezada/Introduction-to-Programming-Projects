import java.util.Scanner;
import java.io.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class FileReaderGUI extends JFrame {

    private String filename;
    private JTable eventTable;
    private JButton openButton;

    public FileReaderGUI() {
        // Set up the JFrame
        setTitle("Event Reader");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        // Create a button to open the file chooser
        openButton = new JButton("Open CSV File");
        openButton.addActionListener(_ -> openFile());

        // Create table to display events
        eventTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(eventTable);

        // Add components to frame
        add(openButton);
        add(scrollPane);
    }

    // Open the file using JFileChooser
    private void openFile() {
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showOpenDialog(this);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            this.filename = file.getAbsolutePath();
            try {
                System.out.println("Selected file: " + filename);  // Log the selected file path
                Event[] events = readEvents();
                displayEvents(events);
            } catch (FileNotFoundException e) {
                JOptionPane.showMessageDialog(this, "File not found: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            } catch (IOException e) {
                e.printStackTrace();  // Print the error stack trace for debugging
                JOptionPane.showMessageDialog(this, "Error reading the file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Read events from the selected file
    public Event[] readEvents() throws FileNotFoundException, IOException {
        Event[] events = new Event[getNumEvents()];
        File file = new File(filename);
        try (Scanner fileScnr = new Scanner(file)) {
            fileScnr.nextLine(); // Skip header line
            int arrayIndex = 0;
            while (fileScnr.hasNextLine()) {
                String line = fileScnr.nextLine();
                String[] lineValues = line.split(",");
                if (lineValues.length >= 6) {
                    Concession eventConcession = new Concession();
                    eventConcession.applyPriceFactor(Double.parseDouble(lineValues[5]));
                    Event currentEvent = new Event(lineValues[0], lineValues[1], lineValues[2],
                            Double.parseDouble(lineValues[3]), Integer.parseInt(lineValues[4]), eventConcession);
                    events[arrayIndex] = currentEvent;
                    arrayIndex++;
                }
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            throw new IOException("Error parsing numbers from the file.");
        }
        return events;
    }

    // Get number of events
    public int getNumEvents() throws FileNotFoundException {
        File file = new File(filename);
        if (!file.exists()) {
            throw new FileNotFoundException("File does not exist.");
        }
        try (Scanner fileScnr = new Scanner(file)) {
            if (!fileScnr.hasNextLine()) {
                throw new IOException("File is empty.");
            }
            fileScnr.nextLine();  // Skip header line
            int count = 0;
            while (fileScnr.hasNextLine()) {
                fileScnr.nextLine();
                count++;
            }
            return count;
        }
    }

    // Display events in the JTable
    private void displayEvents(Event[] events) {
        String[] columnNames = {"Event Name", "Location", "Date", "Price", "Quantity", "Concession"};
        Object[][] data = new Object[events.length][6];

        for (int i = 0; i < events.length; i++) {
            data[i][0] = events[i].getName();
            data[i][1] = events[i].getLocation();
            data[i][2] = events[i].getDate();
            data[i][3] = events[i].getPrice();
            data[i][4] = events[i].getQuantity();
            data[i][5] = events[i].getConcession().getPriceFactor();
        }

        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        eventTable.setModel(model);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            FileReaderGUI frame = new FileReaderGUI();
            frame.setVisible(true);
        });
    }
}

class Event {
    private String name;
    private String location;
    private String date;
    private double price;
    private int quantity;
    private Concession concession;

    public Event(String name, String location, String date, double price, int quantity, Concession concession) {
        this.name = name;
        this.location = location;
        this.date = date;
        this.price = price;
        this.quantity = quantity;
        this.concession = concession;
    }

    public String getName() { return name; }
    public String getLocation() { return location; }
    public String getDate() { return date; }
    public double getPrice() { return price; }
    public int getQuantity() { return quantity; }
    public Concession getConcession() { return concession; }
}

class Concession {
    private double priceFactor;

    public void applyPriceFactor(double priceFactor) {
        this.priceFactor = priceFactor;
    }

    public double getPriceFactor() {
        return priceFactor;
    }
}

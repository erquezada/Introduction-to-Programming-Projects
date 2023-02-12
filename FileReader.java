import java.util.Scanner;
import java.io.*;

public class FileReader {
  public String filename;

  public FileReader() {}

  public FileReader(String filename) {
    this.filename = filename;
  }
  public void setFilename(String filename) {
    this.filename = filename;
  }
  public String getFilename() {
    return this.filename;
  }
  public Event[] readEvents() throws FileNotFoundException {
    Event[] events = new Event[getNumEvents()];
    // Initialize Scanner to read csv file.
    File file = new File(filename);
    try (Scanner fileScnr = new Scanner(file)) {
      // Skip the first line of the file (because it just contains column names).
      fileScnr.nextLine();
      //Keep track of next index in event array
      int arrayIndex = 0;
      // For every line in the CSV file...
      while (fileScnr.hasNextLine()) {

        // Read the line of comma separated values
        String line = fileScnr.nextLine();

        // "Split" the string by a comma, which gives an array of values.
        String[] lineValues = line.split(",");

        // Print all the values in the array.

        // Now, you can create a Concession and Event object from this line using these values!

        Concession eventConcession = new Concession();
        eventConcession.applyPriceFactor(Double.parseDouble(lineValues[5]));
        Event currentEvent = new Event(lineValues[0], lineValues[1], lineValues[2], Double.parseDouble(lineValues[3]), Integer.parseInt(lineValues[4]), eventConcession);
        events[arrayIndex] = currentEvent;
        arrayIndex++;
      }
    } catch (NumberFormatException e) {
      e.printStackTrace();
    }
    return events;
  }
  public int getNumEvents() throws FileNotFoundException {
    File file = new File(filename);
    try (Scanner fileScnr = new Scanner(file)) {
      fileScnr.nextLine();
      int count = 0;
      while (fileScnr.hasNextLine()) {
        fileScnr.nextLine();
        count++;
      }
      return count;
    }
  }
}
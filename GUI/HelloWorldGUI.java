import javax.swing.*;

public class HelloWorldGUI {
    public static void main(String[] args) {
        // Create the frame
        JFrame frame = new JFrame("Hello World GUI");
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        // Create a label to display the message
        JLabel label = new JLabel("Hello World", SwingConstants.CENTER);
        label.setBounds(50, 50, 200, 50);  // x, y, width, height
        frame.add(label);

        // Make the frame visible
        frame.setVisible(true);
    }
}

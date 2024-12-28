import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GroceryStorePriceGUI extends JFrame {
    private JTextArea cartArea;
    private JButton addButton, viewButton, clearButton, checkoutButton;

    public GroceryStorePriceGUI() {
        setTitle("Grocery Store");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        cartArea = new JTextArea();
        cartArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(cartArea);

        addButton = new JButton("Add Item");
        viewButton = new JButton("View Cart");
        clearButton = new JButton("Clear Cart");
        checkoutButton = new JButton("Checkout");

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addItem();
            }
        });
        viewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                viewCart();
            }
        });
        clearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clearCart();
            }
        });
        checkoutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                checkout();
            }
        });

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.add(addButton);
        panel.add(viewButton);
        panel.add(clearButton);
        panel.add(checkoutButton);

        getContentPane().add(BorderLayout.CENTER, scrollPane);
        getContentPane().add(BorderLayout.SOUTH, panel);
    }

    private void addItem() {
        // Simulating adding an item to the cart
        cartArea.append("Added 1 Apple\n");
    }

    private void viewCart() {
        // Displaying current items in cart
        JOptionPane.showMessageDialog(this, "Viewing Cart");
    }

    private void clearCart() {
        cartArea.setText("");
    }

    private void checkout() {
        JOptionPane.showMessageDialog(this, "Proceeding to Checkout");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new GroceryStoreGUI().setVisible(true);
            }
        });
    }
}

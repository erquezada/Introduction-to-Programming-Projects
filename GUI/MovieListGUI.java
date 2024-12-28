import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MovieListGUI {

    private static String userName;
    private static String[] movies = new String[5];

    public static void main(String[] args) {
        // Get the user's name and initialize the movie list
        userName = JOptionPane.showInputDialog("What is your name?");
        initializePlaylist();

        // Create the main window (JFrame)
        JFrame frame = new JFrame("Movie Playlist");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);
        frame.setLayout(new BorderLayout());

        // Create the list to display movies
        JList<String> movieList = new JList<>(movies);
        JScrollPane scrollPane = new JScrollPane(movieList);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Create buttons for the menu options
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 2, 10, 10));
        buttonPanel.setBackground(Color.LIGHT_GRAY);

        JButton viewButton = createButton("View Movies", e -> updateMovieList(movieList));
        JButton addButton = createButton("Add Movie", e -> addMovieAction(movieList));
        JButton removeButton = createButton("Remove Movie", e -> removeMovieAction(movieList));
        JButton updateButton = createButton("Update Movie", e -> updateMovieAction(movieList));
        JButton watchButton = createButton("Watch Movie", e -> watchMovieAction());
        JButton exitButton = createButton("Exit", e -> exitAction(frame));

        // Add buttons to the button panel
        buttonPanel.add(viewButton);
        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(watchButton);
        buttonPanel.add(exitButton);

        // Add the button panel to the frame
        frame.add(buttonPanel, BorderLayout.SOUTH);

        // Make the window visible
        frame.setVisible(true);
    }

    // Create a button with text and an action listener
    private static JButton createButton(String text, ActionListener action) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.PLAIN, 14));
        button.addActionListener(action);
        return button;
    }

    // Initialize the playlist
    public static void initializePlaylist() {
        for (int i = 0; i < movies.length; i++) {
            movies[i] = JOptionPane.showInputDialog("Enter movie " + (i + 1) + ":");
        }
    }

    // Update the movie list in the GUI
    public static void updateMovieList(JList<String> movieList) {
        movieList.setListData(movies);
    }

    // Check if the movie list is full
    private static boolean isPlaylistFull() {
        for (String movie : movies) {
            if (movie == null || movie.trim().isEmpty()) {
                return false;
            }
        }
        return true;
    }

    // Add a movie to the playlist
    public static void addMovie(String newMovie) {
        for (int i = 0; i < movies.length; i++) {
            if (movies[i] == null || movies[i].trim().isEmpty()) {
                movies[i] = newMovie;
                JOptionPane.showMessageDialog(null, "Movie added to your playlist.");
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Your playlist is full. You can't add more movies.");
    }

    // Validate if the input movie index is valid
    private static boolean isValidMovieIndex(int index) {
        return index >= 0 && index < movies.length;
    }

    // Add movie action for the Add Movie button
    private static void addMovieAction(JList<String> movieList) {
        if (confirmAction("add a movie")) {
            String newMovie = JOptionPane.showInputDialog("Enter the movie you want to add:");
            if (newMovie != null && !newMovie.trim().isEmpty()) {
                addMovie(newMovie);
                updateMovieList(movieList);
            } else {
                JOptionPane.showMessageDialog(null, "Please enter a valid movie name.");
            }
        }
    }

    // Remove movie action for the Remove Movie button
    private static void removeMovieAction(JList<String> movieList) {
        if (confirmAction("remove a movie")) {
            String movieIndexStr = JOptionPane.showInputDialog("Enter the movie number you want to remove:");
            try {
                int movieIndex = Integer.parseInt(movieIndexStr) - 1;
                if (isValidMovieIndex(movieIndex)) {
                    movies[movieIndex] = null;
                    updateMovieList(movieList);
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid movie number.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Please enter a valid number.");
            }
        }
    }

    // Update movie action for the Update Movie button
    private static void updateMovieAction(JList<String> movieList) {
        if (confirmAction("update a movie")) {
            String movieIndexStr = JOptionPane.showInputDialog("Enter the movie number you want to update:");
            try {
                int movieIndex = Integer.parseInt(movieIndexStr) - 1;
                if (isValidMovieIndex(movieIndex)) {
                    String newMovieName = JOptionPane.showInputDialog("Enter the new movie name:");
                    if (newMovieName != null && !newMovieName.trim().isEmpty()) {
                        movies[movieIndex] = newMovieName;
                        updateMovieList(movieList);
                    } else {
                        JOptionPane.showMessageDialog(null, "Please enter a valid movie name.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid movie number.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Please enter a valid number.");
            }
        }
    }

    // Watch movie action for the Watch Movie button
    private static void watchMovieAction() {
        String pickMovie = JOptionPane.showInputDialog("What movie would you like to watch?");
        boolean movieFound = false;
        for (String movie : movies) {
            if (movie != null && movie.trim().equalsIgnoreCase(pickMovie.trim())) {
                JOptionPane.showMessageDialog(null, "Enjoy your movie, " + userName + ".");
                movieFound = true;
                break;
            }
        }
        if (!movieFound) {
            JOptionPane.showMessageDialog(null, "Sorry, " + userName + ", this movie is unavailable.");
        }
    }

    // Exit action for the Exit button
    private static void exitAction(JFrame frame) {
        if (confirmExit()) {
            JOptionPane.showMessageDialog(frame, "Exiting the program. Goodbye, " + userName + "!");
            System.exit(0);
        }
    }

    // Confirm exit action
    private static boolean confirmExit() {
        int response = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?", "Confirm Exit", JOptionPane.YES_NO_OPTION);
        return response == JOptionPane.YES_OPTION;
    }

    // Confirm the movie action before adding/removing/updating
    private static boolean confirmAction(String action) {
        int response = JOptionPane.showConfirmDialog(null, "Are you sure you want to " + action + "?", "Confirm Action", JOptionPane.YES_NO_OPTION);
        return response == JOptionPane.YES_OPTION;
    }
}

import java.util.Scanner;

public class MovieList {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean enjoy = false;

        System.out.print("What is your name?: ");
        String userName = scanner.nextLine();
        System.out.println(userName + ", welcome to your favorite movie playlist.");

        String[] movies = new String[5];
        System.out.println("I see that you have no movies in your favorite movie playlist.");
        System.out.println("Let's add some movies to your favorite movie playlist. Choose five movies to add:");

        for (int i = 0; i < movies.length; i++) {
            String movieName = scanner.nextLine();
            movies[i] = movieName;
        }

        System.out.println("There are " + movies.length + " movies in your favorite movie playlist:");
        for (String movie : movies) {
            System.out.println(movie);
        }

        System.out.print("What movie would you like to watch? ");
        String pickMovie = scanner.nextLine();
        
        for (int i = 0; i < movies.length; i++) {
            if (movies[i].equals(pickMovie)) {
                System.out.println("This movie is listed as #" + (i + 1) + " in your list.");
                enjoy = true;
                break; // Exit loop since movie is found
            }
        }

        if (enjoy) {
            System.out.println("Enjoy your movie, " + userName + ".");
        } else {
            System.out.println("Sorry, " + userName + ", this movie is unavailable.");
        }

        scanner.close();
    }
}
